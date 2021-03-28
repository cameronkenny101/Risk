package Game;

import Online.ClientSideConnection;
import Online.OnlineGameHandler;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used for storing critical game data
 */
public class Player {

    private String name;
    private Constants.PLAYER_COLOUR colour;
    private ArrayList<Card> cardsInHand;//Cards in hand
    private int[] insignias = new int[4];//Keeps track of the number of each insignia
    private int diceNum;
    private int troops;
    private int initTroops;
    private boolean isTurn;
    private boolean conquerTerritory;

    OnlineGameHandler onlineGameHandler;
    private ClientSideConnection csc;

    /**
     * Constructor for the player
     *
     * @param name   Name provided in the login screen
     * @param colour Colour allocated
     */
    public Player(String name, Constants.PLAYER_COLOUR colour) {
        setName(name);
        setColour(colour);
        setTroops(Constants.INIT_UNITS_PLAYER - Constants.INIT_COUNTRIES_PLAYER); // Player must place 1 troop of each country it initializes with
        setTroops(3); // Player must place 1 troop of each country it initializes with
        setInitTroops(3);
        cardsInHand = new ArrayList<Card>();
    }

    // Constructors for online game
    public Player(String name, Constants.PLAYER_COLOUR colour, boolean isOnline) {
        this(name, colour);
        setOnlineGameHandler();
        csc = new ClientSideConnection();
        onlineGameHandler.connectPlayerToServer(name, colour, csc);
    }

    private void setOnlineGameHandler() {
        this.onlineGameHandler = OnlineGameHandler.getInstance();
    }

    public ClientSideConnection getCsc() {
        return csc;
    }

    public Player(String name, String color) {
        this(name, Constants.PLAYER_COLOUR.RED);
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

    public boolean isConquerTerritory() {
        return conquerTerritory;
    }

    public void setConquerTerritory(boolean conquerTerritory) {
        this.conquerTerritory = conquerTerritory;
    }

    public void addCardToHand(Card card) {
        cardsInHand.add(card);
        insignias[card.getCardIndex()]++;
    }

    public String printCardHand() {
        return "Deck:\n" + "Infantry: " + insignias[0] + "\n"
                + "Cavalry: " + insignias[1] + "\n"
                + "Artillery: " + insignias[2] + "\n"
                + "Wild Card: " + insignias[3] + "\n";
    }

    private boolean isAvailableCards(int[] insigniasID) {
        int[] insigniaCopy = insignias.clone();//Copy of the insignia count

        for (int i = 0; i < Card.SET_SIZE; i++) {
            if (insigniaCopy[insigniasID[i]]-- < 0) {
                return false;
            }
        }

        return true;
    }

    public boolean exchangeCards(int[] insigniasID) {
        if (!Card.isValidSet(insigniasID))
            throw new IllegalArgumentException("Not a valid set of insignias");
        if (!isAvailableCards(insigniasID))
            throw new IllegalArgumentException("You don't have enough cards");

        for (int i = 0; i < Card.SET_SIZE; i++) {
            for (int j = 0; j < cardsInHand.size(); j++) {
                if (insigniasID[i] == cardsInHand.get(j).getInsignia()) {
                    cardsInHand.remove(j);
                    break;
                }
            }
        }
        return true;
    }
}
