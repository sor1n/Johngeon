package utility;

import cards.Card;

import java.util.ArrayList;

/**
 * Created by Sorin on 11/25/2015.
 */
public class SnapPoint {
    public int x = 0, y = 0;
    private Card original;
    public static ArrayList<SnapPoint> snapPoints = new ArrayList<SnapPoint>();
    public String entrance;

    public SnapPoint() {
        snapPoints.add(this);
    }

    public void setCoords(int i, int j) {
        x = i;
        y = j;
    }

    public void setOriginal(Card card) {
        original = card;
    }
    public void setEntrance(String ent) {
        entrance = ent;
    }
    public Card getOriginal() {
        return original;
    }
}
