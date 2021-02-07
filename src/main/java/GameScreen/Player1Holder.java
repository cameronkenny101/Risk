package GameScreen;

import Game.Player;

public final class Player1Holder {

    private Player player;
    private final static Player1Holder INSTANCE = new Player1Holder();

    private Player1Holder() {}

    public static Player1Holder getInstance() {
        return INSTANCE;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }
}
