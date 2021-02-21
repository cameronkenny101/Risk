package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class testUserInputLogic extends TestCase {

    @Test
    public void testNextTurn() {
        UserInputLogic userInputLogic = new UserInputLogic();
        Player player1=new Player("Mark", Constants.PLAYER_COLOUR.RED,1);
        Player player2=new Player("Cameron", Constants.PLAYER_COLOUR.BLUE,2);
        player1.setTurn(true);
        player2.setTurn(false);

        assertTrue(player1.isTurn());
        userInputLogic.nextTurn(player1,player2);
        assertFalse(player1.isTurn());
        assertTrue(player2.isTurn());

    }

    @Test
    public void testShortCountryName() {
        UserInputLogic userInputLogic = new UserInputLogic();
        assertEquals(14,userInputLogic.shortCountryName("Icelan")); //Iceland
        assertEquals(8,userInputLogic.shortCountryName("Alas")); //Alaska
        assertEquals(27,userInputLogic.shortCountryName("Ch")); //China
        assertEquals(33,userInputLogic.shortCountryName("P")); //Peru
        assertEquals(17,userInputLogic.shortCountryName("Ind")); //see if it handles ambiguous
        //inputs , this could  be interrupted by someone to mean India or Indonesia , the algorithm goes
        //with India due to it being shorter
        assertEquals(10,userInputLogic.shortCountryName("Western E"));
        assertEquals(10,userInputLogic.shortCountryName("Western U"));


        assertNotSame(14,userInputLogic.shortCountryName("Mada"));
        assertNotSame(13,userInputLogic.shortCountryName("Ukraine"));
        assertNotSame(1,userInputLogic.shortCountryName("New"));
        assertNotSame(6,userInputLogic.shortCountryName("Mong"));

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


}
