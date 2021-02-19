package GameScreen;

import Game.Constants;
import Game.Game;
import Game.UserInput;
import com.sun.prism.paint.Color;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameScreenController {
    //Africa
    @FXML
    SVGPath Egypt;
    @FXML
    SVGPath East_Africa;
    @FXML
    SVGPath Congo;
    @FXML
    SVGPath Madagascar;
    @FXML
    SVGPath South_Africa;
    @FXML
    SVGPath North_Africa;

    //Asia
    @FXML
    SVGPath Afghanistan;
    @FXML
    SVGPath India;
    @FXML
    SVGPath Irkutsk;
    @FXML
    SVGPath Kamchatka;
    @FXML
    SVGPath Middle_East;
    @FXML
    SVGPath Mongolia;
    @FXML
    SVGPath Siam;
    @FXML
    SVGPath China;
    @FXML
    SVGPath Japan;
    @FXML
    SVGPath Siberia;
    @FXML
    SVGPath Ural;
    @FXML
    SVGPath Yakutsk;

    //Australia
    @FXML
    SVGPath Eastern_Australia;
    @FXML
    SVGPath New_Guinea;
    @FXML
    SVGPath Western_Australia;
    @FXML
    SVGPath Indonesia;

    // Europe
    @FXML
    SVGPath Great_Britain;
    @FXML
    SVGPath Iceland;
    @FXML
    SVGPath Northern_Europe;
    @FXML
    SVGPath Scandinavia;
    @FXML
    SVGPath Southern_Europe;
    @FXML
    SVGPath Ukraine;
    @FXML
    SVGPath Western_Europe;

    // North America
    @FXML
    SVGPath Alaska;
    @FXML
    SVGPath Alberta;
    @FXML
    SVGPath Central_America;
    @FXML
    SVGPath Eastern_United_States;
    @FXML
    SVGPath Greenland;
    @FXML
    SVGPath Northwest_Territory;
    @FXML
    SVGPath Ontario;
    @FXML
    SVGPath Western_United_States;
    @FXML
    SVGPath Quebec;

    //South America
    @FXML
    SVGPath Argentina;
    @FXML
    SVGPath Brazil;
    @FXML
    SVGPath Peru;
    @FXML
    SVGPath Venezuela;

    @FXML
    Label Name;
    @FXML
    public TextArea output;
    @FXML
    TextField input;
    @FXML
    Group root;
    Game game;
    UserInput userInput;
    String question;

    SVGPath[] countries_to_SVG = new SVGPath[Constants.NUM_COUNTRIES];//Returns the controller for the countryId

    /**
     * This initialises and assigns names to the countries
     *
     */

    @FXML
    public void initialize() {
        countries_to_SVG[0] = Ontario;
        countries_to_SVG[1] = Quebec;
        countries_to_SVG[2] = Northwest_Territory;
        countries_to_SVG[3] = Alberta;
        countries_to_SVG[4] = Greenland;
        countries_to_SVG[5] = Eastern_United_States;
        countries_to_SVG[6] = Western_United_States;
        countries_to_SVG[7] = Central_America;
        countries_to_SVG[8] = Alaska;
        countries_to_SVG[9] = Great_Britain;
        countries_to_SVG[10] = Western_Europe;
        countries_to_SVG[11] = Southern_Europe;
        countries_to_SVG[12] = Ukraine;
        countries_to_SVG[13] = Northern_Europe;
        countries_to_SVG[14] = Iceland;
        countries_to_SVG[15] = Scandinavia;
        countries_to_SVG[16] = Afghanistan;
        countries_to_SVG[17] = India;
        countries_to_SVG[18] = Middle_East;
        countries_to_SVG[19] = Japan;
        countries_to_SVG[20] = Ural;
        countries_to_SVG[21] = Yakutsk;
        countries_to_SVG[22] = Kamchatka;
        countries_to_SVG[23] = Siam;
        countries_to_SVG[24] = Irkutsk;
        countries_to_SVG[25] = Siberia;
        countries_to_SVG[26] = Mongolia;
        countries_to_SVG[27] = China;
        countries_to_SVG[28] = Eastern_Australia;
        countries_to_SVG[29] = New_Guinea;
        countries_to_SVG[30] = Western_Australia;
        countries_to_SVG[31] = Indonesia;
        countries_to_SVG[32] = Venezuela;
        countries_to_SVG[33] = Peru;
        countries_to_SVG[34] = Brazil;
        countries_to_SVG[35] = Argentina;
        countries_to_SVG[36] = Congo;
        countries_to_SVG[37] = North_Africa;
        countries_to_SVG[38] = South_Africa;
        countries_to_SVG[39] = Egypt;
        countries_to_SVG[40] = East_Africa;
        countries_to_SVG[41] = Madagascar;
        output.setEditable(false);
        outputText();
    }

    /**
     * Shows text indicating country name upon hovering
     * @param evt this is used to get the source of the assigned country to be able to parse  a path into countryNameInTerminal function
     *
     */
    @FXML
    private void hoverCountry(Event evt) {
        countryNameInTerminal(((SVGPath) evt.getSource()));
    }

    /**
     * prints the name of the country to terminal
     * @param path this is used to access the country's ID variable in the path object
     */
    @FXML
    private void countryNameInTerminal(SVGPath path) {
        output.appendText("> " + path.getId() + "\n");
    }

    /**
     * waits until the user presses the ENTER button to begin processing the input entered into the terminal on the right side of the screen
     */
    @FXML
    private void outputText() {
        input.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                output.appendText("> ");
                output.appendText(input.getText());
                output.appendText("\n");
                String in = input.getText();
                getUserInput(in);
                input.clear();
            }
        });
    }

    /**
     * This is used to provide the user a particular question
     * @param question is a string of such a question
     *                 Example:"How many troops do you want to place"
     */

    @FXML
    public void askQuestion(String question) {
        this.question = question;
        output.appendText("> " + question + "\n");
    }

    /**
     * This is used to pass in the question previously asked, with the user's answer which is then compared to decide
     * next course of action
     * @param in input of user
     *              Example: "North Africa" / "2" (troops)
     */
    private void getUserInput(String in) {
        userInput.receiveInput(question, in);
    }

    /**
     * Stops the name of the player being visible on the side of the input i.e ">ALEX how many troops would you like to place"
     * becomes
     * ">how many troops would you like to place"
     * @param event The particular event in code that triggers this function
     *              Example: Pressing ENTER after dice roll etc
     */
    @FXML
    private void removeLabel(Event event) {
        Name.setVisible(false);
    }

    /**Sets the colour and number of a country on the map
     *
     * @param country_id this is the index of a given country  in the countries_to_SVG[]
     * @param Colour this is the player's color
     * @param troop_count amount of troops that will be placed
     */
    public void setRegion(int country_id, Constants.PLAYER_COLOUR Colour, int troop_count) {
        countries_to_SVG[country_id].getStyleClass().clear();
        switch (Colour) {                   //This switch statement makes the a given country turn a different colour depending on what player owns it
            case RED:
                countries_to_SVG[country_id].getStyleClass().add("red-country");
                break;
            case BLUE:
                countries_to_SVG[country_id].getStyleClass().add("blue-country");
                break;
            case GREY:
                countries_to_SVG[country_id].getStyleClass().add("grey-country");
                break;
            case PURPLE:
                countries_to_SVG[country_id].getStyleClass().add("purple-country");
                break;
            case GREEN:
                countries_to_SVG[country_id].getStyleClass().add("green-country");
                break;
            case ORANGE:
                countries_to_SVG[country_id].getStyleClass().add("orange-country");
                break;
        }

        //TODO: Set Troop Count
        //^^ was not sure to delete this comment or not -mark
        displayTroops(countries_to_SVG[country_id], troop_count,Colour);

        countries_to_SVG[country_id].getStyleClass().add("country");
    }

    /**
     * Function for displaying the troop count of a given country in a white bubble in it's centre
     * @param path //used to get dimensions of a country
     * @param troop_count amount of troops to be displayed
     * @param Colour colour of a player / neutral
     */
    @FXML
    private void displayTroops(SVGPath path, int troop_count, Constants.PLAYER_COLOUR Colour) {
        Bounds bounds = path.getBoundsInLocal();
        Circle circle = new Circle(10);

        if(troop_count >= 10){
            circle = new Circle(15);    //makes circle bigger if the toop count is too large
        }

        circle.setCenterX(bounds.getCenterX());
        circle.setCenterY(bounds.getCenterY());
        // Quick fix for center of argentina
        if (path.getContent().equals("M227 487c1,0 3,-1 5,-2 0,2 1,5 1,8 6,2 5,8 10,10 1,-2 2,-4 3,-5 1,0 2,-1 3,-1 3,4 4,3 9,1 0,1 0,2 0,3 1,0 3,0 5,0 4,7 9,7 16,10 -1,4 -2,7 -4,11 4,-1 8,-1 12,-2 2,-4 2,-4 5,-6 1,1 1,2 2,3 -4,3 -10,8 -11,13 2,0 4,0 7,0 0,2 0,2 0,6 5,1 6,4 10,8 0,2 0,2 -3,2 0,1 0,2 -1,4 -8,3 -12,-1 -19,-1 5,5 5,5 7,8 0,0 1,0 1,-1 0,3 0,6 0,8 -3,2 -13,3 -16,5 -1,6 -1,6 -2,9 -2,0 -4,0 -6,0 0,-1 -1,-1 -1,-2 0,1 0,3 0,4 0,1 1,2 2,3 -3,1 -3,1 -3,2 1,0 2,0 2,0 0,1 0,2 0,3 0,0 -1,0 -2,0 0,1 0,2 1,3 -4,4 -4,4 -4,9 1,0 2,1 3,1 -1,3 -1,5 -1,8 -1,0 -2,1 -3,1 1,3 -2,8 -2,11 1,1 3,3 4,4 0,1 -1,1 -2,2 1,1 1,2 1,3 1,0 2,0 3,0 0,1 0,2 0,3 6,3 12,6 18,10 -14,4 -13,3 -26,0 1,-1 1,-1 1,-2 -1,0 -2,0 -3,0 0,-2 0,-3 0,-4 -2,0 -4,0 -5,0 0,-1 0,-2 0,-3 0,0 -1,1 -2,1 -1,-1 -3,-1 -5,-2 0,-2 0,-3 0,-4 -9,-5 -9,-14 -10,-23 -3,0 -3,0 -4,-2 1,-1 1,-2 1,-3 -1,0 -1,0 -2,0 0,-1 0,-3 0,-4 2,0 2,0 2,-1 -1,0 -1,0 -2,0 0,-2 0,-4 0,-6 1,0 2,0 4,0 0,-4 0,-9 0,-13 -1,0 -2,-1 -3,-1 5,-28 6,-61 4,-89 M309 631c-2,0 -3,-1 -4,-2 -1,-3 -1,-6 -1,-9 7,-7 9,-5 18,-4 0,1 0,3 1,5 -6,2 -7,3 -10,9 -2,1 -3,1 -4,1z M295 630c-1,-2 -1,-2 -4,-3 0,0 0,-1 0,-2 1,0 2,-1 4,-1 -1,-1 -2,-3 -3,-4 3,-2 6,-3 9,-4 0,3 0,7 1,10 -3,3 -3,3 -7,4z")) {
            circle.setCenterX(circle.getCenterX() - 25);
        }

        switch (Colour){        //similar to switch in setRegion, this assigns the css classes of the bubbles displaying the troop count
            case RED:
                circle.setId("red-circle");
                break;
            case BLUE:
                circle.setId("blue-circle");
                break;
            case GREY:
                circle.setId("grey-circle");
                break;
            case PURPLE:
                circle.setId("purple-circle");
                break;
            case GREEN:
                circle.setId("green-circle");
                break;
            case ORANGE:
                circle.setId("orange-circle");
                break;
        }

        Text text = new Text("" + troop_count);
        text.setFont(Font.font(null,FontWeight.BOLD,15));

        if(troop_count<10) {                    //sets the number circles to a default small size
            text.setX(circle.getCenterX() - 4);
            text.setY(circle.getCenterY() + 4.5);
        }else{
            text.setX(circle.getCenterX() - 9); //makes the circles bigger to accommodate double digit figures
            text.setY(circle.getCenterY() + 4);
        }

        circle.setStrokeWidth(2);

        root.getChildren().addAll(circle, text);
    }

    public void receiveHandler(Game game, UserInput userInput) {
        this.game = game;
        this.userInput = userInput;
    }
}
