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

    @Test
    public void testColour() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);
        assertEquals(Constants.PLAYER_COLOUR.RED, player.getColour());
        Player player1 = new Player("Cameron", Constants.PLAYER_COLOUR.BLUE);
        assertEquals(Constants.PLAYER_COLOUR.BLUE, player1.getColour());
        Player player2 = new Player("Cameron", Constants.PLAYER_COLOUR.GREY);
        assertEquals(Constants.PLAYER_COLOUR.GREY, player2.getColour());
    }
}