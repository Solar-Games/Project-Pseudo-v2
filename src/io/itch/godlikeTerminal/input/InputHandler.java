package io.itch.godlikeTerminal.input;

import io.itch.godlikeTerminal.Display;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener, MouseMotionListener, MouseListener {

    public InputHandler(Display display){

        display.addKeyListener(this);

    }

    public Key up = new Key(KeyEvent.VK_UP);
    public Key down = new Key(KeyEvent.VK_DOWN);
    public Key left = new Key(KeyEvent.VK_LEFT);
    public Key right = new Key(KeyEvent.VK_RIGHT);
    public Key select = new Key(KeyEvent.VK_ENTER, KeyEvent.VK_Z);

    public int mouseX = 0, mouseY = 0;
    public int lastMouseX = 0, lastMouseY = 0;

    public List<Key> keys = new ArrayList<>();

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        toggleKey(e.getKeyCode(), true);

    }

    public void keyReleased(KeyEvent e) {

        toggleKey(e.getKeyCode(), false);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        lastMouseX = mouseX;
        lastMouseY = mouseY;

        mouseX = e.getX();
        mouseY = e.getY();

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        lastMouseX = mouseX;
        lastMouseY = mouseY;

        mouseX = e.getX();
        mouseY = e.getY();

    }

    public static class Key{

        public int[] keyCode;
        private boolean isPressed = false;
        public int timePressed = 0;

        public Key(int... keyCode){

            this.keyCode = keyCode;

        }

        public Key(int keyCode){

            this.keyCode = new int[] { keyCode };

        }

        public boolean getPressed() {
            return isPressed;
        }

        public void toggle(boolean pressed) {

            this.isPressed = pressed;

            if(pressed == true)
                timePressed++;
            else
                timePressed = 0;

        }

    }

    public void toggleKey(int keyCode, boolean toggle) {

        if(keyCode == up.keyCode[0])
            up.toggle(toggle);

        if(keyCode == down.keyCode[0])
            down.toggle(toggle);

        if(keyCode == left.keyCode[0])
            left.toggle(toggle);

        if(keyCode == right.keyCode[0])
            right.toggle(toggle);

        for (int i = 0; i < select.keyCode.length; i++) {

            if (keyCode == select.keyCode[i]) {

                select.toggle(toggle);

            }

        }

    }

}
