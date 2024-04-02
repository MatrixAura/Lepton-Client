package org.newdawn.slick;

import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

import java.io.Serializable;
import java.nio.FloatBuffer;

/**
 * A simple wrapper round the values required for a colour
 *
 * @author Kevin Glass
 */
public class Color implements Serializable {
    /**
     * The version ID for this class
     */
    private static final long serialVersionUID = 1393939L;

    /**
     * The renderer to use for all GL operations
     */
    protected transient SGL GL = Renderer.get();

    /**
     * The fixed colour white
     */
    public static final Color white = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    /**
     * The fixed colour yellow
     */
    public static final Color yellow = new Color(1.0f, 1.0f, 0, 1.0f);
    /**
     * The fixed colour blue
     */
    public static final Color blue = new Color(0, 0, 1.0f, 1.0f);

    /**
     * The red component of the colour
     */
    public float r;
    /**
     * The green component of the colour
     */
    public float g;
    /**
     * The blue component of the colour
     */
    public float b;
    /**
     * The alpha component of the colour
     */
    public float a = 1.0f;

    /**
     * Create a 4 component colour
     *
     * @param r The red component of the colour (0.0 -> 1.0)
     * @param g The green component of the colour (0.0 -> 1.0)
     * @param b The blue component of the colour (0.0 -> 1.0)
     * @param a The alpha component of the colour (0.0 -> 1.0)
     */
    public Color(float r, float g, float b, float a) {
        this.r = Math.min(r, 1);
        this.g = Math.min(g, 1);
        this.b = Math.min(b, 1);
        this.a = Math.min(a, 1);
    }

    /**
     * Create a 4 component colour
     *
     * @param r The red component of the colour (0 -> 255)
     * @param g The green component of the colour (0 -> 255)
     * @param b The blue component of the colour (0 -> 255)
     * @param a The alpha component of the colour (0 -> 255)
     */
    public Color(int r, int g, int b, int a) {
        this.r = r / 255.0f;
        this.g = g / 255.0f;
        this.b = b / 255.0f;
        this.a = a / 255.0f;
    }

    /**
     * Create a colour from an evil integer packed 0xAARRGGBB. If AA
     * is specified as zero then it will be interpreted as unspecified
     * and hence a value of 255 will be recorded.
     *
     * @param value The value to interpret for the colour
     */
    public Color(int value) {
        int r = (value & 0x00FF0000) >> 16;
        int g = (value & 0x0000FF00) >> 8;
        int b = (value & 0x000000FF);
        int a = (value & 0xFF000000) >> 24;

        if (a < 0) {
            a += 256;
        }
        if (a == 0) {
            a = 255;
        }

        this.r = r / 255.0f;
        this.g = g / 255.0f;
        this.b = b / 255.0f;
        this.a = a / 255.0f;
    }

    /**
     * Bind this colour to the GL context
     */
    public void bind() {
        GL.glColor4f(r, g, b, a);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return ((int) (r + g + b + a) * 255);
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object other) {
        if (other instanceof Color) {
            Color o = (Color) other;
            return ((o.r == r) && (o.g == g) && (o.b == b) && (o.a == a));
        }

        return false;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Color (" + r + "," + g + "," + b + "," + a + ")";
    }

    /**
     * Make a darker instance of this colour
     *
     * @param scale The scale down of RGB (i.e. if you supply 0.03 the colour will be darkened by 3%)
     * @return The darker version of this colour
     */
    public Color darker(float scale) {
        scale = 1 - scale;
        Color temp = new Color(r * scale, g * scale, b * scale, a);

        return temp;
    }

    /**
     * Make a brighter instance of this colour
     *
     * @param scale The scale up of RGB (i.e. if you supply 0.03 the colour will be brightened by 3%)
     * @return The brighter version of this colour
     */
    public Color brighter(float scale) {
        scale += 1;
        Color temp = new Color(r * scale, g * scale, b * scale, a);

        return temp;
    }

}
