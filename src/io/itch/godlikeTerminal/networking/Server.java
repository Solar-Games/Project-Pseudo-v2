package io.itch.godlikeTerminal.networking;

import io.itch.godlikeTerminal.networking.packets.ClientPacket;
import io.itch.godlikeTerminal.networking.packets.PacketIO;
import io.itch.godlikeTerminal.sermanapp.ServerManagerApp;

import java.util.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class Server implements Runnable {

    public int port;
    public static boolean serverExistent = false;
    public ServerSocket serverSocket;
    public Socket socket;
    private boolean running = false;

    public Server(int port) {

        try {

            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();

        } catch (IOException e) {

            e.printStackTrace();

        }

        new ServerManagerApp();

        start();

    }

    public void start() {

        new Thread(this).start();
        running = true;

    }

    public void run() {

        PacketIO pIO = new PacketIO();

        while(running) {

            if(socket != null) {

                if (socket.isConnected()) {

                    pIO.usePacket();

                }

            }

        }

        try {

            new Thread(this).join();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        running = false;

    }

}