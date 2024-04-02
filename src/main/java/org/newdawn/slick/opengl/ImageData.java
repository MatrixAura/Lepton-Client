package org.newdawn.slick.opengl;

import java.nio.ByteBuffer;

/**
 * A description of any class providing ImageData in a form suitable for OpenGL texture
 * creation.
 *
 * @author kevin
 */
public interface ImageData {

    /**
     * Get the last bit depth read from a TGA
     *
     * @return The last bit depth read
     */
    int getDepth();

    /**
     * Get the last width read from a TGA
     *
     * @return Get the last width in pixels fread from a TGA
     */
    int getWidth();

    /**
     * Get the last height read from a TGA
     *
     * @return Get the last height in pixels fread from a TGA
     */
    int getHeight();

    /**
     * Get the last required texture width for a loaded image
     *
     * @return Get the ast required texture width for a loaded image
     */
    int getTexWidth();

    /**
     * Get the ast required texture height for a loaded image
     *
     * @return Get the ast required texture height for a loaded image
     */
    int getTexHeight();

    /**
     * Get the store image
     *
     * @return The stored image
     */
    ByteBuffer getImageBufferData();

}