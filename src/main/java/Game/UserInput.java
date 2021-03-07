package Game;

import java.lang.reflect.Array;
import java.util.*;


public class UserInput {

    Game game;
    Player player1, player2;
    Dice dice;
    int troops;
    int countryIndex;
    int adjacentIndex;
    int neutralTurnCountdown = Constants.NUM_PLAYERS;
    UserInputLogic userInputLogic = new UserInputLogic();

    /**
     * Constructor for UserInput class
     *
     * @param game    controller for the game class
     * @param player1 instance of player1
     * @param player2 instance of player2
     */
    public UserInput(Game game, Player player1, Player player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.dice = new Dice();
    }

    /**
     * This is used to align a question with a given answer
     *
     * @param question the question that has been displayed to the player on the terminal ie "How many troops do you want to place"
     * @param input    the input of the user ie in response to how many troops question "3" troops
     */
    public void receiveInput(String question, String input) {
        Player player = player1.isTurn() ? player1 : player2;
        Player nextPlayer = player1.isTurn() ? player2 : player1;

        switch (question) {
            case "Press enter to choose your 9 cards from the deck":
            case "Press enter to let neutrals choose their cards":
                game.start();
                break;
            case "Press enter to roll the dice":
                game.setFirstTurn();
                break;
            case "How many troops do you want to place":
                placeTroops(input, player);
                break;
            case "What country do you want to reinforce":
                askForCountry(input, player);
                break;
            case "Are you sure you want to reinforce this country? (yes/no)":
                reinforceCountry(input, player, nextPlayer);
                break;
            case "Do you want to fortify your territories":
                fortifyCountry(input, player, nextPlayer);
                break;
            case "What country do you want to fortify":
                askToFortify(input, player);
                break;
            case "What country will fortify your country":
                countryToGive(input, player);
                break;
            case "How many troops do you want to move":
                troopsToFortify(input, player);
                break;
        }
    }

