package UI.NameScreen;

import Game.Constants;
import Game.Game;
import Game.Player;
import Game.UserInput;
import UI.GameScreen.GameScreenController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NameScreenController {

    @FXML
    private TextField username;
    @FXML
    Button button;
    String[] name = new String[Constants.NUM_PLAYERS];
    int playerNum = 0;
    UserInput input;
    Game game;
    boolean isOnline = false;

    public void setOnline() {
        isOnline = true;
    }

    @FXML
    public void ButtonClicked(Event evt) throws Exception {
        TextField();
        username.clear();
    }

    @FXML
    public void TextField() throws Exception {
        if (username.getText().equals("")) {
            username.clear();
        } else if (isOnline) {
            name[playerNum] = username.getText();
            writingDetailsForPlayers();
        } else {
            name[playerNum] = username.getText();
            if (playerNum == Constants.NUM_PLAYERS - 1)
                writingDetailsForPlayers();
            playerNum++;
        }
    }

    private void writingDetailsForPlayers() throws Exception {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.setResizable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/GameScreen/gameScreen.fxml"));
        Parent root = (Parent) loader.load();
        GameScreenController gameScreenController = loader.getController();

        if (isOnline) {
            Player player = new Player(name[0], Constants.PLAYER_COLOUR.RED, true);
            Scene scene = new Scene(root, 1320, 700);
            stage.setScene(scene);
            game = new Game(gameScreenController, player);
            input = new UserInput(game);
        } else {
            Player player1 = new Player(name[0], Constants.PLAYER_COLOUR.RED);
            Player player2 = new Player(name[1], Constants.PLAYER_COLOUR.BLUE);
            Scene scene = new Scene(root, 1320, 700);
            stage.setScene(scene);
            game = new Game(gameScreenController, player1, player2);
            input = new UserInput(game, player1, player2);
        }
        gameScreenController.receiveHandler(game, input);
    }

}
