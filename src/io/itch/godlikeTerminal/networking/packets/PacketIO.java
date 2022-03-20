package io.itch.godlikeTerminal.networking.packets;

import io.itch.godlikeTerminal.networking.Server;

public class PacketIO {

    Packet packet;

    public void sendPacket(Packet packet) {

        this.packet = packet;

    }

    public static boolean packetSent = false;

    public void usePacket() {

        if (packetSent) {

            if (packet.pt == Packet.PacketType.CLIENT)
                callClientPacket((ClientPacket) packet);

            if (packet.pt == Packet.PacketType.SERVER)
                callServerPacket((ServerPacket) packet);

        }

    }

    public void callClientPacket(ClientPacket packet) {

        System.out.println("[Server Packet]");
        packet.action();

    }

    public void callServerPacket(ServerPacket packet) {

        System.out.println("[Client Packet]");
        packet.action();

    }

    public Packet getPacket() {

        return packet;

    }

}
