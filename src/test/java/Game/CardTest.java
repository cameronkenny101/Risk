package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest extends TestCase {

    @Test
    void Test_getCardIndex() {
        Card card = new Card(0,5);
        assertEquals(5,card.getCardIndex());

        Card card2 = new Card(1,10);
        assertEquals(10,card2.getCardIndex());

        Card card3 = new Card(2,21);
        assertEquals(21,card3.getCardIndex());
    }

    @Test
    void Test_setCardIndex() {
        Card card = new Card(0,5);
        assertEquals(5,card.getCardIndex());
        card.setCardIndex(3);
        assertEquals(3,card.getCardIndex());

        Card card2 = new Card(0,7);
        assertEquals(7,card2.getCardIndex());
        card2.setCardIndex(8);
        assertEquals(8,card2.getCardIndex());

        Card card3 = new Card(0,8);
        assertEquals(8,card3.getCardIndex());
        card3.setCardIndex(31);
        assertEquals(31,card3.getCardIndex());
    }

    @Test
    void Test_getInsignia() {
        Card card = new Card(0,5);
        assertEquals(0,card.getInsignia());

        Card card2 = new Card(1,5);
        assertEquals(1,card2.getInsignia());

        Card card3 = new Card(2,5);
        assertEquals(2,card3.getInsignia());
    }

    @Test
    void Test_setInsignia() {
        Card card = new Card(0,5);
        assertEquals(0,card.getInsignia());
        card.setInsignia(6);
        assertEquals(6,card.getInsignia());

        Card card2 = new Card(1,5);
        assertEquals(1,card2.getInsignia());
        card2.setInsignia(26);
        assertEquals(26,card2.getInsignia());

        Card card3 = new Card(12,5);
        assertEquals(12,card3.getInsignia());
        card3.setInsignia(36);
        assertEquals(36,card3.getInsignia());
    }

    @Test
    void Test_isWildCardANDsetWildCard() {
        Card card = new Card(0,5);
        card.setWildCard(true);
        assertTrue(card.isWildCard());

        Card card2 = new Card(0,5);
        card2.setWildCard(true);
        assertTrue(card2.isWildCard());

    }

    @Test
    void TestToString() {
        Card card = new Card(0,3);
        assertEquals("Alberta Infantry",card.toString());

        Card card2 = new Card(1,8);
        assertEquals("Alaska Cavalry",card2.toString());

        Card card3 = new Card(2,12);
        assertEquals("Ukraine Battalion",card3.toString());

        Card card4 = new Card(33,3);
        assertEquals("Wildcard",card4.toString());
    }
}