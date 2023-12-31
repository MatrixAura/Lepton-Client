package cn.matrixaura.lepton.util.render.shader;

import cn.matrixaura.lepton.Lepton;
import cn.matrixaura.lepton.inject.wrapper.impl.MinecraftWrapper;
import cn.matrixaura.lepton.inject.wrapper.impl.gui.ScaledResolutionWrapper;
import cn.matrixaura.lepton.util.file.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderUtils {
    private final int programID;

    public ShaderUtils(Shaders shaderType) {
        int program = glCreateProgram();
        int fragmentShaderID, vertexShaderID;

        switch (shaderType) {
            case RoundedRect: {
                fragmentShaderID = createFrag(roundedRect);
                vertexShaderID = createVertex(vertex);
                break;
            }
            case Shadow: {
                fragmentShaderID = createFrag(shadow);
                vertexShaderID = createVertex(shadow_vertex);
                break;
            }
            case Blur: {
                fragmentShaderID = createFrag(blur);
                vertexShaderID = createVertex(vertex);
                break;
            }
            case RoundedOutline: {
                fragmentShaderID = createFrag(roundRectOutline);
                vertexShaderID = createVertex(vertex);
                break;
            }
            case RoundedTexture: {
                fragmentShaderID = createFrag(roundRectTexture);
                vertexShaderID = createVertex(vertex);
                break;
            }
            default:
                throw new RuntimeException("Unreachable case");
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

    public void setUniformb(String name, FloatBuffer fb) {
        int loc = glGetUniformLocation(programID, name);
        glUniform1(loc, fb);
    }

    public void setUniformb(String name, IntBuffer fb) {
        int loc = glGetUniformLocation(programID, name);
        glUniform1(loc, fb);
    }

    public static void drawQuads(float x, float y, float x1, float y1) {
        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glTexCoord2f(0, 1);
        glVertex2f(x, y1);
        glTexCoord2f(1, 1);
        glVertex2f(x1, y1);
        glTexCoord2f(1, 0);
        glVertex2f(x1, y);
        glEnd();
    }

    public static void drawQuads() {
        ScaledResolutionWrapper sr = new ScaledResolutionWrapper(MinecraftWrapper.get());
        float width = (float) sr.getScaledWidth_double();
        float height = (float) sr.getScaledHeight_double();
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2f(0, 0);
        glTexCoord2f(0, 0);
        glVertex2f(0, height);
        glTexCoord2f(1, 0);
        glVertex2f(width, height);
        glTexCoord2f(1, 1);
        glVertex2f(width, 0);
        glEnd();
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

    private int createFrag(String fsh) {
        return createShader(new ByteArrayInputStream(fsh.getBytes()), GL_FRAGMENT_SHADER);
    }

    private int createVertex(String vsh) {
        return createShader(new ByteArrayInputStream(vsh.getBytes()), GL_VERTEX_SHADER);
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
            "\n" +
            "uniform sampler2D u_diffuse_sampler;\n" +
            "uniform sampler2D u_other_sampler;\n" +
            "uniform vec2 u_texel_size;\n" +
            "uniform vec2 u_direction;\n" +
            "uniform float u_radius;\n" +
            "uniform float u_kernel[128];\n" +
            "\n" +
            "void main(void)\n" +
            "{\n" +
            "    vec2 uv = gl_TexCoord[0].st;\n" +
            "\n" +
            "    if (u_direction.x == 0.0) {\n" +
            "        float alpha = texture2D(u_other_sampler, uv).a;\n" +
            "        if (alpha > 0.0) discard;\n" +
            "    }\n" +
            "\n" +
            "    float half_radius = u_radius / 2.0;\n" +
            "    vec4 pixel_color = texture2D(u_diffuse_sampler, uv);\n" +
            "    pixel_color.rgb *= pixel_color.a;\n" +
            "    pixel_color *= u_kernel[0];\n" +
            "\n" +
            "    for (float f = 1; f <= u_radius; f++) {\n" +
            "        vec2 offset = f * u_texel_size * u_direction;\n" +
            "        vec4 left = texture2D(u_diffuse_sampler, uv - offset);\n" +
            "        vec4 right = texture2D(u_diffuse_sampler, uv + offset);\n" +
            "\n" +
            "        left.rgb *= left.a;\n" +
            "        right.rgb *= right.a;\n" +
            "        pixel_color += (left + right) * u_kernel[int(f)];\n" +
            "    }\n" +
            "\n" +
            "    gl_FragColor = vec4(0.0, 0.0, 0.0, pixel_color.a);\n" +
            "}\n";

    private static final String blur = "#version 120\n" +
            "\n" +
            "uniform sampler2D textureIn;\n" +
            "uniform vec2 texelSize, direction;\n" +
            "uniform float radius;\n" +
            "uniform float weights[256];\n" +
            "\n" +
            "#define offset texelSize * direction\n" +
            "\n" +
            "void main() {\n" +
            "    vec3 blr = texture2D(textureIn, gl_TexCoord[0].st).rgb * weights[0];\n" +
            "\n" +
            "    for (float f = 1.0; f <= radius; f++) {\n" +
            "        blr += texture2D(textureIn, gl_TexCoord[0].st + f * offset).rgb * (weights[int(abs(f))]);\n" +
            "        blr += texture2D(textureIn, gl_TexCoord[0].st - f * offset).rgb * (weights[int(abs(f))]);\n" +
            "    }\n" +
            "\n" +
            "    gl_FragColor = vec4(blr, 1.0);\n" +
            "}\n";

    private final String roundRectTexture = "#version 120\n" +
            "\n" +
            "uniform vec2 location, rectSize;\n" +
            "uniform sampler2D textureIn;\n" +
            "uniform float radius, alpha;\n" +
            "\n" +
            "float roundedBoxSDF(vec2 centerPos, vec2 size, float radius) {\n" +
            "    return length(max(abs(centerPos) -size, 0.)) - radius;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "void main() {\n" +
            "    float distance = roundedBoxSDF((rectSize * .5) - (gl_TexCoord[0].st * rectSize), (rectSize * .5) - radius - 1., radius);\n" +
            "    float smoothedAlpha =  (1.0-smoothstep(0.0, 2.0, distance)) * alpha;\n" +
            "    gl_FragColor = vec4(texture2D(textureIn, gl_TexCoord[0].st).rgb, smoothedAlpha);\n" +
            "}";

    private final String roundRectOutline = "#version 120\n" +
            "\n" +
            "uniform vec2 location, rectSize;\n" +
            "uniform vec4 color, outlineColor;\n" +
            "uniform float radius, outlineThickness;\n" +
            "\n" +
            "float roundedSDF(vec2 centerPos, vec2 size, float radius) {\n" +
            "    return length(max(abs(centerPos) - size + radius, 0.0)) - radius;\n" +
            "}\n" +
            "\n" +
            "void main() {\n" +
            "    float distance = roundedSDF(gl_FragCoord.xy - location - (rectSize * .5), (rectSize * .5) + (outlineThickness *.5) - 1.0, radius);\n" +
            "\n" +
            "    float blendAmount = smoothstep(0., 2., abs(distance) - (outlineThickness * .5));\n" +
            "\n" +
            "    vec4 insideColor = (distance < 0.) ? color : vec4(outlineColor.rgb,  0.0);\n" +
            "    gl_FragColor = mix(outlineColor, insideColor, blendAmount);\n" +
            "\n" +
            "}";

    public enum Shaders {
        RoundedRect,
        RoundedOutline,
        RoundedTexture,
        Shadow,
        Blur
    }

}
