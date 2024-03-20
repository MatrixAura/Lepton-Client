package cn.matrixaura.lepton.uiengines.LAIT.nodes;

import cn.matrixaura.lepton.uiengines.LAIT.RenderNode;

public class RectangleNode extends RenderNode {
    public Number width = 0, height = 0, radius = 0, borderThickness = 0, borderColor = 0;

    public RectangleNode() {
        super("Rectangle");
    }

    @Override
    protected void run() {
        //RenderUtils.drawRoundedOutlineRect();
    }
}
