package Game;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class PlayerTest extends TestCase {
    @Test
    public void testName() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);

        assertEquals("Cameron", player.getName());

        player.setName("Mark");

        assertEquals("Mark", player.getName());
    }

    @Test
    public void testColour() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);
        assertEquals(Constants.PLAYER_COLOUR.RED, player.getColour());
        Player player1 = new Player("Cameron", Constants.PLAYER_COLOUR.BLUE);
        assertEquals(Constants.PLAYER_COLOUR.BLUE, player1.getColour());
        Player player2 = new Player("Cameron", Constants.PLAYER_COLOUR.GREY);
        assertEquals(Constants.PLAYER_COLOUR.GREY, player2.getColour());
    }

    @Test
    public void testTurn() {
        Player player = new Player("Cameron", Constants.PLAYER_COLOUR.RED);
        player.setTurn(true);
        assertTrue(player.isTurn());
        player.setTurn(false);
        assertFalse(player.isTurn());
        Player player2 = new Player("Ash", Constants.PLAYER_COLOUR.BLUE);
        player2.setTurn(true);
        assertTrue(player2.isTurn());
        player2.setTurn(false);
        assertFalse(player2.isTurn());
    }


    @Test
    public void testInitTroops() {
        Player player = new Player("Mark", Constants.PLAYER_COLOUR.BLUE);
        assertEquals(player.getInitTroops(), 3);
        player.setInitTroops(2);
        assertEquals(player.getInitTroops(), 2);
    }

    @Test
    public void TestAddCardToHand(){
        Player player = new Player("MARK", Constants.PLAYER_COLOUR.RED);
        Card card = new Card(0,1);
        assertEquals(player.getCardsInHand(),0);
        player.addCardToHand(card);
        assertEquals(1,player.getCardsInHand());

        for(int i = 2; i < 27;i++){
            player.addCardToHand(new Card(i % 3,i));
        }
        assertEquals(26,player.getCardsInHand());

    }

    @Test
    public void TestUpdateTroops(){
        Player player = new Player("Mark" , Constants.PLAYER_COLOUR.BLUE);
        player.setTroops(5);
        assertEquals(5,player.getTroops());
        player.updateTroops(10);
        assertEquals(15,player.getTroops());

        player.updateTroops(-10);
        assertEquals(player.getTroops(),5);
    }

    @Test
    public void TestRemoveCards() {
        Player player = new Player("Mark", Constants.PLAYER_COLOUR.RED);

        for (int i = 0; i < 10; i++) {
            player.addCardToHand(new Card(0, 2));
        }

            assertEquals(10, player.getCardsInHand());

            int[] cardsToRemove = {1, 2, 0,0};
            player.removeCards(cardsToRemove);
            assertEquals(10, player.getCardsInHand());
        }
    }
