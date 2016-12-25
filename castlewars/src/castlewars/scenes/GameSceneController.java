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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private Label opponentBuildersChange;
    @FXML
    private Label opponentBricksChange;
    @FXML
    private Label opponentWeaponsmithsChange;
    @FXML
    private Label opponentWeaponsChange;
    @FXML
    private Label opponentMagesChange;
    @FXML
    private Label opponentCrystalsChange;
    @FXML
    private Label opponentCastleHpChange;
    @FXML
    private Label opponentWallHpChange;
    @FXML
    private Label playerBuildersChange;
    @FXML
    private Label playerBricksChange;
    @FXML
    private Label playerWeaponsmithsChange;
    @FXML
    private Label playerWeaponsChange;
    @FXML
    private Label playerMagesChange;
    @FXML
    private Label playerCrystalsChange;
    @FXML
    private Label playerCastleHpChange;
    @FXML
    private Label playerWallHpChange;
    
    
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
        gameController.setSceneController(this);
        gameController.gameStart();
        
    }
    
    @FXML
    private void playCardHandle(Event event) {
        TitledPane source = (TitledPane)event.getSource();
        /*Card lastPlayedCard = gameController.playCard(Integer.parseInt(source.getId()));
        */
        source.setVisible(false);
        //displayCastles();
        Task <Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                gameController.playCard(Integer.parseInt(source.getId()));
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    public void displayPlayerHand(List<Card> hand) {
        AnchorPane[] cardPanes = {card1, card2, card3, card4, card5};
        Platform.runLater(() -> {
            for (int i = 0; i < 5; i++) {
                TitledPane card = CardPaneBuilder.buildPane(hand.get(i), this::playCardHandle, i);
                if (!hand.get(i).canPlay(gameController.getPlayerCastle())) {
                    card.setDisable(true);
                }
                setCardToFill(card);
                cardPanes[i].getChildren().setAll(card);
            }
        });
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
     * Overwrites displayed values with correct ones
     * @param playerCastle
     * @param opponentCastle
     * @param hideChangesAfter how long should be labels with changed values displayed
     */
    public void displayCastles(Castle playerCastle, Castle opponentCastle, int hideChangesAfter) {
        Platform.runLater(() -> {
            displayChange(playerBuildersChange, playerCastle.getBuilders(), playerBuilders);
            displayChange(playerBricksChange, playerCastle.getBricks(), playerBricks);
            displayChange(playerWeaponsmithsChange, playerCastle.getWeaponsmiths(), playerWeaponsmiths);
            displayChange(playerWeaponsChange, playerCastle.getWeapons(), playerWeapons);
            displayChange(playerMagesChange, playerCastle.getMages(), playerMages);
            displayChange(playerCrystalsChange, playerCastle.getCrystals(), playerCrystals);
            displayChange(playerCastleHpChange, playerCastle.getHp(), playerCastleHp);
            displayChange(playerWallHpChange, playerCastle.getWallHp(), playerWallHp);

            displayChange(opponentBuildersChange, opponentCastle.getBuilders(), opponentBuilders);
            displayChange(opponentBricksChange, opponentCastle.getBricks(), opponentBricks);
            displayChange(opponentWeaponsmithsChange, opponentCastle.getWeaponsmiths(), opponentWeaponsmiths);
            displayChange(opponentWeaponsChange, opponentCastle.getWeapons(), opponentWeapons);
            displayChange(opponentMagesChange, opponentCastle.getMages(), opponentMages);
            displayChange(opponentCrystalsChange, opponentCastle.getCrystals(), opponentCrystals);
            displayChange(opponentCastleHpChange, opponentCastle.getHp(), opponentCastleHp);
            displayChange(opponentWallHpChange, opponentCastle.getWallHp(), opponentWallHp);
            Task <Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                try {
                    Thread.sleep(hideChangesAfter);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Platform.runLater(() -> {
                    hideAllChanges();
                    synchronized(gameController.syncObject) {
                        gameController.syncObject.notify();
                    }
                });
                return null;
                }
            };
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        });
    }
    /**
     * Displays changed castle value
     * @param changeLabel label where to show change (difference)
     * @param newValue 
     * @param origLabel label with original value 
     */
    private void displayChange(Label changeLabel, int newValue, Label origLabel) {
        int origValue = Integer.parseInt(origLabel.getText());
        origLabel.setText(Integer.toString(newValue));
        if (newValue > origValue) {
            changeLabel.setText("+" + Integer.toString(newValue - origValue));
            changeLabel.getStyleClass().setAll("increase");
            //changeLabel.getStyleClass().remove("decrease");
            changeLabel.applyCss();
            changeLabel.setVisible(true);
        } else if (newValue < origValue) {
            changeLabel.setText(Integer.toString(newValue - origValue));
            changeLabel.getStyleClass().setAll("decrease");
            //changeLabel.getStyleClass().remove("increase");
            changeLabel.applyCss();
            changeLabel.setVisible(true);
        } else {
            changeLabel.setVisible(false);
        }
    }
    /**
     * Hides all changeLabels
     */
    private void hideAllChanges() {
        Label[] changeLabels = {playerBuildersChange, playerBricksChange, playerWeaponsmithsChange, playerWeaponsChange,
            playerMagesChange, playerCrystalsChange, playerCastleHpChange, playerWallHpChange,
            opponentBuildersChange, opponentBricksChange, opponentWeaponsmithsChange, opponentWeaponsChange,
            opponentMagesChange, opponentCrystalsChange, opponentCastleHpChange, opponentWallHpChange};
        for (Label changeLabel : changeLabels) {
            changeLabel.setVisible(false);
        }
    }
    /**
     * Displays last played card in the appropriate slot
     * @param lastPlayedCard 
     */
    public void displayLastPlayed(Card lastPlayedCard) {
        Platform.runLater(() -> {
            TitledPane lastPlayed = CardPaneBuilder.buildPane(lastPlayedCard, null, 0);
            setCardToFill(lastPlayed);
            cardLastPlayed.getChildren().add(lastPlayed);
        });
    }
}
