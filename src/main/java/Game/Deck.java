package Game;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;
    private ArrayList<Integer> shuffle;
    private int cardsRemoved;

    public Deck() {
        cards = new ArrayList<Card>();
        shuffleDeck();
        for(int i = 0; i < Constants.NUM_CARDS; i++)
            cards.add(new Card(Constants.COUNTRY_INSIGNIAS[shuffle.get(i)], shuffle.get(i)));
    }

    private void shuffleDeck() {
        for(int i = 0; i < Constants.NUM_CARDS; i++)
            shuffle.add(i);
        Collections.shuffle(shuffle);
    }
}
