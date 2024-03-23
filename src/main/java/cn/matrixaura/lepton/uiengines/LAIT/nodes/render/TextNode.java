package cn.matrixaura.lepton.uiengines.LAIT.nodes.render;

import cn.matrixaura.lepton.uiengines.LAIT.nodes.RenderNode;

public class TextNode extends RenderNode {
    public String text;

    public TextNode() {
        super("Text");
    }

    @Override
    protected void run() {

    }

    @Override
    protected void calcExtent() {
        throw new RuntimeException("未完成");
    }
    // TODO: Font Render
    //public Font font;
}
