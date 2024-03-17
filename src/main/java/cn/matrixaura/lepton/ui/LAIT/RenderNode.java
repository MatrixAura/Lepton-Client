package cn.matrixaura.lepton.ui.LAIT;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class RenderNode extends Node {
    private Integer[] align = {0, 0};
    private Double[] offset = {0D, 0D};
    private Boolean shown = true;
    private Color color = Color.WHITE;

    public RenderNode(String type) {
        super(type);
    }
}
