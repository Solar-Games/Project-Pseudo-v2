package io.itch.godlikeTerminal.util;

public class Timer {

    private long lastTime = System.currentTimeMillis();

    private void reset() {

        lastTime = System.currentTimeMillis();

    }

    public boolean hasTimeElasped(long time, boolean reset) {

        if(System.currentTimeMillis() - lastTime >= time) {

            if(reset)
                reset();

            return true;

        }

        return false;

    }

}
