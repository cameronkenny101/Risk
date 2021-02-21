package Game;

public class GameLogic {

    public void takeCountryLogic(Constants.PLAYER_COLOUR[] country_owner, int[] troop_count, int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        country_owner[countryId] = colour;
        troop_count[countryId] += troops;
    }
}
