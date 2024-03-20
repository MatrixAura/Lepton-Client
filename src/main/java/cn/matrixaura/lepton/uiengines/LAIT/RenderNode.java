package cn.matrixaura.lepton.uiengines.LAIT;

public abstract class RenderNode extends Node {
    public Number[] align = {0, 0}, offset = {0D, 0D};
    public boolean shown = true;
    public Number color = 0xFFFFFF;

    public RenderNode(String type) {
        super(type);
    }

    public abstract void render();
}
