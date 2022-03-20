package io.itch.godlikeTerminal.gfx;

public class Camera {

    public int camX = 0, camY = 0;
    public double velX = 0, velY = 0;
    public Screen screen;

    public Camera(Screen screen) {

        this.screen = screen;

    }

    public void tick() {

        if (!(screen.maxClampedX || screen.minClampedX)) {

            camX += velX;

            screen.xOffset = camX;

        }

        if (!(screen.maxClampedY || screen.minClampedY)) {

            camY += velY;

            screen.yOffset = camY;

        }

    }

}
