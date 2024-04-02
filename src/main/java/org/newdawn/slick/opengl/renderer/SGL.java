package org.newdawn.slick.opengl.renderer;

import org.lwjgl.opengl.EXTSecondaryColor;
import org.lwjgl.opengl.EXTTextureMirrorClamp;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * The description of the OpenGL functions used Slick. Any other rendering method will
 * need to emulate these.
 *
 * @author kevin
 */
public interface SGL {
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_TEXTURE_2D = GL11.GL_TEXTURE_2D;

    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_RGBA = GL11.GL_RGBA;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_RGB = GL11.GL_RGB;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_UNSIGNED_BYTE = GL11.GL_UNSIGNED_BYTE;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_LINEAR = GL11.GL_LINEAR;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_NEAREST = GL11.GL_NEAREST;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_TEXTURE_MIN_FILTER = GL11.GL_TEXTURE_MIN_FILTER;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_TEXTURE_MAG_FILTER = GL11.GL_TEXTURE_MAG_FILTER;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_POLYGON_SMOOTH = GL11.GL_POLYGON_SMOOTH;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_LINE_SMOOTH = GL11.GL_LINE_SMOOTH;

    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_QUADS = GL11.GL_QUADS;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_LINE_STRIP = GL11.GL_LINE_STRIP;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_TRIANGLE_FAN = GL11.GL_TRIANGLE_FAN;

    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_MAX_TEXTURE_SIZE = GL11.GL_MAX_TEXTURE_SIZE;

    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_RGBA8 = GL11.GL_RGBA;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_RGBA16 = GL11.GL_RGBA16;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_MIRROR_CLAMP_TO_EDGE_EXT = EXTTextureMirrorClamp.GL_MIRROR_CLAMP_TO_EDGE_EXT;

    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_TEXTURE_WRAP_S = GL11.GL_TEXTURE_WRAP_S;
    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_TEXTURE_WRAP_T = GL11.GL_TEXTURE_WRAP_T;

    /**
     * OpenGL Enum - @url http://www.opengl.org/documentation
     */
    int GL_CLAMP = GL11.GL_CLAMP;

    /**
     * Flush the current state of the renderer down to GL
     */
    void flush();

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param width
     */
    void glLineWidth(float width);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param id
     * @param ret
     */
    void glGetInteger(int id, IntBuffer ret);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param item
     */
    void glEnable(int item);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param item
     */
    void glDisable(int item);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param target
     * @param id
     */
    void glBindTexture(int target, int id);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param target
     * @param level
     * @param format
     * @param type
     * @param pixels
     */
    void glGetTexImage(int target, int level, int format, int type, ByteBuffer pixels);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param buffer
     */
    void glDeleteTextures(IntBuffer buffer);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param r
     * @param g
     * @param b
     * @param a
     */
    void glColor4f(float r, float g, float b, float a);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param u
     * @param v
     */
    void glTexCoord2f(float u, float v);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param x
     * @param y
     * @param z
     */
    void glVertex3f(float x, float y, float z);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param x
     * @param y
     */
    void glVertex2f(float x, float y);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param geomType
     */
    void glBegin(int geomType);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     */
    void glEnd();

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param target
     * @param param
     * @param value
     */
    void glTexParameteri(int target, int param, int value);

    /**
     * Get the current colour being rendered
     *
     * @return The current colour being rendered
     */
    float[] getCurrentColor();

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     *
     * @param ids
     */
    void glGenTextures(IntBuffer ids);

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     */
    void glGetError();

    /**
     * OpenGL Method - @url http://www.opengl.org/documentation/
     */
    void glTexImage2D(int target, int i, int dstPixelFormat,
                      int get2Fold, int get2Fold2, int j, int srcPixelFormat,
                      int glUnsignedByte, ByteBuffer textureBuffer);

    /**
     * Check if the mirror clamp extension is available
     *
     * @return True if the mirro clamp extension is available
     */
    boolean canTextureMirrorClamp();

}
