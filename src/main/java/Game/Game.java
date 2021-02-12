package Game;

import GameScreen.GameScreenController;

import java.util.concurrent.ThreadLocalRandom;

public class Game {

    GameScreenController uiController;
    Player player1, player2;
    private Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES];//Tells which players owns which country
    private int[] troop_count = new int[Constants.NUM_COUNTRIES];//States the number of troops per country

    public Game(GameScreenController uiController, Player player1, Player player2) throws Exception {
        this.uiController = uiController;
        this.player1 = player1;
        this.player2 = player2;
        Thread.sleep(300);
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
            int random_id = ThreadLocalRandom.current().nextInt(0, 41 + 1);
            while (country_owner[random_id] != null) {
                random_id = ThreadLocalRandom.current().nextInt(0, 41 + 1);
            }
            takeCountry(random_id, Constants.PLAYER_COLOUR.RED);
            uiController.output.appendText("> Red Takes " + Constants.COUNTRY_NAMES[random_id] + "\n");
        }
        //Init Blue Player
        for (int i = 0; i < 9; i++) {
            int random_id = ThreadLocalRandom.current().nextInt(0, 41 + 1);
            while (country_owner[random_id] != null) {
                random_id = ThreadLocalRandom.current().nextInt(0, 41 + 1);
            }
            takeCountry(random_id, Constants.PLAYER_COLOUR.BLUE);
            uiController.output.appendText("> Blue Takes " + Constants.COUNTRY_NAMES[random_id] + "\n");
        }
        //Init Neutral
        for (int i = 0; i < 42; i++) {
            if (country_owner[i] == null) {
                takeCountry(i, Constants.PLAYER_COLOUR.GREY);
                uiController.output.appendText("> Neutral Takes " + Constants.COUNTRY_NAMES[i] + "\n");
            }
        }
    }

    public void takeCountry(int country_id, Constants.PLAYER_COLOUR colour) throws Exception {
        country_owner[country_id] = colour;
        troop_count[country_id] = 2;
        uiController.setRegion(country_id, colour, troop_count[country_id]);
    }

}
