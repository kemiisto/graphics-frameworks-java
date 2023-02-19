import static org.lwjgl.glfw.GLFW.*;
import graphics.core.*;

public class Test_2_10 extends Base {
    public void initialize() {
        System.out.println("Initializing...");
    }

    public void update() {
        if (input.isKeyDown(GLFW_KEY_SPACE)) {
            System.out.println("space key");
        }
        if (input.isKeyPressed(GLFW_KEY_RIGHT)) {
            System.out.println("right arrow key");
        }
        if (input.isKeyUp(GLFW_KEY_A)) {
            System.out.println("letter ’A’ key");
        }
    }

    public static void main(String[] args) {
        new Test_2_10().run();
    }
}
