import graphics.core.Base;
import graphics.core.OpenGLUtils;

import static org.lwjgl.opengl.GL30.*;

public class Test_2_2 extends Base {
    public int program;

    @Override
    public void initialize() {
        program = OpenGLUtils.initFromFiles("Test_2_2.vert", "Test_2_2.frag");

        int vao = glGenVertexArrays();
        glBindVertexArray(vao);

        glPointSize(10);
    }

    @Override
    public void update() {
        glUseProgram(program);
        glDrawArrays(GL_POINTS, 0, 1);
    }

    public static void main(String[] args) {
        new Test_2_2().run();
    }
}
