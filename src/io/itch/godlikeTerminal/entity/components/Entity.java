package io.itch.godlikeTerminal.entity.components;

import io.itch.godlikeTerminal.gfx.*;
import io.itch.godlikeTerminal.util.Timer;
import io.itch.godlikeTerminal.util.Vector2F;

public class Entity {

    public float health = 5;
    public int x = 0, y = 0;
    public double actualPosX = 0, actualPosY = 0;
    public Vector2F velo;

    public Screen screen;
    public Sprite[] sprite;
    public Sprite selectedSprite;
    public EntityDirection dir;

    public Entity(Screen screen, Sprite... sprite) {

        this.sprite = sprite;
        this.screen = screen;

        velo = new Vector2F(0, 0);
        this.selectedSprite = sprite[0];

        whenEntitySpawns();

    }

    public void tick() {

        if(getDirection() != EntityDirection.NONE) {

            if (isEntityMovingHorizontally())
                x += velo.x;

            if (isEntityMovingVertically())
                y += velo.x;

        }

    }

    public void render() {

        screen.draw(selectedSprite, x, y);

    }

    public void action() {



    }

    Timer timer = new Timer();
    public int spriteIndex = 0;

    /**
    *
    *    Animate: Animates the entity's sprite.
    *
    *   WARNING: If the frames are inconsistent for the animationLength var, then make a new version animate function.
    *
    */

    protected void animate(long time, Sprite[] down, Sprite[] left, Sprite[] right, Sprite[] up, int animationLength) {

        if(timer.hasTimeElasped(time, true) && isMoving()) {

            spriteIndex++;

            if(spriteIndex > animationLength - 1)
                spriteIndex = 0;

        }

        if(dir == EntityDirection.DOWN)
            selectedSprite = down[spriteIndex];

        if(dir == EntityDirection.LEFT)
            selectedSprite = left[spriteIndex];

        if(dir == EntityDirection.RIGHT)
            selectedSprite = right[spriteIndex];

        if(dir == EntityDirection.UP)
            selectedSprite = up[spriteIndex];

    }

    protected boolean isMoving() {

        if(dir == EntityDirection.NONE || dir == null)
            return false;

        return true;

    }

    protected boolean isEntityMovingHorizontally() {

        return (dir == EntityDirection.LEFT || dir == EntityDirection.RIGHT);

    }

    protected boolean isEntityMovingVertically() {

        return (dir == EntityDirection.UP || dir == EntityDirection.DOWN);

    }

    protected void whenEntitySpawns() {



    }

    public void setDirection(EntityDirection directionBecauseYes) {

        dir = directionBecauseYes;

    }

    public EntityDirection getDirection() {

        return dir;

    }

    public enum EntityDirection {

        NONE(0),
        DOWN(4),
        LEFT(-4),
        RIGHT(4),
        UP(-4);

        public int speed;

        EntityDirection(int speed) {

            this.speed = speed;

        }

    }

}
