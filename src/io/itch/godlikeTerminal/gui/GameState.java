package io.itch.godlikeTerminal.gui;

import io.itch.godlikeTerminal.Display;
import io.itch.godlikeTerminal.entity.EntityClientPlayer;
import io.itch.godlikeTerminal.entity.components.EntityPlayer;
import io.itch.godlikeTerminal.gfx.*;
import io.itch.godlikeTerminal.gui.gameUI.HUD;
import io.itch.godlikeTerminal.input.InputHandler;
import io.itch.godlikeTerminal.networking.ClientPlayer;

public class GameState extends Screen {

    public HUD hud;

    public InputHandler key;
    public EntityPlayer te;

    public GameState(Display display, int width, int height) {

        super(width, height, 256, 256);

        hud = new HUD(this);

        key = new InputHandler(display);

        new ClientPlayer(9090, new EntityClientPlayer(this, display));

    }

    Camera camera = new Camera(this);

    public void tick() {

        gameTick(camera);
        ClientPlayer.player.tick();
        hud.tick();

    }

    public void render() {

        super.gameRender();
        ClientPlayer.player.render();
        hud.render();

    }

}
