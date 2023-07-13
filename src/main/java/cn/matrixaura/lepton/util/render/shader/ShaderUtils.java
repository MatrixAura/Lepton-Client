package cn.matrixaura.lepton.util.render.shader;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.util.file.FileUtils;
import cn.matrixaura.lepton.util.inject.Mappings;
import cn.matrixaura.lepton.util.inject.ReflectionUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
    private final int programID;

    public ShaderUtils(Shaders shaderType) {
        int program = glCreateProgram();
        int fragmentShaderID, vertexShaderID;

        switch (shaderType) {
            case RoundedRect:
                fragmentShaderID = createShader(new ByteArrayInputStream(roundedRect.getBytes()), GL_FRAGMENT_SHADER);
                vertexShaderID = createShader(new ByteArrayInputStream(vertex.getBytes()), GL_VERTEX_SHADER);
                break;
            case Shadow:
                fragmentShaderID = createShader(new ByteArrayInputStream(shadow.getBytes()), GL_FRAGMENT_SHADER);
                vertexShaderID = createShader(new ByteArrayInputStream(shadow_vertex.getBytes()), GL_VERTEX_SHADER);
                break;
            default: throw new RuntimeException("Unreachable case");
        }

        glAttachShader(program, fragmentShaderID);
        glAttachShader(program, vertexShaderID);


        glLinkProgram(program);
        int status = glGetProgrami(program, GL_LINK_STATUS);

        if (status == 0) {
            throw new RuntimeException("Failed to link shader: " + shaderType.name());
        }
        this.programID = program;
    }


    public void init() {
        glUseProgram(programID);
    }

    public void unload() {
        glUseProgram(0);
    }

    public int getUniform(String name) {
        return glGetUniformLocation(programID, name);
    }


    public void setUniformf(String name, float... args) {
        int loc = glGetUniformLocation(programID, name);
        switch (args.length) {
            case 1:
                glUniform1f(loc, args[0]);
                break;
            case 2:
                glUniform2f(loc, args[0], args[1]);
                break;
            case 3:
                glUniform3f(loc, args[0], args[1], args[2]);
                break;
            case 4:
                glUniform4f(loc, args[0], args[1], args[2], args[3]);
                break;
        }
    }

    public void setUniformi(String name, int... args) {
        int loc = glGetUniformLocation(programID, name);
        if (args.length > 1) glUniform2i(loc, args[0], args[1]);
        else glUniform1i(loc, args[0]);
    }

    public static void drawQuads(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glTexCoord2f(0, 1);
        glVertex2f(x, y + height);
        glTexCoord2f(1, 1);
        glVertex2f(x + width, y + height);
        glTexCoord2f(1, 0);
        glVertex2f(x + width, y);
        glEnd();
    }

    public static void drawQuads() {
        try {
            Object mc = MinecraftWrapper.get().getMinecraftObj();
            Object sr = ReflectionUtils.newInstance(Class.forName(Mappings.getObfClass("net/minecraft/client/gui/ScaledResolution")), new Class[]{mc.getClass()}, mc);
            double width = (Double) ReflectionUtils.invokeMethod(sr.getClass(), sr, Mappings.getObfMethod("func_78327_c"));
            double height = (Double) ReflectionUtils.invokeMethod(sr.getClass(), sr, Mappings.getObfMethod("func_78324_d"));
            glBegin(GL_QUADS);
            glTexCoord2f(0, 1);
            glVertex2f(0, 0);
            glTexCoord2f(0, 0);
            glVertex2f(0, (float) height);
            glTexCoord2f(1, 0);
            glVertex2f((float) width, (float) height);
            glTexCoord2f(1, 1);
            glVertex2f((float) width, 0);
            glEnd();
        } catch (ClassNotFoundException ignored) {

        }
    }

    private int createShader(InputStream inputStream, int shaderType) {
        int shader = glCreateShader(shaderType);
        glShaderSource(shader, FileUtils.readInputStream(inputStream));
        glCompileShader(shader);


        if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            Lepton.logger.info(glGetShaderInfoLog(shader, 4096));
            throw new IllegalStateException("Failed to compile shader " + shaderType);
        }

        return shader;
    }

    private static final String vertex = "#version 120\n" +
            "\n" +
            "    void main() {\n" +
            "        gl_TexCoord[0] = gl_MultiTexCoord0;\n" +
            "        gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
            "    }";

    private static final String shadow_vertex = "#version 120\n" +
            "varying vec2 f_Position;\n" +
            "\n" +
            "void main() {\n" +
            "    f_Position = gl_Vertex.xy;\n" +
            "    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n" +
            "    gl_FrontColor = gl_Color;\n" +
            "}";

    private static final String roundedRect = "#version 120\n" +
            "\n" +
            "uniform vec2 location, rectSize;\n" +
            "uniform vec4 color;\n" +
            "uniform float radius;\n" +
            "uniform bool blur;\n" +
            "\n" +
            "float roundSDF(vec2 p, vec2 b, float r) {\n" +
            "    return length(max(abs(p) - b, 0.0)) - r;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "void main() {\n" +
            "    vec2 rectHalf = rectSize * .5;\n" +
            "    // Smooth the result (free antialiasing).\n" +
            "    float smoothedAlpha =  (1.0-smoothstep(0.0, 1.0, roundSDF(rectHalf - (gl_TexCoord[0].st * rectSize), rectHalf - radius - 1., radius))) * color.a;\n" +
            "    gl_FragColor = vec4(color.rgb, smoothedAlpha);// mix(quadColor, shadowColor, 0.0);\n" +
            "\n" +
            "}";

    private static final String shadow = "#version 120\n" +
            "precision highp float;\n" +
            "uniform vec4 u_InnerRect;\n" +
            "uniform float u_Spread;\n" +
            "uniform vec4 u_Color;\n" +
            "varying vec2 f_Position;\n" +
            "\n" +
            "// blend two color by alpha\n" +
            "vec4 blend(vec4 src, vec4 append) {\n" +
            "    return vec4(src.rgb + append.rgb,\n" +
            "    1.0 - (1.0 - (src.a)) * (1.0 - append.a));\n" +
            "}\n" +
            "\n" +
            "// approximation to the gaussian integral [x, infty)\n" +
            "float gi(float x) {\n" +
            "    float i6 = 1.0 / 6.0;\n" +
            "    float i4 = 1.0 / 4.0;\n" +
            "    float i3 = 1.0 / 3.0;\n" +
            "\n" +
            "    if (x > 1.5) return 0.0;\n" +
            "    if (x < -1.5) return 1.0;\n" +
            "\n" +
            "    float x2 = x * x;\n" +
            "    float x3 = x2 * x;\n" +
            "\n" +
            "    if (x >  0.5) return .5625  - ( x3 * i6 - 3. * x2 * i4 + 1.125 * x);\n" +
            "    if (x > -0.5) return 0.5    - (0.75 * x - x3 * i3);\n" +
            "    return 0.4375 + (-x3 * i6 - 3. * x2 * i4 - 1.125 * x);\n" +
            "}\n" +
            "\n" +
            "// create a line shadow mask\n" +
            "float lineShadow(vec2 border, float pos , float sigma) {\n" +
            "    float t = (border.y - border.x) / sigma;\n" +
            "\n" +
            "    float pos1 = ((border.x - pos) / sigma) * 1.5;\n" +
            "    float pos2 = ((pos - border.y) / sigma) * 1.5;\n" +
            "\n" +
            "    return 1.0 - abs(gi(pos1) - gi(pos2));\n" +
            "}\n" +
            "\n" +
            "// create a rect shadow by two line shadow\n" +
            "float rectShadow(vec4 rect, vec2 point, float sigma) {\n" +
            "\n" +
            "    float lineV = lineShadow(vec2(rect.x, rect.x + rect.z), point.x, sigma);\n" +
            "    float lineH = lineShadow(vec2(rect.y, rect.y + rect.w), point.y, sigma);\n" +
            "\n" +
            "    return lineV * lineH;\n" +
            "}\n" +
            "\n" +
            "// draw shadow\n" +
            "vec4 drawRectShadow(vec2 pos, vec4 rect, vec4 color, float sigma) {\n" +
            "    vec4 result = color;\n" +
            "\n" +
            "    float shadowMask = rectShadow(rect, pos, sigma);\n" +
            "\n" +
            "    result.a *= shadowMask;\n" +
            "\n" +
            "    return result;\n" +
            "}\n" +
            "\n" +
            "void main() {\n" +
            "    float sigma = u_Spread;\n" +
            "\n" +
            "    vec4 rect = u_InnerRect;\n" +
            "    vec4 shadowRect = vec4(vec2(rect.x , rect.y), vec2(rect.z, rect.w));\n" +
            "    vec4 shadowColor = u_Color;\n" +
            "\n" +
            "    vec4 result = drawRectShadow(f_Position, shadowRect, shadowColor, sigma);\n" +
            "    gl_FragColor = result;\n" +
            "}";


    public enum Shaders {
        Shadow,
        RoundedRect
    }

}
