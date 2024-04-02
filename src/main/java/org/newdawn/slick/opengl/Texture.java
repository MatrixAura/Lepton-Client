package org.newdawn.slick.opengl;

/**
 * The description of a texture loaded by the TextureLoader utility
 *
 * @author kevin
 */
public interface Texture {

    /**
     * Bind the  GL context to a texture
     */
    void bind();

    /**
     * Get the width of the original image
     *
     * @return The width of the original image
     */
    int getImageWidth();

    /**
     * Get the height of the actual texture
     *
     * @return The height of the actual texture
     */
    int getTextureHeight();

    /**
     * Get the width of the actual texture
     *
     * @return The width of the actual texture
     */
    int getTextureWidth();

    /**
     * Get the OpenGL texture ID for this texture
     *
     * @return The OpenGL texture ID
     */
    int getTextureID();

}