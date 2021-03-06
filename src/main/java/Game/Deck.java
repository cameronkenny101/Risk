package Game;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    protected ArrayList<Card> cards;
    private ArrayList<Integer> shuffle = new ArrayList<>();
    private int cardsRemoved;

    /**
     * Constructor for the deck
     */
    public Deck() {
        cards = new ArrayList<Card>();
        shuffleDeck();
        for(int i = 0; i < Constants.NUM_CARDS; i++)
            cards.add(new Card(Constants.COUNTRY_INSIGNIAS[shuffle.get(i)], shuffle.get(i)));
        setCardsRemoved(0);
    }

    /**
     * function used for shuffling
     */
    private void shuffleDeck() {
        for(int i = 0; i < 44; i++)
            shuffle.add(i);
        Collections.shuffle(shuffle);
    }

    /**
     * Used to remove a card from the deck
     * @return the removed cards
     */
    public Card removeCard() {
        Card removedCard = cards.remove(getCardsRemoved());
        setCardsRemoved(getCardsRemoved() + 1);
        return removedCard;
    }

    public int getCardsRemoved() {
        return cardsRemoved;
    }

    public void setCardsRemoved(int cardsRemoved) {
        this.cardsRemoved = cardsRemoved;
    }
}
