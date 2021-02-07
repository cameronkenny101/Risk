package MainMenu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Text title;
    @FXML
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackpane.getChildren().addAll(rectangle, title);
        Menu vbox = new Menu(
                new MenuItem("OFFLINE GAME", 0),
                new MenuItem("SETTINGS", 1),
                new MenuItem("GITHUB", 2),
                new MenuItem("EXIT", 3)
        );
        vbox.setTranslateX(325);
        vbox.setTranslateY(220);
        root.getChildren().addAll(vbox);
    }
}
