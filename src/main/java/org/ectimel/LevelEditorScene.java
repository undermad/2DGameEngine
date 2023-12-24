package org.ectimel;

import java.awt.event.KeyEvent;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene() {

        System.out.println("Inside level editor scene");
        System.out.println(changingScene);
        System.out.println(timeToChangeScene);
    }

    @Override
    public void update(float deltaTime) {


        if (!changingScene && KeyListener.isKeyPressed(GLFW_KEY_SPACE)) {
            changingScene = true;
            System.out.println("Here");
        } else if (changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= deltaTime;
            Window.getInstance().getRgba().fadeToBlack(deltaTime * 5.0f, deltaTime * 5.0f, deltaTime * 5.0f, deltaTime * 5.0f);
        } else if(changingScene){
            Window.getInstance().changeScene(1);
        }
    }
}
