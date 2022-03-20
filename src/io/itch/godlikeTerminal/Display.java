package io.itch.godlikeTerminal;

import io.itch.godlikeTerminal.gfx.Screen;
import io.itch.godlikeTerminal.gui.GameState;
import io.itch.godlikeTerminal.gui.MainMenu;
import io.itch.godlikeTerminal.gui.ScreenType;
import io.itch.godlikeTerminal.input.InputHandler;
import io.itch.godlikeTerminal.map.MapCreator;
import io.itch.godlikeTerminal.util.XMLParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Display extends Canvas implements Runnable{

    public static int WIDTH = 1080, HEIGHT = 720;

    public static final String title = "game";

    public static MapCreator map;

    private Game g;
    private MainMenu menu;
    private GameState game;
    private InputHandler ki;

    public BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();

    private boolean running = false;

    public Display(){

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setSize(new Dimension(WIDTH, HEIGHT));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        start();

        ki = new InputHandler(this);

        g = new Game(this);

    }

    public void init(){

        Screen.setScreenType(ScreenType.MAIN_MENU);

        menu = new MainMenu(this, WIDTH, HEIGHT);
        game = new GameState(this, WIDTH, HEIGHT);

        try {

            map = new MapCreator(game.MAP_WIDTH, game.MAP_HEIGHT);

        }catch (IOException e) {

            e.printStackTrace();

        }

    }

    public synchronized void start() {

        running = true;
        new Thread(this).start();

    }

    public synchronized void stop() {

        running = false;
        try {
            new Thread(this).join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() {

        init();

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        double unprocessed = 0;
        int ticks = 0;
        int frames = 0;
        long lastMillis = System.currentTimeMillis();

        while(running){

            long now = System.nanoTime();
            unprocessed += (now - lastTime)/nsPerTick;
            lastTime = now;

            while (unprocessed >= 1){

                tick();
                ticks++;
                unprocessed--;

            }

            try {
                Thread.sleep(4);
            }catch (Exception e){

                e.printStackTrace();

            }

            render();
            frames++;


            if(System.currentTimeMillis() - lastMillis > 1000){

                System.out.println("Frames: " + frames + ", Ticks: " + ticks + "\n");

                ticks = 0;
                frames = 0;

                lastMillis = System.currentTimeMillis();

            }

        }

        stop();

    }

    public void tick(){

        g.tick();

        if(Screen.getScreenType() == ScreenType.GAME) {

            game.tick();

        }else if(Screen.getScreenType() == ScreenType.MAIN_MENU) {

            menu.tick();

        }

    }

    public void render(){

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {

            this.createBufferStrategy(3);
            return;

        }

        if(Screen.getScreenType() == ScreenType.GAME) {

            game.render();

        }else if(Screen.getScreenType() == ScreenType.MAIN_MENU){

            menu.render();

        }

        for (int i = 0; i < WIDTH*HEIGHT; i++){

            if(Screen.getScreenType() == ScreenType.GAME) {

                pixels[i] = game.pixels[i];

            }else if(Screen.getScreenType() == ScreenType.MAIN_MENU){

                pixels[i] = menu.pixels[i];

            }

        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(img, 0, 0, null);

        g.dispose();
        bs.show();

    }

    public static void getScripts(String scriptsDirectory) {

        List<File> scriptPaths = new ArrayList<>();

        try {

            if(scriptsDirectory != null) {
                Files.list(Paths.get(scriptsDirectory)).filter(Files::isRegularFile).forEach((path) -> {
                    scriptPaths.add(path.toFile());
                });
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        if(scriptPaths != null)
            for (File script : scriptPaths) {

                System.out.println(script.getName());

            }

    }

    public static void createAppdata() {

        String adpath = System.getenv("APPDATA") + "//Project Pseudo";

        File appdata = new File(adpath);

        if(!appdata.isDirectory())
            if(!appdata.mkdirs())
                System.out.println("couldn't add to appdata");

        if(!appdata.exists()) {

            try {

                appdata.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        File appdataScripts = new File(adpath + "//Scripts");

        if(!appdataScripts.isDirectory())
            if(!appdataScripts.mkdirs())
                System.out.println("couldn't add to appdata");

        if(!appdataScripts.exists()) {

            try {

                appdataScripts.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        getScripts(adpath + "//Scripts" + "//");

    }

    public static void main(String[] args) {

        XMLParser xml = new XMLParser(new File("config.xml"));

        try {

            xml.parseXML("width", Display.WIDTH);
            xml.parseXML("height", Display.HEIGHT);
            xml.endParsing(null);

        } catch (IOException e) {

            e.printStackTrace();

        }

        createAppdata();

        Display display = new Display();

        //new Server(9090);

    }

}