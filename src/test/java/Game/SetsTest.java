package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static Game.Sets.*;

class SetsTest extends TestCase {

    @Test
    void TestupdateSetsValue() {
        updateSetsValue();
        assertEquals(6,getSetsValue());
        updateSetsValue();
        assertEquals(8,getSetsValue());
        updateSetsValue();
        assertEquals(10,getSetsValue());
        updateSetsValue();
        assertEquals(15,getSetsValue());
        updateSetsValue();
        assertEquals(20,getSetsValue());
    }

    @Test
    void TestgetSetsValue() {
        setsValue = 4;
        assertEquals(4,getSetsValue());
        setsValue++;
        assertEquals(5,getSetsValue());
        setsValue--;
        assertEquals(4,getSetsValue());
    }

    @Test
    void TestisValidSet() {
        int[] sets = {3, 3, 0, 0};
        int[] sets2 = {1, 1, 1, 0};
        int[] sets3 = {3, 0, 0, 0};
        int[] sets4 = {0, 3, 1, 1};
        int[] sets5 = {0, 0, 2, 1};
        int[] sets6 = {3, 1, 1, 0};
        int[] sets7 = {1, 1, 1, 0};
        int[] sets8 = {0, 0, 1, 2};
        int[] sets9 = {0, 3, 1, 1};
        int[] sets10 = {0, 0, 2, 1};

        assertFalse(isValidSet(sets));
        assertTrue(isValidSet(sets2));
        assertTrue(isValidSet(sets3));
        assertFalse(isValidSet(sets4));
        assertTrue(isValidSet(sets5));
        assertFalse(isValidSet(sets6));
        assertTrue(isValidSet(sets7));
        assertTrue(isValidSet(sets8));
        assertFalse(isValidSet(sets9));
        assertTrue(isValidSet(sets10));

    }

    @Test
    void TestsetsToPlay() {
    }
}