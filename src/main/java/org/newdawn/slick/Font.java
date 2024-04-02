package org.newdawn.slick;


/**
 * The proprites of any font implementation
 *
 * @author Kevin Glass
 */
public interface Font {

    /**
     * Draw a string to the screen
     *
     * @param x    The x location at which to draw the string
     * @param y    The y location at which to draw the string
     * @param text The text to be displayed
     * @param col  The colour to draw with
     */
    void drawString(float x, float y, String text, Color col);


}