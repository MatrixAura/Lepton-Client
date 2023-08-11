package cn.matrixaura.lepton.inject.asm.transformers;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.asm.api.Inject;
import cn.matrixaura.lepton.inject.asm.api.Transformer;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.listener.bus.EventBus;
import cn.matrixaura.lepton.listener.events.input.EventKeyInput;
import cn.matrixaura.lepton.listener.events.input.EventMouseInput;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class MinecraftTransformer extends Transformer {
    public MinecraftTransformer() {
        super("net/minecraft/client/Minecraft");
    }

    // func_152348_aa,dispatchKeypresses,0,
    @Inject(method = "func_152348_aa")
    public void dispatchKeypresses(MethodNode methodNode) {
        for (int i = 0; i < methodNode.instructions.size(); ++i) {
            AbstractInsnNode node = methodNode.instructions.get(i);
            // this should be similar to
            //          L0 {
            //             invokestatic org/lwjgl/input/Keyboard.getEventKey()I
            //             ifne L1
            //             invokestatic org/lwjgl/input/Keyboard.getEventCharacter()C
            //             goto L2
            //         }
            //         L1 {
            //             f_new (Locals[1]: ave) (Stack[0])
            //             invokestatic org/lwjgl/input/Keyboard.getEventKey()I
            //         }
            //         L2 {
            //             f_new (Locals[1]: ave) (Stack[1]: int)
            //             istore 1
            //         }

            if (node.getOpcode() == ISTORE) {

                VarInsnNode v = (VarInsnNode) node;

                InsnList list = new InsnList();

                // we're shooting for this:
                // if (Keyboard.getEventKeyState()) {
                //      Lepton.getEventBus().dispatch(new EventKeyInput(var1));
                // }

                // get the result of "Keyboard.getEventKeyState()" and load into a var (var 2 ig)
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Keyboard.class), "getEventKeyState", "()Z", false));
                list.add(new VarInsnNode(ISTORE, 2));
                list.add(new VarInsnNode(ILOAD, 2));

                // if var 2 is true, jump to this label
                LabelNode label = new LabelNode();
                list.add(new JumpInsnNode(IFEQ, label));

                // this is a mindfuck innit
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Lepton.class), "getEventBus", "()Lcn/matrixaura/lepton/listener/bus/EventBus;", false));
                list.add(new TypeInsnNode(NEW, Type.getInternalName(EventKeyInput.class)));
                list.add(new InsnNode(DUP));
                list.add(new VarInsnNode(ILOAD, v.var));
                list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventKeyInput.class), "<init>", "(I)V", false));
                list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
                list.add(new InsnNode(POP));

                // add label to jump to
                list.add(label);

                // insert after var 1 was defined
                methodNode.instructions.insert(node, list);
                break;
            }
        }
    }

    // MD: ave/s ()V net/minecraft/client/Minecraft/func_71407_l ()V
    @Inject(method = "func_71407_l")
    public void runTick(MethodNode methodNode) {

        for (int i = 0; i < methodNode.instructions.size(); ++i) {
            AbstractInsnNode x = methodNode.instructions.get(i);
            if (x instanceof MethodInsnNode) {
                MethodInsnNode m = (MethodInsnNode) x;
                if (m.owner.equals(Type.getInternalName(Mouse.class)) && m.name.equals("getEventButton")) {
                    InsnList list = new InsnList();

                    // get the result of "Mouse.getEventButtonState()" and load into a var (var 2 ig)
                    list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Mouse.class), "getEventButtonState", "()Z", false));
                    list.add(new VarInsnNode(ISTORE, 2));
                    list.add(new VarInsnNode(ILOAD, 2));

                    // if var 2 is true, jump to this label
                    LabelNode label = new LabelNode();
                    list.add(new JumpInsnNode(IFEQ, label));

                    // this is a mindfuck innit
                    list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Lepton.class), "getEventBus", "()Lcn/matrixaura/lepton/listener/bus/EventBus;", false));
                    list.add(new TypeInsnNode(NEW, Type.getInternalName(EventMouseInput.class)));
                    list.add(new InsnNode(DUP));
                    list.add(new VarInsnNode(ILOAD, 1));
                    list.add(new MethodInsnNode(INVOKESPECIAL, Type.getInternalName(EventMouseInput.class), "<init>", "(I)V", false));
                    list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(EventBus.class), "dispatch", "(Ljava/lang/Object;)Z", false));
                    list.add(new InsnNode(POP));

                    // add label to jump to
                    list.add(label);

                    methodNode.instructions.insert(methodNode.instructions.get(i + 1), list);

                    break;
                }
            }
        }
    }

    // MD: ave/a (Laxu;)V net/minecraft/client/Minecraft/func_147108_a (Lnet/minecraft/client/gui/GuiScreen;)V
    @Inject(method = "func_147108_a", descriptor = "(Laxu;)V")
    public void displayGuiScreen(MethodNode methodNode) {
        InsnList list = new InsnList();
        list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(MinecraftWrapper.class), "get", "()Lcn/matrixaura/lepton/inject/wrapper/impl/MinecraftWrapper;", false));
        list.add(new VarInsnNode(ALOAD, 1));
        list.add(new MethodInsnNode(INVOKEVIRTUAL, Type.getInternalName(MinecraftWrapper.class), "setCurrentScreen", "(Ljava/lang/Object;)V", false));
        methodNode.instructions.insert(list);
    }
}
