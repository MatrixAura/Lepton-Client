package cn.matrixaura.lepton.uiengines.LAIT;

import net.minecraft.client.Minecraft;

import java.util.LinkedList;

public abstract class Node {
    public Node parent = null;
    public final String type;
    public String id = "";
    public final LinkedList<Node> body = new LinkedList<>();
    public int[] align = {0, 0};
    public float[] offset = {0, 0};
    public boolean shown = true;
    public int color = 0xFFFFFF;
    protected float x, y, nWidth, nHeight;

    public Node(String type) {
        this.type = type;
    }

    protected abstract void run();

    protected abstract void calcExtent();

    public void runAll() {
        if (!shown) return;
        // Get Width & Height
        calcExtent();
        // Get Coords
        calcCoords();
        this.run();
        body.forEach(Node::runAll);
    }

    private void calcCoords() {
        float parentX = 0, parentY = 0, parentWidth = Minecraft.getMinecraft().displayWidth, parentHeight = Minecraft.getMinecraft().displayHeight;
        if (parent != null) {
            parentX = parent.x;
            parentY = parent.y;
            parentWidth = parent.nWidth;
            parentHeight = parent.nHeight;
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
}
