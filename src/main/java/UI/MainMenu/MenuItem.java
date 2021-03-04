package UI.MainMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class MenuItem extends StackPane {

    public MenuItem(String item, int value) {
        Rectangle rectangle = new Rectangle(350, 80);
        rectangle.setId("menuBox");
        Text text = new Text(item);
        text.setId("itemText");
        getChildren().addAll(rectangle, text);
        setOnMouseEntered(event -> {
            text.setFill(Color.WHITE);
            rectangle.setFill(Color.DARKVIOLET);
        });

        setOnMouseExited(event -> {
            text.setFill(Color.rgb(221, 221, 221));
            rectangle.setFill(Color.BLACK);
        });

        if (value == 0) {
            setOnMouseClicked(event -> {
                try {
                    switchScene();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        /*Github Link*/
        if (value == 2) {
            setOnMouseClicked(event -> {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/UCD-COMP20050/AlphaRisk").toURI());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        if (value == 3) {
            setOnMouseClicked(event -> {
                Stage stage = (Stage) getScene().getWindow();
                stage.close();
            });
        }
    }

    private void switchScene() throws Exception {
        Stage stage = (Stage) getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../NameScreen/NameScreen.fxml"));
        Scene scene = new Scene(root, 1023, 437);
        stage.setScene(scene);
    }
}
