package Game;

import UI.GameScreen.GameScreenController;
import com.sun.source.tree.AssertTree;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GameTest extends TestCase {

    @Test
    public void testisWinner() {
        Game game = new Game();
        Player player = new Player("Mark", Constants.PLAYER_COLOUR.RED);

        Player player2 = new Player("Paul", Constants.PLAYER_COLOUR.BLUE);

        GameLogic gameLogic = new GameLogic();
        Arrays.fill(gameLogic.country_owner, Constants.PLAYER_COLOUR.RED);
        assertTrue(game.isWinner(player,gameLogic.country_owner));
        gameLogic.country_owner[3] = Constants.PLAYER_COLOUR.BLUE;
        assertFalse(game.isWinner(player,gameLogic.country_owner));
        Arrays.fill(gameLogic.country_owner, Constants.PLAYER_COLOUR.BLUE);
        assertTrue(game.isWinner(player2,gameLogic.country_owner));
    }

}
