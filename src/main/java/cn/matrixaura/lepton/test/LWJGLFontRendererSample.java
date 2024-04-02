package cn.matrixaura.lepton.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class LWJGLFontRendererSample {

    private TrueTypeFont font;
    private int textureId;

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL();
        initFont();

        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);

            font.drawString(40, 40, "text", Color.blue);
            //renderTextToTexture("Hello, World!");

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }

    private void initGL() {
        glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glViewport(0,0,800,600);
        glMatrixMode(GL_MODELVIEW);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 800, 600, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
    }

    private void initFont() {
        Font awtFont = new Font("Consolas", Font.BOLD, 128);
        font = new TrueTypeFont(awtFont, true);
    }

    private void renderTextToTexture(String text) {
        int width = 400; // 超采样后的宽度
        int height = 300; // 超采样后的高度

        glPushMatrix();
        // 创建超采样后的纹理
        textureId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        // 渲染文字到纹理
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        //g.setColor(new java.awt.Color(0, 0, 0, 0)); // 设置透明背景色
        //g.fillRect(0, 0, width, height);

        font.drawString(10, 10, text, Color.blue);
        g.dispose();


        // 将渲染好的文字纹理数据传递给OpenGL
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * width * height);
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixels[y * width + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red
                buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green
                buffer.put((byte) (pixel & 0xFF)); // Blue
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
            }
        }
        buffer.flip();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        // 缩小显示
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1); glVertex2f(100, 100);
        glTexCoord2f(1, 1); glVertex2f(500, 100);
        glTexCoord2f(1, 0); glVertex2f(500, 400);
        glTexCoord2f(0, 0); glVertex2f(100, 400);
        glEnd();

        glPopMatrix();
    }

    public static void main(String[] argv) {
        LWJGLFontRendererSample example = new LWJGLFontRendererSample();
        example.start();
    }
}