package cn.matrixaura.lepton.inject.wrapper.impl.gui;

import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiScreenWrapper extends GuiScreen {

    @Override
    public void initGui() {
        this.hook_initGui();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.hook_keyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return this.hook_doesGuiPauseGame();
    }

    @Override
    public void onGuiClosed() {
        this.hook_onGuiClosed();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.hook_mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        this.hook_mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.hook_drawScreen(mouseX, mouseY, partialTicks);
    }

    public void hook_initGui() {

    }

    protected void hook_keyTyped(char typedChar, int keyCode) {

    }


    public void hook_drawScreen(int mouseX, int mouseY, float partialTicks) {
    }


    public boolean hook_doesGuiPauseGame() {
        return false;
    }

    public void hook_onGuiClosed() {

    }

    protected void hook_mouseReleased(int mouseX, int mouseY, int state) {

    }


    protected void hook_mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }

}
