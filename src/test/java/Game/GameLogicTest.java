package Game;


import UI.GameScreen.GameScreenController;
//import GameScreen.GameScreenController;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameLogicTest extends TestCase {


    @Test
    public void testTakeCountryLogic() {
        GameLogic logic = new GameLogic();
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE};
        logic.takeCountryLogic(0, Constants.PLAYER_COLOUR.GREEN, 1);
        logic.takeCountryLogic(1, Constants.PLAYER_COLOUR.GREY, 2);
        assertEquals(country_owner[0], Constants.PLAYER_COLOUR.GREEN);
        assertEquals(country_owner[1], Constants.PLAYER_COLOUR.GREY);
    }

    @Test
    public void testSetDiceToZero() {
        GameLogic logic = new GameLogic();
        Player player1 = new Player("Cam", Constants.PLAYER_COLOUR.GREY);
        Player player2 = new Player("Mike", Constants.PLAYER_COLOUR.BLUE);
        player1.setDiceNum(2);
        player2.setDiceNum(3);
        logic.setDiceToZero(player1, player2);
        assertEquals(0, player1.getDiceNum());
        assertEquals(0, player2.getDiceNum());
    }

    @Test
    public void testCalculateReinforcements(){
        GameLogic logic = new GameLogic();
        Player testPlayer = new Player("Max", Constants.PLAYER_COLOUR.RED);
        for(int i = 0 ; i < 7 ; i ++) {
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
    public void testCalculateContReinforcements(){
        GameLogic logic = new GameLogic();
        Player player = new Player("MARK", Constants.PLAYER_COLOUR.RED);
        for(int i = 0; i < 42;i++){
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

    @Test
    public void testSetRandomCountries() {
        GameLogic logic = new GameLogic();
        ArrayList<Integer> list = new ArrayList<>();
        logic.setRandomCountries();
        assertNotNull(list);
        assertTrue(list.get(0) >= 0 && list.get(0) <= 2);
        assertTrue(list.get(1) >= 0 && list.get(1) <= 2);
        assertTrue(list.get(2) >= 0 && list.get(2) <= 2);
    }

    @Test
    public void testTakeCountryTroops() {
        GameLogic logic = new GameLogic();
        Constants.PLAYER_COLOUR[] country_owner = {Constants.PLAYER_COLOUR.RED, Constants.PLAYER_COLOUR.ORANGE, Constants.PLAYER_COLOUR.PURPLE};
        int[] troopCount = {0, 0, 0};
        logic.takeCountryLogic(0, Constants.PLAYER_COLOUR.GREY, 3);
        logic.takeCountryLogic(1, Constants.PLAYER_COLOUR.GREY, 3);
        logic.takeCountryLogic(2, Constants.PLAYER_COLOUR.GREY, 5);
        assertEquals(2, troopCount[0]);
        assertEquals(3, troopCount[1]);
        assertEquals(5, troopCount[2]);
    }

    @Test
    public void testendInitPhaseANDgetInitPhase(){
        GameLogic logic = new GameLogic();
        assertTrue(logic.getInitPhase());
        logic.endInitPhase();
        assertFalse(logic.getInitPhase());
    }

    @Test
    public void testgetCountryIndexANDsetCountryIndex(){
        GameLogic logic = new GameLogic();
        assertEquals(0,logic.getCountryIndex());
        logic.setCountryIndex(4);
        assertEquals(4,logic.getCountryIndex());
        logic.setCountryIndex(100);
        assertEquals(100,logic.getCountryIndex());
        logic.setCountryIndex(432);
        assertEquals(432,logic.getCountryIndex());
    }

    @Test
    public void testsetRandomCountriesANDgetRandomCountries(){
        GameLogic logic = new GameLogic();
        logic.setRandomCountries();
        assertEquals(42,logic.getRandomCountries().size());
    }

    /**
     * This is to test the getters for each of the colour lists
     */
    @Test
    public void testgetOwnedColours(){
        GameLogic logic = new GameLogic();
        for(int i = 0; i < 10 ; i++) {
            logic.setOwnedGray(i, i);
        }
        assertEquals(1,logic.getOwnedGray().get(1).intValue());
        assertEquals(4,logic.getOwnedGray().get(4).intValue());
        assertNotSame(1, logic.getOwnedGray().get(4).intValue());
        assertEquals(5,logic.getOwnedGray().get(5).intValue());

        for(int i = 0; i < 10 ; i++) {
            logic.setOwnedGreen(i, i);
        }
        assertEquals(1,logic.getOwnedGreen().get(1).intValue());
        assertEquals(2,logic.getOwnedGreen().get(2).intValue());
        assertNotSame(1,logic.getOwnedGreen().get(5).intValue());
        assertEquals(9,logic.getOwnedGreen().get(9).intValue());

        for(int i = 0; i < 10 ; i++) {
            logic.setOwnedPurple(i, i);
        }
        assertEquals(1,logic.getOwnedPurple().get(1).intValue());
        assertEquals(3,logic.getOwnedPurple().get(3).intValue());
        assertNotSame(6,logic.getOwnedPurple().get(5).intValue());
        assertEquals(7,logic.getOwnedPurple().get(7).intValue());

        for(int i = 0; i < 10 ; i++) {
            logic.setOwnedOrange(i, i);
        }
        assertEquals(4,logic.getOwnedOrange().get(4).intValue());
        assertEquals(2,logic.getOwnedOrange().get(2).intValue());
        assertNotSame(7,logic.getOwnedOrange().get(4).intValue());
        assertEquals(8,logic.getOwnedOrange().get(8).intValue());


        //These below tests the conditional statements of the setters
        logic.setOwnedOrange(8,9);
        assertEquals(9,logic.getOwnedOrange().get(8).intValue());
        assertEquals(9,logic.getOwnedOrange().get(9).intValue());

        System.out.println(logic.getOwnedGreen().size());
        logic.setOwnedGreen(10,3);
        assertEquals(3,logic.getOwnedGreen().get(10).intValue());

    }

    @Test
    public void testgetTroop_countANDsetTroopCount(){
        GameLogic logic = new GameLogic();
        int[]arr = {1,2,3,4,5};
        logic.setTroop_count(arr);
        assertEquals(arr,logic.getTroop_count());
    }








}
