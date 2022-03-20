package io.itch.godlikeTerminal.util;

import io.itch.godlikeTerminal.map.MapCreator;

public class FMath {

    public static float clamp(float x, float min, float max) {

        return Math.max(Math.min(max, x), min);

    }

}
