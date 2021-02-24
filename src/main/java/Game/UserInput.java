package Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class UserInput {

    Game game;
    Player player1, player2;
    Dice dice;
    int troops;
    int countryIndex;
    int adjacentIndex;
    int neutralTurnCountdown = Constants.NUM_PLAYERS;
    UserInputLogic userInputLogic = new UserInputLogic();

    public UserInput(Game game, Player player1, Player player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.dice = new Dice();
    }

    /**
     * This is used to align a question with a given answer
     * @param question the question that has been displayed to the player on the terminal ie "How many troops do you want to place"
     * @param input the input of the user ie in response to how many troops question "3" troops
     */
    public void receiveInput(String question, String input) {
        Player player = player1.isTurn() ? player1 : player2;
        Player nextPlayer = player1.isTurn() ? player2 : player1;

        switch (question) {
            case "Press enter to choose your 9 cards from the deck":
            case "Press enter to let neutrals choose there cards":
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
            case "How many troops do you want to move":
                troopsToFortify(input, player);
                break;
        }
    }

    /**
     * Used to place process input and call functions in Game to place troops
     * @param input the amount of troops that want to be placed
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
     * @param input
     * @param player
     * @param nextPlayer
     */
    private void reinforceCountry(String input, Player player, Player nextPlayer) {
        if(input.equals("yes")) {
            game.setCountry(countryIndex, player.getColour(), troops);
            endPlacingTroops(player, nextPlayer);
        } else {
            game.uiController.output.appendText("> You chose not to reinforce this country \n");
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    /**
     * This is used for the Neutral (non player) entities to be allocated countries
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

        if(nextPlayer.getTroops() > 0)
            askForTroops(nextPlayer);
        else
            game.endInitPhase();
    }

    /**
     * Used for when all the initial troop numbers are allocated to countries
     * @param player the player doing the operations
     * @param nextPlayer the player waiting for their turn
     */
    private void endPlacingTroops(Player player, Player nextPlayer) {
        if (player.getInitTroops() > 0) {
            game.uiController.output.appendText("> You have " + player.getInitTroops() + " troops left to move \n");
            game.uiController.askQuestion("How many troops do you want to place");
        } else {
            userInputLogic.nextTurn(player,nextPlayer);
            neutralTurnCountdown--;
            if(neutralTurnCountdown == 0)
                chooseNeutralTerritory(nextPlayer);
            else
                askForTroops(nextPlayer);
        }
    }

    /**
     *  Used to start the troop laying process
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

    private void askForCountry(String country, Player player) {
        setCountryIndex(userInputLogic.shortCountryName(country));
        if(game.logic.getCountry_owner()[countryIndex] == player.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(countryIndex) + "\n");
            game.uiController.askQuestion("Are you sure you want to reinforce this country? (yes/no)");
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(countryIndex) + " You choose a country you do not own. \n");
            game.uiController.askQuestion("What country do you want to reinforce");
        }
    }

    private void fortifyCountry(String input, Player player1, Player player2) {
        if (input.equals("yes")) {
            game.uiController.askQuestion("What country do you want to fortify");
        } else {
            userInputLogic.nextTurn(player1, player2);
            game.uiController.output.appendText("> It is now " + player2.getName() + " turn\n");
        }
    }

    private void askToFortify(String country, Player player1) {
        setCountryIndex(userInputLogic.shortCountryName(country));
        if(game.logic.getCountry_owner()[countryIndex] == player1.getColour()) {
            game.uiController.output.appendText("> You selected the country " + Constants.COUNTRY_NAMES.get(countryIndex) + " to fortify.\n");
            countriesThatCanFortify(player1);
        } else {
            game.uiController.output.appendText(Constants.COUNTRY_NAMES.get(countryIndex) + " You choose a country you do not own. \n");
            game.uiController.askQuestion("What country do you want to fortify");
        }
    }

    private void countriesThatCanFortify(Player player1) {
        int count = 0;
        for (int i = 0; i < Constants.ADJACENT[countryIndex].length; i++) {
            if (game.logic.getCountry_owner()[Constants.ADJACENT[countryIndex][i]] == player1.getColour()) {
                String country = Constants.COUNTRY_NAMES.get(Constants.ADJACENT[countryIndex][i]);
                game.uiController.output.appendText("> You can fortify " + Constants.COUNTRY_NAMES.get(countryIndex) + " with " + country + " with at most "
                        + (game.logic.getTroop_count()[Constants.ADJACENT[countryIndex][i]] - 1) + " troops \n");
                count++;
                adjacentIndex = i;
            }
        }
        if(count == 0) {
            game.uiController.output.appendText("> There are no adjacent countries you own \n");
            game.uiController.askQuestion("Do you want to fortify your territories");
        } else if(count == 1) {
            game.uiController.askQuestion("How many troops do you want to move");
        } else {
            game.uiController.askQuestion("What country will fortify your country");
        }
    }

    private void troopsToFortify(String input, Player player1) {
        int troops = Integer.parseInt(input);
        if(troops < 0 || troops >= game.logic.getTroop_count()[Constants.ADJACENT[countryIndex][adjacentIndex]])
            incorrectNumber();
        else {
            game.takeCountry(countryIndex, player1.getColour(), troops);
            game.takeCountry(Constants.ADJACENT[countryIndex][adjacentIndex], player1.getColour(), -troops);
        }
        game.uiController.askQuestion("Do you want to fortify your territories");
    }

    public void setCountryIndex(int countryIndex) {
        this.countryIndex = countryIndex;
    }
}


