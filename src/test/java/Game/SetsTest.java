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
    }

    @Test
    void TestsetsToPlay() {
    }
}