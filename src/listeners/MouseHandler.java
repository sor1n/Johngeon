package listeners;

import cards.Card;
import main.Game;
import main.Main;
import utility.Variables;

import javax.swing.event.MouseInputListener;
import java.awt.event.*;

/**
 * Created by Sorin on 11/18/2015.
 */
public class MouseHandler implements MouseWheelListener, MouseInputListener {
    public int mouseX = 0, mouseY = 0;
    int previousY, previousX;

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(e.getModifiers()) {
            case InputEvent.BUTTON1_MASK: {
                Game.placement.setCard(mouseX - Variables.cardX / 2, mouseY - Variables.cardY / 2);
                Game.placement.setSelectedCard(Card.cards.get(Game.rand.nextInt(Card.cards.size())));
                break;
            }
            case InputEvent.BUTTON2_MASK: {
                break;
            }
            case InputEvent.BUTTON3_MASK: {
                previousY = e.getY();
                previousX = e.getX();
                break;
            }
        }
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
        int y = e.getY(), x = e.getX();
        if (y > previousY) Game.camera.incY();
        else if (y < previousY) Game.camera.decY();

        if (x > previousX) Game.camera.incX();
        else if (x < previousX) Game.camera.decX();

        previousY = y;
        previousX = x;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
