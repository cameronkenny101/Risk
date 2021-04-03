package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest extends TestCase {

    @Test
    void getCardIndex() {
        Card card = new Card(0,5);
        assertEquals(5,card.getCardIndex());

        Card card2 = new Card(1,10);
        assertEquals(10,card.getCardIndex());

        Card card3 = new Card(2,21);
        assertEquals(21,card.getCardIndex());
    }

    @Test
    void setCardIndex() {
        Card card = new Card(0,5);
        assertEquals(5,card.getCardIndex());
        card.setCardIndex(3);
        assertEquals(3,card.getCardIndex());

        Card card2 = new Card(0,7);
        assertEquals(7,card.getCardIndex());
        card.setCardIndex(8);
        assertEquals(8,card.getCardIndex());

        Card card3 = new Card(0,8);
        assertEquals(8,card.getCardIndex());
        card.setCardIndex(31);
        assertEquals(31,card.getCardIndex());
    }

    @Test
    void getInsignia() {
        Card card = new Card(0,5);
        assertEquals(0,card.getInsignia());

        Card card2 = new Card(1,5);
        assertEquals(1,card.getInsignia());

        Card card3 = new Card(2,5);
        assertEquals(2,card.getInsignia());
    }

    @Test
    void setInsignia() {
    }

    @Test
    void isWildCard() {
    }

    @Test
    void setWildCard() {
    }

    @Test
    void testToString() {
    }
}