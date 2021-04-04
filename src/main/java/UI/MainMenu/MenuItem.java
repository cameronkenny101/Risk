package UI.MainMenu;

import UI.NameScreen.NameScreenController;
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
                    switchScene(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        if (value == 1) {
            setOnMouseClicked(event -> {
                try {
                    switchScene(true);
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

    private void switchScene(boolean online) throws Exception {
        Stage stage = (Stage) getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/NameScreen/NameScreen.fxml"));
        Parent root = loader.load();
        NameScreenController playerScreenController = loader.getController();
        Scene scene = new Scene(root, 1023, 437);
        stage.setScene(scene);
        stage.setResizable(false);

        if (online)
            playerScreenController.setOnline();
    }
}
