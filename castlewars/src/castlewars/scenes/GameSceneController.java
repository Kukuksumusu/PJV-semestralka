/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars.scenes;

import castlewars.GameController;
import castlewars.playable.Card;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Kukuksumusu
 */
public class GameSceneController extends BaseSceneController {

    @FXML
    private AnchorPane card1;
    @FXML
    private AnchorPane card2;
    @FXML
    private AnchorPane card3;
    @FXML
    private AnchorPane card4;
    @FXML
    private AnchorPane card5;

    private GameController gameController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void postInit() {
        gameController = application.getGameController();
        gameController.gameStart();
        displayPlayerHand();
    }
    
    @FXML
    private void playCardHandle(Event event) {
        System.out.println(((TitledPane)event.getSource()).getId());
        gameController.playCard(Integer.parseInt(((TitledPane)event.getSource()).getId()));
    }

    private void displayPlayerHand() {
        List<Card> hand = gameController.getPlayerHand();
        AnchorPane[] cardPanes = {card1, card2, card3, card4, card5};
        for (int i = 0; i < 5; i++) {
            TitledPane card = CardPaneBuilder.buildPane(hand.get(i), this::playCardHandle, i);
            AnchorPane.setTopAnchor(card, 0.0);
            AnchorPane.setBottomAnchor(card, 0.0);
            AnchorPane.setLeftAnchor(card, 0.0);
            AnchorPane.setRightAnchor(card, 0.0);
            cardPanes[i].getChildren().setAll(card);
        }
    }


    
}
