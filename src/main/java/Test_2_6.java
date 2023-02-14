import graphics.core.Attribute;
import graphics.core.Base;
import graphics.core.OpenGLUtils;
import graphics.core.Uniform;
import graphics.math.Vector;

import static org.lwjgl.opengl.GL30.*;

public class Test_2_6 extends Base {
    public int program;
    public int vao;
    public Uniform<Vector> translation1, translation2, baseColor1, baseColor2;

    @Override
    public void initialize() {
        program = OpenGLUtils.initFromFiles("Test_2_6.vert", "Test_2_6.frag");

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        float[] positionData = {
             0.0f,  0.2f,  0.0f,
             0.2f, -0.2f,  0.0f,
            -0.2f, -0.2f,  0.0f
        };
        Attribute positionAttributeTri = new Attribute("vec3", positionData);
        positionAttributeTri.associateVariable(program, "position");

        translation1 = new Uniform<>("vec3", new Vector(-0.5f, 0.0f, 0.0f));
        translation1.locateVariable(program, "translation");

        translation2 = new Uniform<>("vec3", new Vector(0.5f, 0.0f, 0.0f) );
        translation2.locateVariable(program, "translation");

        baseColor1 = new Uniform<>("vec3", new Vector(1.0f, 0.0f, 0.0f) );
        baseColor1.locateVariable(program, "baseColor");

        baseColor2 = new Uniform<>("vec3", new Vector(0.0f, 0.0f, 1.0f) );
        baseColor2.locateVariable(program, "baseColor");
    }

    @Override
    public void update() {
        glUseProgram(program);
        glBindVertexArray(vao);

        // draw the first triangle
        translation1.uploadData();
        baseColor1.uploadData();
        glDrawArrays(GL_TRIANGLES, 0, 3);

        // draw the second triangle
        translation2.uploadData();
        baseColor2.uploadData();
        glDrawArrays(GL_TRIANGLES, 0, 3);
    }

    public static void main(String[] args) {
        new Test_2_6().run();
    }
}
