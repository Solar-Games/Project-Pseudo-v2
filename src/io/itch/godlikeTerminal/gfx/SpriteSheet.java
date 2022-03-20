package io.itch.godlikeTerminal.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class SpriteSheet {

    public final BufferedImage image;
    public int width, height;
    public int[] pixels;

    public SpriteSheet(String path){

        BufferedImage image = null;

        try{

            image = ImageIO.read(Objects.requireNonNull(SpriteSheet.class.getResource("/textures/" + path)));

        }catch (IOException e){
            e.printStackTrace();
        }

        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.pixels = image.getRGB(0, 0, width, height, null, 0, width);

    }

    public BufferedImage setSheetPosition(int xPos, int yPos, int width, int height) {

        this.width = width;
        this.height = height;

        this.pixels = image.getRGB(xPos, yPos, width, height, null, 0, width);

        return image.getSubimage(xPos, yPos, width, height);

    }

}
