package castlewars;

import castlewars.ai.AI;
import castlewars.ai.BeginnerAI;
import castlewars.playable.Archer;
import castlewars.playable.Card;
import castlewars.playable.Playable;
import castlewars.scenes.GameSceneController;
import java.sql.SQLException;
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
    private List<Playable> playerHand;
    private GameSceneController sceneController;
    private int difficulty = 0;
    private AI opponent;
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
        try {
            playerDeck = application.getPlayer().loadDeck();
        } catch (SQLException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 5; i++) {
            playerHand.add(playerDeck.draw());
        }
        sceneController.displayPlayerHand(playerHand);
        sceneController.displayCastles(playerCastle, opponentCastle, 0);
        opponent = buildAI();
    }

    
    public List<Playable> getPlayerHand() {
        return playerHand;
    }

    /**
     * plays card on the specified position in players hand
     * @param positionInHand
     */
    public void playCard(int positionInHand) {
        Playable played = playerHand.remove(positionInHand);
        try {
            played.play(playerCastle, opponentCastle);
        } catch (Playable.GameEnd ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Playable.CanNotPlayException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerHand.add(positionInHand, playerDeck.draw());
        sceneController.displayLastPlayed(played);
        displayChanges();
        //opponents turn start
        opponentCastle.nextTurn();
        displayChanges();
        //play card
        Playable aiPlay = opponent.chooseCard(opponentCastle, playerCastle);
        if (aiPlay != null) {
            try {
                aiPlay.play(opponentCastle, playerCastle);
            } catch (Playable.GameEnd ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Playable.CanNotPlayException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
        }
        sceneController.displayLastPlayed(aiPlay);
        displayChanges();
        //player turn start
        playerCastle.nextTurn();
        displayChanges();
        sceneController.displayPlayerHand(playerHand); 
    }
    
    private void displayChanges() {
        sceneController.displayCastles(playerCastle, opponentCastle, DELAY);
        try {
            synchronized(syncObject) {
                syncObject.wait();
            }
            Thread.sleep(DELAY/3);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    private AI buildAI() {
        switch (difficulty) {
            case 0: return new BeginnerAI();
            default: return new BeginnerAI();
        }
    }
}
