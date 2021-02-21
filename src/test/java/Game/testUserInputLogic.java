package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class testUserInputLogic extends TestCase {

    @Test
    public void testnextTurn(){
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
    public void testShortCountryName(){ //this test by proxy also tests the Levenstein algorithm
        UserInputLogic userInputLogic = new UserInputLogic();
        assertEquals(14,userInputLogic.shortCountryName("Icelan")); //Iceland
        assertEquals(8,userInputLogic.shortCountryName("Alas")); //Alaska
        assertEquals(27,userInputLogic.shortCountryName("Ch")); //China
        assertEquals(33,userInputLogic.shortCountryName("P")); //Peru

        assertNotSame(14,userInputLogic.shortCountryName("Mada"));
        assertNotSame(13,userInputLogic.shortCountryName("Ukraine"));
        assertNotSame(1,userInputLogic.shortCountryName("New"));
        assertNotSame(4,userInputLogic.shortCountryName("Mong"));
    }


}
