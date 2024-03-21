package cn.matrixaura.lepton.uiengines.LAIT.nodes;

import cn.matrixaura.lepton.uiengines.LAIT.Node;

public class TextNode extends Node {
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
