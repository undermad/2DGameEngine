package org.ectimel;

public class RGBA {
    private float r;
    private float g;
    private float b;
    private float a;

    public RGBA() {
        this.r = 1f;
        this.g = 1f;
        this.b = 1f;
        this.a = 1.0f;
    }
    public void fadeToBlack(float r, float g, float b, float a){
        this.r -= r;
        this.g -= g;
        this.b -= b;
        this.a -= a;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }
}
