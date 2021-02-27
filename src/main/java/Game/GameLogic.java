package Game;

import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

    protected Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES]; //Tells which players owns which country
    private int[] troop_count = new int[Constants.NUM_COUNTRIES];//States the number of troops per country
    private ArrayList<Integer> ownedOrange = new ArrayList<>(); // Countries Orange neutral owns
    private ArrayList<Integer> ownedPurple = new ArrayList<>(); // Countries Purple neutral owns
    private ArrayList<Integer> ownedGreen = new ArrayList<>(); // Countries Green neutral owns
    private ArrayList<Integer> ownedGray = new ArrayList<>(); // Countries Gray neutral owns
    private int countryIndex = 0;
    private boolean initPhase = true;
    private ArrayList<Integer> randomCountries = new ArrayList<>();

    public void takeCountryLogic(int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        country_owner[countryId] = colour;
        troop_count[countryId] += troops;
    }

    public void setDiceToZero(Player player1, Player player2) {
        player1.setDiceNum(0);
        player2.setDiceNum(0);
    }

    /**
     * Fills and allocates unselected countries by the players to neutrals
     * @param
     */
    public void setRandomCountries() {
        for(int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            randomCountries.add(i);
        }
        Collections.shuffle(randomCountries);
    }

    public int calculateReinforcements(Player player){
        int countryControlled = 0;
        for(int i = 0 ; i < country_owner.length; i ++){
            if(country_owner[i] == player.getColour()){
                countryControlled++;
            }
        }

        if(countryControlled <= 8){
            return 3;
        }

        return countryControlled/3; //As per the game rules in brightspace, under Phase 1 of the rules ( fraction is ignored)
    }


    public void endInitPhase() {
        initPhase = false;
    }

    public boolean getInitPhase() {
        return initPhase;
    }

    public int getCountryIndex() {
        return countryIndex;
    }

    public void setCountryIndex(int countryIndex) {
        this.countryIndex = countryIndex;
    }

    public ArrayList<Integer> getRandomCountries() {
        return randomCountries;
    }

    public ArrayList<Integer> getOwnedGray() {
        return ownedGray;
    }

    public ArrayList<Integer> getOwnedGreen() {
        return ownedGreen;
    }

    public ArrayList<Integer> getOwnedPurple() {
        return ownedPurple;
    }

    public int[] getTroop_count() {
        return troop_count;
    }

    public Constants.PLAYER_COLOUR[] getCountry_owner() {
        return country_owner;
    }

    public ArrayList<Integer> getOwnedOrange() {
        return ownedOrange;
    }
}
