package cn.matrixaura.lepton.inject.asm.api;

import cn.matrixaura.lepton.inject.asm.transformers.*;
import cn.matrixaura.lepton.util.inject.InjectUtils;
import cn.matrixaura.lepton.util.inject.Mappings;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.ClassWriter.COMPUTE_MAXS;

public class Transformers {

    public static final Logger logger = LogManager.getLogger("Transformers");

    public static final List<Transformer> transformers = new ArrayList<>();

    public static void transform(Instrumentation inst) throws IOException {
        if (transformers.isEmpty()) {
            logger.warn("No transformers were added");
            return;
        }

        logger.info("{} transformers to load", transformers.size());

        for (Transformer transformer : transformers) {
            if (transformer.getClazz() == null) {
                logger.warn("Class for {} ({}) is null", transformer.getObfName(), transformer.getName());
                continue;
            }

            byte[] bytes = transformer.getOldBytes();
            ClassNode node = node(bytes);
            if (node == null) {
                logger.error("Class node could not be created from {}", transformer.getClazz());
                continue;
            }

            for (Method method : transformer.getClass().getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Inject.class)) continue;

                if (method.getParameterCount() != 1 || !MethodNode.class.isAssignableFrom(method.getParameterTypes()[0]))
                    continue;

                Inject inject = method.getAnnotation(Inject.class);

                String methodToModify = inject.method();
                String desc = inject.descriptor();

                String obfName = Mappings.getObfMethod(methodToModify);
                if (obfName == null || obfName.isEmpty()) {
                    logger.error("Could not find {} in class {}", methodToModify, transformer.getName());
                    continue;
                }

                // huh???
                for (MethodNode mNode : (List<MethodNode>) node.methods) {
                    if (mNode.name.equals(obfName) && mNode.desc.equals(desc)) {
                        try {
                            method.invoke(transformer, mNode);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            logger.error("Failed to invoke method {} {}", e.getMessage(), e.getStackTrace()[0]);
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }

            try {
                bytes = rewriteClass(node);
            } catch (Exception e) {
                logger.error("Could not rewrite class bytes for {} ({})", transformer.getClazz(), transformer.getName());
                logger.error(e.getMessage() + " -> " + e.getStackTrace()[0]);
            }

            if (bytes.length != 0) {
                ClassDefinition classDef = new ClassDefinition(transformer.getClazz(), bytes);
                try {
                    logger.info("Redefined class {} ({}) ({} bytes)", transformer.getObfName(), transformer.getName(), bytes.length);
                    inst.redefineClasses(classDef);
                } catch (ClassNotFoundException | UnmodifiableClassException e) {
                    logger.error("Failed to modify {} ({})", transformer.getObfName(), transformer.getName());
                    logger.error(e.getMessage() + " -> " + e.getStackTrace()[0]);
                    e.printStackTrace();
                }
            }
        }
    }

    private static ClassNode node(byte[] bytes) {
        if (bytes != null && bytes.length != 0) {
            ClassReader reader = new ClassReader(bytes);
            ClassNode node = new ClassNode();
            reader.accept(node, 0);
            return node;
        }

        return null;
    }

    private static byte[] rewriteClass(ClassNode node) {
        ClassWriter writer = new ClassWriter(COMPUTE_MAXS | COMPUTE_FRAMES);
        node.accept(writer);
        return writer.toByteArray();
    }

    static {
        transformers.add(new EntityPlayerSPTransformer());
        transformers.add(new GuiIngameTransformer());
        transformers.add(new EntityRendererTransformer());
        transformers.add(new NetworkManagerTransFormer());
        transformers.add(new MinecraftTransformer());
    }
}
