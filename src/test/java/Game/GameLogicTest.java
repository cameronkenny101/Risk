package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GameLogicTest extends TestCase {

    GameLogic logic = new GameLogic();

    @Test
    public void testTakeCountryColor() {
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE};
        int[] troopCount = {2, 4};
        logic.takeCountryLogic(country_owner, troopCount, 0, Constants.PLAYER_COLOUR.GREEN, 1);
        logic.takeCountryLogic(country_owner, troopCount, 1, Constants.PLAYER_COLOUR.GREY, 2);
        assertEquals(country_owner[0], Constants.PLAYER_COLOUR.GREEN);
        assertEquals(country_owner[1], Constants.PLAYER_COLOUR.GREY);

    }

    @Test
    public void testTakeCountryTroops() {
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE, Constants.PLAYER_COLOUR.PURPLE};
        int[] troopCount = {0, 0, 0};
        logic.takeCountryLogic(country_owner, troopCount, 0, Constants.PLAYER_COLOUR.GREY, 2);
        logic.takeCountryLogic(country_owner, troopCount, 1, Constants.PLAYER_COLOUR.GREY, 3);
        logic.takeCountryLogic(country_owner, troopCount, 2, Constants.PLAYER_COLOUR.GREY, 5);
        assertEquals(2, troopCount[0]);
        assertEquals(3, troopCount[1]);
        assertEquals(5, troopCount[2]);
    }

    @Test
    public void testSetDiceToZero() {
        Player player1 = new Player("Cam", Constants.PLAYER_COLOUR.GREY, 0);
        Player player2 = new Player("Mike", Constants.PLAYER_COLOUR.BLUE, 1);
        player1.setDiceNum(2);
        player2.setDiceNum(3);
        logic.setDiceToZero(player1, player2);
        assertEquals(0, player1.getDiceNum());
        assertEquals(0, player2.getDiceNum());
    }

    @Test
    public void testSetRandomCountries() {
        ArrayList<Integer> list = new ArrayList<>();
        logic.setRandomCountries(list, 3);
        assertNotNull(list);
        assertNotEquals(list.get(0), list.get(1), list.get(2));
        assertTrue(list.get(0) >= 0 && list.get(0) <= 2);
        assertTrue(list.get(1) >= 0 && list.get(1) <= 2);
        assertTrue(list.get(2) >= 0 && list.get(2) <= 2);
    }



}
