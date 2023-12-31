package org.ectimel.utils;

public class TimeConverter {
    public static final float TIME_STARTED = System.nanoTime();

    public static float getRunningTime() {
        return (float) ((System.nanoTime() - TIME_STARTED) * 1E-9);
    }
}
