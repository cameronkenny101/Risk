package Game;

import SocketProgramming.ClientSideConnection;

/**
 * This class is used for storing critical game data
 */
public class Player {

    private String name;
    private Constants.PLAYER_COLOUR colour;
    private int commanderID;
    private int diceNum;
    private int troops;
    private int initTroops;
    private boolean isTurn;
    private ClientSideConnection csc;

    /**
     * Constructor for the player
     * @param name Name provided in the login screen
     * @param colour Colour allocated
     * @param commanderID their allocated ID
     */
    public Player(String name, Constants.PLAYER_COLOUR colour, int commanderID) {
        setName(name);
        setColour(colour);
        setCommanderID(commanderID);
        setTroops(3); // Player must place 1 troop of each country it initializes with
        setInitTroops(3);
    }

    // Constructors for online game
    public Player(String name, Constants.PLAYER_COLOUR colour, int commanderID, boolean isOnline) {
        this(name, colour, commanderID);
        connectToServer();
        Thread t = new Thread(() -> startGame(name, colour));
        t.start();
    }

    public Player(String name, String color) {
        this(name, Constants.PLAYER_COLOUR.RED, 1);
    }

    public void startGame(String name, Constants.PLAYER_COLOUR colour) {
        csc.writePlayerInfo(name, colour);
    }

    public void connectToServer() {
        csc = new ClientSideConnection();
    }

    public ClientSideConnection getCsc() {
        return csc;
    }

    /**
     * Below is a variety of getters and setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public Constants.PLAYER_COLOUR getColour() {
        return colour;
    }

    public void setColour(Constants.PLAYER_COLOUR colour) {
        this.colour = colour;
    }

    public int getCommanderID() {
        return commanderID;
    }

    public void setCommanderID(int commanderID) {
        this.commanderID = commanderID;
    }

    public int getTroops() {
        return troops;
    }

    public void setTroops(int troops) {
        this.troops = troops;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public int getDiceNum() {
        return diceNum;
    }

    public void setDiceNum(int diceNum) {
        this.diceNum = diceNum;
    }

    public int getInitTroops() {
        return initTroops;
    }

    public void setInitTroops(int initTroops) {
        this.initTroops = initTroops;
    }
}
