package GameScreen;

import Game.Player;

public final class Player2Holder {

    private Player player;
    private final static Player2Holder INSTANCE = new Player2Holder();

    private Player2Holder() {}

    public static Player2Holder getInstance() {
        return INSTANCE;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}