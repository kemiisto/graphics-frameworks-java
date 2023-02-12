import graphics.core.Attribute;
import graphics.core.Base;
import graphics.core.OpenGLUtils;

import static org.lwjgl.opengl.GL30.*;

public class Test_2_3 extends Base {
    public int program;

    @Override
    public void initialize() {
        program = OpenGLUtils.initFromFiles("Test_2_3.vert", "Test_2_3.frag");

        glLineWidth(4);

        int vao = glGenVertexArrays();
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
    }

    @Override
    public void update() {
        glUseProgram(program);
        glDrawArrays(GL_LINE_LOOP, 0, 6);
    }

    public static void main(String[] args) {
        new Test_2_3().run();
    }
}
