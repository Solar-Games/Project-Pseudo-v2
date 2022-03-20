package io.itch.godlikeTerminal.networking.packets;

public abstract class ClientPacket extends Packet{

    public ClientPacket(String packetName) {

        super(packetName);

        pt = PacketType.CLIENT;

    }

    public abstract void action();

}
