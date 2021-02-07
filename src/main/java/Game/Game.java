package Game;

import GameScreen.GameScreenController;
import PlayerChoosingScreen.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class Game {
    private Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES];//Tells which players owns which country
    private int[] troop_count = new int[Constants.NUM_COUNTRIES];//States the number of troops per country

    public void takeCountry(int country_id, Constants.PLAYER_COLOUR colour) {
        country_owner[country_id] = colour;
        troop_count[country_id] = 2;
    }

}
