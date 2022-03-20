package io.itch.godlikeTerminal.networking.packets;

public abstract class Packet {

    public PacketType pt;
    public String packetName;

    public Packet(String packetName) {

        this.packetName = packetName;

    }

    public abstract void action();

    public enum PacketType {

        CLIENT,
        SERVER;

    }

}
