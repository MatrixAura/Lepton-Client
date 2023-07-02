package cn.matrixaura.lepton.inject.asm.transformers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.asm.api.Inject;
import cn.matrixaura.lepton.inject.asm.api.Transformer;
import cn.matrixaura.lepton.inject.asm.api.Transformers;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.listener.events.render.EventRender2D;
import cn.matrixaura.lepton.util.inject.Mappings;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class GuiIngameTransformer extends Transformer {
    private static final String GL_STATE_MANAGER_NAME = "net/minecraft/client/renderer/GlStateManager";

    public GuiIngameTransformer() {
        super("net/minecraft/client/gui/GuiIngame");
    }

    @Inject(method = "func_175180_a", descriptor = "(F)V")
    public void renderGameOverlay(MethodNode methodNode) {
        InsnList list = new InsnList();

        AbstractInsnNode point = null;
        for (int i = 0; i < methodNode.instructions.size(); ++i) {
            AbstractInsnNode aisn = methodNode.instructions.get(i);
            if (aisn instanceof MethodInsnNode) {
                // we're looking for GlStateManager.color(...)
                // we dont break because we want to do it after .color is invoked

                MethodInsnNode meth = (MethodInsnNode) aisn; // hehe

                if (meth.owner.equals(Mappings.getObfClass(GL_STATE_MANAGER_NAME))
                        // MD: bfl/c (FFFF)V net/minecraft/client/renderer/GlStateManager/func_179131_c (FFFF)V
                        && meth.name.equals(Mappings.seargeToNotchMethod("func_179131_c"))
                        && meth.desc.equals("(FFFF)V")) {

                    point = aisn;
                }
            }
        }

        if (point == null) {
            Transformers.logger.error("Failed to find last GlStateManager#color call in GuiInGame");
            return;
        }

        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Lepton.class), "getBus", "()Lcn/matrixaura/lepton/listener/bus/EventBus;", false));
        list.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender2D.class)));
        list.add(new InsnNode(DUP));

        list.add(new VarInsnNode(ILOAD, 3));
        list.add(new VarInsnNode(ILOAD, 4));
        list.add(new VarInsnNode(FLOAD, 1));

        list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(IIF)V", false));
        list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
        list.add(new InsnNode(POP));

        methodNode.instructions.insert(point, list);
    }
}
