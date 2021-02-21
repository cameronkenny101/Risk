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


}
