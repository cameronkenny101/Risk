package PlayerChoosingScreen;

import Game.Constants;
import Game.Game;
import Game.Player;
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
    String[] NAME_INPUT = new String[Constants.NUM_PLAYERS];
    int playerNum = 0;
    Game game;

    @FXML
    public void ButtonClicked(Event evt) throws Exception {
        Button button = (Button) evt.getSource();
        TextField(playerNum++);
        username.clear();
    }

    @FXML
    public void TextField(int playerNum) throws Exception {
        NAME_INPUT[playerNum] = username.getText();
        if(playerNum == Constants.NUM_PLAYERS - 1)
            writingDetailsForPlayers();
    }

    private void writingDetailsForPlayers() throws Exception {
        Player player1 = new Player(NAME_INPUT[0], Constants.PLAYER_COLOUR.RED);
        Player player2 = new Player(NAME_INPUT[1], Constants.PLAYER_COLOUR.BLUE);

        Stage stage = (Stage) username.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GameScreen/gameScreen.fxml"));
        Parent root = (Parent) loader.load();
        GameScreenController gameScreenController = loader.getController();
        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        game = new Game(gameScreenController, player1, player2);
    }

}
