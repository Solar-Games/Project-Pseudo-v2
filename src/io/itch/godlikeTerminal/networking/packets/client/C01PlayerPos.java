package io.itch.godlikeTerminal.networking.packets.client;

import io.itch.godlikeTerminal.networking.ClientPlayer;
import io.itch.godlikeTerminal.networking.packets.ClientPacket;

public class C01PlayerPos extends ClientPacket {

    public int x, y;

    public C01PlayerPos(int x, int y) {

        super("C01PlayerPos");

        this.x = x;
        this.y = y;

    }

    public void action() {

        ClientPlayer.player.x = x;
        ClientPlayer.player.y = y;

    }

}