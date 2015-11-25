package main;

import cards.Card;
import cards.rooms.RoomCard;
import listeners.MouseHandler;
import player.CardPlacement;
import utility.Camera;
import utility.CardType;
import utility.SnapPoint;
import utility.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Onyx on 18-Nov-15.
 */
public class Game extends JPanel {
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
        Graphics2D g2 = (Graphics2D) g;
        if (table.getImage() != null)
            g.drawImage(table.getImage(), 0, 0, Main.mainProgram.getWidth(), Main.mainProgram.getHeight(), null);

        if (!placedCards.isEmpty())
            for (Card c : placedCards) {
                c.drawCard(g);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                if (c.getType().equals(CardType.ROOM)) {
                    g.setColor(Color.green);
                    RoomCard room = (RoomCard) c;
                    if (room.isNOccupied() && ((RoomCard) placement.selectedCard).isSOccupied())
                        g2.fillRoundRect(room.getX() + camera.getX(), room.getY() - Variables.cardY + camera.getY(), Variables.cardX, Variables.cardY, 25, 25);
                    if (room.isSOccupied() && ((RoomCard) placement.selectedCard).isNOccupied())
                        g2.fillRoundRect(room.getX() + camera.getX(), room.getY() + Variables.cardY + camera.getY(), Variables.cardX, Variables.cardY, 25, 25);
                    if (room.isWOccupied() && ((RoomCard) placement.selectedCard).isEOccupied())
                        g2.fillRoundRect(room.getX() - Variables.cardX + camera.getX(), room.getY() + camera.getY(), Variables.cardX, Variables.cardY, 25, 25);
                    if (room.isEOccupied() && ((RoomCard) placement.selectedCard).isWOccupied())
                        g2.fillRoundRect(room.getX() + Variables.cardX + camera.getX(), room.getY() + camera.getY(), Variables.cardX, Variables.cardY, 25, 25);
                }
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        if (placement.selectedCard != null) {
            if(!((RoomCard)placement.selectedCard).isSnapped){
                g.setColor(Color.red);
                g.fillRoundRect(Main.mouse.mouseX - Variables.cardX / 2 + 2, Main.mouse.mouseY - Variables.cardY / 2 + 2, Variables.cardX - 4, Variables.cardY - 4, 25, 25);
                g.drawImage(placement.selectedCard.getImage().getImage(), Main.mouse.mouseX - Variables.cardX / 2, Main.mouse.mouseY - Variables.cardY / 2, Variables.cardX, Variables.cardY, null);
            }
            else g.drawImage(placement.selectedCard.getImage().getImage(), camera.getX() + placement.selectedCard.getX(), camera.getY() + placement.selectedCard.getY(), Variables.cardX, Variables.cardY, null);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        if (deck.getImage() != null)
            g.drawImage(deck.getImage(), Variables.roomsDeckX, Variables.roomsDeckY, deck.getIconWidth(), deck.getIconHeight(), null);

        if (placement.selectedCard != null)
            placement.selectedCard.drawCard(g, Variables.roomsDeckX + 9, Variables.roomsDeckY);

        g.setColor(Color.BLACK);
        ((Graphics2D) g).scale(2D, 2D);
        drawStringWithBg(g, "Camera X: " + camera.getX(), 0, 20);
        drawStringWithBg(g, "Camera Y: " + camera.getY(), 0, 20 + 12);
        drawStringWithBg(g, "Mouse X: " + Main.mouse.mouseX, 0, 20 + 12*2);
        drawStringWithBg(g, "Mouse Y: " + Main.mouse.mouseY, 0, 20 + 12*3);
        if(placement.selectedCard != null)
        {
            drawStringWithBg(g, "Selected Card X: " + placement.selectedCard.getX(), 0, 20 + 12*4);
            drawStringWithBg(g, "Selected Card Y: " + placement.selectedCard.getY(), 0, 20 + 12*5);
        }
    }

    public void run() {
        if (opacity > 0.2f && oscillatingDown) {
            opacity -= 0.001f;
            if (opacity <= 0.2f) oscillatingDown = !oscillatingDown;
        } else if (opacity < 0.65f && !oscillatingDown) {
            opacity += 0.001f;
            if (opacity >= 0.65f) oscillatingDown = !oscillatingDown;
        }
        for(SnapPoint s: SnapPoint.snapPoints) {
            if ((s.entrance.equals("W") && ((RoomCard)Game.placement.selectedCard).isEOccupied() ||
                    s.entrance.equals("S") && ((RoomCard)Game.placement.selectedCard).isNOccupied() ||
                    s.entrance.equals("E") && ((RoomCard)Game.placement.selectedCard).isWOccupied() ||
                    s.entrance.equals("N") && ((RoomCard)Game.placement.selectedCard).isSOccupied()) &&
                    Main.mouse.mouseX > camera.getX() + s.x && Main.mouse.mouseX < camera.getX() + s.x + Variables.cardX &&
                    Main.mouse.mouseY > camera.getY() + s.y && Main.mouse.mouseY < camera.getY() + s.y + Variables.cardY) {
                Game.placement.selectedCard.setPosition(s.x, s.y);
                ((RoomCard)Game.placement.selectedCard).isSnapped = true;
                break;
            }
            else ((RoomCard)Game.placement.selectedCard).isSnapped = false;
        }
        repaint();
    }

    public void drawStringWithBg(Graphics g, String s, int x, int y)
    {
        g.setColor(Color.white);
        g.fillRect(x, y - 10, g.getFontMetrics().stringWidth(s) + 1, 12);
        g.setColor(Color.black);
        g.drawString(s, x, y);
    }

    public static void init() {
        placement.setSelectedCard(Card.cards.get(0));
        Card.cards.remove(0);
    }
}
