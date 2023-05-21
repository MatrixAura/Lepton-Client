package wtf.agent.inject.asm.api;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import wtf.agent.inject.asm.api.annotation.Inject;
import wtf.agent.inject.asm.transformers.MinecraftTransformer;
import wtf.agent.inject.asm.transformers.entity.EntityPlayerSPTransformer;
import wtf.agent.inject.asm.transformers.gui.GuiIngameTransformer;
import wtf.agent.inject.asm.transformers.render.EntityRendererTransformer;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

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

    public static final Logger logger = LogManager.getLogger("Mixins");

    // LOL
    private static final List<Wrapper> transformers = new ArrayList<>();

    public static void init(Instrumentation is) throws IOException {
        if (transformers.isEmpty()) {
            logger.warn("No mixins were added");
            return;
        }

        logger.info("{} mixins to load", transformers.size());

        for (Wrapper mixin : transformers) {
            if (mixin.getClazz() == null) {
                logger.warn("Class for {} ({}) is null", mixin.getObfName(), mixin.getName());
                continue;
            }

            byte[] bytes = getClassBytes(mixin.getClazz());
            ClassNode node = node(bytes);
            if (node == null) {
                logger.error("Class node could not be created from {}", mixin.getClazz());
                continue;
            }

            for (Method method : mixin.getClass().getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Inject.class)) continue;

                if (method.getParameterCount() != 1 || !MethodNode.class.isAssignableFrom(method.getParameterTypes()[0])) continue;

                Inject inject = method.getAnnotation(Inject.class);

                String methodToModify = inject.method();
                String desc = inject.descriptor();

                String obfName = Mappings.seargeToNotchMethod(methodToModify);
                if (obfName == null || obfName.isEmpty()) {
                    logger.error("Could not find {} in class {}", methodToModify, mixin.getName());
                    continue;
                }

                // huh???
                for (MethodNode mNode : (List<MethodNode>) node.methods) {
                    if (mNode.name.equals(obfName) && mNode.desc.equals(desc)) {
                        try {
                            method.invoke(mixin, mNode);
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
                logger.error("Could not rewrite class bytes for {} ({})", mixin.getClazz(), mixin.getName());
                logger.error(e.getMessage() + " -> " + e.getStackTrace()[0]);
            }

            if (bytes.length != 0) {
                ClassDefinition classDef = new ClassDefinition(mixin.getClazz(), bytes);
                try {
                    logger.info("Redefined class {} ({}) ({} bytes)", mixin.getObfName(), mixin.getName(), bytes.length);
                    is.redefineClasses(classDef);
                } catch (ClassNotFoundException | UnmodifiableClassException e) {
                    logger.error("Failed to modify {} ({})", mixin.getObfName(), mixin.getName());
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

    private static byte[] getClassBytes(Class<?> c) throws IOException {
        String className = c.getName();
        String classAsPath = className.replace('.', '/') + ".class";
        InputStream stream = c.getClassLoader().getResourceAsStream(classAsPath);
        return stream == null ? null : IOUtils.toByteArray(stream);
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
        transformers.add(new MinecraftTransformer());
    }
}
