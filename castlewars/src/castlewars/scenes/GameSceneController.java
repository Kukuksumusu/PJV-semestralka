/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars.scenes;

import castlewars.Castle;
import castlewars.GameController;
import castlewars.playable.Card;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

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
    @FXML
    private Label opponentBuilders;
    @FXML
    private Label opponentBricks;
    @FXML
    private Label opponentWeaponsmiths;
    @FXML
    private Label opponentWeapons;
    @FXML
    private Label opponentMages;
    @FXML
    private Label opponentCrystals;
    @FXML
    private Label opponentCastleHp;
    @FXML
    private Label opponentWallHp;
    @FXML
    private Label playerBuilders;
    @FXML
    private Label playerBricks;
    @FXML
    private Label playerWeaponsmiths;
    @FXML
    private Label playerWeapons;
    @FXML
    private Label playerMages;
    @FXML
    private Label playerCrystals;
    @FXML
    private Label playerCastleHp;
    @FXML
    private Label playerWallHp;
    @FXML
    private AnchorPane cardLastPlayed;   
    
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
        displayCastles();
    }
    
    @FXML
    private void playCardHandle(Event event) {
        Card lastPlayedCard = gameController.playCard(Integer.parseInt(((TitledPane)event.getSource()).getId()));
        TitledPane lastPlayed = CardPaneBuilder.buildPane(lastPlayedCard, null, 0);
        setCardToFill(lastPlayed);
        cardLastPlayed.getChildren().add(lastPlayed);
        ((TitledPane)event.getSource()).setVisible(false);
        displayCastles();
        displayPlayerHand();
    }

    private void displayPlayerHand() {
        List<Card> hand = gameController.getPlayerHand();
        AnchorPane[] cardPanes = {card1, card2, card3, card4, card5};
        for (int i = 0; i < 5; i++) {
            TitledPane card = CardPaneBuilder.buildPane(hand.get(i), this::playCardHandle, i);
            if (!hand.get(i).canPlay(gameController.getPlayerCastle())) {
                card.setDisable(true);
            }
            setCardToFill(card);
            cardPanes[i].getChildren().setAll(card);
        }
    }
    /**
     * Makes card fill her parent AnchorPane
     * @param card 
     */
    private void setCardToFill(TitledPane card) {
        AnchorPane.setTopAnchor(card, 0.0);
        AnchorPane.setBottomAnchor(card, 0.0);
        AnchorPane.setLeftAnchor(card, 0.0);
        AnchorPane.setRightAnchor(card, 0.0);
    }
    /**
     * Overwrites current displayed values with correct ones
     */
    private void displayCastles() {
        Castle playerCastle = gameController.getPlayerCastle();
        playerBuilders.setText(Integer.toString(playerCastle.getBuilders()));
        playerBricks.setText(Integer.toString(playerCastle.getBricks()));
        playerWeaponsmiths.setText(Integer.toString(playerCastle.getWeaponsmiths()));
        playerWeapons.setText(Integer.toString(playerCastle.getWeapons()));
        playerMages.setText(Integer.toString(playerCastle.getMages()));
        playerCrystals.setText(Integer.toString(playerCastle.getCrystals()));
        playerCastleHp.setText(Integer.toString(playerCastle.getHp()));
        playerWallHp.setText(Integer.toString(playerCastle.getWallHp()));
        
        Castle opponentCastle = gameController.getOpponentCastle();
        opponentBuilders.setText(Integer.toString(opponentCastle.getBuilders()));
        opponentBricks.setText(Integer.toString(opponentCastle.getBricks()));
        opponentWeaponsmiths.setText(Integer.toString(opponentCastle.getWeaponsmiths()));
        opponentWeapons.setText(Integer.toString(opponentCastle.getWeapons()));
        opponentMages.setText(Integer.toString(opponentCastle.getMages()));
        opponentCrystals.setText(Integer.toString(opponentCastle.getCrystals()));
        opponentCastleHp.setText(Integer.toString(opponentCastle.getHp()));
        opponentWallHp.setText(Integer.toString(opponentCastle.getWallHp()));
    }


    
}
