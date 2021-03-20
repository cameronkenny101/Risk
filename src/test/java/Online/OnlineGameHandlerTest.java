package Online;

import Game.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnlineGameHandlerTest {

    @Test
    void getInstance() {
        assertTrue(OnlineGameHandler.getInstance() != null);
        assertTrue(OnlineGameHandler.getInstance() instanceof OnlineGameHandler);

    }

    @Test
    void connectPlayerToServer() {
    }

    @Test
    void startGame() {
    //    ClientSideConnection P = new ClientSideConnection();
    //    OnlineGameHandler ogh = new OnlineGameHandler();
//        ogh.startGame("Mark", Constants.PLAYER_COLOUR.RED,P);
  //      assertEquals("Mark",P.dataOut.toString());
    }

    @Test
    void sendRandomCountries() {
    }

    @Test
    void sendIntArray() {
    }

    @Test
    void sendInt() {
    }

    @Test
    void sendNextMove() {
    }

    @Test
    void sendDiceRoll() {
    }
}