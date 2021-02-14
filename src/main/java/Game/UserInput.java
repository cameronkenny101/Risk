package Game;


public class UserInput {

    Game game;
    Player player1, player2;
    Dice dice;
    int placeTroops;

    public UserInput(Game game, Player player1, Player player2) {
        this.game = game;
        this.player1 = player1;
        this.player2 = player2;
        this.dice = new Dice();
    }

    public void receiveInput(String question, String input) {
        Player player = player1.isTurn() ? player1 : player2;
        Player nextPlayer = player1.isTurn() ? player2 : player1;

        if (question.equals("Press enter to choose your 9 cards from the deck") || question.equals("Press enter to let neutrals choose there cards")) {
            game.start();
        } else if (question.equals("Press enter to roll the dice")) {
            game.setFirstTurn();
        } else if (question.equals("How many troops do you want to place")) {
            placeTroops = Integer.parseInt(input);

            if(placeTroops < 0 || placeTroops > player.getTroops()) {
                game.uiController.output.appendText("You placed an incorrect number of troops. Try again \n");
                game.uiController.askQuestion("How many troops do you want to place");
            } else if(game.initPhase && placeTroops > player.getInitTroops()) {
                game.uiController.output.appendText("You placed an incorrect number of troops. Try again \n");
                game.uiController.askQuestion("How many troops do you want to place");
            } else {
                player.setTroops(player.getTroops() - placeTroops);
                if(game.initPhase)
                    player.setInitTroops(player.getInitTroops() - placeTroops);
                game.uiController.askQuestion("What country do you want to fortify");
            }

        } else if(question.equals("What country do you want to fortify")) {
            if(Constants.COUNTRY_NAMES.contains(input)) {
                int index = Constants.COUNTRY_NAMES.indexOf(input);
                game.takeCountry(index, player.getColour(), placeTroops);
                if(player.getInitTroops() > 0) {
                    game.uiController.output.appendText("> You have " + player.getInitTroops() + " troops left to move \n");
                    game.uiController.askQuestion("How many troops do you want to place");
                }
            } else {
                game.uiController.output.appendText("Country not recognised. Try again\n");
                game.uiController.askQuestion("What country do you want to fortify");
            }
        }
    }
}


