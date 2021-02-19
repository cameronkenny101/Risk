package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DiceTest extends TestCase {

    @Test
    public void testDiceRoll() {
        Dice dice = new Dice();
        assertTrue(dice.rollDice() >= 2 && dice.rollDice() <= 12);
        assertTrue(dice.rollDice() >= 2 && dice.rollDice() <= 12);
        assertTrue(dice.rollDice() >= 2 && dice.rollDice() <= 12);
        Dice newDice = new Dice();
        assertTrue(newDice.rollDice() >= 2 && newDice.rollDice() <= 12);
        assertTrue(newDice.rollDice() >= 2 && newDice.rollDice() <= 12);
        assertTrue(newDice.rollDice() >= 2 && newDice.rollDice() <= 12);
    }

    @Test
    public void testDiceCompare() {
        Dice dice = new Dice();
        assertTrue(dice.bestRoll(10, 2) > 0);
        assertEquals(0, dice.bestRoll(10, 10));
        assertTrue(dice.bestRoll(2, 3) < 0);
    }
}
