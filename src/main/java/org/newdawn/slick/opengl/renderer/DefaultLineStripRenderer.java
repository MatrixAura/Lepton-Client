package org.newdawn.slick.opengl.renderer;

/**
 * The default version of the renderer relies of GL calls to do everything.
 * Unfortunately this is driver dependent and often implemented inconsistantly
 *
 * @author kevin
 */
public class DefaultLineStripRenderer implements LineStripRenderer {
    /**
     * The access to OpenGL
     */
    private final SGL GL = Renderer.get();

    /**
     * @see org.newdawn.slick.opengl.renderer.LineStripRenderer#color(float, float, float, float)
     */
    public void color(float r, float g, float b, float a) {
        GL.glColor4f(r, g, b, a);
    }

}
