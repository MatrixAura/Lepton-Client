package wtf.agent.client.listener.events.render;

public class EventRender2D {
    private final int width, height;
    private final float partialTicks;

    public EventRender2D() {
        this(0, 0, 0.0f);
    }

    public EventRender2D(int width, int height, float partialTicks) {
        this.width = width;
        this.height = height;
        this.partialTicks = partialTicks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
