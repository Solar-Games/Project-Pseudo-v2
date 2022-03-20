package io.itch.godlikeTerminal.gfx;

import java.util.*;
import java.awt.image.*;

public class Sprite extends TexturedRender {

    public Sprite(BufferedImage image) {

            super(image, 32, 32);

            pixels = image.getRGB(image.getMinX(), image.getMinY(), image.getWidth(), image.getHeight(), null, 0, image.getWidth());

    }

}