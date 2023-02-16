import graphics.core.Attribute;
import graphics.core.Base;
import graphics.core.OpenGLUtils;
import graphics.core.Uniform;
import graphics.math.Vector;

import static org.lwjgl.opengl.GL30.*;

public class Test_2_8 extends Base {
    public int program;
    public int vao;
    public Uniform<Vector> translation, baseColor;

    @Override
    public void initialize() {
        program = OpenGLUtils.initFromFiles("Test_2_6.vert", "Test_2_6.frag");

        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        float[] positionData = {
             0.0f,  0.2f,  0.0f,
             0.2f, -0.2f,  0.0f,
            -0.2f, -0.2f,  0.0f
        };
        Attribute positionAttributeTri = new Attribute("vec3", positionData);
        positionAttributeTri.associateVariable(program, "position");

        translation = new Uniform<>("vec3", new Vector(-0.5f, 0.0f, 0.0f));
        translation.locateVariable(program, "translation");

        baseColor = new Uniform<>("vec3", new Vector(1.0f, 0.0f, 0.0f) );
        baseColor.locateVariable(program, "baseColor");
    }

    @Override
    public void update() {
        translation.data.values[0] = (float)(0.75 * Math.cos(time));
        translation.data.values[1] = (float)(0.75 * Math.sin(time));

        glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(program);
        glBindVertexArray(vao);

        translation.uploadData();
        baseColor.uploadData();
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    public static void main(String[] args) {
        new Test_2_8().run();
    }
}
