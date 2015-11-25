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
        Card start = new RoomCard("start", "start", CardType.ROOM).setEntrances(2222).addCardToList();
        Card left = new RoomCard("left", CardType.ROOM).setEntrances(2111).addCardToList();
        Card leftUp = new RoomCard("leftUp", CardType.ROOM).setEntrances(2211).addCardToList();
        Card leftRight = new RoomCard("leftRight", CardType.ROOM).setEntrances(2121).addCardToList();
        Card leftDownRight = new RoomCard("leftDownRight", CardType.ROOM).setEntrances(2122).addCardToList();
        Card right = new RoomCard("right", CardType.ROOM).setEntrances(1121).addCardToList();
        Card rightUpLeft = new RoomCard("rightUpLeft", CardType.ROOM).setEntrances(2221).addCardToList();
        Card up = new RoomCard("up", CardType.ROOM).setEntrances(1211).addCardToList();
        Card upRight = new RoomCard("upRight", CardType.ROOM).setEntrances(1221).addCardToList();
        Card upLeftDown = new RoomCard("upLeftDown", CardType.ROOM).setEntrances(2212).addCardToList();
        Card upRightDown = new RoomCard("upRightDown", CardType.ROOM).setEntrances(1222).addCardToList();
        Card down = new RoomCard("down", CardType.ROOM).setEntrances(1112).addCardToList();
        Card downLeft = new RoomCard("downLeft", CardType.ROOM).setEntrances(2112).addCardToList();
        Card downRight = new RoomCard("downRight", CardType.ROOM).setEntrances(1122).addCardToList();
        Card downUp = new RoomCard("downUp", CardType.ROOM).setEntrances(1212).addCardToList();
        Card all = new RoomCard("all", CardType.ROOM).setEntrances(2222).addCardToList();
    }
}
