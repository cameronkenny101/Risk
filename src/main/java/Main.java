import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Risk");
        Parent root = FXMLLoader.load(getClass().getResource("UI/MainMenu/mainMenu.fxml"));
        Scene scene = new Scene(root, 1000, 600);

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream icon = classLoader.getResourceAsStream("icon.png");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(icon)));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
