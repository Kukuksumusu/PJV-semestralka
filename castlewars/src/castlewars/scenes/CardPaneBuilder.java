package castlewars.scenes;

import castlewars.playable.Card;
import castlewars.playable.Playable;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Kukuksumusu
 */
public class CardPaneBuilder {
    public static TitledPane buildPane(Playable card, EventHandler handler, int id, AnchorPane parent) {
        VBox box = new VBox();
        Card.Cost cost = card.getCost();
        if (cost.getBricks() > 0) {
            box.getChildren().add(new Label("Bricks: " + cost.getBricks()));
        }
        if (cost.getWeapons() > 0) {
            box.getChildren().add(new Label("Weapons: " + cost.getWeapons()));
        }
        if (cost.getCrystals() > 0) {
            box.getChildren().add(new Label("Bricks: " + cost.getCrystals()));
        }
        box.getChildren().add(new Separator(Orientation.HORIZONTAL));
        Text t = new Text(card.getDescription());
        t.setWrappingWidth(parent.getPrefWidth());
        box.getChildren().add(t);
        TitledPane cardPane = new TitledPane(card.getName(), box);
        cardPane.setCollapsible(false);
        cardPane.setAlignment(Pos.CENTER);
        if (handler != null) {
            cardPane.setCursor(Cursor.HAND);
            cardPane.setOnMouseClicked(handler);
        }
        cardPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        cardPane.setId(Integer.toString(id));
        AnchorPane.setTopAnchor(cardPane, 0.0);
        AnchorPane.setBottomAnchor(cardPane, 0.0);
        AnchorPane.setLeftAnchor(cardPane, 0.0);
        AnchorPane.setRightAnchor(cardPane, 0.0);
        return cardPane;
    }

    /**
     * Makes card fill her parent AnchorPane
     * @param card
     */
    public static void setCardToFill(TitledPane card) {

    }
}
