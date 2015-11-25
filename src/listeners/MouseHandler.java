package listeners;

import cards.Card;
import cards.rooms.RoomCard;
import main.Game;
import utility.Coordonates;
import utility.SnapPoint;
import utility.Variables;

import javax.swing.event.MouseInputListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Sorin on 11/18/2015.
 */
public class MouseHandler implements MouseWheelListener, MouseInputListener {
    public int mouseX = 0, mouseY = 0;
    int previousY, previousX, card = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getModifiers()) {
            case InputEvent.BUTTON1_MASK: {
                RoomCard card = (RoomCard) Game.placement.selectedCard;
                if (card.getDeck().equals("start") || card.isSnapped) {
                    Game.placement.setCard(card.isSnapped ? Game.camera.getX() + card.getX() : mouseX - Variables.cardX / 2, card.isSnapped ? Game.camera.getY() + card.getY() : mouseY - Variables.cardY / 2);
                    for(Card placedC: Game.placedCards) {
                        if (card.isWOccupied() && placedC.getX() == card.getX() - Variables.cardX && placedC.getY() == card.getY())
                            card.setWOccupied(false);
                        if (card.isEOccupied() && placedC.getX() == card.getX() + Variables.cardX && placedC.getY() == card.getY())
                            card.setEOccupied(false);
                        if (card.isSOccupied() && placedC.getX() == card.getX() && placedC.getY() == card.getY() + Variables.cardY)
                            card.setSOccupied(false);
                        if (card.isNOccupied() && placedC.getX() == card.getX() && placedC.getY() == card.getY() - Variables.cardY)
                            card.setNOccupied(false);
                    }
                    Iterator<SnapPoint> it = SnapPoint.snapPoints.iterator();
                    while(it.hasNext())
                    {
                        SnapPoint s = it.next();
                        if(s.x == card.getX() && s.y == card.getY()){
                            if(s.entrance.equals("W")){
                                ((RoomCard)s.getOriginal()).setWOccupied(false);
                                card.setEOccupied(false);
                            }
                            else if(s.entrance.equals("N")){
                                ((RoomCard)s.getOriginal()).setNOccupied(false);
                                card.setSOccupied(false);
                            }
                            else if(s.entrance.equals("E")){
                                ((RoomCard)s.getOriginal()).setEOccupied(false);
                                card.setWOccupied(false);
                            }
                            else if(s.entrance.equals("S")){
                                ((RoomCard)s.getOriginal()).setSOccupied(false);
                                card.setNOccupied(false);
                            }
                            it.remove();
                        }
                    }
                    HashMap<String, Coordonates> coords = card.getEntrance();
                    for (String c : coords.keySet()) {
                        SnapPoint s = new SnapPoint();
                        s.setOriginal(card);
                        s.setEntrance(c);
                        s.setCoords(coords.get(c).x, coords.get(c).y);
                    }
                    ArrayList<Card> possibilities = new ArrayList<Card>();
                    int entrances[] = {0, 0, 0, 0};
                    for(SnapPoint s: SnapPoint.snapPoints)
                        if(s.entrance.equals("W")) entrances[0]++;
                        else if(s.entrance.equals("N")) entrances[1]++;
                        else if(s.entrance.equals("E")) entrances[2]++;
                        else if(s.entrance.equals("S")) entrances[3]++;

                    for(Card c: Card.cards)
                        if(entrances[0] > 0 && ((RoomCard)c).isEOccupied()) possibilities.add(c);
                        else if(entrances[1] > 0 && ((RoomCard)c).isSOccupied()) possibilities.add(c);
                        else if(entrances[2] > 0 && ((RoomCard)c).isWOccupied()) possibilities.add(c);
                        else if(entrances[3] > 0 && ((RoomCard)c).isNOccupied()) possibilities.add(c);

                    Game.placement.setSelectedCard(possibilities.get(Game.rand.nextInt(possibilities.size())));
                    break;
                }
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
        if (y > previousY) Game.camera.incY(5);
        else if (y < previousY) Game.camera.decY(5);

        if (x > previousX) Game.camera.incX(5);
        else if (x < previousX) Game.camera.decX(5);

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
        if(e.getWheelRotation() < 0){
            if(++card >= Card.cards.size()) card = 0;
            Game.placement.setSelectedCard(Card.cards.get(card));
        }
        else{
            if(--card < 0) card = Card.cards.size() - 1;
            Game.placement.setSelectedCard(Card.cards.get(card));
        }
    }
}
