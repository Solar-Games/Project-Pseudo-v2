package io.itch.godlikeTerminal;

import io.itch.godlikeTerminal.input.*;
import io.itch.godlikeTerminal.util.XMLParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game {

    private int looped = 0;
    public static int time = 0;
    public static int mil = 0;
    public static int sec = 0;
    public static int min = 0;
    public static int hrs = 0;

    public Game(Display display) {

        looped++;

        InputHandler ki = new InputHandler(display);

        mil = 0;
        sec = 0;
        min = 0;
        hrs = 0;

    }

    public void tick() {

        time++;

        mil++;
        
        if(mil == 1000)
            sec++;
        
        if(sec == 60)
            min++;
        
        if(min == 60)
            hrs++;

    }

}
