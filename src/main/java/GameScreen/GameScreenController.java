package GameScreen;

import Game.Constants;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.SVGPath;

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
    TextArea output;
    @FXML
    TextField input;

    private SVGPath[] countries_to_SVG = new SVGPath[Constants.NUM_COUNTRIES];//Returns the controller for the countryid

    @FXML
    public void initialize() {
        setText();
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
    }


    //Shows text indicating country name upon hovering
    @FXML
    private void hoverCountry(Event evt) throws Exception {
        Name.setText(((SVGPath) evt.getSource()).getId());
        Name.setVisible(true);
        countryNameInTerminal(((SVGPath) evt.getSource()));
    }

    @FXML
    private void countryNameInTerminal (SVGPath path) {
        output.clear();
        output.appendText(path.getId());
    }

    @FXML
    private void setText() {
        input.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                output.appendText("> ");
                output.appendText(input.getText());
                output.appendText("\n");
                input.clear();
            }
        });
    }

    @FXML
    private void removeLabel(Event event) {
        Name.setVisible(false);
    }

    //Sets the colour and number of a country on the map
    public void setRegion(int country_id, Constants.PLAYER_COLOUR Colour, int troop_count) throws Exception {
        countries_to_SVG[country_id].getStyleClass().clear();
        switch (Colour) {
            case RED:
                countries_to_SVG[country_id].getStyleClass().add("red-country");
                break;
            case BLUE:
                countries_to_SVG[country_id].getStyleClass().add("blue-country");
                break;
            case GREY:
                countries_to_SVG[country_id].getStyleClass().add("grey-country");
                break;
        }

        //TODO: Set Troop Count

        countries_to_SVG[country_id].getStyleClass().add("country");
    }
}
