import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import graphics.core.*;
import graphics.math.*;

public class Test_3_1 extends Base {
    public void initialize() {
        System.out.println("Testing matrix class:");

        Matrix m = new Matrix(2,3);
        m.setValues(1,2,3,4,5,6);
        System.out.println(m);

        Matrix n = m.transpose();
        System.out.println(n);

        System.out.println(Matrix.multiply(m, n));
        System.out.println(Matrix.multiply(n, m));

        System.out.println("Testing inverse method:");
        Matrix p = new Matrix(2,2);
        p.setValues(1,2,3,4);
        Matrix pInv = p.inverse();
        System.out.println(p);
        System.out.println(pInv);
        System.out.println("Product should be identity matrix:");
        System.out.println( Matrix.multiply(p, pInv) );
        System.out.println( Matrix.multiply(pInv, p) );
    }

    public void update() {}

    public static void main(String[] args) {
        new Test_3_1().run();
    }
}
