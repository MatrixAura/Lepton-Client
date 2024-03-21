package cn.matrixaura.lepton.uiengines.LAIT.nodes;

import cn.matrixaura.lepton.uiengines.LAIT.Node;
import cn.matrixaura.lepton.util.render.RenderUtils;

import java.awt.*;

public class RectangleNode extends Node {
    public float width = 0, height = 0, radius = 0;
    public int borderThickness = 0, borderColor = 0;

    public RectangleNode() {
        super("Rectangle");
    }

    @Override
    protected void run() {
        RenderUtils.drawRoundedOutlineRect(x, y, width, height, radius, borderThickness, new Color(color), new Color(borderColor));
    }

    @Override
    protected void calcExtent() {
        this.nHeight = height;
        this.nWidth = width;
    }
}
