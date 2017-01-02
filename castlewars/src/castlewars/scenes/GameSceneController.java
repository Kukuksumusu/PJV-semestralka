
package castlewars.scenes;

import castlewars.Castle;
import castlewars.GameController;
import castlewars.fxmlPaths;
import castlewars.playable.Playable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    @FXML
    private Label discardInfoLabel;
    
    private GameController gameController;
    private AnchorPane[] cardPanes; 
    private boolean discarding = false;
    private boolean isPlayerTurn = true;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane[] cards = {card1, card2, card3, card4, card5};
        cardPanes = cards;
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
        source.setVisible(false);
        disableCards();
        Task <Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                gameController.playCard(Integer.parseInt(source.getId()), discarding);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void keyPressHandle(KeyEvent event) {
        if (event.getCode() == KeyCode.CONTROL) {            
            discarding = true;
            discardInfoLabel.setText("DISCARDING");
            discardInfoLabel.getStyleClass().setAll("discarding");
            if (isPlayerTurn) {
                for (AnchorPane cardPane : cardPanes) {
                    cardPane.setDisable(false);
                }
            }
        }
    }
    
    @FXML
    private void keyReleaseHandle(KeyEvent event) {
        if (event.getCode() == KeyCode.CONTROL) {
            discarding = false;
            discardInfoLabel.setText("Hold CTRL to discard");
            discardInfoLabel.getStyleClass().setAll("info");
            if (isPlayerTurn) {
                displayPlayerHand(gameController.getPlayerHand());
            }
        }
    }
    
    public void displayPlayerHand(List<Playable> hand) {
        Platform.runLater(() -> {
            for (int i = 0; i < 5; i++) {
                TitledPane card = CardPaneBuilder.buildPane(hand.get(i), this::playCardHandle, i, cardPanes[i]);
                if (!hand.get(i).canPlay(gameController.getPlayerCastle())) {
                    cardPanes[i].setDisable(true);
                } else {
                    cardPanes[i].setDisable(false);
                }
                cardPanes[i].getChildren().setAll(card);
            }
        });
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
            changeLabel.applyCss();
            changeLabel.setVisible(true);
        } else if (newValue < origValue) {
            changeLabel.setText(Integer.toString(newValue - origValue));
            changeLabel.getStyleClass().setAll("decrease");
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
        Platform.runLater(() -> {
            Label[] changeLabels = {playerBuildersChange, playerBricksChange, playerWeaponsmithsChange, playerWeaponsChange,
                playerMagesChange, playerCrystalsChange, playerCastleHpChange, playerWallHpChange,
                opponentBuildersChange, opponentBricksChange, opponentWeaponsmithsChange, opponentWeaponsChange,
                opponentMagesChange, opponentCrystalsChange, opponentCastleHpChange, opponentWallHpChange};
            for (Label changeLabel : changeLabels) {
                changeLabel.setVisible(false);
            }
        });
    }
    /**
     * Displays last played card in the appropriate slot
     * @param lastPlayedCard 
     * @param isDiscarded true if card was discarded
     */
    public void displayLastPlayed(Playable lastPlayedCard, boolean isDiscarded) {
        Platform.runLater(() -> {
            TitledPane lastPlayed = CardPaneBuilder.buildPane(lastPlayedCard, null, 0, cardLastPlayed);
            cardLastPlayed.getChildren().setAll(lastPlayed);
            cardLastPlayed.setDisable(isDiscarded);
        });
    }
    /**
     * Disables all cards, so that player can not play anything
     */
    private void disableCards() {
        Platform.runLater(() -> {
            for (AnchorPane cardPane : cardPanes) {
                for (Node card : cardPane.getChildren()) {
                    card.setDisable(true);
                }
            }
        });
    }
    
    public void endPlayerTurn() {
        Platform.runLater(() -> {
            isPlayerTurn = false;
            discardInfoLabel.setVisible(false);
        });
    }
    
    public void startPlayerTurn() {
        Platform.runLater(() -> {
            isPlayerTurn = true;
            discardInfoLabel.setVisible(true);
            keyReleaseHandle(new KeyEvent(null, null, KeyEvent.KEY_RELEASED, "", "", KeyCode.CONTROL, false, false, false, false));
        });
    }

    public void endOfGame(boolean won) {
        Platform.runLater(() -> {
            if (won) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(null);
                alert.setHeaderText("VICTORY");
                alert.setContentText("Congratulations, you won!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(null);
                alert.setHeaderText("DEFEAT");
                alert.setContentText("Sorry, you lost");
                alert.showAndWait();
            }
            try {
                application.replaceSceneContent(fxmlPaths.MENU);
            } catch (Exception ex) {
                Logger.getLogger(GameSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
