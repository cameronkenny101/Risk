package Game;

import java.util.ArrayList;
import java.util.Collections;

public class GameLogic {

    public Constants.PLAYER_COLOUR[] country_owner = new Constants.PLAYER_COLOUR[Constants.NUM_COUNTRIES]; // Tells which players owns which country
    public int[] troop_count = new int[Constants.NUM_COUNTRIES]; // States the number of troops per country
    private final ArrayList<Integer> ownedOrange = new ArrayList<>(); // Countries Orange neutral owns
    private final ArrayList<Integer> ownedPurple = new ArrayList<>(); // Countries Purple neutral owns
    private final ArrayList<Integer> ownedGreen = new ArrayList<>(); // Countries Green neutral owns
    private final ArrayList<Integer> ownedGray = new ArrayList<>(); // Countries Gray neutral owns
    private int countryIndex = 0;
    private boolean initPhase = true;
    protected ArrayList<Integer> randomCountries = new ArrayList<>();
    Deck deck;

    /**
     * Logic for taking a territory
     *
     * @param countryId the country id
     * @param colour    the new color of that country
     * @param troops    number of troops entering the territory
     */
    public void takeCountryLogic(int countryId, Constants.PLAYER_COLOUR colour, int troops) {
        country_owner[countryId] = colour;
        troop_count[countryId] += troops;
    }

    /**
     * Sets the dices for both players to zero
     *
     * @param player1 instance of player 1
     * @param player2 instance of player 2
     */
    public void setDiceToZero(Player player1, Player player2) {
        player1.setDiceNum(0);
        player2.setDiceNum(0);
    }

    /**
     * Fills and allocates unselected countries by the players to neutrals
     */
    public void setRandomCountries() {
        for (int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            randomCountries.add(i);
        }
        Collections.shuffle(randomCountries);
    }

    /**
     * Used for calculating the reinforcements dynamically as the game progresses
     *
     * @param player the player
     * @return returns the amount of reinforcements needed
     */
    public int calculateReinforcements(Player player) {
        int countryControlled = 0;
        int bonusTroops = calculateContReinforcements(player, 0, 9, 9, 5) + //For North America
                calculateContReinforcements(player, 9, 16, 7, 5) +//For Europe
                calculateContReinforcements(player, 16, 28, 12, 7) + //For Asia
                calculateContReinforcements(player, 28, 32, 4, 2) + //For Oceania
                calculateContReinforcements(player, 32, 36, 4, 2) + //For South America
                calculateContReinforcements(player, 36, 42, 6, 3); //For Africa

        for (Constants.PLAYER_COLOUR colour : country_owner) {
            if (colour == player.getColour()) {
                countryControlled++;
            }
        }

        if (countryControlled <= 8) {
            return 3 + bonusTroops;
        }
        return (countryControlled / 3) + bonusTroops; //As per the game rules in brightspace, under Phase 1 of the rules ( fraction is ignored)
    }

    /**
     * Used for inspecting what continents a player controls and allocates them more troops based on this
     *
     * @param player             the player
     * @param startOfIndexOfCont the given start position of the "countries[]" array, ie countries[0] == "Ontario"
     * @param endOfIndexOfCont   the last country in the continent + 1 i.e countries[9] == Britain , so the last country visited by the for loop
     *                           is countries[8] == Alaska (the last country in the North American continent)
     * @param amountOfCountries  amount of countries needed to be able to control the continent
     * @param bonusTroops        amount of troops that will be rewarded if the player controls the amountOfCountries specified
     * @return the bonus troops or 0
     */
    protected int calculateContReinforcements(Player player, int startOfIndexOfCont, int endOfIndexOfCont, int amountOfCountries, int bonusTroops) {
        int cont = 0;
        for (int i = startOfIndexOfCont; i < endOfIndexOfCont; i++) {
            if (country_owner[i] == player.getColour()) {
                cont++;
            }
        }
        if (cont == amountOfCountries) {
            return bonusTroops;
        }
        return 0;
    }

    public void createDeck() {
        deck = new Deck();
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

    public void setRandomCountriesOnline(ArrayList<Integer> randomCountries) {
        this.randomCountries = randomCountries;
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

    public void setTroop_count(int[] troop_count) {
        this.troop_count = troop_count;
    }

    public Constants.PLAYER_COLOUR[] getCountry_owner() {
        return country_owner;
    }

    public ArrayList<Integer> getOwnedOrange() {
        return ownedOrange;
    }

    /**
     * I ADDED IN THESE SETTERS FOR TESTING ONLY.
     */
    protected void setOwnedGreenTest(int index,int value){
        if(index == getOwnedGreen().size()){ownedGreen.add(index, value);}
        if(index < getOwnedGreen().size()){ownedGreen.set(index, value);} }

    protected void setOwnedGrayTest(int index,int value){
        if(index == getOwnedGray().size()){ownedGray.add(index, value);}
        if(index < getOwnedGray().size()){ownedGray.set(index, value);}
    }

    protected void setOwnedPurpleTest(int index,int value){
        if(index == getOwnedPurple().size()){ownedPurple.add(index, value);}
        if(index < getOwnedPurple().size()){ownedPurple.set(index, value);} }

    protected void setOwnedOrangeTest(int index,int value){
        if(index == getOwnedOrange().size()){ownedOrange.add(index, value);}
        if(index < getOwnedOrange().size()){ownedOrange.set(index, value);}
    }
}
