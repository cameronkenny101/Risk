package UI.MainMenu;

import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class Menu extends VBox {

    public Menu(MenuItem... menuItems) {
        getChildren().add(addLine());

        for(MenuItem menuItem : menuItems) {
            getChildren().addAll(menuItem, addLine());
        }
    }

    private Line addLine() {
        Line line = new Line();
        line.setId("line");
        line.setEndX(350);
        return line;
    }
}
