package Game;

import GameScreen.GameScreenController;
import java.util.ArrayList;
import java.util.Collections;

public class Game {

    GameScreenController uiController;
    Player player1, player2;
    Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES];//Tells which players owns which country
    int[] troop_count = new int[Constants.NUM_COUNTRIES];//States the number of troops per country
    Dice dice;
    int countryIndex = 0;
    boolean initPhase = true;
    ArrayList<Integer> randomCountries = new ArrayList<>();

    public Game(GameScreenController uiController, Player player1, Player player2) {
        this.uiController = uiController;
        this.player1 = player1;
        this.player2 = player2;
        dice = new Dice();
        fill(randomCountries);
        printPlayerToConsole();
    }

    private void printPlayerToConsole() {
        uiController.output.appendText("> Player 1 name: " + player1.getName() + "\n");
        uiController.output.appendText("> Player 1 color: " + player1.getColour() + "\n");
        uiController.output.appendText("> Player 2 name: " + player2.getName() + "\n");
        uiController.output.appendText("> Player 2 color: " + player2.getColour() + "\n");
        uiController.output.appendText("> It is " + player1.getColour() + " turn to choose there cards! \n");
        uiController.askQuestion("Press enter to choose your 9 cards from the deck");
    }

    public void start() {
        if(countryIndex == 0) {
            initCountries(Constants.PLAYER_COLOUR.RED, Constants.INIT_COUNTRIES_PLAYER);
            uiController.output.appendText("> It is " + player2.getColour() + " turn to choose there cards! \n");
            uiController.askQuestion("Press enter to choose your 9 cards from the deck");
        }
        else if(countryIndex == 9) {
            initCountries(Constants.PLAYER_COLOUR.BLUE, Constants.INIT_COUNTRIES_PLAYER);
            uiController.askQuestion("Press enter to let neutrals choose there cards");
        } else {
            initCountries(Constants.PLAYER_COLOUR.ORANGE, Constants.INIT_COUNTRIES_NEUTRAL);
            initCountries(Constants.PLAYER_COLOUR.PURPLE, Constants.INIT_COUNTRIES_NEUTRAL);
            initCountries(Constants.PLAYER_COLOUR.GREEN, Constants.INIT_COUNTRIES_NEUTRAL);
            initCountries(Constants.PLAYER_COLOUR.GREY, Constants.INIT_COUNTRIES_NEUTRAL);
            uiController.output.appendText("> " + player1.getName() + " must now roll the dice\n");
            uiController.askQuestion("Press enter to roll the dice");
        }
    }

    public void setFirstTurn() {
        if (player1.getDiceNum() == 0) {
            player1.setDiceNum(dice.rollDice());
            uiController.output.appendText("> " + player1.getName() + " rolled a " + player1.getDiceNum() + "\n");
            uiController.output.appendText("> " + player2.getName() + " must now roll the dice \n");
            uiController.askQuestion("Press enter to roll the dice");
        } else {
            player2.setDiceNum(dice.rollDice());
            uiController.output.appendText("> " + player2.getName() + " rolled a " + player2.getDiceNum() + "\n");
            if (dice.bestRoll(player1.getDiceNum(), player2.getDiceNum()) > 0) {
                player1.setTurn(true);
                uiController.output.appendText("> " + player1.getName() + " won the roll. " + player1.getName() + " will go first \n");
                uiController.output.appendText("> " + player1.getName() + ", you will now fortify your territories. You can place 3 troops at a time\n");
                uiController.askQuestion("How many troops do you want to place");
            } else if (dice.bestRoll(player1.getDiceNum(), player2.getDiceNum()) < 0) {
                player2.setTurn(true);
                uiController.output.appendText("> " + player2.getName() + " won the roll. " + player2.getName() + " will go first \n");
                uiController.output.appendText("> " + player2.getName() + ", you will now fortify your territories. You can place 3 troops at a time\n");
                uiController.askQuestion("How many troops do you want to place");
            } else {
                uiController.output.appendText("> The dice roll was a draw. Try again \n");
                player1.setDiceNum(0);
                player2.setDiceNum(0);
                uiController.askQuestion("Press enter to roll the dice");
            }
        }
    }

    private void initCountries(Constants.PLAYER_COLOUR color, int numCountries) {
        int numOccupyCountries = numCountries + countryIndex;
        for (; countryIndex < numOccupyCountries; countryIndex++) {
            takeCountry(randomCountries.get(countryIndex), color, 1);
            uiController.output.appendText("> " + color + " selects " + Constants.COUNTRY_NAMES.get(countryIndex) + " card\n");
        }
    }

    public void takeCountry(int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        country_owner[countryId] = colour;
        troop_count[countryId] += troops;
        uiController.setRegion(countryId, colour, troop_count[countryId]);
        uiController.output.appendText("> " + colour + " puts " + troops + " into " + Constants.COUNTRY_NAMES.get(countryId) + "\n");
    }

    public boolean setCountry(int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        if(country_owner[countryId] == colour) {
            troop_count[countryId] += troops;
            uiController.setRegion(countryId, colour, troop_count[countryId]);
            uiController.output.appendText("> " + colour + " puts " + troops + " into " + Constants.COUNTRY_NAMES.get(countryId) + "\n");
            return true;
        } else
            return false;
    }

    private void fill(ArrayList<Integer> randomCountries) {
        for(int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            randomCountries.add(i);
        }
        Collections.shuffle(randomCountries);
    }

}
