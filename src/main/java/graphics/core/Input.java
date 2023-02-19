package graphics.core;

import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;

public class Input {
    public boolean quit;

    public ArrayList<Integer> keyPressQueue;
    public ArrayList<Integer> keyReleaseQueue;

    // store names of keys corresponding to the discrete press and release events
    public ArrayList<Integer> keyDownList;
    public ArrayList<Integer> keyUpList;
    // stores names of keys in the continuous state between these discrete events
    public ArrayList<Integer> keyPressedList;


    public Input(long window) {
        quit = false;

        keyPressQueue = new ArrayList<Integer>();
        keyReleaseQueue = new ArrayList<Integer>();

        keyDownList = new ArrayList<Integer>();
        keyPressedList = new ArrayList<Integer>();
        keyUpList = new ArrayList<Integer>();

        glfwSetKeyCallback(window, (window_, key, scancode, action, mods) -> {
            if (action == GLFW_PRESS) {
                keyPressQueue.add(key);
            }
            if (action == GLFW_RELEASE) {
                keyReleaseQueue.add(key);
            }
        });
    }

    public void update() {
        keyDownList.clear();
        keyUpList.clear();

        for (Integer key : keyPressQueue) {
            keyDownList.add(key);
            keyPressedList.add(key);
        }

        for (Integer key : keyReleaseQueue) {
            keyUpList.add(key);
            keyPressedList.remove(key);
        }

        keyPressQueue.clear();
        keyReleaseQueue.clear();
    }

    public boolean isKeyDown(Integer key) {
        return keyDownList.contains(key);
    }

    public boolean isKeyPressed(Integer key) {
        return keyPressedList.contains(key);
    }

    public boolean isKeyUp(Integer key) {
        return keyUpList.contains(key);
    }
}
