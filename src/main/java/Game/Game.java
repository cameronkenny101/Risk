package Game;

import UI.GameScreen.GameScreenController;

import java.util.ArrayList;

public class Game {

    GameScreenController uiController;
    Player player1, player2;
    GameLogic logic;

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
        logic = new GameLogic();
        logic.setRandomCountries();
        printPlayerToConsole();
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
        uiController.output.appendText("> It is " + player1.getColour() + " turn to choose there cards! \n");
        uiController.askQuestion("Press enter to choose your 9 cards from the deck");
    }

    /**
     * Starts the game, allowing players to choose there territory cards
     */
    public void start() {
        if (logic.getCountryIndex() == 0) {
            initCountries(Constants.PLAYER_COLOUR.RED, Constants.INIT_COUNTRIES_PLAYER, null);
            uiController.output.appendText("> It is " + player2.getColour() + " turn to choose there cards! \n");
            uiController.askQuestion("Press enter to choose your 9 cards from the deck");
        } else if (logic.getCountryIndex() == 9) {
            initCountries(Constants.PLAYER_COLOUR.BLUE, Constants.INIT_COUNTRIES_PLAYER, null);
            uiController.askQuestion("Press enter to let neutrals choose there cards");
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
            uiController.askQuestion("Press enter to roll the dice");
        } else {
            player2.setDiceNum(Dice.rollDice());
            uiController.output.appendText("> " + player2.getName() + " rolled a " + player2.getDiceNum() + "\n");
            if (Dice.bestRoll(player1.getDiceNum(), player2.getDiceNum()) > 0) {
                setTurn(player1);
            } else if (Dice.bestRoll(player1.getDiceNum(), player2.getDiceNum()) < 0) {
                setTurn(player2);
            } else {
                uiController.output.appendText("> The dice roll was a draw. Try again \n");
                logic.setDiceToZero(player1, player2);
                uiController.askQuestion("Press enter to roll the dice");
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
        uiController.output.appendText("> Everyone has allocated there troops! \n");
        uiController.output.appendText("> We must decide who goes first! \n");
        uiController.askQuestion("Press enter to roll the dice");
    }

    /**
     * used to give permission to a player to fortify his territories
     *
     * @param player player that won the roll
     */
    private void setTurn(Player player) {
        player.setTurn(true);
        logic.setDiceToZero(player1, player2);
        uiController.output.appendText("> " + player.getName() + " won the roll. " + player.getName() + " will go first \n");
        if (logic.getInitPhase()) {
            uiController.output.appendText("> " + player.getName() + ", you will now fortify your territories. You can place 3 troops at a time\n");
            uiController.askQuestion("How many troops do you want to place");
        } else {
            uiController.output.appendText("> FINISHED WEEK 2! \n");
            uiController.askQuestion("Do you want to fortify your territories");
        }
    }


    /**
     * @param p is either gonna be player 1 or 2
     * @return a boolean value if the player has successfully taken over all the countries
     */
    public boolean isWinner(Player p) {
        for (int i = 0; i < logic.country_owner.length; i++) {
            if (logic.country_owner[i] != p.getColour()) {
                return false;
            }
        }
        uiController.output.appendText(p.getName() + " has won!");
        return true;
    }

}
