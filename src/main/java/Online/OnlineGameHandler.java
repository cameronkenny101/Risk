package Online;

import Game.Constants;
import Game.Player;

import java.io.IOException;
import java.util.ArrayList;

public class OnlineGameHandler {

    private static OnlineGameHandler instance = new OnlineGameHandler();

    private OnlineGameHandler() { }

    public static OnlineGameHandler getInstance() {
        return instance;
    }

    public void connectPlayerToServer(String name, Constants.PLAYER_COLOUR colour, ClientSideConnection csc) {
        Thread t = new Thread(() -> startGame(name, colour, csc));
        t.start();
    }

    public void startGame(String name, Constants.PLAYER_COLOUR colour, ClientSideConnection csc) {
        csc.writePlayerInfo(name, colour);
    }

    public void sendRandomCountries(ArrayList<Integer> randomCountries, ClientSideConnection csc) {
        csc.writeArrayInfo(randomCountries);
    }

    public void sendIntArray(int[] array, boolean isPlayer1, ClientSideConnection csc) {
        csc.writeIntArrayInfo(array, isPlayer1);
    }

    public void sendNextMove(ClientSideConnection csc) {
        csc.writeBoolean(true);
    }

    public void sendDiceRoll(ClientSideConnection csc, int diceNum) {
        csc.writeInt(diceNum);
    }

}
