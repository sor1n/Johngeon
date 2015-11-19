package cards.rooms;

import cards.Card;
import utility.CardType;

/**
 * Created by Sorin on 11/18/2015.
 */
public class RoomCard extends Card {
    private int entrances;
    private boolean isNOccupied, isSOccupied, isWOccupied, isEOccupied;

    public RoomCard(String name, CardType type) {
        super(name, "room", type);
    }

    public Card setEntrances(int e) {
        entrances = e;
        return this;
    }

    public int getEntrances() {
        return entrances;
    }
    public boolean isWOccupied() {
        isWOccupied = (entrances/1000)>1;
        return isWOccupied;
    }

    public boolean isNOccupied() {
        isNOccupied = ((entrances/100)%10)>1;
        return isNOccupied;
    }

    public boolean isEOccupied() {
        isEOccupied = ((entrances/10)%10)>1;
        return isEOccupied;
    }

    public boolean isSOccupied() {
        isSOccupied = (entrances%10)>1;
        return isSOccupied;
    }

    public void setEOccupied(boolean EOccupied) {
        isEOccupied = EOccupied;
    }

    public void setNOccupied(boolean NOccupied) {
        isNOccupied = NOccupied;
    }

    public void setSOccupied(boolean SOccupied) {
        isSOccupied = SOccupied;
    }

    public void setWOccupied(boolean WOccupied) {
        isWOccupied = WOccupied;
    }

    @Override
    public String toString() {
        return "W: " + isWOccupied() + "; N: " + isNOccupied() + "; E: " + isEOccupied() + "; S: " + isSOccupied();
    }
}
