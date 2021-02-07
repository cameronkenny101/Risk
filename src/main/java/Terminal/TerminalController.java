package Terminal;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.SVGPath;

import java.net.URL;
import java.util.ResourceBundle;

public class TerminalController implements Initializable {

    @FXML
    private TextArea output;
    @FXML
    private TextField input;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextOfCountry();
    }

    public void setTextOfCountry() {
        output.setWrapText(true);
        output.setDisable(true);

        input.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                setTextOfCountry();
                output.appendText("> ");
                output.appendText(input.getText());
                output.appendText("\n");
                input.clear();
            }
        });
    }
}