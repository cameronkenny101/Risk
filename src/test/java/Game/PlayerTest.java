package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class PlayerTest extends TestCase {
    @Test
    public void testName() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);

        assertEquals("Cameron", player.getName());

        player.setName("Mark");

        assertEquals("Mark", player.getName());
    }
}