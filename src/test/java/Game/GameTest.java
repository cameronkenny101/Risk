package Game;

import GameScreen.GameScreenController;
import javafx.scene.control.Control;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GameTest extends TestCase {

    @Test
    public void TakeCountryTest(){
        Game test = new Game();
        test.uiController = new GameScreenController();
        test.takeCountry(1, Constants.PLAYER_COLOUR.RED,1);
        assertEquals(Constants.PLAYER_COLOUR.RED,test.country_owner[1]);

        test.takeCountry(2, Constants.PLAYER_COLOUR.BLUE,1);
        assertNotSame(Constants.PLAYER_COLOUR.GREY,test.country_owner[2]);
    }

    @Test
    public void setCountryTest(){
        Game test = new Game();
        test.uiController = new GameScreenController();
        test.country_owner[1] = Constants.PLAYER_COLOUR.RED;
        assertTrue(test.setCountry(1, Constants.PLAYER_COLOUR.RED,1));
        assertFalse(test.setCountry(1, Constants.PLAYER_COLOUR.BLUE,1));
    }

    @Test
    public void endInnitPhaseTest(){
        Game test = new Game();
        test.uiController = new GameScreenController();
        assertTrue(test.initPhase);    //to verify that the init phase is true when game starts
        test.endInitPhase();
        assertFalse(test.initPhase);
    }





}
