package PlayerChoosingScreen;

import Game.Constants;
import Game.Game;
import Game.Player;
import Game.UserInput;
import GameScreen.GameScreenController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField username;
    String[] name = new String[Constants.NUM_PLAYERS];
    int[] commanderID = new int[Constants.NUM_PLAYERS];
    int playerNum = 0;
    Game game;
    UserInput userInput;

    @FXML
    public void ButtonClicked(Event evt) throws Exception {
        Button button = (Button) evt.getSource();
        commanderID[playerNum] = Integer.parseInt(button.getId());
        TextField(playerNum++);
        username.clear();
    }

    @FXML
    public void TextField(int playerNum) throws Exception {
        name[playerNum] = username.getText();
        if(playerNum == Constants.NUM_PLAYERS - 1)
            writingDetailsForPlayers();
    }

    private void writingDetailsForPlayers() throws Exception {
        Player player1 = new Player(name[0], Constants.PLAYER_COLOUR.RED, commanderID[0]);
        Player player2 = new Player(name[1], Constants.PLAYER_COLOUR.BLUE, commanderID[1]);

        Stage stage = (Stage) username.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GameScreen/gameScreen.fxml"));
        Parent root = (Parent) loader.load();
        GameScreenController gameScreenController = loader.getController();
        Scene scene = new Scene(root, 1400, 700);
        stage.setScene(scene);
        game = new Game(gameScreenController, player1, player2);
        userInput = new UserInput(game, player1, player2);
        gameScreenController.receiveHandler(game, userInput);
    }

}
