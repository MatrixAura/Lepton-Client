package cn.matrixaura.lepton.inject.asm.transformers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.asm.api.Inject;
import cn.matrixaura.lepton.inject.asm.api.Transformer;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.listener.events.packet.EventPacketReceive;
import cn.matrixaura.lepton.listener.events.packet.EventPacketSend;
import cn.matrixaura.lepton.util.inject.Mappings;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class NetworkManagerTransFormer extends Transformer {
    public NetworkManagerTransFormer() {
        super("net/minecraft/network/NetworkManager");
    }

    // MD: ek/a (Lio/netty/channel/ChannelHandlerContext;Lff;)V net/minecraft/network/NetworkManager/channelRead0 (Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V
    @Inject(method = "channelRead0", descriptor = "(Lio/netty/channel/ChannelHandlerContext;Lff;)V")
    public void channelRead0(MethodNode mn) {
        InsnList list = new InsnList();
        LabelNode label = new LabelNode();

        for (int i = 0; i < mn.instructions.size(); ++i) {
            AbstractInsnNode node = mn.instructions.get(i);

            if (node instanceof FieldInsnNode && ((FieldInsnNode) node).name.equals(Mappings.seargeToNotchField("field_150744_m"))) {

                list.add(new TypeInsnNode(NEW, Type.getInternalName(EventPacketReceive.class)));
                list.add(new InsnNode(DUP));
                list.add(new VarInsnNode(ALOAD, 2)); // #2 Packet p_channelRead0_2_
                list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPacketReceive.class), "<init>", "(Ljava/lang/Object;)V", false));
                list.add(new VarInsnNode(ASTORE, 3));
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Lepton.class), "getBus", "()Lcn/matrixaura/lepton/listener/bus/EventBus;", false));
                list.add(new VarInsnNode(ALOAD, 3));
                list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
                list.add(new InsnNode(POP));
                list.add(new VarInsnNode(ALOAD, 3));
                list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventPacketReceive.class), "isCancelled", "()Z", false));
                list.add(new JumpInsnNode(IFEQ, label));
                list.add(new InsnNode(RETURN));

                list.add(label);

                mn.instructions.insertBefore(node // GETFIELD INetHandler NetworkManager.packetListener
                                .getPrevious() // ALOAD 0
                                .getPrevious(), // ALOAD 2
                        list);
            }
        }
    }

    // func_179290_a,sendPacket,2,
    @Inject(method = "func_179290_a", descriptor = "(Lff;)V")
    public void sendPacket(MethodNode mn) {
        InsnList list = new InsnList();
        LabelNode label = new LabelNode();

        for (int i = 0; i < mn.instructions.size(); ++i) {
            AbstractInsnNode node = mn.instructions.get(i);

            if (node instanceof MethodInsnNode && ((MethodInsnNode) node).name.equals(Mappings.seargeToNotchMethod("func_150733_h"))) {
                list.add(new TypeInsnNode(NEW, Type.getInternalName(EventPacketSend.class)));
                list.add(new InsnNode(DUP));
                list.add(new VarInsnNode(ALOAD, 1)); // #1 Packet packetIn
                list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventPacketSend.class), "<init>", "(Ljava/lang/Object;)V", false));
                list.add(new VarInsnNode(ASTORE, 2));
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Lepton.class), "getBus", "()Lcn/matrixaura/lepton/listener/bus/EventBus;", false));
                list.add(new VarInsnNode(ALOAD, 2));
                list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
                list.add(new InsnNode(POP));
                list.add(new VarInsnNode(ALOAD, 2));
                list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventPacketSend.class), "isCancelled", "()Z", false));
                list.add(new JumpInsnNode(IFEQ, label));
                list.add(new InsnNode(RETURN));

                list.add(label);

                mn.instructions.insertBefore(node // INVOKESPECIAL void NetworkManager.flushOutboundQueue()
                                .getPrevious(), // ALOAD 0
                        list);
            }
        }
    }
}
