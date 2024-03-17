package cn.matrixaura.lepton.ui.LAIT.nodes;

import cn.matrixaura.lepton.ui.LAIT.RenderNode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class RectangleNode extends RenderNode {
    private Number width = 0, height = 0, radius = 0;

    public RectangleNode() {
        super("Rectangle");
    }
}
