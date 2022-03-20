package io.itch.godlikeTerminal.entity;

import io.itch.godlikeTerminal.Display;
import io.itch.godlikeTerminal.entity.components.EntityPlayer;
import io.itch.godlikeTerminal.gfx.Screen;
import io.itch.godlikeTerminal.networking.packets.Packet;
import io.itch.godlikeTerminal.networking.packets.PacketIO;
import io.itch.godlikeTerminal.networking.packets.client.C01PlayerPos;
import io.itch.godlikeTerminal.util.Timer;

public class EntityClientPlayer extends EntityPlayer {

    public EntityClientPlayer(Screen screen, Display display) {
        super(screen, display);
    }

    public Packet cPacket;
    public boolean packetSent = false;

    Timer timer = new Timer();

    public void tick() {

        if(timer.hasTimeElasped(1000, true))
            sendPacket(new C01PlayerPos(50, 50));

    }

    PacketIO packetIO = new PacketIO();

    public void sendPacket(Packet packet) {

        packetIO.sendPacket(packet);
        cPacket = packet;
        packetSent = true;
        PacketIO.packetSent = true;

    }

}
