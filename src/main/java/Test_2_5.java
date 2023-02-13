import graphics.core.Attribute;
import graphics.core.Base;
import graphics.core.OpenGLUtils;

import static org.lwjgl.opengl.GL30.*;

public class Test_2_5 extends Base {
    public int program, vao;

    @Override
    public void initialize() {
        program = OpenGLUtils.initFromFiles("Test_2_5.vert", "Test_2_5.frag");

        glPointSize(10);
        glLineWidth(4);

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        float[] positionData = {
             0.8f,  0.0f,  0.0f,
             0.4f,  0.6f,  0.0f,
            -0.4f,  0.6f,  0.0f,
            -0.8f,  0.0f,  0.0f,
            -0.4f, -0.6f,  0.0f,
             0.4f, -0.6f,  0.0f
        };
        Attribute positionAttribute = new Attribute("vec3", positionData);
        positionAttribute.associateVariable(program, "position");

        float[] colorData = {
            1.0f, 0.0f, 0.0f,
            1.0f, 0.5f, 0.0f,
            1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            0.5f, 0.0f, 1.0f
        };
        Attribute colorAttribute = new Attribute("vec3", colorData);
        colorAttribute.associateVariable(program, "vertexColor");
    }

    @Override
    public void update() {
        glUseProgram(program);
        glBindVertexArray(vao);
        // glDrawArrays(GL_POINTS, 0, 6);
        // glDrawArrays(GL_LINE_LOOP, 0, 6);
        glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
    }

    public static void main(String[] args) {
        new Test_2_5().run();
    }
}
