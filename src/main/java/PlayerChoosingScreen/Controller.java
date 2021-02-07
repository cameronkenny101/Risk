package PlayerChoosingScreen;

import Game.Constants;
import Game.Game;
import Game.Player;
import GameScreen.GameScreenController;
import GameScreen.Player1Holder;
import GameScreen.Player2Holder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField username;
    public String NAME_INPUT = null;
    private boolean isPlayer1 = true;
    private Game game;

    @FXML
    public void ButtonClicked() throws Exception {
        TextField1();
        if(isPlayer1) {
            writingDetailsToFileForPlayer_ONE();
            moveToSecondScreen();
            System.out.println("here");
        } else {
            System.out.println("here");
            writingDetailsToFileForPlayer_TWO();
        }
    }

    public void ButtonClicked2() throws Exception {
        TextField1();
        if(isPlayer1) {
            writingDetailsToFileForPlayer_ONE();
            moveToSecondScreen();
        } else {
            writingDetailsToFileForPlayer_TWO();
        }
    }


    public void ButtonClicked3() throws Exception {
        TextField1();
        if(isPlayer1) {
            writingDetailsToFileForPlayer_ONE();
            moveToSecondScreen();
        } else {
            writingDetailsToFileForPlayer_TWO();
        }
    }

    public void ButtonClicked4() throws Exception {
        TextField1();
        if(isPlayer1) {
            writingDetailsToFileForPlayer_ONE();
            moveToSecondScreen();
        } else {
            writingDetailsToFileForPlayer_TWO();
        }
    }

    public void ButtonClicked5() throws Exception {
        TextField1();
        if(isPlayer1) {
            writingDetailsToFileForPlayer_ONE();
            moveToSecondScreen();
        } else {
            writingDetailsToFileForPlayer_TWO();
        }
    }

    public void ButtonClicked6() throws Exception {
        TextField1();
        if(isPlayer1) {
            writingDetailsToFileForPlayer_ONE();
            moveToSecondScreen();
        } else {
            writingDetailsToFileForPlayer_TWO();
        }
    }

    @FXML
    public void TextField1() {
        NAME_INPUT = username.getText();
    }

    private void writingDetailsToFileForPlayer_ONE() throws IOException {
        Player player1 = new Player(NAME_INPUT, Constants.PLAYER_COLOUR.RED);
        Player1Holder player1Holder = Player1Holder.getInstance();
        player1Holder.setPlayer(player1);
        isPlayer1 = false;
    }

    private void writingDetailsToFileForPlayer_TWO() throws Exception {
        Player player2 = new Player(NAME_INPUT, Constants.PLAYER_COLOUR.BLUE);
        Player2Holder player2Holder = Player2Holder.getInstance();
        player2Holder.setPlayer(player2);

        Stage stage = (Stage) username.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GameScreen/gameScreen.fxml"));
        Parent root = (Parent) loader.load();
        GameScreenController gameScreenController = loader.getController();
        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
        game = new Game(gameScreenController);
    }

    private void moveToSecondScreen() throws IOException{
        username.clear();
    }

}
