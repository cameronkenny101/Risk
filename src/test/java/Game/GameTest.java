package Game;

import com.sun.source.tree.AssertTree;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class GameTest extends TestCase {

    @Test
    public void testisWinner() {
        Game game = new Game();
        Player player = new Player("Mark", Constants.PLAYER_COLOUR.RED, 1);
        GameLogic gameLogic = new GameLogic();

        for (int i = 0; i < gameLogic.country_owner.length; i++) {
            gameLogic.country_owner[i] = player.getColour();
            System.out.println(gameLogic.country_owner[i]);
        }
        assertTrue(game.isWinner(player));

    }
}
