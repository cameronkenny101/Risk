package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DiceTest extends TestCase {

    @Test
    public void testDiceRoll() {
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
}
