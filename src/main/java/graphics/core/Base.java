package graphics.core;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

public abstract class Base {
    private int windowWidth;
    private int windowHeight;
    private long window;
    private boolean running;

    public Base() {}

    public void startup() {
        boolean initSuccess = glfwInit();
        if (!initSuccess) {
            throw new RuntimeException("Unable to initialize GLFW!");
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(windowWidth, windowHeight, "Graphics Window", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window!");
        }

        running = true;

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();

        OpenGLUtils.checkVersion();
        System.out.println("LWJGL version: " + Version.getVersion());
    }

    public abstract void initialize();

    public abstract void update();

    public void run(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        startup();
        // application-specific startup code
        initialize();

        while (running) {
            glfwPollEvents();
            if (glfwWindowShouldClose(window)) {
                running = false;
            }
            // application-specific update code
            update();
            glfwSwapBuffers(window);
        }

        shutdown();
    }

    public void run() {
        run(1024, 1024);
    }

    public void shutdown() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
