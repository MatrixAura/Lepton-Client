package wtf.agent.inject.asm.transformers.render;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import wtf.agent.client.Agent;
import wtf.agent.client.listener.bus.EventBus;
import wtf.agent.client.listener.events.input.EventAttackReach;
import wtf.agent.inject.asm.api.Transformers;
import wtf.agent.inject.asm.api.annotation.Inject;
import wtf.agent.inject.asm.wrapper.Wrapper;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.POP;

public class EntityRendererTransformer extends Wrapper {
    public EntityRendererTransformer() {
        super("net/minecraft/client/renderer/EntityRenderer");
    }

    @Inject(method = "func_78473_a", descriptor = "(F)V")
    public void getMouseOver(MethodNode methodNode) {

        InsnList list = new InsnList();

        list.add(new TypeInsnNode(NEW, Type.getInternalName(EventAttackReach.class)));
        list.add(new InsnNode(DUP));
        list.add(new LdcInsnNode(3.0));
        list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventAttackReach.class), "<init>", "(D)V", false));
        list.add(new VarInsnNode(ASTORE, 16));

        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Agent.class), "getBus", "()Lwtf/agent/client/listener/bus/EventBus;", false));
        list.add(new VarInsnNode(ALOAD, 16));
        list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
        list.add(new InsnNode(POP));

        methodNode.instructions.insert(list);

        // above is checked

        LdcInsnNode ldc = null;
        for (int i = 0; i < methodNode.instructions.size(); ++i) {
            AbstractInsnNode x = methodNode.instructions.get(i);

            if (x instanceof LdcInsnNode) {
                LdcInsnNode t = (LdcInsnNode) x;

                if (t.cst instanceof Double && ((Double) t.cst) == 3.0) {
                    ldc = t;
                }
            }
        }

        if (ldc == null) return;

//        InsnList improvised = new InsnList();
//        improvised.add(new VarInsnNode(ALOAD, 16));
//        improvised.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventAttackReach.class), "getReach", "()D", false));

    }
}
