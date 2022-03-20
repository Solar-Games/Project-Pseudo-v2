package io.itch.godlikeTerminal.gfx;

import java.awt.image.BufferedImage;

public class Tile extends Render{
    public static SpriteSheet tilesheet = new SpriteSheet("tiles.png");

    public Tile(TileID tileID) {

        super(tileID.width, tileID.height);

        BufferedImage texture = tileID.texture;

        pixels = texture.getRGB(texture.getMinX(), texture.getMinY(), texture.getWidth(), texture.getHeight(), null,0,texture.getWidth());

    }

    public enum TileID {

        COAL(tilesheet.setSheetPosition(32,64, 32, 32), 32, 32, 0xff262424),
        DYNAMIUM(tilesheet.setSheetPosition(0, 96, 32, 32), 32, 32, 0xff00c9d9),
        GRASS(tilesheet.setSheetPosition(0, 0, 32, 32), 32, 32, 0xff96f035),
        IRON(tilesheet.setSheetPosition(64, 64, 32, 32), 32, 32, 0),
        RUBY(tilesheet.setSheetPosition(96, 64, 32, 32), 32, 32, 0),
        SAND(tilesheet.setSheetPosition(32, 32, 32, 32), 32, 32, 0xfffff177),
        STONE_G(tilesheet.setSheetPosition(32, 0, 32, 32), 32, 32, 0xfffff1AA),
        STONE_O(tilesheet.setSheetPosition(0, 64, 32, 32), 32, 32, 0xff4c4c4c),
        TREE(tilesheet.setSheetPosition(64, 0, 32, 32), 32, 32, 0xff7ed969),
        WATER(tilesheet.setSheetPosition(0, 32, 32, 32), 32, 32, 0xff429fd8);

        public final BufferedImage texture;
        public final int width, height, color;

        TileID(BufferedImage texture, int width, int height, int color) {

            this.texture = texture;
            this.width = width;
            this.height = height;
            this.color = color;

        }

    }

}
