package cn.matrixaura.lepton.inject.dynamic.impl.gui;

import cn.matrixaura.lepton.inject.dynamic.Dynamic;
import cn.matrixaura.lepton.util.inject.Mappings;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class GuiScreenDynamic extends Dynamic {

    public GuiScreenDynamic() {
        super("net.minecraft.client.gui.GuiScreen");
    }

    @Override
    public byte[] dump() throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        MethodVisitor methodVisitor;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "net/minecraft/client/gui/GuiScreen", null, Mappings.getObfClass("net/minecraft/client/gui/GuiScreen"), null);

        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(7, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, Mappings.getObfClass("net/minecraft/client/gui/GuiScreen"), "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, Mappings.seargeToNotchMethod("func_73866_w_"), "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(11, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "initGui", "()V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(12, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label2, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PROTECTED, Mappings.seargeToNotchMethod("func_73869_a"), "(CI)V", null, new String[]{"java/io/IOException"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(16, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "keyTyped", "(CI)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(17, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, Mappings.getObfClass("net/minecraft/client/gui/GuiScreen"), Mappings.seargeToNotchMethod("func_73869_a"), "(CI)V", false);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(18, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("typedChar", "C", null, label0, label3, 1);
            methodVisitor.visitLocalVariable("keyCode", "I", null, label0, label3, 2);
            methodVisitor.visitMaxs(3, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, Mappings.seargeToNotchMethod("func_73868_f"), "()Z", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(22, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "doesGuiPauseGame", "()Z", false);
            methodVisitor.visitInsn(IRETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, Mappings.seargeToNotchMethod("func_146281_b"), "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(27, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "onGuiClosed", "()V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(28, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label2, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PROTECTED, Mappings.seargeToNotchMethod("func_73864_a"), "(III)V", null, new String[]{"java/io/IOException"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(32, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitVarInsn(ILOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "mouseClicked", "(III)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(33, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("mouseX", "I", null, label0, label2, 1);
            methodVisitor.visitLocalVariable("mouseY", "I", null, label0, label2, 2);
            methodVisitor.visitLocalVariable("mouseButton", "I", null, label0, label2, 3);
            methodVisitor.visitMaxs(4, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PROTECTED, Mappings.seargeToNotchMethod("func_146286_b"), "(III)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(37, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitVarInsn(ILOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "mouseReleased", "(III)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(38, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("mouseX", "I", null, label0, label2, 1);
            methodVisitor.visitLocalVariable("mouseY", "I", null, label0, label2, 2);
            methodVisitor.visitLocalVariable("state", "I", null, label0, label2, 3);
            methodVisitor.visitMaxs(4, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, Mappings.seargeToNotchMethod("func_73863_a"), "(IIF)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(42, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitVarInsn(FLOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/GuiScreen", "drawScreen", "(IIF)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(43, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("mouseX", "I", null, label0, label2, 1);
            methodVisitor.visitLocalVariable("mouseY", "I", null, label0, label2, 2);
            methodVisitor.visitLocalVariable("partialTicks", "F", null, label0, label2, 3);
            methodVisitor.visitMaxs(4, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "initGui", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(47, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitMaxs(0, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PROTECTED, "keyTyped", "(CI)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(51, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("typedChar", "C", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("keyCode", "I", null, label0, label1, 2);
            methodVisitor.visitMaxs(0, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "drawScreen", "(IIF)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(55, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("mouseX", "I", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("mouseY", "I", null, label0, label1, 2);
            methodVisitor.visitLocalVariable("partialTicks", "F", null, label0, label1, 3);
            methodVisitor.visitMaxs(0, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "doesGuiPauseGame", "()Z", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(59, label0);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitInsn(IRETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "onGuiClosed", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(64, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitMaxs(0, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PROTECTED, "mouseReleased", "(III)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(68, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("mouseX", "I", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("mouseY", "I", null, label0, label1, 2);
            methodVisitor.visitLocalVariable("state", "I", null, label0, label1, 3);
            methodVisitor.visitMaxs(0, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PROTECTED, "mouseClicked", "(III)V", null, new String[]{"java/io/IOException"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(73, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lnet/minecraft/client/gui/GuiScreen;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("mouseX", "I", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("mouseY", "I", null, label0, label1, 2);
            methodVisitor.visitLocalVariable("mouseButton", "I", null, label0, label1, 3);
            methodVisitor.visitMaxs(0, 4);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}
