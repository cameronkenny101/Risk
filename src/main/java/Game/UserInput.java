package Game;


public class UserInput {

    Game game;

    public UserInput(Game game) {
        this.game = game;
    }

    public void receiveInput(String question, String input) {
        if(question.equals("Press enter to choose your 9 cards from the deck") || question.equals("Press enter to let neutrals choose there cards")) {
            game.start();
        }
        if(question.equals("Do you want to roll the dice?")){
            System.out.println("NOT GOT");
            System.out.println(input + "------------");

            if (input.equals("yes")){
                int diceRoll=game.dice.rollDice(game.player1, game.uiController);
                System.out.println(diceRoll);
            }
        }
    }
}
