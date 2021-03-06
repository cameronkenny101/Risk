package UI.GameScreen;

import Game.Constants;
import Game.Game;
import Game.UserInput;
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
import javafx.stage.Stage;

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


    // Continent Labels
    @FXML
    Text Africa;
    @FXML
    Text Asia;
    @FXML
    Text NA;
    @FXML
    Text SA;
    @FXML
    Text Australia;
    @FXML
    Text Europe;

    //Country Hover Text
    @FXML
    Text countryNameOnHover;

    @FXML
    public TextArea output;
    @FXML
    TextField input;
    @FXML
    Group root;
    Game game;
    UserInput userInput;
    String question;
    CountryUI[] countries = new CountryUI[Constants.NUM_COUNTRIES];//Stores the relevant UI information for all 42 countries

    /**
     * This initialises and assigns names to the countries
     */
    @FXML
    public void initialize() {
        countries[0] = new CountryUI(Ontario);
        countries[1] = new CountryUI(Quebec);
        countries[2] = new CountryUI(Northwest_Territory);
        countries[3] = new CountryUI(Alberta);
        countries[4] = new CountryUI(Greenland);
        countries[5] = new CountryUI(Eastern_United_States);
        countries[6] = new CountryUI(Western_United_States);
        countries[7] = new CountryUI(Central_America);
        countries[8] = new CountryUI(Alaska);
        countries[9] = new CountryUI(Great_Britain);
        countries[10] = new CountryUI(Western_Europe);
        countries[11] = new CountryUI(Southern_Europe);
        countries[12] = new CountryUI(Ukraine);
        countries[13] = new CountryUI(Northern_Europe);
        countries[14] = new CountryUI(Iceland);
        countries[15] = new CountryUI(Scandinavia);
        countries[16] = new CountryUI(Afghanistan);
        countries[17] = new CountryUI(India);
        countries[18] = new CountryUI(Middle_East);
        countries[19] = new CountryUI(Japan);
        countries[20] = new CountryUI(Ural);
        countries[21] = new CountryUI(Yakutsk);
        countries[22] = new CountryUI(Kamchatka);
        countries[23] = new CountryUI(Siam);
        countries[24] = new CountryUI(Irkutsk);
        countries[25] = new CountryUI(Siberia);
        countries[26] = new CountryUI(Mongolia);
        countries[27] = new CountryUI(China);
        countries[28] = new CountryUI(Eastern_Australia);
        countries[29] = new CountryUI(New_Guinea);
        countries[30] = new CountryUI(Western_Australia);
        countries[31] = new CountryUI(Indonesia);
        countries[32] = new CountryUI(Venezuela);
        countries[33] = new CountryUI(Peru);
        countries[34] = new CountryUI(Brazil);
        countries[35] = new CountryUI(Argentina);
        countries[36] = new CountryUI(Congo);
        countries[37] = new CountryUI(North_Africa);
        countries[38] = new CountryUI(South_Africa);
        countries[39] = new CountryUI(Egypt);
        countries[40] = new CountryUI(East_Africa);
        countries[41] = new CountryUI(Madagascar);


        setContinentLabels();//Sets the continent Labels on top of the Map
        output.setEditable(false);
        outputText();
    }

    /**
     * Code used the get the center of the x coordinate of a bound
     * Used as .getCenterX is not available in java 8's javafx
     *
     * @param bounds Bounds used to get the center of
     * @return center on the x-axis
     */
    private double centerX(Bounds bounds) {
        return bounds.getMinX() + bounds.getWidth() / 2;
    }

    /**
     * Code used the get the center of the x coordinate of a bound
     * Used as .getCenterY is not available in java 8's javafx
     *
     * @param bounds Bounds used to get the center of
     * @return center on the y-axis
     */
    private double centerY(Bounds bounds) {
        return bounds.getMinY() + bounds.getHeight() / 2;
    }

    /**
     * Sets the label of each continent in the map
     */
    private void setContinentLabels() {
        //Asia
        Bounds bounds = countries[22].getCountryPath().getBoundsInLocal();
        Asia.setY(bounds.getMinY() - 14);
        Asia.setX(centerX(bounds) + 10);
        Asia.setRotate(15);

        //Europe
        bounds = countries[12].getCountryPath().getBoundsInLocal();
        Europe.setY(bounds.getMinY() - 15);
        Europe.setX(centerX(bounds));

        //North America
        bounds = countries[8].getCountryPath().getBoundsInLocal();
        NA.setY(bounds.getMinY() - 30);
        NA.setX(centerX(bounds));
        NA.setRotate(-15);

        //South America
        bounds = Argentina.getBoundsInLocal();
        SA.setY(centerY(bounds) - 10);
        SA.setX(bounds.getMinX() - 170);

        //Africa
        bounds = Madagascar.getBoundsInLocal();
        Africa.setY(bounds.getMaxY() + 30);
        Africa.setX(centerX(bounds) - 40);

        //Australia
        bounds = New_Guinea.getBoundsInLocal();
        Australia.setY(bounds.getMaxY() + 30);
        Australia.setX(centerX(bounds) - 25);

        //Set Bounds for on hover country
        bounds = South_Africa.getBoundsInLocal();
        countryNameOnHover.setY(bounds.getMaxY() + 50);
        countryNameOnHover.setX(centerY(bounds) - 100);
    }

    /**
     * Shows text indicating country name upon hovering
     *
     * @param evt this is used to get the source of the assigned country to be able to parse  a path into countryNameInTerminal function
     */
    @FXML
    private void hoverCountry(Event evt) {
        SVGPath path = (SVGPath) evt.getSource();
        countryNameOnHover.setText(path.getId());
        countryNameOnHover.setVisible(true);
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
     *
     * @param question is a string of such a question
     *                 Example:"How many troops do you want to place"
     */

    @FXML
    public void askQuestion(String question) {
        this.question = question;
        output.appendText("> " + question + "\n");
    }

    @FXML
    public void clearQuestion() {
        question = "";
    }

    /**
     * This is used to pass in the question previously asked, with the user's answer which is then compared to decide
     * next course of action
     *
     * @param in input of user
     *           Example: "North Africa" / "2" (troops)
     */
    private void getUserInput(String in) {
        userInput.receiveInput(question, in);
    }

    /**
     * Called after a the mouse hos been removed out of a certain region
     *
     * @param event The particular event in code that triggers this function
     */
    @FXML
    private void removeLabel(Event event) {
        countryNameOnHover.setVisible(false);
    }

    /**
     * Sets the colour and number of a country on the map
     *
     * @param country_id  this is the index of a given country  in the countries_to_SVG[]
     * @param Colour      this is the player's color
     * @param troop_count amount of troops that will be placed
     */
    public void setRegion(int country_id, Constants.PLAYER_COLOUR Colour, int troop_count) {
        SVGPath countryPath = countries[country_id].getCountryPath();
        countryPath.getStyleClass().clear();
        countryPath.getStyleClass().add("country");
        switch (Colour) {                   //This switch statement makes the a given country turn a different colour depending on what player owns it
            case RED:
                countryPath.getStyleClass().add("red-country");
                break;
            case BLUE:
                countryPath.getStyleClass().add("blue-country");
                break;
            case GREY:
                countryPath.getStyleClass().add("grey-country");
                break;
            case PURPLE:
                countryPath.getStyleClass().add("purple-country");
                break;
            case GREEN:
                countryPath.getStyleClass().add("green-country");
                break;
            case ORANGE:
                countryPath.getStyleClass().add("orange-country");
                break;
        }
        displayTroops(country_id, troop_count, Colour);
    }

    public void setMap(int[] troopCount, Constants.PLAYER_COLOUR[] owner) {
        for (int i = 0; i < Constants.NUM_COUNTRIES; i++) {
            setRegion(i, owner[i], troopCount[i]);
        }
    }

    /**
     * Function for displaying the troop count of a given country in a white bubble in it's centre
     *
     * @param country_id  //Tells us to the country to use
     * @param troop_count amount of troops to be displayed
     * @param Colour      colour of a player / neutral
     */
    @FXML
    private void displayTroops(int country_id, int troop_count, Constants.PLAYER_COLOUR Colour) {
        SVGPath path = countries[country_id].getCountryPath();
        Bounds bounds = path.getBoundsInLocal();
        Circle circle = countries[country_id].getTroopCircle();
        Text text = countries[country_id].getTroopNumber();


        //Create new circle if one doesn't exist
        if (circle == null && text == null) {
            text = new Text();
            countries[country_id].setTroopNumber(text);

            circle = new Circle(10);
            countries[country_id].setTroopCircle(circle);

            circle.setCenterX(centerX(bounds));
            circle.setCenterY(centerY(bounds));
            // Quick fix for center of argentina
            if (path.getContent().equals("M227 487c1,0 3,-1 5,-2 0,2 1,5 1,8 6,2 5,8 10,10 1,-2 2,-4 3,-5 1,0 2,-1 3,-1 3,4 4,3 9,1 0,1 0,2 0,3 1,0 3,0 5,0 4,7 9,7 16,10 -1,4 -2,7 -4,11 4,-1 8,-1 12,-2 2,-4 2,-4 5,-6 1,1 1,2 2,3 -4,3 -10,8 -11,13 2,0 4,0 7,0 0,2 0,2 0,6 5,1 6,4 10,8 0,2 0,2 -3,2 0,1 0,2 -1,4 -8,3 -12,-1 -19,-1 5,5 5,5 7,8 0,0 1,0 1,-1 0,3 0,6 0,8 -3,2 -13,3 -16,5 -1,6 -1,6 -2,9 -2,0 -4,0 -6,0 0,-1 -1,-1 -1,-2 0,1 0,3 0,4 0,1 1,2 2,3 -3,1 -3,1 -3,2 1,0 2,0 2,0 0,1 0,2 0,3 0,0 -1,0 -2,0 0,1 0,2 1,3 -4,4 -4,4 -4,9 1,0 2,1 3,1 -1,3 -1,5 -1,8 -1,0 -2,1 -3,1 1,3 -2,8 -2,11 1,1 3,3 4,4 0,1 -1,1 -2,2 1,1 1,2 1,3 1,0 2,0 3,0 0,1 0,2 0,3 6,3 12,6 18,10 -14,4 -13,3 -26,0 1,-1 1,-1 1,-2 -1,0 -2,0 -3,0 0,-2 0,-3 0,-4 -2,0 -4,0 -5,0 0,-1 0,-2 0,-3 0,0 -1,1 -2,1 -1,-1 -3,-1 -5,-2 0,-2 0,-3 0,-4 -9,-5 -9,-14 -10,-23 -3,0 -3,0 -4,-2 1,-1 1,-2 1,-3 -1,0 -1,0 -2,0 0,-1 0,-3 0,-4 2,0 2,0 2,-1 -1,0 -1,0 -2,0 0,-2 0,-4 0,-6 1,0 2,0 4,0 0,-4 0,-9 0,-13 -1,0 -2,-1 -3,-1 5,-28 6,-61 4,-89 M309 631c-2,0 -3,-1 -4,-2 -1,-3 -1,-6 -1,-9 7,-7 9,-5 18,-4 0,1 0,3 1,5 -6,2 -7,3 -10,9 -2,1 -3,1 -4,1z M295 630c-1,-2 -1,-2 -4,-3 0,0 0,-1 0,-2 1,0 2,-1 4,-1 -1,-1 -2,-3 -3,-4 3,-2 6,-3 9,-4 0,3 0,7 1,10 -3,3 -3,3 -7,4z")) {
                circle.setCenterX(circle.getCenterX() - 25);
            }
            // Fix for the number bubble completely covering Iceland when reached doubled digits, this moves the button down below iceland proper
            if (path.getContent().equals("M425 156c-2,-1 -5,-2 -8,-2 0,-2 0,-3 -1,-5 3,0 3,0 3,-1 -1,0 -3,0 -4,0 0,-1 0,-2 0,-3 1,0 3,0 4,0 0,0 0,-1 0,-1 -1,0 -3,0 -4,0 0,-1 1,-2 1,-3 4,-2 4,-2 8,-1 0,0 0,1 0,2 7,0 15,-1 23,-1 1,1 2,1 3,2 -1,1 -1,1 -2,2 1,0 2,0 3,0 -2,12 -17,10 -26,11z")) {
                circle.setCenterY(circle.getCenterY() + 15);
            }


            root.getChildren().addAll(circle, text);
        }
        assert circle != null;
        assert text != null;


        text.setText("" + troop_count);
        if (troop_count >= 10) {
            circle.setRadius(15);    //makes circle bigger if the troop count is too large
        } else
            circle.setRadius(10);
        circle.setId("circle");
        circle.getStyleClass().clear();
        switch (Colour) {        //similar to switch in setRegion, this assigns the css classes of the bubbles displaying the troop count
            case RED:
                circle.getStyleClass().add("red-country");
                break;
            case BLUE:
                circle.getStyleClass().add("blue-circle");
                break;
            case GREY:
                circle.getStyleClass().add("grey-circle");
                break;
            case PURPLE:
                circle.getStyleClass().add("purple-circle");
                break;
            case GREEN:
                circle.getStyleClass().add("green-circle");
                break;
            case ORANGE:
                circle.getStyleClass().add("orange-circle");
                break;
        }
        text.setId("circle-text");
        if (troop_count < 10) {                    //sets the number circles to a default small size
            text.setX(circle.getCenterX() - 4);
            text.setY(circle.getCenterY() + 4.5);
        } else {
            text.setX(circle.getCenterX() - 9); //makes the circles bigger to accommodate double digit figures
            text.setY(circle.getCenterY() + 4);
        }
    }

    public void receiveHandler(Game game, UserInput userInput) {
        this.game = game;
        this.userInput = userInput;
    }

    /**
     * Close the Game after a victory
     */
    public void closeGame() {
        input.setDisable(true);
        output.appendText("> Exiting Game in 3\n");
        try {
            Thread.sleep(1000);
            output.appendText("> Exiting Game in 2\n");
            Thread.sleep(1000);
            output.appendText("> Exiting Game in 1\n");
            Thread.sleep(1000);
            output.appendText("> GG\n");
            Thread.sleep(500);
            Stage stage = (Stage) output.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Sleep Exception");
        }
    }

    /**
     * Stores all the UI components of a country
     */
    private class CountryUI {

        SVGPath countryPath = null;
        Circle troopCircle = null;
        Text troopNumber = null;

        CountryUI(SVGPath countryPath) {
            this.countryPath = countryPath;
        }

        public SVGPath getCountryPath() {
            return countryPath;
        }

        public void setCountryPath(SVGPath countryPath) {
            this.countryPath = countryPath;
        }

        public Circle getTroopCircle() {
            return troopCircle;
        }

        public void setTroopCircle(Circle troopCircle) {
            this.troopCircle = troopCircle;
        }

        public Text getTroopNumber() {
            return troopNumber;
        }

        public void setTroopNumber(Text troopNumber) {
            this.troopNumber = troopNumber;
        }
    }
}
