package wtf.agent.inject.mixin.mixins;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import wtf.agent.client.Agent;
import wtf.agent.inject.mixin.api.Mixin;
import wtf.agent.inject.mixin.api.annotation.Inject;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class EntityPlayerSPMixin extends Mixin {
    public EntityPlayerSPMixin() {
        super("net/minecraft/client/entity/EntityPlayerSP");
    }

    // func_70071_h_,onUpdate,2,Called to update the entity's position/logic.
    @Inject(method = "func_70071_h_")
    public void onUpdate(MethodNode methodNode) {
        InsnList insn = new InsnList();
        //insn.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Agent.class), "test", "()V", false));
        methodNode.instructions.insert(insn);
    }
}
