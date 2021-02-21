package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class GameLogicTest extends TestCase {

    GameLogic logic = new GameLogic();

    @Test
    public void testTakeCountry(){
        Player player1 = new Player("Mark", Constants.PLAYER_COLOUR.RED, 4);
        Player player2 = new Player("Cam", Constants.PLAYER_COLOUR.BLUE, 2);
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE};
        int[] troopCount = {2, 4};
        logic.takeCountryLogic(country_owner, troopCount, 1, player1.getColour(), 2);
        assertEquals(country_owner[1], Constants.PLAYER_COLOUR.RED);
        assertEquals(troopCount[1], 6);
    }





}
