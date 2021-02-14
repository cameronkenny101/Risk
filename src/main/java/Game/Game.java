package Game;

import GameScreen.GameScreenController;

import java.util.ArrayList;

import java.util.Collections;

public class Game {

    GameScreenController uiController;
    Player player1, player2;
    Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES];//Tells which players owns which country
    int[] troop_count = new int[Constants.NUM_COUNTRIES];//States the number of troops per country
    int countryIndex = 0;
    ArrayList<Integer> randomCountries = new ArrayList<>();

    public Game(GameScreenController uiController, Player player1, Player player2) throws Exception {
        this.uiController = uiController;
        this.player1 = player1;
        this.player2 = player2;
        fill(randomCountries);
        printPlayerToConsole();
    }

    private void printPlayerToConsole() {
        uiController.output.appendText("> Player 1 name: " + player1.getName() + "\n");
        uiController.output.appendText("> Player 1 color: " + player1.getColour() + "\n");
        uiController.output.appendText("> Player 2 name: " + player2.getName() + "\n");
        uiController.output.appendText("> Player 2 color: " + player2.getColour() + "\n");
        uiController.output.appendText("> " + player1.getColour() + " type yes to choose your cards \n");
    }

    public void start() throws Exception {
        if(countryIndex == 0) {
            initCountries(Constants.PLAYER_COLOUR.RED, Constants.INIT_COUNTRIES_PLAYER);
            uiController.output.appendText("> " + player2.getColour() + " type yes to choose your cards \n");
        }
        else if(countryIndex == 9) {
            initCountries(Constants.PLAYER_COLOUR.BLUE, Constants.INIT_COUNTRIES_PLAYER);
            uiController.output.appendText("> Are you ready for neutrals to select territories? \n");
        } else {
            initCountries(Constants.PLAYER_COLOUR.ORANGE, Constants.INIT_COUNTRIES_NEUTRAL);
            initCountries(Constants.PLAYER_COLOUR.PURPLE, Constants.INIT_COUNTRIES_NEUTRAL);
            initCountries(Constants.PLAYER_COLOUR.GREEN, Constants.INIT_COUNTRIES_NEUTRAL);
            initCountries(Constants.PLAYER_COLOUR.GREY, Constants.INIT_COUNTRIES_NEUTRAL);
        }
    }

    private void initCountries(Constants.PLAYER_COLOUR color, int numCountries) throws Exception {
        int numOccupyCountries = numCountries + countryIndex;
        for (; countryIndex < numOccupyCountries; countryIndex++) {
            takeCountry(randomCountries.get(countryIndex), color);
            uiController.output.appendText("> " + color + " selects " + Constants.COUNTRY_NAMES[randomCountries.get(countryIndex)] + " card\n");
        }
    }

    public void takeCountry(int country_id, Constants.PLAYER_COLOUR colour) throws Exception {
        country_owner[country_id] = colour;
        troop_count[country_id] = 2;
        uiController.setRegion(country_id, colour, troop_count[country_id]);
    }

    private void fill(ArrayList<Integer> randomCountries) {
        for(int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            randomCountries.add(i);
        }
        Collections.shuffle(randomCountries);
        for(int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            System.out.println(randomCountries.get(i));
        }
    }

}
