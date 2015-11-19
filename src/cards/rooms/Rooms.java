package cards.rooms;

import cards.Card;
import utility.CardType;

/**
 * Created by Sorin on 11/18/2015.
 */
public class Rooms
{
    public static void init()
    {
        Card start = new RoomCard("start", CardType.ROOM).setEntrances(2222).setDeck("start").addCardToList();
        Card left = new RoomCard("left", CardType.ROOM).setEntrances(2111).addCardToList();
        Card right = new RoomCard("right", CardType.ROOM).setEntrances(1121).addCardToList();
        Card up = new RoomCard("up", CardType.ROOM).setEntrances(1211).addCardToList();
        Card down = new RoomCard("down", CardType.ROOM).setEntrances(1112).addCardToList();
    }
}
