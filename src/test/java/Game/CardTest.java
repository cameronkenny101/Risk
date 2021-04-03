package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest extends TestCase {

    @Test
    void getCardIndex() {
        Card card = new Card(0,5);
        assertEquals(0,card.getInsignia());

        Card card2 = new Card(1,5);
        assertEquals(1,card.getInsignia());

        Card card3 = new Card(2,5);
        assertEquals(2,card.getInsignia());
    }

    @Test
    void setCardIndex() {
        Card card = new Card(0,5);
        assertEquals(0,card.getInsignia());
    }

    @Test
    void getInsignia() {
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