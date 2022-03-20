package io.itch.godlikeTerminal.networking.packets;

public abstract class ServerPacket extends Packet {

    public ServerPacket(String packetName) {

        super(packetName);

        pt = PacketType.SERVER;

    }

    public abstract void action();

}
