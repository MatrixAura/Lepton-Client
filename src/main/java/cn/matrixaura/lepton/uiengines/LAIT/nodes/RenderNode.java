package cn.matrixaura.lepton.uiengines.LAIT.nodes;

import cn.matrixaura.lepton.uiengines.LAIT.Node;
import net.minecraft.client.Minecraft;

public abstract class RenderNode extends Node {

    public int[] align = {0, 0};
    public float[] offset = {0, 0};
    public boolean shown = true;
    public int color = 0xFFFFFF;

    protected float x, y, nWidth, nHeight;

    private void calcCoords() {
        float parentX = 0, parentY = 0, parentWidth = Minecraft.getMinecraft().displayWidth, parentHeight = Minecraft.getMinecraft().displayHeight;
        if (parent != null && parent instanceof RenderNode) {
            parentX = ((RenderNode) parent).x;
            parentY = ((RenderNode) parent).y;
            parentWidth = ((RenderNode) parent).nWidth;
            parentHeight = ((RenderNode) parent).nHeight;
        }
        switch (align[0]) {
            case 0: {
                x = parentX + offset[0];
                break;
            }
            case 1: {
                x = parentX + parentWidth / 2F + offset[0];
                break;
            }
            case 2: {
                x = parentX + parentWidth + offset[0];
                break;
            }
        }
        switch (align[1]) {
            case 0: {
                y = parentY + offset[0];
                break;
            }
            case 1: {
                y = parentY + parentHeight / 2F + offset[1];
                break;
            }
            case 2: {
                y = parentY + parentHeight + offset[1];
                break;
            }
        }
    }

    protected RenderNode(String type) {
        super(type);
    }

    @Override
    public void runAll() {
        if (!shown) return;
        // Get Width & Height
        calcExtent();
        // Get Coords
        calcCoords();
        this.run();
    }
    protected abstract void run();

    protected abstract void calcExtent();
}
