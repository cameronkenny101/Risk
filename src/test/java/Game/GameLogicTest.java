package Game;

import UI.GameScreen.GameScreenController;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameLogicTest extends TestCase {

    GameLogic logic = new GameLogic();

    @Test
    public void testTakeCountryColor() {
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE};
        int[] troopCount = {2, 4};
        logic.takeCountryLogic(0, Constants.PLAYER_COLOUR.GREEN, 1);
        logic.takeCountryLogic(1, Constants.PLAYER_COLOUR.GREY, 2);
        assertEquals(country_owner[0], Constants.PLAYER_COLOUR.GREEN);
        assertEquals(country_owner[1], Constants.PLAYER_COLOUR.GREY);

    }

    @Test
    public void testTakeCountryTroops() {
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE, Constants.PLAYER_COLOUR.PURPLE};
        int[] troopCount = {0, 0, 0};
        logic.takeCountryLogic(0, Constants.PLAYER_COLOUR.GREY, 2);
        logic.takeCountryLogic(1, Constants.PLAYER_COLOUR.GREY, 3);
        logic.takeCountryLogic(2, Constants.PLAYER_COLOUR.GREY, 5);
        assertEquals(2, troopCount[0]);
        assertEquals(3, troopCount[1]);
        assertEquals(5, troopCount[2]);
    }

    @Test
    public void testSetDiceToZero() {
        Player player1 = new Player("Cam", Constants.PLAYER_COLOUR.GREY);
        Player player2 = new Player("Mike", Constants.PLAYER_COLOUR.BLUE);
        player1.setDiceNum(2);
        player2.setDiceNum(3);
        logic.setDiceToZero(player1, player2);
        assertEquals(0, player1.getDiceNum());
        assertEquals(0, player2.getDiceNum());
    }

    @Test
    public void testSetRandomCountries() {
        ArrayList<Integer> list = new ArrayList<>();
        logic.setRandomCountries();
        assertNotNull(list);
        assertTrue(list.get(0) >= 0 && list.get(0) <= 2);
        assertTrue(list.get(1) >= 0 && list.get(1) <= 2);
        assertTrue(list.get(2) >= 0 && list.get(2) <= 2);
    }

    @Test
    public void testCalculateReinforcements() {
        Player testPlayer = new Player("Max", Constants.PLAYER_COLOUR.RED);
        for (int i = 0; i < 7; i++) {
            logic.country_owner[i] = Constants.PLAYER_COLOUR.RED;
        }
        assertEquals(3, logic.calculateReinforcements(testPlayer));

        for (int i = 0; i < 9; i++) {
            logic.country_owner[i] = Constants.PLAYER_COLOUR.RED;
        }
        assertEquals(8, logic.calculateReinforcements(testPlayer)); // 9 / 3 = 3 + 5 (from the control of America)

        for (int i = 0; i < 16; i++) {
            logic.country_owner[i] = Constants.PLAYER_COLOUR.RED;
        }
        assertEquals(15, logic.calculateReinforcements(testPlayer)); // 16 / 3 = 5 + 5 + 5

        for (int i = 0; i < 42; i++) {
            logic.country_owner[i] = Constants.PLAYER_COLOUR.RED;
        }
        //If the player hypothetically conquered all territory
        assertEquals(38, logic.calculateReinforcements(testPlayer)); // 42 / 3 = 13 + 24 = 38
        logic.country_owner[19] = Constants.PLAYER_COLOUR.GREY;
        logic.country_owner[0] = Constants.PLAYER_COLOUR.GREY;
        logic.country_owner[1] = Constants.PLAYER_COLOUR.GREY;
        //After losing the extra troops by not fully conquering asia and North America
        assertEquals(25, logic.calculateReinforcements(testPlayer)); // 42/3 = 14 + 2 + 2 + 5 +2
    }

    @Test
    public void testCalculateContReinforcements() {
        Player player = new Player("MARK", Constants.PLAYER_COLOUR.RED);
        for (int i = 0; i < 42; i++) {
            logic.country_owner[i] = Constants.PLAYER_COLOUR.RED;
        }

        //TESTS FOR NORMAL OPERATION  //NOTE THESE ARE THE SAME FUNCTION CALLS IN THE PROGRAM
        assertEquals(2, logic.calculateContReinforcements(player, 0, 9, 9, 2));
        assertEquals(5, logic.calculateContReinforcements(player, 9, 16, 7, 5));
        assertEquals(7, logic.calculateContReinforcements(player, 16, 28, 12, 7));
        assertEquals(2, logic.calculateContReinforcements(player, 28, 32, 4, 2));
        assertEquals(2, logic.calculateContReinforcements(player, 32, 36, 4, 2));
        assertEquals(3, logic.calculateContReinforcements(player, 36, 42, 6, 3));

        //TESTS FOR THE FUNCTION TO ID THAT SOME CONTS ARE CONTROLLED AND SOME ARENT
        logic.country_owner[15] = Constants.PLAYER_COLOUR.BLUE;
        logic.country_owner[40] = Constants.PLAYER_COLOUR.GREY;
        assertEquals(2, logic.calculateContReinforcements(player, 0, 9, 9, 2));
        assertEquals(0, logic.calculateContReinforcements(player, 9, 16, 7, 5));
        assertEquals(7, logic.calculateContReinforcements(player, 16, 28, 12, 7));
        assertEquals(2, logic.calculateContReinforcements(player, 28, 32, 4, 2));
        assertEquals(2, logic.calculateContReinforcements(player, 32, 36, 4, 2));
        assertEquals(0, logic.calculateContReinforcements(player, 36, 42, 6, 3));


    }


}
