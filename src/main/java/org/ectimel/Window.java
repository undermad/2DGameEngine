package org.ectimel;

import org.ectimel.utils.TimeConverter;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width, height;
    private String title;
    private static Window window;
    private long glfwWindow;
    private Scene currentScene;

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Experimental Game Engine";
    }

    public void changeScene(int scene) {
        switch (scene) {
            case 0:
                currentScene = new LevelEditorScene();
                currentScene.init();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                break;
            default:
                assert false : "Unknown scene '" + scene + "'";
        }

    }


    public void loop() {
        float startTime = TimeConverter.getRunningTime();
        float endTime = TimeConverter.getRunningTime();
        float deltaTime = -1f;

        while (!glfwWindowShouldClose(glfwWindow)) {

            // poll events
            glfwPollEvents();

            glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            if (deltaTime != 0) {
                currentScene.update(deltaTime);
            }

            glfwSwapBuffers(glfwWindow);



            endTime = TimeConverter.getRunningTime();
            deltaTime = endTime - startTime;
            float framePerSecond = 1 / deltaTime;
//            System.out.println(framePerSecond);
            startTime = endTime;
        }

    }

    public void run() {
        System.out.println("Hello Experimental Game Engine");
        System.out.println("LWJGL version: " + Version.getVersion());
        init();
        loop();

        // free the memory
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // terminate glfw and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        // error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the window.");
        }

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        // OpenGL context current
        glfwMakeContextCurrent(glfwWindow);

        // enable v-sync
        glfwSwapInterval(1);

        // make the window visible
        glfwShowWindow(glfwWindow);

        // critical part
        GL.createCapabilities();
        changeScene(0);

    }

    public static Window getInstance() {
        if (Window.window == null) window = new Window();
        return window;
    }

}
