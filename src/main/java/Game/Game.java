package Game;

import Online.OnlineGameHandler;
import UI.GameScreen.GameScreenController;
import javafx.application.Platform;
import org.graalvm.compiler.lir.LIRInstruction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    GameScreenController uiController;
    Player player1, player2;
    GameLogic logic;
    OnlineGameHandler onlineGameHandler;
    UserInput userInput;
    boolean isOnline = false;
    boolean isPlayer1;

    /**
     * Used to start up the game and create player objects and a uiController
     *
     * @param uiController this is used to control the gameplay on the screen
     * @param player1      this is used to interact with the player1 instance of the player class
     * @param player2      this is used to interact with the player2 instance of the player class
     */
    public Game(GameScreenController uiController, Player player1, Player player2) {
        this.uiController = uiController;
        this.player1 = player1;
        this.player2 = player2;
        initClasses();
        printPlayerToConsole();
    }

    // Online Constructor
    public Game(GameScreenController uiController, Player player) {
        this.uiController = uiController;
        this.isOnline = true;
        this.onlineGameHandler = OnlineGameHandler.getInstance();
        initClasses();
        if(player.getCsc().getPlayerID() == 1) {
            this.player1 = player;
            isPlayer1 = true;
            uiController.output.appendText("> Waiting for player 2 \n");
            Thread t = new Thread(this::startGame);
            t.start();
            onlineGameHandler.sendRandomCountries(logic.getRandomCountries(), player1.getCsc());
        } else {
            String[] playerInfo = player.getCsc().receivePlayerInfo();
            isPlayer1 = false;
            logic.setRandomCountriesOnline(player.getCsc().receiveArrayInfo());
            this.player1 = new Player(playerInfo[0], playerInfo[1]);
            this.player2 = player;
            player2.setColour(Constants.PLAYER_COLOUR.BLUE);
            printPlayerToConsole();
            player1.setTurn(true);
            player2.setTurn(false);
        }
    }

    private void initClasses() {
        logic = new GameLogic();
        logic.setRandomCountries();
    }

    /**
     * default constructor for testing
     */
    public Game() {

    }

    /**
     * Used to display basic player info on game start up
     */
    private void printPlayerToConsole() {
        uiController.output.appendText("> Player 1 name: " + player1.getName() + "\n");
        uiController.output.appendText("> Player 1 color: " + player1.getColour() + "\n");
        uiController.output.appendText("> Player 2 name: " + player2.getName() + "\n");
        uiController.output.appendText("> Player 2 color: " + player2.getColour() + "\n");
        uiController.output.appendText("> It is " + player1.getColour() + " turn to choose their cards! \n");
        if(isOnline && !isPlayer1) {
            uiController.output.appendText("> Wait for player 1 to choose there cards\n");
            Thread t = new Thread(() -> pickUserTerritories(player1, player2));
            t.start();
        }
        else
            uiController.askQuestion("Press enter to choose your 9 cards from the deck");
    }

    public void startGame() {
        String[] playerInfo = player1.getCsc().receivePlayerInfo();
        this.player2 = new Player(playerInfo[0], Constants.PLAYER_COLOUR.BLUE);
        printPlayerToConsole();
        player1.setTurn(true);
        player2.setTurn(false);
    }

    public void pickUserTerritories(Player player, Player nextPlayer) {
        System.out.println(nextPlayer.getCsc().receiveBoolean());
        Platform.runLater(() -> {
            initCountries(player.getColour(), Constants.INIT_COUNTRIES_PLAYER, null);
            if(player.getColour() == Constants.PLAYER_COLOUR.RED) {
                uiController.output.appendText("> It is " + nextPlayer.getColour() + "'s turn to choose their cards! \n");
                uiController.askQuestion("Press enter to choose your 9 cards from the deck");
            } else {
                uiController.output.appendText("> Players have chosen all there cards\n");
                uiController.askQuestion("Press enter to let neutrals choose their cards");
            }
        });
    }

    public void receiveDiceRoll(Player player, Player nextPlayer) throws IOException {
        int diceNum = player.getCsc().receiveInt();
        System.out.println("Receiving dice roll " + diceNum);
        nextPlayer.setDiceNum(diceNum);
        uiController.output.appendText("> " + nextPlayer.getName() + " rolled a " + nextPlayer.getDiceNum() + "\n");
        if(player.getColour() == Constants.PLAYER_COLOUR.RED)
            diceRollWinner();
        else
            uiController.askQuestion("Press enter to roll the dice");
    }

    public void pickNeutralTerritories(Player nextPlayer) {
        System.out.println(nextPlayer.getCsc().receiveBoolean());
        Platform.runLater(() -> {
            initCountries(Constants.PLAYER_COLOUR.ORANGE, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedOrange());
            initCountries(Constants.PLAYER_COLOUR.PURPLE, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedPurple());
            initCountries(Constants.PLAYER_COLOUR.GREEN, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedGreen());
            initCountries(Constants.PLAYER_COLOUR.GREY, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedGray());
            initOnlineDiceRoll(player2, player1);
        });
    }

    public void initOnlineDiceRoll(Player player, Player nextPlayer) {
        uiController.output.appendText("> Wait for " + nextPlayer.getName() + " to roll the dice\n");
        Thread thread = new Thread(() -> {
            try {
                receiveDiceRoll(player, nextPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    /**
     * Starts the game, allowing players to choose their territory cards
     */
    public void start() {
        if (logic.getCountryIndex() == 0) {
            initCountries(player1.getColour(), Constants.INIT_COUNTRIES_PLAYER, null);
            if(isOnline) {
                uiController.output.appendText("> It is " + player2.getColour() + "'s turn to choose their cards! \n");
                uiController.output.appendText("> Wait for player 2 to choose there cards\n");
                Thread t = new Thread(() -> pickUserTerritories(player2, player1));
                t.start();
            } else {
                uiController.output.appendText("> It is " + player2.getColour() + "'s turn to choose their cards! \n");
                uiController.askQuestion("Press enter to choose your 9 cards from the deck");
            }
        } else if (logic.getCountryIndex() == 9) {
            initCountries(player2.getColour(), Constants.INIT_COUNTRIES_PLAYER, null);
            if(isOnline) {
                uiController.output.appendText("> Wait for player 1 to choose random cards for the neutral territories\n");
                Thread t = new Thread(() -> pickNeutralTerritories(player2));
                t.start();
            } else {
                uiController.askQuestion("Press enter to let neutrals choose their cards");
            }
        } else {
            initCountries(Constants.PLAYER_COLOUR.ORANGE, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedOrange());
            initCountries(Constants.PLAYER_COLOUR.PURPLE, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedPurple());
            initCountries(Constants.PLAYER_COLOUR.GREEN, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedGreen());
            initCountries(Constants.PLAYER_COLOUR.GREY, Constants.INIT_COUNTRIES_NEUTRAL, logic.getOwnedGray());
            uiController.output.appendText("> " + player1.getName() + " must now roll the dice\n");
            uiController.askQuestion("Press enter to roll the dice");
        }
    }

    /**
     * This is the first operation of the game by getting the two players to roll a dice to see who places their armies first
     */
    public void setFirstTurn() {
        if (player1.getDiceNum() == 0) {
            player1.setDiceNum(Dice.rollDice());
            uiController.output.appendText("> " + player1.getName() + " rolled a " + player1.getDiceNum() + "\n");
            uiController.output.appendText("> " + player2.getName() + " must now roll the dice \n");
            if(isOnline) {
                player1.onlineGameHandler.sendDiceRoll(player1.getCsc(), player1.getDiceNum());
                initOnlineDiceRoll(player1, player2);
            } else {
                uiController.askQuestion("Press enter to roll the dice");
            }
        } else {
            player2.setDiceNum(Dice.rollDice());
            uiController.output.appendText("> " + player2.getName() + " rolled a " + player2.getDiceNum() + "\n");
            if(isOnline)
                player2.onlineGameHandler.sendDiceRoll(player2.getCsc(), player2.getDiceNum());
            diceRollWinner();
        }
    }

    public void diceRollWinner() {
        if (Dice.bestRoll(player1.getDiceNum(), player2.getDiceNum()) > 0) {
            setTurn(player1, player2);
        } else if (Dice.bestRoll(player1.getDiceNum(), player2.getDiceNum()) < 0) {
            setTurn(player2, player1);
        } else {
            uiController.output.appendText("> The dice roll was a draw. Try again \n");
            logic.setDiceToZero(player1, player2);
            if(isOnline && isPlayer1) {
                player1.getCsc().writeBoolean(true);
                uiController.askQuestion("Press enter to roll the dice");
            } else if(isOnline) {
                initOnlineDiceRoll(player2, player1);
            }
        }
    }

    /**
     * Randomly allocates the countries as if the players drew cards from a deck
     *
     * @param color          the colour of the player/neutral
     * @param numCountries   number of countries
     * @param ownedCountries this is a list of all countries that are already assigned to a player
     */
    private void initCountries(Constants.PLAYER_COLOUR color, int numCountries, ArrayList<Integer> ownedCountries) {
        int numOccupyCountries = numCountries + logic.getCountryIndex();
        for (; logic.getCountryIndex() < numOccupyCountries; logic.setCountryIndex(logic.getCountryIndex() + 1)) {
            int i = logic.getRandomCountries().get(logic.getCountryIndex());
            takeCountry(i, color, 1);
            if (ownedCountries != null)
                ownedCountries.add(logic.getRandomCountries().get(logic.getCountryIndex()));
            uiController.output.appendText("> " + color + " selects " + Constants.COUNTRY_NAMES.get(i) + " card\n");
        }
        player1.setTurn(!player1.isTurn());
        player2.setTurn(!player2.isTurn());
    }

    /**
     * function used to make a country fall under the ownership of another player
     *
     * @param countryId a countries ID
     * @param colour    a players colour
     * @param troops    amount of troops to be stationed
     */
    public void takeCountry(int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        logic.takeCountryLogic(countryId, colour, troops);
        uiController.setRegion(countryId, colour, logic.getTroop_count()[countryId]);
        if (troops > 0)
            uiController.output.appendText("> " + colour + " puts " + troops + " into " + Constants.COUNTRY_NAMES.get(countryId) + "\n");
        else
            uiController.output.appendText("> " + colour + " removes " + -troops + " out of " + Constants.COUNTRY_NAMES.get(countryId) + "\n");
    }

    /**
     * Sets the country on initialisation
     *
     * @param countryId a countries ID
     * @param colour    a players colour
     * @param troops    troop number
     */
    public void setCountry(int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        if (logic.getCountry_owner()[countryId] == colour) {
            takeCountry(countryId, colour, troops);
        }
    }

    /**
     * ends the first phases of the game ie the set up
     */
    public void endInitPhase() {
        logic.endInitPhase();
        uiController.output.appendText("> Everyone has allocated their troops! \n");
        uiController.output.appendText("> We must decide who goes first! \n");
        if(isOnline) {
            if (isPlayer1)
                uiController.askQuestion("Press enter to roll the dice");
            else {
                // uiController.output.appendText("> Wait for " + player1.getName() + " to roll there dice");
                initOnlineDiceRoll(player2, player1);
            }
        } else
            uiController.askQuestion("Press enter to roll the dice");

    }

    /**
     * used to give permission to a player to fortify his territories
     *
     * @param player player that won the roll
     */
    private void setTurn(Player player, Player nextPlayer) {
        if(isOnline && isPlayer1) {
            player1.getCsc().writeBoolean(false);
            player1.getCsc().writeBoolean(player.getColour() == player1.getColour());
        }
        player.setTurn(true);
        nextPlayer.setTurn(false);
        logic.setDiceToZero(player1, player2);
        uiController.output.appendText("> " + player.getName() + " won the roll. " + player.getName() + " will go first \n");
        if (logic.getInitPhase()) {
            if(isOnline)
                setOnlineTurn(player, nextPlayer);
            else {
                uiController.output.appendText("> " + player.getName() + ", you will now reinforce your territories. You can place 3 troops at a time\n");
                uiController.askQuestion("How many troops do you want to place");
            }
        } else {
            //Asks Battle Question START OF BATTLE
            if(isOnline) {
                if (isPlayer1 && player1.isTurn() || !isPlayer1 && player2.isTurn())
                    uiController.askQuestion("Would you like to invade a country? (yes/no)");
                else if (!isPlayer1) {
                    uiController.output.appendText("> Wait for " + player1.getName() + " to finish the battle sequence\n");
                    threadForBattle(player2, player1);
                } else {
                    uiController.output.appendText("> Wait for " + player2.getName() + " to finish the battle sequence\n");
                    threadForBattle(player1, player2);
                }
            } else
                uiController.askQuestion("Would you like to invade a country? (yes/no)");
            // uiController.askQuestion("Do you want to fortify your territories");
        }
    }

    private void threadForBattle(Player player, Player nextPlayer) {
        Thread t = new Thread(() -> {
            try {
                waitForBattle(player, nextPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    private void setOnlineTurn(Player player, Player nextPlayer) {
        if(isPlayer1 && (player.getColour() == player1.getColour()) || !isPlayer1 && (player.getColour() == player2.getColour())) {
            uiController.output.appendText("> " + player.getName() + ", you will now fortify your territories. You can place 3 troops at a time. You have " + player.getTroops() + " troops left to place.\n");
            uiController.askQuestion("How many troops do you want to place");
        } else {
            uiController.output.appendText("> Wait for " + player.getName() + " to fortify there territories\n");
            Thread t = new Thread(() -> {
                try {
                    reinforcementTurn(player, nextPlayer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }

    private void waitForBattle(Player player, Player nextPlayer) throws IOException {
        int numAttackingTroops = player.getCsc().receiveInt();
        int attackCountry = player.getCsc().receiveInt();
        int defendingCountry = player.getCsc().receiveInt();
        userInput.battle.defenceCountryId = defendingCountry;
        uiController.output.appendText("> " + Constants.COUNTRY_NAMES.get(defendingCountry) + " is under attack from " + Constants.COUNTRY_NAMES.get(attackCountry) + "!\n");
        uiController.output.appendText("> " + Constants.COUNTRY_NAMES.get(attackCountry) + " is attacking with " + numAttackingTroops + "\n");
        uiController.askQuestion("How many units do you wish to defend for you?");
    }

    public void reinforcementTurn(Player player, Player nextPlayer) throws IOException {
        boolean cont = nextPlayer.getCsc().receiveBoolean();
        int troops = nextPlayer.getCsc().receiveInt();
        int countryIndex = nextPlayer.getCsc().receiveInt();
        int[] countryArray = nextPlayer.getCsc().receiveIntArrayInfo();
        setCountry(countryIndex, player.getColour(), troops - logic.troop_count[countryIndex]);
        userInput.neutralTurnCountdown--;
        player.setTroops(player.getTroops() - 3);
        if (!Arrays.equals(countryArray, logic.getTroop_count())) {
            logic.setTroop_count(countryArray);
            uiController.setMap(countryArray, logic.getCountry_owner());
            uiController.output.appendText("> Neutrals have reinforced there territories\n");
        }
        userInput.userInputLogic.nextTurn(player, nextPlayer);
        if(cont) {
            uiController.output.appendText("> " + nextPlayer.getName() + ", you will now fortify your territories. You can place 3 troops at a time. You have " + nextPlayer.getTroops() + " troops left to place.\n");
            uiController.askQuestion("How many troops do you want to place");
        } else
            endInitPhase();
    }

    public void receiveUserInput(UserInput userInput) {
        this.userInput = userInput;
    }


    /**
     * @param p is either gonna be player 1 or 2
     * @param arrayOfCountries this is the country_owner array from GameLogic, i had to pass it in
     *                         manually because the testisWinner would give me a null pointer if i didnt pass it in
     * @return a boolean value if the player has successfully taken over all the countries
     */
    public boolean isWinner(Player p,Constants.PLAYER_COLOUR[] arrayOfCountries) {
        for (Constants.PLAYER_COLOUR arrayOfCountry : arrayOfCountries) {
            if (arrayOfCountry != p.getColour()) {
                return false;
            }
        }
//        uiController.output.appendText(p.getName() + " has won!");
        return true;
    }
}
