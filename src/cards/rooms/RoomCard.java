package cards.rooms;

import cards.Card;
import utility.CardType;
import utility.Coordonates;
import utility.Variables;

import java.util.HashMap;

/**
 * Created by Sorin on 11/18/2015.
 */
public class RoomCard extends Card {
    private int entrances;
    private boolean isNOccupied, isSOccupied, isWOccupied, isEOccupied;
    public boolean isSnapped;

    public RoomCard(String name, CardType type) {
        this(name, "room", type);
    }

    public RoomCard(String name, String deck, CardType type) {
        super(name, deck, type);
    }

    public Card setEntrances(int e) {
        entrances = e;
        return this;
    }

    public int getEntrances() {
        return entrances;
    }

    public boolean isWOccupied() {
        isWOccupied = (entrances / 1000) > 1;
        return isWOccupied;
    }

    public boolean isNOccupied() {
        isNOccupied = ((entrances / 100) % 10) > 1;
        return isNOccupied;
    }

    public boolean isEOccupied() {
        isEOccupied = ((entrances / 10) % 10) > 1;
        return isEOccupied;
    }

    public boolean isSOccupied() {
        isSOccupied = (entrances % 10) > 1;
        return isSOccupied;
    }

    public void setEOccupied(boolean EOccupied) {
        isEOccupied = EOccupied;
        entrances -= 10;
    }

    public void setNOccupied(boolean NOccupied) {
        isNOccupied = NOccupied;
        entrances -= 100;
    }

    public void setSOccupied(boolean SOccupied) {
        isSOccupied = SOccupied;
        entrances -= 1;
    }

    public void setWOccupied(boolean WOccupied) {
        isWOccupied = WOccupied;
        entrances -= 1000;
    }

    public HashMap<String, Coordonates> getEntrance() {
        HashMap<String, Coordonates> ent = new HashMap<String, Coordonates>();
        if (isWOccupied()) ent.put("W", new Coordonates(getX() - Variables.cardX, getY()));
        if (isNOccupied()) ent.put("N", new Coordonates(getX(), getY() - Variables.cardY));
        if (isEOccupied()) ent.put("E", new Coordonates(getX() + Variables.cardX, getY()));
        if (isSOccupied()) ent.put("S", new Coordonates(getX(), getY() + Variables.cardY));
        return ent;
    }

    @Override
    public String toString() {
        return "Name: '" + getName() + "' W: " + isWOccupied() + "; N: " + isNOccupied() + "; E: " + isEOccupied() + "; S: " + isSOccupied();
    }
}
