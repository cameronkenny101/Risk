package Game;

import UI.GameScreen.GameScreenController;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import Game.Constants;
import Game.Game;
import Game.UserInput;
import Game.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

public class GameScreenControllerTest extends TestCase {

    @Test
    public void testInitialize(){
        GameScreenController controller = new GameScreenController();

    }

}
