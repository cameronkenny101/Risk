package Game;

import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

    public void takeCountryLogic(Constants.PLAYER_COLOUR[] country_owner, int[] troop_count, int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        country_owner[countryId] = colour;
        troop_count[countryId] += troops;
    }

    public void setDiceToZero(Player player1, Player player2) {
        player1.setDiceNum(0);
        player2.setDiceNum(0);
    }

    /**
     * Fills and allocates unselected countries by the players to neutrals
     * @param randomCountries
     */
    public void setRandomCountries(ArrayList<Integer> randomCountries, int numCountries) {
        for(int i = 0; i < numCountries; i++) {
            randomCountries.add(i);
        }
        Collections.shuffle(randomCountries);
    }
}
