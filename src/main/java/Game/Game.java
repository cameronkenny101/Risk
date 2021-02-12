package Game;

import GameScreen.GameScreenController;

import java.util.ArrayList;

import java.util.Collections;

public class Game {

    GameScreenController uiController;
    Player player1, player2;
    Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES];//Tells which players owns which country
    int[] troop_count = new int[Constants.NUM_COUNTRIES];//States the number of troops per country
    ArrayList<Integer> randomCountries = new ArrayList<>();

    public Game(GameScreenController uiController, Player player1, Player player2) throws Exception {
        this.uiController = uiController;
        this.player1 = player1;
        this.player2 = player2;
        fill(randomCountries);
        printPlayerToConsole();
        initMap();
    }

    private void printPlayerToConsole() throws Exception {
        uiController.output.appendText("> Player 1 name: " + player1.getName() + "\n");
        uiController.output.appendText("> Player 1 color: " + player1.getColour() + "\n");
        uiController.output.appendText("> Player 2 name: " + player2.getName() + "\n");
        uiController.output.appendText("> Player 2 color: " + player2.getColour() + "\n");
    }

    private void initMap() throws Exception {
        uiController.output.appendText("> Randomly allocating countries\n");

        //Init Red Player
        for (int i = 0; i < 9; i++) {
            takeCountry(randomCountries.get(i), Constants.PLAYER_COLOUR.RED);
            uiController.output.appendText("> Red Takes " + Constants.COUNTRY_NAMES[randomCountries.get(i)] + "\n");
        }
        //Init Blue Player
        for (int i = 9; i < 18; i++) {
            takeCountry(randomCountries.get(i), Constants.PLAYER_COLOUR.BLUE);
            uiController.output.appendText("> Blue Takes " + Constants.COUNTRY_NAMES[randomCountries.get(i)] + "\n");
        }
        //Init Neutral 1
        for (int i = 18; i < 24; i++) {
            takeCountry(randomCountries.get(i), Constants.PLAYER_COLOUR.GREY);
            uiController.output.appendText("> Gray Takes " + Constants.COUNTRY_NAMES[randomCountries.get(i)] + "\n");
        }
        //Init Neutral 2
        for (int i = 24; i < 30; i++) {
            takeCountry(randomCountries.get(i), Constants.PLAYER_COLOUR.GREEN);
            uiController.output.appendText("> Green Takes " + Constants.COUNTRY_NAMES[randomCountries.get(i)] + "\n");
        }
        //Init Neutral 3
        for (int i = 30; i < 36; i++) {
            takeCountry(randomCountries.get(i), Constants.PLAYER_COLOUR.PURPLE);
            uiController.output.appendText("> Pink Takes " + Constants.COUNTRY_NAMES[randomCountries.get(i)] + "\n");
        }
        //Init Neutral 4
        for (int i = 36; i < 42; i++) {
            takeCountry(randomCountries.get(i), Constants.PLAYER_COLOUR.ORANGE);
            uiController.output.appendText("> Orange Takes " + Constants.COUNTRY_NAMES[randomCountries.get(i)] + "\n");
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
