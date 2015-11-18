package listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Onyx on 18-Nov-15.
 */
public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 27) System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
