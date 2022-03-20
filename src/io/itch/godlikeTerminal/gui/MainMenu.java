package io.itch.godlikeTerminal.gui;

import io.itch.godlikeTerminal.Display;
import io.itch.godlikeTerminal.gfx.Font;
import io.itch.godlikeTerminal.gfx.Screen;
import io.itch.godlikeTerminal.gfx.SpriteSheet;
import io.itch.godlikeTerminal.gfx.TexturedRender;
import io.itch.godlikeTerminal.input.InputHandler;
import io.itch.godlikeTerminal.util.FMath;
import io.itch.godlikeTerminal.util.Timer;

public class MainMenu extends Screen {

    public int selected = 1;
    public InputHandler key;
    public TexturedRender[] buttons;
    public double y;

    Timer timer = new Timer();

    public MainMenu(Display display, int width, int height) {

        super(width, height);

        buttons = new TexturedRender[] {

                new TexturedRender(new SpriteSheet("buttons.png").setSheetPosition(0, selected - 1 == 0? 64 : 0, 128, 64), 128, 64),
                new TexturedRender(new SpriteSheet("buttons.png").setSheetPosition(0, selected - 1 == 1? 64 : 0, 128, 64), 128, 64),
                new TexturedRender(new SpriteSheet("buttons.png").setSheetPosition(0, selected - 1 == 2? 64 : 0, 128, 64), 128, 64)

        };

        key = new InputHandler(display);

    }

    public void tick() {

        if(key.down.getPressed() && timer.hasTimeElasped(1000/10, true))
            changeButton(true);

        if(key.up.getPressed() && timer.hasTimeElasped(1000/10, true))
            changeButton(false);

        if(key.select.getPressed())
            select();

    }

    public void render() {

        int count = 0;

        for (TexturedRender button : buttons) {

            draw(button, (width - 128) / 2, (int) (((height - (256 - 128) + ((128 + 64) * count)) / 2 )+ (y * 10)));

            count++;

        }

    }

    public void changeButton(boolean reversed) {

        if (reversed) {

            selected++;

        }else {

            selected--;

        }

        selected = (int) FMath.clamp(selected, 1, 3);

        for (int i = 0; i < 3; i++) {

            buttons[i] = new TexturedRender(new SpriteSheet("buttons.png").setSheetPosition(0, (selected - 1 == i? 64 : 0), 128, 64), 128, 64);

        }

    }

    public void select() {

        if (selected - 1 == 0)
            setScreenType(ScreenType.GAME);

        if (selected - 1 == 2)
            System.exit(-1);

    }

}