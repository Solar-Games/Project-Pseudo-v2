package io.itch.godlikeTerminal.networking;

import io.itch.godlikeTerminal.entity.EntityClientPlayer;
import io.itch.godlikeTerminal.networking.packets.PacketIO;

public class ClientPlayer extends Client {

    public static EntityClientPlayer player;

    public ClientPlayer(int port, EntityClientPlayer player) {

        super(port);

        ClientPlayer.player = player;

    }

    public void update() {

        if(player.packetSent) {

            player.cPacket.action();
            player.packetSent = false;
            PacketIO.packetSent = false;

        }

    }

}