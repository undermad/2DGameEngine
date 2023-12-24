package org.ectimel;

public class LevelScene extends Scene{

    public LevelScene() {
        System.out.println("Inside level scene");

        Window.getInstance().getRgba().setR(1);
        Window.getInstance().getRgba().setG(1);
        Window.getInstance().getRgba().setB(1);
        Window.getInstance().getRgba().setA(1);
        Window.getInstance().changeScene(0);
    }

    @Override
    public void update(float deltaTime) {

    }
}
