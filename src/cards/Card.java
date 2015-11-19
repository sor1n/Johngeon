package cards;

import main.Game;
import main.Main;
import utility.CardType;
import utility.Variables;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Sorin on 11/18/2015.
 */
public abstract class Card {
    private String name, deck;
    private CardType type;
    private int x, y;

    private ImageIcon image;
    public static ArrayList<Card> cards = new ArrayList<Card>();

    public Card(String name, String deck, CardType type) {
        setDeck(deck);
        setName(name);
        setType(type);
        if (deck == "room") setPosition(Variables.roomsDeckX + 9, Variables.roomsDeckY - 4);
        else setPosition(0, 0);
        image = new ImageIcon(getClass().getClassLoader().getResource(type.name().toLowerCase() + "_" + name + ".png"));
    }

    public Card addCardToList() {
        cards.add(this);
        return this;
    }

    public void drawCard(Graphics g) {
        g.drawImage(image.getImage(), Game.camera.getX() + x, Game.camera.getY() + y, image.getIconWidth(), image.getIconHeight(), null);
    }

    public Card setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Card setType(CardType type) {
        this.type = type;
        return this;
    }

    public CardType getType() {
        return type;
    }

    public Card setDeck(String deck) {
        this.deck = deck;
        return this;
    }

    public String getDeck() {
        return deck;
    }

    public Card setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public void setImage(ImageIcon img) {
        this.image = img;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
