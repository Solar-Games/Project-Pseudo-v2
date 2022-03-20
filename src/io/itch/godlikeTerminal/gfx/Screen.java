package io.itch.godlikeTerminal.gfx;

import io.itch.godlikeTerminal.Display;
import io.itch.godlikeTerminal.gui.ScreenType;

import java.awt.*;
import java.util.Random;

public abstract class Screen extends Render {

    public static ScreenType screenType;

    public int xOffset = 0, yOffset = 0;
    public int MAP_WIDTH, MAP_HEIGHT;
    public int clampX, clampY;
    public boolean minClampedX, minClampedY, maxClampedX, maxClampedY;

    public Screen(int width, int height, int MAP_WIDTH, int MAP_HEIGHT) {

        super(width, height);

        this.MAP_WIDTH = MAP_WIDTH;
        this.MAP_HEIGHT = MAP_HEIGHT;
        clampX = (MAP_WIDTH * 32) - Display.WIDTH - 24;
        clampY = (MAP_HEIGHT * 32) - Display.HEIGHT - 48 + 28 + 27;

    }

    public Screen(int width, int height) {

        super(width, height);

    }

    public abstract void tick();

    protected void gameTick(Camera camera) {

        camera.tick();
        //camera.velX = 4;
        //camera.velY = 4;

    }

    public abstract void render();

    public Tile COAL = new Tile(Tile.TileID.COAL);
    public Tile DYNAMIUM = new Tile(Tile.TileID.DYNAMIUM);
    public Tile GRASS = new Tile(Tile.TileID.GRASS);
    public Tile IRON = new Tile(Tile.TileID.IRON);
    public Tile RUBY = new Tile(Tile.TileID.RUBY);
    public Tile SAND = new Tile(Tile.TileID.SAND);
    public Tile STONE_G = new Tile(Tile.TileID.STONE_G);
    public Tile STONE_O = new Tile(Tile.TileID.STONE_O);
    public Tile TREE = new Tile(Tile.TileID.TREE);
    public Tile WATER = new Tile(Tile.TileID.WATER);

    protected void gameRender() {

        if(xOffset < 0) {

            xOffset = 0;
            minClampedX = true;
            maxClampedX = false;

        } else if(xOffset > clampX) {

            xOffset = clampX;
            maxClampedX = true;
            minClampedX = false;

        } else if(yOffset < 0) {

            yOffset = 0;
            minClampedY = true;
            maxClampedY = false;

        } else if(yOffset > clampY) {

            yOffset = clampY;
            maxClampedY = true;
            minClampedY = false;

        } else {

            for (int i = 0; i < width*height; i++) {

                pixels[i] = 0;

            }

            for (int x = 0; x < MAP_WIDTH; x++) {

                for (int y = 0; y < MAP_HEIGHT; y++) {

                    if(Display.map.groundPix[x + y * MAP_WIDTH] == 1)
                        draw(WATER, (x * 32) - xOffset, (y * 32) - yOffset);

                    if(Display.map.groundPix[x + y * MAP_WIDTH] == 2)
                        draw(GRASS, (x * 32) - xOffset, (y * 32) - yOffset);

                    if(Display.map.groundPix[x + y * MAP_WIDTH] == 3)
                        draw(SAND, (x * 32) - xOffset, (y * 32) - yOffset);

                    if(Display.map.resourcePix[x + y * MAP_WIDTH] == 1)
                        draw(TREE, (x * 32) - xOffset, (y * 32) - yOffset);

                    if(Display.map.resourcePix[x + y * MAP_WIDTH] == 2)
                        draw(STONE_O, (x * 32) - xOffset, (y * 32) - yOffset);

                    if(Display.map.resourcePix[x + y * MAP_WIDTH] == 3)
                        draw(COAL, (x * 32) - xOffset, (y * 32) - yOffset);

                }

            }

        }

    }

    public static void setScreenType(ScreenType screenType) {

        Screen.screenType = screenType;

    }

    public static ScreenType getScreenType(){

        return Screen.screenType;

    }

}
