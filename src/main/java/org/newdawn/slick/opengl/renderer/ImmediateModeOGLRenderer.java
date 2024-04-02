package org.newdawn.slick.opengl.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * The default OpenGL renderer, uses immediate mode for everything
 *
 * @author kevin
 */
public class ImmediateModeOGLRenderer implements SGL {
    /**
     * The width of the display
     */
    private int width;
    /**
     * The height of the display
     */
    private int height;
    /**
     * The current colour
     */
    private final float[] current = new float[]{1, 1, 1, 1};
    /**
     * The global colour scale
     */
    protected float alphaScale = 1;

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glBegin(int)
     */
    public void glBegin(int geomType) {
        GL11.glBegin(geomType);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glBindTexture(int, int)
     */
    public void glBindTexture(int target, int id) {
        GL11.glBindTexture(target, id);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glColor4f(float, float, float, float)
     */
    public void glColor4f(float r, float g, float b, float a) {
        a *= alphaScale;

        current[0] = r;
        current[1] = g;
        current[2] = b;
        current[3] = a;

        GL11.glColor4f(r, g, b, a);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glDeleteTextures(java.nio.IntBuffer)
     */
    public void glDeleteTextures(IntBuffer buffer) {
        GL11.glDeleteTextures(buffer);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glDisable(int)
     */
    public void glDisable(int item) {
        GL11.glDisable(item);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glEnable(int)
     */
    public void glEnable(int item) {
        GL11.glEnable(item);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glEnd()
     */
    public void glEnd() {
        GL11.glEnd();
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glGetInteger(int, java.nio.IntBuffer)
     */
    public void glGetInteger(int id, IntBuffer ret) {
        GL11.glGetInteger(id, ret);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glGetTexImage(int, int, int, int, java.nio.ByteBuffer)
     */
    public void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels) {
        GL11.glGetTexImage(target, level, format, type, pixels);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glLineWidth(float)
     */
    public void glLineWidth(float width) {
        GL11.glLineWidth(width);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glTexCoord2f(float, float)
     */
    public void glTexCoord2f(float u, float v) {
        GL11.glTexCoord2f(u, v);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glVertex2f(float, float)
     */
    public void glVertex2f(float x, float y) {
        GL11.glVertex2f(x, y);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glVertex3f(float, float, float)
     */
    public void glVertex3f(float x, float y, float z) {
        GL11.glVertex3f(x, y, z);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#flush()
     */
    public void flush() {
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#glTexParameteri(int, int, int)
     */
    public void glTexParameteri(int target, int param, int value) {
        GL11.glTexParameteri(target, param, value);
    }

    /**
     * @see org.newdawn.slick.opengl.renderer.SGL#getCurrentColor()
     */
    public float[] getCurrentColor() {
        return current;
    }

    /*
     * (non-Javadoc)
     * @see org.newdawn.slick.opengl.renderer.SGL#glGenTextures(java.nio.IntBuffer)
     */
    public void glGenTextures(IntBuffer ids) {
        GL11.glGenTextures(ids);
    }

    /*
     * (non-Javadoc)
     * @see org.newdawn.slick.opengl.renderer.SGL#glGetError()
     */
    public void glGetError() {
        GL11.glGetError();
    }

    /*
     * (non-Javadoc)
     * @see org.newdawn.slick.opengl.renderer.SGL#glTexImage2D(int, int, int, int, int, int, int, int, java.nio.ByteBuffer)
     */
    public void glTexImage2D(int target, int i, int dstPixelFormat,
                             int width, int height, int j, int srcPixelFormat,
                             int glUnsignedByte, ByteBuffer textureBuffer) {
        GL11.glTexImage2D(target, i, dstPixelFormat, width, height, j, srcPixelFormat, glUnsignedByte, textureBuffer);
    }

    /*
     * (non-Javadoc)
     * @see org.newdawn.slick.opengl.renderer.SGL#canTextureMirrorClamp()
     */
    public boolean canTextureMirrorClamp() {
        return GLContext.getCapabilities().GL_EXT_texture_mirror_clamp;
    }

}
