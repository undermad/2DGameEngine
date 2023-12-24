package org.ectimel;

public class MouseListener {

    private static MouseListener mouseListener;

    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastY = 0;
        this.lastX = 0;
    }


    public static MouseListener getInstance() {
        if (mouseListener == null)
            mouseListener = new MouseListener();
        return mouseListener;
    }

}
