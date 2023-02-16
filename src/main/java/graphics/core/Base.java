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

    // number of seconds application has been running
    public float time;
    // seconds since last iteration of run loop
    public float deltaTime;

    // store time data from last iteration of run loop
    private long previousTime;
    private long currentTime;

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
        time = 0;
        deltaTime = 1/60.0f;
        currentTime = System.currentTimeMillis();
        previousTime = System.currentTimeMillis();

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

            // recalculate time variables
            currentTime = System.currentTimeMillis();
            deltaTime = (currentTime - previousTime) / 1000.0f;
            time += deltaTime;
            previousTime = currentTime;

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
