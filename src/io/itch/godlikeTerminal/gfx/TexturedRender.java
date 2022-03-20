package io.itch.godlikeTerminal.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class TexturedRender extends Render{

    public final BufferedImage image;

    public TexturedRender(BufferedImage sheetTile, int width, int height) {

        super(width, height);

        this.image = sheetTile;

        sheetTile.getRGB(sheetTile.getMinX(), sheetTile.getMinY(), sheetTile.getWidth(), sheetTile.getHeight(), pixels, 0, sheetTile.getWidth());

    }

}
