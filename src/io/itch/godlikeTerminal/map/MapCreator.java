package io.itch.godlikeTerminal.map;

import io.itch.godlikeTerminal.gfx.Render;
import io.itch.godlikeTerminal.gfx.TexturedRender;
import io.itch.godlikeTerminal.gfx.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MapCreator {

    public int[] groundPix;
    public int[] resourcePix;
    public TexturedRender[] tR = new TexturedRender[2];

    public MapCreator(int width, int height) throws IOException {

        Random random = new Random();

        BufferedImage map1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage map2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        groundPix = new int[width * height];
        resourcePix = new int[width * height];

        for(int y = 0; y < height; y++) {

            for (int x = 0; x < width; x++) {

                float ground = random.nextFloat();
                int groundColour = 0x00000000;

                if (ground <= 0.625f) {

                    groundColour = Tile.TileID.WATER.color;
                    groundPix[y + x * width] = 1;


                }else if (ground <= 0.95f){

                    groundColour = Tile.TileID.GRASS.color;
                    groundPix[y + x * width] = 2;

                }else if (ground <= 1.25f) {

                    groundColour = Tile.TileID.SAND.color;
                    groundPix[y + x * width] = 3;

                }

                map1.setRGB(x, y, groundColour);

                boolean shouldMakeResTile = random.nextBoolean();
                int fgTile = random.nextInt(6) + 1;
                int resourceColour = 0x00000000;

                if (groundPix[y + x * width] != 1) {

                    if(shouldMakeResTile) {

                        if (fgTile <= 3) {

                            resourceColour = Tile.TileID.TREE.color;
                            resourcePix[y + x * width] = 1;

                        } else if (fgTile <= 5) {

                            resourceColour = Tile.TileID.STONE_O.color;
                            resourcePix[y + x * width] = 2;

                        } else if (fgTile <= 7) {

                            resourceColour = Tile.TileID.COAL.color;
                            resourcePix[y + x * width] = 3;

                        }

                    }

                }

                map2.setRGB(x, y, resourceColour);

            }

        }

        tR[0] = new TexturedRender(map1, width, height);
        tR[1] = new TexturedRender(map2, width, height);

        File mapImg1 = new File("res/textures/map1.png");
        ImageIO.write(map1, "png", mapImg1);
        File mapImg2 = new File("res/textures/map2.png");
        ImageIO.write(map2, "png", mapImg2);

    }

}