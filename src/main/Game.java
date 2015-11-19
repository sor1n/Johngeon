package main;

import cards.Card;
import cards.rooms.RoomCard;
import player.CardPlacement;
import utility.Camera;
import utility.CardType;
import utility.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Onyx on 18-Nov-15.
 */
public class Game extends JPanel {
    int x = 0;
    ImageIcon table, deck;
    public static CardPlacement placement = new CardPlacement();
    public static Random rand = new Random();
    public static ArrayList<Card> placedCards = new ArrayList<Card>();
    float opacity = 0.5f;
    boolean oscillatingDown = true;
    public static Camera camera = new Camera();

    public Game() {
        table = new ImageIcon(getClass().getClassLoader().getResource("table_top.jpg"));
        deck = new ImageIcon(getClass().getClassLoader().getResource("deck.png"));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (table.getImage() != null)
            g.drawImage(table.getImage(), 0, 0, Main.mainProgram.getWidth(), Main.mainProgram.getHeight(), null);

        if (deck.getImage() != null)
            g.drawImage(deck.getImage(), Variables.roomsDeckX, Variables.roomsDeckY, deck.getIconWidth(), deck.getIconHeight(), null);

        if(!Card.cards.isEmpty())
            for(Card c:Card.cards)
                c.drawCard(g);

        if(!placedCards.isEmpty())
            for(Card c:placedCards) {
                c.drawCard(g);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                if(c.getType().equals(CardType.ROOM))
                {
                    g.setColor(Color.green);
                    RoomCard room = (RoomCard)c;
                    if(room.isNOccupied() && ((RoomCard)placement.selectedCard).isSOccupied())
                        g2.fillRoundRect(room.getX() + camera.getX(), room.getY() - Variables.cardY + camera.getY(), Variables.cardX - 7, Variables.cardY - 5, 25, 25);
                    if(room.isSOccupied() && ((RoomCard)placement.selectedCard).isNOccupied())
                        g2.fillRoundRect(room.getX() + camera.getX(), room.getY() + Variables.cardY + camera.getY(), Variables.cardX - 7, Variables.cardY - 5, 25, 25);
                    if(room.isWOccupied() && ((RoomCard)placement.selectedCard).isEOccupied())
                        g2.fillRoundRect(room.getX() - Variables.cardX + camera.getX(), room.getY() + camera.getY(), Variables.cardX - 7, Variables.cardY - 5, 25, 25);
                    if(room.isEOccupied() && ((RoomCard)placement.selectedCard).isWOccupied())
                        g2.fillRoundRect(room.getX() + Variables.cardX + camera.getX(), room.getY() + camera.getY(), Variables.cardX - 7, Variables.cardY - 5, 25, 25);
                }
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        if(placement.selectedCard != null) {
            g.setColor(Color.red);
            g.fillRoundRect(Main.mouse.mouseX - Variables.cardX / 2 + 2, Main.mouse.mouseY - Variables.cardY / 2 + 2, Variables.cardX - 7, Variables.cardY - 5, 25, 25);
            g.drawImage(placement.selectedCard.getImage().getImage(), Main.mouse.mouseX - Variables.cardX / 2, Main.mouse.mouseY - Variables.cardY / 2, placement.selectedCard.getImage().getIconWidth(), placement.selectedCard.getImage().getIconHeight(), null);
        }
    }

    public void run() {
        if(opacity > 0.2f && oscillatingDown){
            opacity-=0.001f;
            if(opacity <= 0.2f) oscillatingDown =!oscillatingDown;
        }
        else if(opacity < 0.65f && !oscillatingDown){
            opacity+=0.001f;
            if(opacity >= 0.65f) oscillatingDown =!oscillatingDown;
        }
        repaint();
    }

    public static void init()
    {
        placement.setSelectedCard(Card.cards.get(0));
        Card.cards.remove(0);
    }
}
