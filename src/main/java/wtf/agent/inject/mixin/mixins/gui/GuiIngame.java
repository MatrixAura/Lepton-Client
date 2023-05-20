package wtf.agent.inject.mixin.mixins.gui;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import wtf.agent.client.Agent;
import wtf.agent.client.listener.bus.EventBus;
import wtf.agent.client.listener.events.render.EventRender2D;
import wtf.agent.inject.mixin.api.annotation.Inject;
import wtf.agent.inject.mixin.wrapper.Wrapper;

import static org.objectweb.asm.Opcodes.*;

public class GuiIngame extends Wrapper {
    public GuiIngame() {
        super("net/minecraft/client/gui/GuiIngame");
    }

    @Inject(method = "func_175180_a")
    public void renderGameOverlay(MethodNode methodNode) {
        InsnList list = new InsnList();

        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Agent.class), "getBus", "()Lwtf/agent/client/listener/bus/EventBus;", false));
        list.add(new TypeInsnNode(NEW, Type.getInternalName(EventRender2D.class)));
        list.add(new InsnNode(DUP));

        list.add(new VarInsnNode(ILOAD, 3));
        list.add(new VarInsnNode(ILOAD, 4));
        list.add(new VarInsnNode(FLOAD, 1));

        list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventRender2D.class), "<init>", "(IIF)V", false));
        list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
        list.add(new InsnNode(POP));

        methodNode.instructions.insertBefore(methodNode.instructions.getLast(), list);
    }
}
