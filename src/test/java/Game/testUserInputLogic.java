package Game;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.jupiter.api.Test;

public class testUserInputLogic extends TestCase {

    Game game = new Game();
    Player player1 = new Player("Mark", Constants.PLAYER_COLOUR.RED);
    Player player2 = new Player("Cam", Constants.PLAYER_COLOUR.BLUE);
    UserInput userInput = new UserInput(game,player1,player2);

    @Test
    public void testNextTurn() {
        UserInputLogic userInputLogic = new UserInputLogic();
        Player player1 = new Player("Mark", Constants.PLAYER_COLOUR.RED);
        Player player2 = new Player("Cameron", Constants.PLAYER_COLOUR.BLUE);
        player1.setTurn(true);
        player2.setTurn(false);

        assertTrue(player1.isTurn());
        userInputLogic.nextTurn(player1, player2);
        assertFalse(player1.isTurn());
        assertTrue(player2.isTurn());

    }

    @Test
    public void testShortCountryName() {
        UserInputLogic userInputLogic = new UserInputLogic();
        assertEquals(14, userInputLogic.shortCountryName("Icelan")); //Iceland
        assertEquals(8, userInputLogic.shortCountryName("Alas")); //Alaska
        assertEquals(27, userInputLogic.shortCountryName("Ch")); //China
        assertEquals(33, userInputLogic.shortCountryName("P")); //Peru
        assertEquals(17, userInputLogic.shortCountryName("Ind")); //see if it handles ambiguous
        //inputs , this could  be interrupted by someone to mean India or Indonesia , the algorithm goes
        //with India due to it being shorter
        assertEquals(10, userInputLogic.shortCountryName("Western E"));
        assertEquals(10, userInputLogic.shortCountryName("Western U"));


        assertNotSame(14, userInputLogic.shortCountryName("Mada"));
        assertNotSame(13, userInputLogic.shortCountryName("Ukraine"));
        assertNotSame(1, userInputLogic.shortCountryName("New"));
        assertNotSame(6, userInputLogic.shortCountryName("Mong"));

    }

    @Test
    public void testLevenshteinAlgorithm() {
        UserInputLogic userInputLogic = new UserInputLogic();
        assertEquals(2, userInputLogic.LevenshteinDistance("Iceland", "ICELA"));
        assertEquals(2, userInputLogic.LevenshteinDistance("Iceland", "ICeLa"));
        assertEquals(2, userInputLogic.LevenshteinDistance("India", "dia"));
        assertEquals(4, userInputLogic.LevenshteinDistance("Siam", ""));
        assertEquals(4, userInputLogic.LevenshteinDistance("Western Europe", "Wstrn ERPE"));
    }

    @Test
    public void testAssertAdjacent(){
       userInput.battle.defenceCountryId = 0;
       userInput.battle.attackCountryId = 1;
       assertTrue(userInput.battle.assertAdjacent());

       userInput.battle.defenceCountryId = 5;
       userInput.battle.attackCountryId = 6;
       assertTrue(userInput.battle.assertAdjacent());

       userInput.battle.defenceCountryId = 40;
       userInput.battle.attackCountryId = 38;
       assertTrue(userInput.battle.assertAdjacent());

       userInput.battle.defenceCountryId = 10;
       userInput.battle.attackCountryId = 37;
       assertTrue(userInput.battle.assertAdjacent());

        userInput.battle.defenceCountryId = 13;
        userInput.battle.attackCountryId = 14;
        assertFalse(userInput.battle.assertAdjacent());

        userInput.battle.defenceCountryId = 32;
        userInput.battle.attackCountryId = 31;
        assertFalse(userInput.battle.assertAdjacent());
    }

    @Test
    public void testAssertValidAttackers(){
        userInput.battle.numAttackUnits = 2;
        userInput.battle.attackCountryId= 1;
        game.logic= new GameLogic();
        game.logic.troop_count[1] = 10;
        assertTrue(userInput.battle.assertValidAttackers());

        userInput.battle.numAttackUnits = 3;
        userInput.battle.attackCountryId= 6;
        game.logic.troop_count[6] = 5;
        assertTrue(userInput.battle.assertValidAttackers());

        userInput.battle.numAttackUnits = 5;
        userInput.battle.attackCountryId= 6;
        game.logic.troop_count[6] = 5;
        assertFalse(userInput.battle.assertValidAttackers()); //if they try attack with more than 3

        userInput.battle.numAttackUnits = 3;
        userInput.battle.attackCountryId= 26;
        game.logic.troop_count[26] = 2;
        assertFalse(userInput.battle.assertValidAttackers()); // if they try attack with more troops than on hand

        userInput.battle.numAttackUnits = 3;
        userInput.battle.attackCountryId= 33;
        game.logic.troop_count[33] = 3;
        assertFalse(userInput.battle.assertValidAttackers());
    }


}
