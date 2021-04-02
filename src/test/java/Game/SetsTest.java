package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static Game.Sets.getSetsValue;
import static Game.Sets.setsValue;

class SetsTest extends TestCase {

    @Test
    void TestgetSetsValue() {
        assertEquals(4,getSetsValue());
        setsValue++;
        assertEquals(5,getSetsValue());
    }

    @Test
    void TestupdateSetsValue() {

    }

    @Test
    void TestisValidSet() {
    }

    @Test
    void TestsetsToPlay() {
    }
}