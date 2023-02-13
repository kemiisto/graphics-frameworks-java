import graphics.core.Attribute;
import graphics.core.Base;
import graphics.core.OpenGLUtils;

import static org.lwjgl.opengl.GL30.*;

public class Test_2_4 extends Base {
    public int program;
    public int vaoTri, vaoSquare;

    @Override
    public void initialize() {
        program = OpenGLUtils.initFromFiles("Test_2_3.vert", "Test_2_3.frag");

        glLineWidth(4);

        vaoTri = glGenVertexArrays();
        glBindVertexArray(vaoTri);

        float[] positionDataTri = {
            -0.5f, 0.8f, 0.0f,
            -0.2f, 0.2f, 0.0f,
            -0.8f, 0.2f, 0.0f
        };
        Attribute positionAttributeTri = new Attribute("vec3", positionDataTri);
        positionAttributeTri.associateVariable(program, "position");

        vaoSquare = glGenVertexArrays();
        glBindVertexArray(vaoSquare);

        float[] positionDataSquare = {
            0.8f, 0.8f, 0.0f,
            0.8f, 0.2f, 0.0f,
            0.2f, 0.2f, 0.0f,
            0.2f, 0.8f, 0.0f
        };
        Attribute positionAttributeSquare = new Attribute("vec3", positionDataSquare);
        positionAttributeSquare.associateVariable(program, "position");
    }

    @Override
    public void update() {
        glUseProgram(program);

        glBindVertexArray(vaoTri);
        glDrawArrays(GL_LINE_LOOP, 0, 3);

        glBindVertexArray(vaoSquare);
        glDrawArrays(GL_LINE_LOOP, 0, 4);
    }

    public static void main(String[] args) {
        new Test_2_4().run();
    }
}
