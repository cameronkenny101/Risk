package PlayerChoosingScreen;

import javafx.event.ActionEvent;
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
        writingDetailsToFileForPlayer_TWO("EUROPE");
    }

    public void ButtonClicked2() throws IOException {
        writingDetailsToFileForPlayer_TWO("NORTH AMERICA");
    }

    public void ButtonClicked3() throws IOException {
        writingDetailsToFileForPlayer_TWO("AFRICA");
    }

    public void ButtonClicked4() throws IOException {
        writingDetailsToFileForPlayer_TWO("SOUTH AMERICA)");
    }

    public void ButtonClicked5() throws IOException {
       writingDetailsToFileForPlayer_TWO("OCEANIA");
    }

    public void ButtonClicked6() throws IOException {
        writingDetailsToFileForPlayer_TWO("ASIA");
    }

    @FXML
    public void TextField1(ActionEvent actionEvent) {
        System.out.println(username.getText());
        NAME_INPUT = username.getText();
    }



    private void writingDetailsToFileForPlayer_TWO(String Area) throws IOException {
        File file= new File("DetailsOfPlayer2.txt");
        FileWriter FILE = new FileWriter(file);
        FILE.write(NAME_INPUT+"\n"+Area);
        FILE.close();
        Stage stage = (Stage) username.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Main.fxml"));
        Scene scene = new Scene(root, 1300, 700);
        stage.setScene(scene);
    }


}
