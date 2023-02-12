package graphics.core;

import static org.lwjgl.opengl.GL40.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenGLUtils {
    public static int initFromFiles(String vertexShaderFileName, String fragmentShaderFileName) {
        return initProgram(readFile(vertexShaderFileName), readFile(fragmentShaderFileName));
    }

    public static String readFile(String fileName) {
        String text = "";
        try {
            var path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
            text = new String(Files.readAllBytes(path));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return text;
    }

    // array used to store debug codes
    static int[] status = new int[1];

    public static int initShader(String shaderCode, int shaderType) {
        shaderCode = "#version 330\n" + shaderCode;
        int shader = glCreateShader(shaderType);
        glShaderSource(shader, shaderCode);

        glCompileShader(shader);

        glGetShaderiv(shader, GL_COMPILE_STATUS, status);
        if (status[0] == GL_FALSE) {
            String errorMessage = glGetShaderInfoLog(shader);
            glDeleteShader(shader);
            throw new RuntimeException(errorMessage);
        }

        return shader;
    }

    public static int initProgram(String vertexShaderCode, String fragmentShaderCode) {
        int vertexShader = initShader(vertexShaderCode, GL_VERTEX_SHADER);
        int fragmentShader = initShader(fragmentShaderCode, GL_FRAGMENT_SHADER);

        int program = glCreateProgram();
        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);

        glLinkProgram(program);

        glGetProgramiv(program, GL_LINK_STATUS, status);
        if (status[0] == GL_FALSE) {
            String errorMessage = glGetProgramInfoLog(program);
            glDeleteProgram(program);
            throw new RuntimeException(errorMessage);
        }

        return program;
    }

    public static void checkVersion() {
        System.out.println("Vendor: " + glGetString(GL_VENDOR));
        System.out.println("Renderer: " + glGetString(GL_RENDERER));
        System.out.println("OpenGL version supported: " + glGetString(GL_VERSION));
    }
}
