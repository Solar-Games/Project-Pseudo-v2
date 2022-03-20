package io.itch.godlikeTerminal.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class Client implements Runnable {

    public int port;
    public Socket socket;
    private boolean running = false;

    public Client(int port, String ip) {

        try {

            if(Server.serverExistent)
                socket = new Socket(ip, port);

        } catch (IOException e) {

            e.printStackTrace();

        }

        start();

    }

    public Client(int port) {

        new Client(port, "localhost");

        start();

    }

    public void start() {

        new Thread(this).start();
        running = true;

    }

    public void update() {

    }

    public void run() {

        while(running) {

            if(socket != null) {

                if(Server.serverExistent) {

                    if (socket.isConnected()) {

                        //System.out.println("connected");
                        update();

                    }

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