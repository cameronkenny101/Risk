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
        GameLogic gameLogic = new GameLogic();
        Arrays.fill(gameLogic.country_owner, Constants.PLAYER_COLOUR.RED);
        assertTrue(game.isWinner(player));

    }
}
