package cn.matrixaura.lepton.ui.LAIT.nodes;

import cn.matrixaura.lepton.ui.LAIT.Node;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class RectangleNode extends Node {
    private Number width = 0, height = 0, radius = 0;
    private Color color = Color.WHITE;

    public RectangleNode() {
        super("Rectangle");
    }
}
