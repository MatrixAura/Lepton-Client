package cn.matrixaura.lepton.inject.asm.transformers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.asm.api.Inject;
import cn.matrixaura.lepton.inject.asm.api.Transformer;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.listener.events.player.EventUpdate;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class EntityPlayerSPTransformer extends Transformer {
    public EntityPlayerSPTransformer() {
        super("net/minecraft/client/entity/EntityPlayerSP");
    }

    // func_70071_h_,onUpdate,2,Called to update the entity's position/logic.
    @Inject(method = "func_70071_h_")
    public void onUpdate(MethodNode methodNode) {
        InsnList list = new InsnList();

        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Lepton.class), "getEventBus", "()Lcn/matrixaura/lepton/listener/bus/EventBus;", false));
        list.add(new TypeInsnNode(NEW, Type.getInternalName(EventUpdate.class)));
        list.add(new InsnNode(DUP));
        list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventUpdate.class), "<init>", "()V", false));
        list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
        list.add(new InsnNode(POP));

        methodNode.instructions.insert(list);
    }
}
