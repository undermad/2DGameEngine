package org.ectimel;

public abstract class Scene {

    protected  Camera camera;

    public Scene() {

    }

    public abstract void update(float deltaTime);

    public void init() {

    }


}
