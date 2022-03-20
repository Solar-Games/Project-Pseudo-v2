package io.itch.godlikeTerminal.entity.components;

import static io.itch.godlikeTerminal.entity.components.Entity.EntityDirection.DOWN;
import static io.itch.godlikeTerminal.entity.components.Entity.EntityDirection.NONE;

import io.itch.godlikeTerminal.util.FMath;
import io.itch.godlikeTerminal.util.Timer;
import io.itch.godlikeTerminal.Display;
import io.itch.godlikeTerminal.entity.components.Entity;
import io.itch.godlikeTerminal.gfx.*;
import io.itch.godlikeTerminal.input.InputHandler;
import io.itch.godlikeTerminal.util.Vector2F;

public class EntityPlayer extends Entity {

    public InputHandler keyIO;

    public EntityPlayer(Screen screen, Display display) {

        super(screen, new Sprite(new SpriteSheet("player.png").setSheetPosition(0, 0,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(32, 0,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(64, 0,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(96, 0,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(0, 32,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(32, 32,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(64, 32,32,32)),
                new Sprite(new SpriteSheet("player.png").setSheetPosition(96, 32,32,32)));

        setDirection(NONE);

        keyIO = new InputHandler(display);

    }

    public void tick() {

        animate(1000/6);

        super.tick();

        if(keyIO.down.getPressed()) {

            setDirection(EntityDirection.DOWN);

        }

        else if(keyIO.up.getPressed()) {

            setDirection(EntityDirection.UP);

        }

        else if(keyIO.left.getPressed()) {

            setDirection(EntityDirection.LEFT);

        }

        else if(keyIO.right.getPressed()) {

            setDirection(EntityDirection.RIGHT);

        }

        else
            setDirection(NONE);

        velo = new Vector2F(dir.speed, dir.speed);

//        y = (int) FMath.clamp(y, 0, screen.height - 71);
//        x = (int) FMath.clamp(x, 0, screen.width - 46);

        x = (int) FMath.clamp(x, 0, (screen.width - 32) / 2.0f);

        y = (int) FMath.clamp(y, 0, (screen.height - 32) / 2.0f);

    }

    public void render() {

        super.render();

    }

    Sprite[] down = { sprite[0], sprite[1] };
    Sprite[] left = { sprite[6], sprite[7] };
    Sprite[] right = { sprite[4], sprite[5] };
    Sprite[] up = { sprite[2], sprite[3] };

    protected void animate(long time) {

        super.animate(time, down, left, right, up,2);

    }

}