    /**
     * Used to place process input and call functions in Game to place troops
     *
     * @param input  the amount of troops that want to be placed
     * @param player the player placing them
     */
    private void placeTroops(String input, Player player) {
        troops = Integer.parseInt(input);

        if (troops < 0 || troops > player.getTroops())
            incorrectNumber();
        else if (game.logic.getInitPhase() && troops > player.getInitTroops())
            incorrectNumber();
        else {
            player.setTroops(player.getTroops() - troops);
            if (game.logic.getInitPhase())
                player.setInitTroops(player.getInitTroops() - troops);
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * Used to call functions in Game to reinforce a held territory of a player
     *
     * @param input      user response to question asking them do they want to reinforce the selected country
     * @param player     the player instance
     * @param nextPlayer the next player instance
     */
    private void reinforceCountry(String input, Player player, Player nextPlayer) {
        if (input.equals("yes")) {
            game.setCountry(countryIndex, player.getColour(), troops);
            endPlacingTroops(player, nextPlayer);
        } else {
            game.uiController.output.appendText("> You chose not to reinforce this country \n");
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * This is used for the Neutral (non player) entities to be allocated countries
     *
     * @param nextPlayer this is used for after the neutrals get their countries, to progress the game and let one of the actual players make moves
     */
    private void chooseNeutralTerritory(Player nextPlayer) {
        game.uiController.output.appendText("> Neutral countries allocating troop\n");
        Random random = new Random();
        int countryId = random.nextInt(6);
        game.setCountry(game.logic.getOwnedGray().get(countryId), Constants.PLAYER_COLOUR.GREY, 1);
        game.setCountry(game.logic.getOwnedOrange().get(countryId), Constants.PLAYER_COLOUR.ORANGE, 1);
        game.setCountry(game.logic.getOwnedGreen().get(countryId), Constants.PLAYER_COLOUR.GREEN, 1);
        game.setCountry(game.logic.getOwnedPurple().get(countryId), Constants.PLAYER_COLOUR.PURPLE, 1);
        neutralTurnCountdown = Constants.NUM_PLAYERS;

        if (nextPlayer.getTroops() > 0)
            askForTroops(nextPlayer);
        else
            game.endInitPhase();
    }

    /**
     * Used for when all the initial troop numbers are allocated to countries
     *
     * @param player     the player doing the operations
     * @param nextPlayer the player waiting for their turn
     */
    private void endPlacingTroops(Player player, Player nextPlayer) {
        if (player.getInitTroops() > 0) {
            game.uiController.output.appendText("> You have " + player.getInitTroops() + " troops left to move \n");
            game.uiController.askQuestion("How many troops do you want to place");
        } else {
            game.isWinner(player);
            userInputLogic.nextTurn(player, nextPlayer);
            neutralTurnCountdown--;
            if (neutralTurnCountdown == 0)
                chooseNeutralTerritory(nextPlayer);
            else
                askForTroops(nextPlayer);
        }
    }

    /**
     * Used to start the troop laying process
     *
     * @param player the player who's turn it is
     */
    private void askForTroops(Player player) {
        game.uiController.output.appendText("> " + player.getName() + ", you will now reinforce your territories. You have " + player.getTroops() + " troops left. You can place 3 troops max at a time\n");
        game.uiController.askQuestion("How many troops do you want to place");
    }

    /**
     * for when an invalid number of troops is requested to be placed
     */
    private void incorrectNumber() {
        game.uiController.output.appendText("You placed an incorrect number of troops. Try again \n");
        game.uiController.askQuestion("How many troops do you want to place");
    }

    /**
     * Asks a user for a country to reinforce
     *
     * @param country the input of the country
     * @param player  the current players instance
     */
    private void askForCountry(String country, Player player) {
        setCountryIndex(userInputLogic.shortCountryName(country));
        if (game.logic.getCountry_owner()[countryIndex] == player.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(countryIndex) + "\n");
            game.uiController.askQuestion("Are you sure you want to reinforce this country? (yes/no)");
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(countryIndex) + " You chose a country you do not own. \n");
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * Asks a player what country do they want to fortify
     *
     * @param input      user input determining whether they want to enter the fortify stage
     * @param player     instance of current player
     * @param nextPlayer instance of next player
     */
    private void fortifyCountry(String input, Player player, Player nextPlayer) {
        if (input.equals("yes")) {
            game.uiController.askQuestion("What country do you want to fortify");
        } else {
            userInputLogic.nextTurn(player, nextPlayer);
            game.uiController.output.appendText("> It is now " + nextPlayer.getName() + " turn\n");
        }
    }

    /**
     * Receives the country a user entered and asks the user to confirm that is the territory they selected
     *
     * @param country user input of a country
     * @param player  the current player instance
     */
    private void askToFortify(String country, Player player) {
        setCountryIndex(userInputLogic.shortCountryName(country));
        if (game.logic.getCountry_owner()[countryIndex] == player.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(countryIndex) + " to fortify.\n");
            countriesThatCanFortify(player);
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(countryIndex) + " You choose a country you do not own. \n");
            game.uiController.askQuestion("What country do you want to fortify");
        }
    }

    /**
     * Shows the countries that can fortify the selected territory
     *
     * @param player the current player instance
     */
    private void countriesThatCanFortify(Player player) {
        int count = 0;
        for (int i = 0; i < Constants.ADJACENT[countryIndex].length; i++) {
            Constants.PLAYER_COLOUR color = game.logic.getCountry_owner()[Constants.ADJACENT[countryIndex][i]];
            int troopCount = game.logic.getTroop_count()[Constants.ADJACENT[countryIndex][i]];
            if (color == player.getColour() && troopCount > 1) {
                String country = Constants.COUNTRY_NAMES.get(Constants.ADJACENT[countryIndex][i]);
                game.uiController.output.appendText("> You can fortify " + Constants.COUNTRY_NAMES.get(countryIndex) + " with " + country + " with at most "
                        + (troopCount - 1) + " troops \n");
                count++;
                setAdjacentIndex(i);
            }
        }
        if (count == 0) {
            game.uiController.output.appendText("> There are no adjacent countries you own \n");
            game.uiController.askQuestion("Do you want to fortify your territories");
        } else if (count == 1) {
            game.uiController.askQuestion("How many troops do you want to move");
        } else {
            game.uiController.askQuestion("What country will fortify your country");
        }
    }

    /**
     * Takes user input for amount of troops the player wants to fortify their territory with
     *
     * @param input  user input for number of troops to fortify
     * @param player player instance
     */
    private void troopsToFortify(String input, Player player) {
        int troops = Integer.parseInt(input);
        if (troops < 0 || troops >= game.logic.getTroop_count()[Constants.ADJACENT[countryIndex][adjacentIndex]])
            incorrectNumber();
        else {
            game.takeCountry(countryIndex, player.getColour(), troops);
            game.takeCountry(Constants.ADJACENT[countryIndex][adjacentIndex], player.getColour(), -troops);
        }
        game.uiController.askQuestion("Do you want to fortify your territories");
    }

    /**
     * Takes the adjacent country and competes a fortify action
     *
     * @param country the adjacent country from user input
     * @param player  current player instance
     */
    private void countryToGive(String country, Player player) {
        setAdjacentIndex(userInputLogic.shortCountryName(country));
        boolean isThere = false;
        for (int i = 0; i < Constants.ADJACENT[countryIndex].length; i++) {
            if (Constants.ADJACENT[countryIndex][i] == adjacentIndex) {
                isThere = true;
                break;
            }
        }
        if (isThere) {
            game.takeCountry(countryIndex, player.getColour(), troops);
            game.takeCountry(adjacentIndex, player.getColour(), -troops);
        } else {
            game.uiController.output.appendText("> You do not own \n" + Constants.COUNTRY_NAMES.get(adjacentIndex));
            game.uiController.askQuestion("What country will fortify your country");
        }
    }

    public void setCountryIndex(int countryIndex) {
        this.countryIndex = countryIndex;
    }

    public void setAdjacentIndex(int adjacentIndex) {
        this.adjacentIndex = adjacentIndex;
    }
}


