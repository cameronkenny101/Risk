package Online;

import Game.Constants;

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

}
