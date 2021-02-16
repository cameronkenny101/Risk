package Game;

import java.util.Random;


public class UserInput {

    Game game;
    Player player1, player2;
    Dice dice;
    int troops;
    int neutralTurnCountdown = Constants.NUM_PLAYERS;

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
            case "What country do you want to fortify":
                fortifyCountry(input, player, nextPlayer);
                break;
        }
    }

    /**
     * Used to place process input and call fucntions in Game to place troops
     * @param input the amount of troops that want to be placed
     * @param player the player placing them
     */
    private void placeTroops(String input, Player player) {
        troops = Integer.parseInt(input);

        if (troops < 0 || troops > player.getTroops())
            incorrectNumber();
        else if (game.initPhase && troops > player.getInitTroops())
            incorrectNumber();
        else {
            player.setTroops(player.getTroops() - troops);
            if (game.initPhase)
                player.setInitTroops(player.getInitTroops() - troops);
            game.uiController.askQuestion("What country do you want to fortify");
        }
    }

    private void fortifyCountry(String input, Player player, Player nextPlayer) {
        if (Constants.COUNTRY_NAMES.contains(input)) {
            int index = Constants.COUNTRY_NAMES.indexOf(input);
            if(game.setCountry(index, player.getColour(), troops)) {
                endPlacingTroops(player, nextPlayer);
            } else {
                game.uiController.output.appendText("You choose a country you do not own. Try again \n");
                game.uiController.askQuestion("What country do you want to fortify");
            }
        } else {
            game.uiController.output.appendText("Country not recognised. Try again\n");
            game.uiController.askQuestion("What country do you want to fortify");
        }
    }

    private void chooseNeutralTerritory(Player nextPlayer) {
        game.uiController.output.appendText("> Neutral countries allocating troop\n");
        Random random = new Random();
        int countryId = random.nextInt(6);
        game.setCountry(game.ownedGray.get(countryId), Constants.PLAYER_COLOUR.GREY, 1);
        game.setCountry(game.ownedOrange.get(countryId), Constants.PLAYER_COLOUR.ORANGE, 1);
        game.setCountry(game.ownedGreen.get(countryId), Constants.PLAYER_COLOUR.GREEN, 1);
        game.setCountry(game.ownedPurple.get(countryId), Constants.PLAYER_COLOUR.PURPLE, 1);
        neutralTurnCountdown = Constants.NUM_PLAYERS;

        if(nextPlayer.getTroops() > 0)
            askForTroops(nextPlayer);
        else
            game.endInitPhase();
    }

    private void endPlacingTroops(Player player, Player nextPlayer) {
        if (player.getInitTroops() > 0) {
            game.uiController.output.appendText("> You have " + player.getInitTroops() + " troops left to move \n");
            game.uiController.askQuestion("How many troops do you want to place");
        } else {
            player.setTurn(false);
            player.setInitTroops(3);
            nextPlayer.setTurn(true);
            neutralTurnCountdown--;
            if(neutralTurnCountdown == 0)
                chooseNeutralTerritory(nextPlayer);
            else
                askForTroops(nextPlayer);
        }
    }

    private void askForTroops(Player player) {
        game.uiController.output.appendText("> " + player.getName() + ", you will now fortify your territories. You have " + player.getTroops() + " troops left. You can place 3 troops at a time\n");
        game.uiController.askQuestion("How many troops do you want to place");
    }

    private void incorrectNumber() {
        game.uiController.output.appendText("You placed an incorrect number of troops. Try again \n");
        game.uiController.askQuestion("How many troops do you want to place");
    }
}


