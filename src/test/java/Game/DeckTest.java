package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest extends TestCase {

    @Test
    void TestRemoveCard() { //Hard to test due to randomisation, tests that function interactes with proper vars
        Deck deck = new Deck();
        deck.setCardsRemoved(5);
        assertEquals(deck.cards.get(5),deck.removeCard());
        deck.setCardsRemoved(1);
        assertEquals(deck.cards.get(1),deck.removeCard());
        deck.setCardsRemoved(32);
        assertEquals(deck.cards.get(32),deck.removeCard());
        deck.setCardsRemoved(27);
        assertEquals(deck.cards.get(27),deck.removeCard());
        assertNotEquals(deck.cards.get(23),deck.removeCard());

    }


    @Test
    void TestSetCardsRemovedANDTestGetCardsRemoved() {
        Deck deck = new Deck();
        deck.setCardsRemoved(5);
        assertEquals(5,deck.getCardsRemoved());

        deck.setCardsRemoved(6);
        assertEquals(6,deck.getCardsRemoved());

        deck.setCardsRemoved(45);
        assertEquals(45,deck.getCardsRemoved());
    }
}