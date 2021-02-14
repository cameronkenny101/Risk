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
    }
}
