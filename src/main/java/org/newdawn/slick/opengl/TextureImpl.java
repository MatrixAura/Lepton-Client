package org.newdawn.slick.opengl;

import lombok.Getter;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/**
 * A texture to be bound within JOGL. This object is responsible for
 * keeping track of a given OpenGL texture and for calculating the
 * texturing mapping coordinates of the full image.
 * <p>
 * Since textures need to be powers of 2 the actual texture may be
 * considerably bigged that the source image and hence the texture
 * mapping coordinates need to be adjusted to matchup drawing the
 * sprite against the texture.
 *
 * @author Kevin Glass
 * @author Brian Matzon
 */
public class TextureImpl implements Texture {
    /**
     * The renderer to use for all GL operations
     */
    protected static SGL GL = Renderer.get();

    /**
     * The last texture that was bound to
     * -- GETTER --
     *  Retrieve the last texture bound through the texture interface
     *
     * @return The last texture bound

     */
    @Getter
    static Texture lastBind;

    /**
     * The GL target type
     */
    private int target;
    /**
     * The GL texture ID
     * -- GETTER --
     *

     */
    @Getter
    private int textureID;
    /**
     * The height of the image
     */
    private int height;
    /**
     * The width of the image
     */
    private int width;
    /**
     * The width of the texture
     */
    private int texWidth;
    /**
     * The height of the texture
     */
    private int texHeight;
    /**
     * The ratio of the width of the image to the texture
     */
    private float widthRatio;
    /**
     * The ratio of the height of the image to the texture
     */
    private float heightRatio;
    /**
     * If this texture has alpha
     */
    private boolean alpha;
    /**
     * The reference this texture was loaded from
     */
    private String ref;
    /**
     * The name the texture has in the cache
     */
    private String cacheName;

    /**
     * Data used to reload this texture
     */
    private ReloadData reloadData;

    /**
     * For subclasses to utilise
     */
    protected TextureImpl() {
    }

    /**
     * Create a new texture
     *
     * @param ref       The reference this texture was loaded from
     * @param target    The GL target
     * @param textureID The GL texture ID
     */
    public TextureImpl(String ref, int target, int textureID) {
        this.target = target;
        this.ref = ref;
        this.textureID = textureID;
        lastBind = this;
    }

    /**
     * Set the name this texture is stored against in the cache
     *
     * @param cacheName The name the texture is stored against in the cache
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * @see org.newdawn.slick.opengl.Texture#hasAlpha()
     */
    public boolean hasAlpha() {
        return alpha;
    }

    /**
     * If this texture has alpha
     *
     * @param alpha True, If this texture has alpha
     */
    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

    /**
     * Clear the binding of the texture
     */
    public static void bindNone() {
        lastBind = null;
        GL.glDisable(SGL.GL_TEXTURE_2D);
    }

    /**
     * @see org.newdawn.slick.opengl.Texture#bind()
     */
    public void bind() {
        if (lastBind != this) {
            lastBind = this;
            GL.glEnable(SGL.GL_TEXTURE_2D);
            GL.glBindTexture(target, textureID);
        }
    }

    /**
     * Set the height of the image
     *
     * @param height The height of the image
     */
    public void setHeight(int height) {
        this.height = height;
        setHeight();
    }

    /**
     * Set the width of the image
     *
     * @param width The width of the image
     */
    public void setWidth(int width) {
        this.width = width;
        setWidth();
    }

    /**
     * @see org.newdawn.slick.opengl.Texture#getImageWidth()
     */
    public int getImageWidth() {
        return width;
    }

    /**
     * @see org.newdawn.slick.opengl.Texture#getTextureHeight()
     */
    public int getTextureHeight() {
        return texHeight;
    }

    /**
     * @see org.newdawn.slick.opengl.Texture#getTextureWidth()
     */
    public int getTextureWidth() {
        return texWidth;
    }

    /**
     * Set the height of this texture
     *
     * @param texHeight The height of the texture
     */
    public void setTextureHeight(int texHeight) {
        this.texHeight = texHeight;
        setHeight();
    }

    /**
     * Set the width of this texture
     *
     * @param texWidth The width of the texture
     */
    public void setTextureWidth(int texWidth) {
        this.texWidth = texWidth;
        setWidth();
    }

    /**
     * Set the height of the texture. This will update the
     * ratio also.
     */
    private void setHeight() {
        if (texHeight != 0) {
            heightRatio = ((float) height) / texHeight;
        }
    }

    /**
     * Set the width of the texture. This will update the
     * ratio also.
     */
    private void setWidth() {
        if (texWidth != 0) {
            widthRatio = ((float) width) / texWidth;
        }
    }

    /**
     * Creates an integer buffer to hold specified ints
     * - strictly a utility method
     *
     * @param size how many int to contain
     * @return created IntBuffer
     */
    protected IntBuffer createIntBuffer(int size) {
        ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
        temp.order(ByteOrder.nativeOrder());

        return temp.asIntBuffer();
    }

    /**
     * Set the texture data that this texture can be reloaded from
     *
     * @param srcPixelFormat The pixel format
     * @param componentCount The component count
     * @param minFilter      The OpenGL minification filter
     * @param magFilter      The OpenGL magnification filter
     * @param textureBuffer  The texture buffer containing the data for the texture
     */
    public void setTextureData(int srcPixelFormat, int componentCount,
                               int minFilter, int magFilter, ByteBuffer textureBuffer) {
        reloadData = new ReloadData();
        reloadData.srcPixelFormat = srcPixelFormat;
        reloadData.componentCount = componentCount;
        reloadData.minFilter = minFilter;
        reloadData.magFilter = magFilter;
        reloadData.textureBuffer = textureBuffer;
    }

    /**
     * Reload this texture
     */
    public void reload() {
        if (reloadData != null) {
            textureID = reloadData.reload();
        }
    }

    /**
     * Reload this texture from it's original source data
     */
    private class ReloadData {
        /**
         * The src pixel format
         */
        private int srcPixelFormat;
        /**
         * The component count
         */
        private int componentCount;
        /**
         * The OpenGL minification filter
         */
        private int minFilter;
        /**
         * The OpenGL magnification filter
         */
        private int magFilter;
        /**
         * The texture buffer of pixel data
         */
        private ByteBuffer textureBuffer;

        /**
         * Reload this texture
         *
         * @return The new texture ID assigned to this texture
         */
        public int reload() {
            Log.error("Reloading texture: " + ref);
            return InternalTextureLoader.get().reload(TextureImpl.this, srcPixelFormat, componentCount, minFilter, magFilter, textureBuffer);
        }
    }
}