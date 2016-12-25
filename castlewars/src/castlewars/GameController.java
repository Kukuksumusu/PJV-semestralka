package castlewars;

import castlewars.playable.Archer;
import castlewars.playable.Card;
import castlewars.playable.Playable;
import castlewars.scenes.GameSceneController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that contains the game logic
 * @author Kukuksumusu
 */
public class GameController{
    private final int DELAY = 1000;
    private final int STARTING_CASTLE_HP = 30;
    private final int STARTING_WALL_HP = 10;
    private final int STARTING_RESOURCE_MAKERS = 3;
    private final int STARTING_RESOURCES = 5;
    private final Launcher application;
    private Castle playerCastle;
    private Castle opponentCastle;
    private Deck playerDeck;
    private Deck opponentDeck;
    private List<Card> playerHand;
    private List<Card> opponentHand;
    private GameSceneController sceneController;
    /**
     * dummy object used for synchronization
     */
    public final Object syncObject;

    public GameController(Launcher application) {
        this.application = application;
        syncObject = new Object();
    }
    /**
     * initializes stuff for the game to be able to start
     */
    public void gameStart() {
        playerCastle = new Castle(STARTING_CASTLE_HP, STARTING_WALL_HP, 
                STARTING_RESOURCE_MAKERS, STARTING_RESOURCE_MAKERS, STARTING_RESOURCE_MAKERS, 
                STARTING_RESOURCES, STARTING_RESOURCES, STARTING_RESOURCES);
        opponentCastle = new Castle(STARTING_CASTLE_HP, STARTING_WALL_HP, 
                STARTING_RESOURCE_MAKERS, STARTING_RESOURCE_MAKERS, STARTING_RESOURCE_MAKERS, 
                STARTING_RESOURCES, STARTING_RESOURCES, STARTING_RESOURCES);
        playerHand = new ArrayList<>(5);
        opponentHand = new ArrayList<>(5);
        playerDeck = loadDeck(application.getPlayer());
        for (int i = 0; i < 5; i++) {
            playerHand.add(playerDeck.draw());
        }
        sceneController.displayPlayerHand(playerHand);
        sceneController.displayCastles(playerCastle, opponentCastle, 0);
    }

    private Deck loadDeck(User player) {
        //TODO
        //just for testing:
        Deck deck = new Deck();
        for (int i = 0; i < 10; i++) {
            deck.addCard(new Archer());
        }
        return deck;
    }
    
    public List<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * plays card on the specified position in players hand
     * @param positionInHand
     */
    public void playCard(int positionInHand) {
        Card played = playerHand.remove(positionInHand);
        try {
            played.play(playerCastle, opponentCastle);
        } catch (Playable.GameEnd ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Playable.CanNotPlayException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerHand.add(positionInHand, playerDeck.draw());
        sceneController.displayCastles(playerCastle, opponentCastle, DELAY);
        sceneController.displayLastPlayed(played);
        try {
            synchronized(syncObject) {
                syncObject.wait();
            }
            Thread.sleep(DELAY/3);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        opponentCastle.nextTurn();
        sceneController.displayCastles(playerCastle, opponentCastle, DELAY);
        //sceneController.displayPlayerHand(playerHand); 
    }
    
    public Castle getPlayerCastle() {
        return playerCastle;
    }
    
    public Castle getOpponentCastle() {
        return opponentCastle;
    }

    public void setSceneController(GameSceneController sceneController) {
        this.sceneController = sceneController;
    }
}
