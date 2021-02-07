package PlayerChoosingScreen;


import javafx.event.ActionEvent;
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
        writingDetailsToFileForPlayer_ONE("EUROPE");
        closeCurrentScene();
        moveToSecondScreen();

    }

    public void ButtonClicked2() throws IOException {
        writingDetailsToFileForPlayer_ONE("NORTH AMERICA");
        closeCurrentScene();
        moveToSecondScreen();
    }


    public void ButtonClicked3() throws IOException {
        writingDetailsToFileForPlayer_ONE("Africa");
        closeCurrentScene();
        moveToSecondScreen();
    }

    public void ButtonClicked4() throws IOException {
        writingDetailsToFileForPlayer_ONE("SOUTH AMERICA");
        closeCurrentScene();
        moveToSecondScreen();
    }

    public void ButtonClicked5() throws IOException {
        writingDetailsToFileForPlayer_ONE("OCEANIA");
        closeCurrentScene();
        moveToSecondScreen();
    }

    public void ButtonClicked6() throws IOException {
        writingDetailsToFileForPlayer_ONE("ASIA");
        closeCurrentScene();
        moveToSecondScreen();
    }

    @FXML
    public void TextField1(ActionEvent actionEvent) {
        NAME_INPUT = username.getText();
    }

    private void writingDetailsToFileForPlayer_ONE(String Area) throws IOException {
        System.out.println("here");
        File file= new File("DetailsOfPlayer1.txt");
        FileWriter FILE = new FileWriter(file);
        FILE.write(NAME_INPUT+"\n"+Area);
        FILE.close();
    }

    private void closeCurrentScene(){
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }

    private void moveToSecondScreen() throws IOException{
        Stage stage = (Stage) username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ChoosePlayerScreen/player2Screen.fxml"));
        Scene scene = new Scene(root,1023,437);
        stage.setScene(scene);
        stage.show();
    }

}
