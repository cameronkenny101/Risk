package PlayerChoosingScreen;

import Game.Constants;
import Game.Player;
import GameScreen.Player2Holder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class Controller2 {

    @FXML
    private TextField username;

    public String NAME_INPUT ;


    @FXML
    public void ButtonClicked() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_TWO("EUROPE");
    }

    public void ButtonClicked2() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_TWO("NORTH AMERICA");
    }

    public void ButtonClicked3() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_TWO("AFRICA");
    }

    public void ButtonClicked4() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_TWO("SOUTH AMERICA)");
    }

    public void ButtonClicked5() throws IOException {
        TextField1();
       writingDetailsToFileForPlayer_TWO("OCEANIA");
    }

    public void ButtonClicked6() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_TWO("ASIA");
    }

    @FXML
    public void TextField1() {
        NAME_INPUT = username.getText();
    }



    private void writingDetailsToFileForPlayer_TWO(String Area) throws IOException {
        Player player2 = new Player(NAME_INPUT, Constants.PLAYER_COLOUR.BLUE);
        Player2Holder player2Holder = Player2Holder.getInstance();
        player2Holder.setPlayer(player2);
        Stage stage = (Stage) username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Main.fxml"));
        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
    }


}
