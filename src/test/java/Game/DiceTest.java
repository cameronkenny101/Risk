package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DiceTest extends TestCase {

    @Test
    public void testRollDice() {
        assertTrue(Dice.rollDice() >= 2 && Dice.rollDice() <= 12);
        assertTrue(Dice.rollDice() >= 2 && Dice.rollDice() <= 12);
        assertTrue(Dice.rollDice() >= 2 && Dice.rollDice() <= 12);
        assertTrue(Dice.rollDice() >= 2 && Dice.rollDice() <= 12);
        assertTrue(Dice.rollDice() >= 2 && Dice.rollDice() <= 12);
        assertTrue(Dice.rollDice() >= 2 && Dice.rollDice() <= 12);
    }

    @Test
    public void testDiceCompare() {
        assertTrue(Dice.bestRoll(10, 2) > 0);
        assertEquals(0, Dice.bestRoll(10, 10));
        assertTrue(Dice.bestRoll(2, 3) < 0);
    }

    @Test
    public void testBestRoll(){
        assertEquals(5,Dice.bestRoll(5,5));
        assertEquals(7,Dice.bestRoll(5,7));
        assertEquals(10,Dice.bestRoll(10,5));
        assertEquals(5,Dice.bestRoll(-10,5));
    }

    @Test
    public void TestRollSetOfDice(){
        assertEquals(2,Dice.rollSetOfDice(2).size());
        assertEquals(6,Dice.rollSetOfDice(6).size());
        assertNotSame(4,Dice.rollSetOfDice(2).size());
        assertNotSame(3,Dice.rollSetOfDice(4).size());
    }
}
