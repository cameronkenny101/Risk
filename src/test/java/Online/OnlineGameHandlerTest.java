package Online;

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
        ClientSideConnection P = new ClientSideConnection();
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