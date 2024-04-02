package org.newdawn.slick.opengl.renderer;


import lombok.Getter;

/**
 * The static holder for the current GL implementation. Note that this
 * renderer can only be set before the game has been started.
 *
 * @author kevin
 */
public class Renderer {
    /**
     * The indicator for immediate mode renderering (the default)
     */
    public static final int IMMEDIATE_RENDERER = 1;
    /**
     * The indicator for vertex array based rendering
     */
    public static final int VERTEX_ARRAY_RENDERER = 2;

    /**
     * The indicator for direct GL line renderer (the default)
     */
    public static final int DEFAULT_LINE_STRIP_RENDERER = 3;
    /**
     * The indicator for consistant quad based lines
     */
    public static final int QUAD_BASED_LINE_STRIP_RENDERER = 4;


    /**
     * The renderer in use
     */
    private static SGL renderer = new ImmediateModeOGLRenderer();
    /**
     * The line strip renderer to use
     * -- GETTER --
     *  Get the line strip renderer to use
     *
     * @return The line strip renderer to use

     */
    @Getter
    private static LineStripRenderer lineStripRenderer = new DefaultLineStripRenderer();

    /**
     * Set the line strip renderer to be used globally
     *
     * @param renderer The line strip renderer to be used
     */
    public static void setLineStripRenderer(LineStripRenderer renderer) {
        lineStripRenderer = renderer;
    }

    /**
     * Set the renderer to be used
     *
     * @param r The renderer to be used
     */
    public static void setRenderer(SGL r) {
        renderer = r;
    }

    /**
     * Get the renderer to be used when accessing GL
     *
     * @return The renderer to be used when accessing GL
     */
    public static SGL get() {
        return renderer;
    }

}
