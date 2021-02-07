package PlayerChoosingScreen;


import Game.Constants;
import Game.Player;
import GameScreen.Player1Holder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Controller {

    @FXML
    private TextField username;
    public String NAME_INPUT = null;

    @FXML
    public void ButtonClicked() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_ONE("EUROPE");
        moveToSecondScreen();
    }

    public void ButtonClicked2() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_ONE("NORTH AMERICA");
        moveToSecondScreen();
    }


    public void ButtonClicked3() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_ONE("Africa");
        moveToSecondScreen();
    }

    public void ButtonClicked4() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_ONE("SOUTH AMERICA");
        moveToSecondScreen();
    }

    public void ButtonClicked5() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_ONE("OCEANIA");
        moveToSecondScreen();
    }

    public void ButtonClicked6() throws IOException {
        TextField1();
        writingDetailsToFileForPlayer_ONE("ASIA");
        moveToSecondScreen();
    }

    @FXML
    public void TextField1() {
        NAME_INPUT = username.getText();
    }

    private void writingDetailsToFileForPlayer_ONE(String Area) throws IOException {
        Player player1 = new Player(NAME_INPUT, Constants.PLAYER_COLOUR.RED);
        File file= new File("DetailsOfPlayer1.txt");
        FileWriter FILE = new FileWriter(file);
        FILE.write(NAME_INPUT+"\n"+Area);
        FILE.close();

        Player1Holder player1Holder = Player1Holder.getInstance();
        player1Holder.setPlayer(player1);
    }

    private void moveToSecondScreen() throws IOException{
        Stage stage = (Stage) username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ChoosePlayerScreen/player2Screen.fxml"));
        Scene scene = new Scene(root,1023,437);
        stage.setScene(scene);
    }

}
