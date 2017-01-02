package castlewars.ai;

import castlewars.Castle;
import castlewars.Deck;
import castlewars.playable.Playable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base class for AI
 * @author Kukuksumusu
 */
public abstract class AI {
    protected Deck deck;
    protected List<Playable> hand;

    public AI(Deck deck) {
        this.deck = deck;
        this.hand = new LinkedList<>();
        
        deck.shuffle();  
        for (int i = 0; i < 5; i++) {
            hand.add(deck.draw());
        }
    }

    public void draw() {
        hand.add(deck.draw());
    }
    
    public abstract Playable chooseCard(Castle aiCastle, Castle playerCastle);
    /**
     * Chooses and discards a card
     * @param aiCastle
     * @param playerCastle
     * @return discarded card
     */
    public abstract Playable chooseAndDiscard(Castle aiCastle, Castle playerCastle);
    
    static protected Deck getDefaultDeck(Connection connection) {
        Deck deck = new Deck();
        try {
            ResultSet rs = connection.prepareStatement("SELECT classname FROM CARDS").executeQuery();
            while (rs.next()) {
                deck.addCard((Playable) Class.forName(rs.getString("classname")).newInstance());
                deck.addCard((Playable) Class.forName(rs.getString("classname")).newInstance());
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deck;
    }
    
}
