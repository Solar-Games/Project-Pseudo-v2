package io.itch.godlikeTerminal.gfx;

import java.awt.image.BufferedImage;

public class Font {

    private static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890.!?             ";

    public static void render(String text, Screen screen, int x, int y) {

        BufferedImage[] images = new BufferedImage[text.length()];

        Render[] renders = new Render[text.length()];

        for(int i = 0; i < text.length(); i++) {

            int charIndex = chars.indexOf(text.charAt(i));

            System.out.println(charIndex);

            if (charIndex >= 0) {

                images[i] = new SpriteSheet("font.png").setSheetPosition((charIndex > 26 ? charIndex - 26 : charIndex), (charIndex > 26 ? 16 : 0), 16, 16);
                renders[i] = new Render(16, 16);

            }

        }

        for(int j = 0; j < renders.length; j++) {

            images[j].getRGB(images[j].getMinX(), images[j].getMinY(), images[j].getWidth(), images[j].getHeight(), renders[j].pixels, 0, images[j].getWidth());

            screen.draw(renders[j], x + (j * 16), y);

        }

    }

}
