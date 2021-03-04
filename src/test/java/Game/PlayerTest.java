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

    @Test
    public void testTurn() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);
        player.setTurn(true);
        assertTrue(player.isTurn());
        player.setTurn(false);
        assertFalse(player.isTurn());
        Player player2 = new Player("Ash", Constants.PLAYER_COLOUR.BLUE);
        player2.setTurn(true);
        assertTrue(player2.isTurn());
        player2.setTurn(false);
        assertFalse(player2.isTurn());
    }

    @Test
    public void testTroops() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);
        assertEquals(player.getTroops(), Constants.INIT_UNITS_PLAYER - Constants.INIT_COUNTRIES_PLAYER);
        player.setTroops(12);
        assertEquals(player.getTroops(), 12);
    }

    @Test
    public void testInitTroops() {
        Player player = new Player("Mark", Constants.PLAYER_COLOUR.BLUE);
        assertEquals(player.getInitTroops(), 3);
        player.setInitTroops(2);
        assertEquals(player.getInitTroops(), 2);
    }
}