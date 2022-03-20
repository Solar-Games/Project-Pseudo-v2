package io.itch.godlikeTerminal.util;

public class Vector2F {

    public float x = 0, y = 0;

    public Vector2F(float x, float y) {

        this.x = x;
        this.y = y;

    }

    public Vector2F(Vector2F vec2F) {

        x = vec2F.x;
        y = vec2F.y;

    }

}