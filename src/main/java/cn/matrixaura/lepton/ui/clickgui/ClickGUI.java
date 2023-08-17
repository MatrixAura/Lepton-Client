package cn.matrixaura.lepton.ui.clickgui;

import cn.matrixaura.lepton.util.render.RenderUtils;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;

public class ClickGUI extends GuiScreen {

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        RenderUtils.drawRoundedRect(0, 0, 1200, 700, 10, new Color(0x313131));
        RenderUtils.startStencil();
        RenderUtils.drawRoundedRect(0, 0, 1200, 700, 10, new Color(0x313131));
        RenderUtils.readStencil(1);
        RenderUtils.drawRect(352, 80, 1200, 700, new Color(0x1F1F1F).getRGB());
        RenderUtils.stopStencil();
        RenderUtils.drawRect(350, 0, 352, 700, new Color(0x4F4F4F).getRGB());
        RenderUtils.drawRect(352, 78, 1200, 80, new Color(0x4F4F4F).getRGB());
    }
}
