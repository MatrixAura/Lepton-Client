package wtf.agent.inject.asm.transformers.gui;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import wtf.agent.client.Agent;
import wtf.agent.client.listener.bus.EventBus;
import wtf.agent.client.listener.events.render.EventRender2D;
import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.asm.api.annotation.Inject;
import wtf.agent.inject.asm.wrapper.Wrapper;
import wtf.agent.inject.mapping.Mappings;

import static org.objectweb.asm.Opcodes.*;

public class GuiIngameTransformer extends Wrapper {
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

                if (meth.owner.equals(Mappings.getUnobfClass(GL_STATE_MANAGER_NAME))
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

        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Agent.class), "getBus", "()Lwtf/agent/client/listener/bus/EventBus;", false));
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
