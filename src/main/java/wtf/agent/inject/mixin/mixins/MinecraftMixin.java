package wtf.agent.inject.mixin.mixins;

import org.lwjgl.input.Keyboard;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import wtf.agent.client.Agent;
import wtf.agent.inject.mixin.api.Mixin;
import wtf.agent.inject.mixin.api.annotation.Inject;

import static org.objectweb.asm.Opcodes.*;

public class MinecraftMixin extends Mixin {
    public MinecraftMixin() {
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

                //          L3 {
                //             iload 1 -> var1
                //             ifeq L4 -> !=
                //             invokestatic org/lwjgl/input/Keyboard.isRepeatEvent()Z
                //             ifeq L5
                //         }

                // we're shooting for this:
                // if (Keyboard.getEventKeyState()) {
                //      Agent.keyPress(var1);
                // }
                // soon we'll use an event bus but yknow

                // get the result of "Keyboard.getEventKeyState()" and load into a var (var 2 ig)
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Keyboard.class), "getEventKeyState", "()Z", false));
                list.add(new VarInsnNode(ISTORE, 2));
                list.add(new VarInsnNode(ILOAD, 2));

                // if var 2 is true, jump to this label
                LabelNode label = new LabelNode();
                list.add(new JumpInsnNode(IFEQ, label));

                // load var 1 into method call
                list.add(new VarInsnNode(ILOAD, v.var));
                list.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(Agent.class), "keyPress", "(I)V", false));

                // add label to jump to
                list.add(label);

                // insert after var 1 was defined
                methodNode.instructions.insert(node, list);
                break;
            }
        }
    }
}
