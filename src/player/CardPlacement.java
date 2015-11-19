package player;

import cards.Card;
import cards.rooms.RoomCard;
import main.Game;
import utility.CardType;

/**
 * Created by Sorin on 11/18/2015.
 */
public class CardPlacement
{
    public Card selectedCard;

    public void setSelectedCard(Card s)
    {
        if(s.getType().equals(CardType.ROOM) && s != null)
            selectedCard = new RoomCard(s.getName(), s.getType()).setEntrances(((RoomCard)s).getEntrances());
    }

    public void setCard(int x, int y)
    {
        selectedCard.setPosition(x, y);
        Game.placedCards.add(selectedCard);
    }
}
