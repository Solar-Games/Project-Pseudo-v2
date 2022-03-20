package io.itch.godlikeTerminal.sermanapp;

import io.itch.godlikeTerminal.networking.Server;
import io.itch.godlikeTerminal.networking.packets.Packet;
import io.itch.godlikeTerminal.networking.packets.PacketIO;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ServerManagerApp extends Canvas implements Runnable {

    private boolean running = false;

    public ServerManagerApp() {

        JFrame frame = new JFrame("SvrMgrApp");
        frame.setPreferredSize(new Dimension(350, 450));
        frame.setSize(350, 450);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(this);
        frame.pack();
        start();

    }

    public void start() {

        new Thread(this).start();
        running = true;

    }

    public void stop() {

        try {

            new Thread(this).join();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        running = false;

    }

    public void run() {

        while (running) {

            try {

                Thread.sleep(4);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            render();

        }

        stop();

    }

    PacketIO packetIO = new PacketIO();

    public void getPacketLog(Graphics g) {

        Packet packet = packetIO.getPacket();

        if(packet != null) {

            if(packet.packetName != null) {

                g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
                g.setColor(new Color(0xbfbfbf));
                g.drawString(packet.packetName, 10, 25);

            }

        }

    }

    public void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {

            this.createBufferStrategy(2);
            return;

        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0x151515));

        g.fillRect(0, 0, 10000, 10000);

        getPacketLog(g);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        g.setColor(new Color(0xbfbfbf));
        g.drawString("hi", 10, 25);

        g.dispose();
        bs.show();

    }

    public static void main(String[] args) {

        new ServerManagerApp();

    }

}