package Game;


public class UserInput {

    Game game;

    public UserInput(Game game) {
        this.game = game;
    }

    public void receiveInput(String question, String input) {
        if (question.equals("Press enter to choose your 9 cards from the deck") || question.equals("Press enter to let neutrals choose there cards")) {
            game.start();
        }


        if (question.equals("Do you want to roll the Dice?")) {
            if (input.equals("yes")) {
                    if(game.player1.getDiceRollNumber() == 0) {
                        game.dice.rollDice(game.player1);
                        System.out.println(game.player1.getDiceRollNumber());
                    }else{
                        game.dice.rollDice(game.player2);
                        System.out.println(game.player2);
                    }
            }
        }
    }
}


