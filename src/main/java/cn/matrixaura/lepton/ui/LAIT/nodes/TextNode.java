package cn.matrixaura.lepton.ui.LAIT.nodes;

import cn.matrixaura.lepton.ui.LAIT.RenderNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class TextNode extends RenderNode {
    private String text;

    public TextNode() {
        super("Text");
    }
    // TODO: Font Render
    //private Font font;
}
