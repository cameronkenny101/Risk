package Game;

import com.sun.source.tree.AssertTree;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GameTest extends TestCase {

    @Test
    public void testisWinner() {
        Game game = new Game();
        Player player = new Player("Mark", Constants.PLAYER_COLOUR.RED);
<<<<<<< HEAD
=======
        Player player2 = new Player("Paul", Constants.PLAYER_COLOUR.BLUE);
>>>>>>> 5d5c8d16c507176450ed6ce3db28e12fae6a008d
        GameLogic gameLogic = new GameLogic();
        Arrays.fill(gameLogic.country_owner, Constants.PLAYER_COLOUR.RED);
        assertTrue(game.isWinner(player,gameLogic.country_owner));
        gameLogic.country_owner[3] = Constants.PLAYER_COLOUR.BLUE;
        assertFalse(game.isWinner(player,gameLogic.country_owner));
        Arrays.fill(gameLogic.country_owner, Constants.PLAYER_COLOUR.BLUE);
        assertTrue(game.isWinner(player2,gameLogic.country_owner));
    }
}
