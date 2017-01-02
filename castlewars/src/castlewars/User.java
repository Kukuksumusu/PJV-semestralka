package castlewars;

import castlewars.playable.*;
import castlewars.scenes.DeckBuilderSceneController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Class that represents user/profile
 * @author Jakub
 */
public class User {
    private final String name;
    private int id;
    private final Connection connection;

    public User(Connection connection, String name) throws SQLException, DoesntExistsException {
        this.name = name;
        this.connection = connection;
        PreparedStatement ps = connection.prepareStatement("SELECT profile_id FROM PROFILES WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            this.id = rs.getInt("profile_id");
        } else {
            throw new DoesntExistsException();
        }
    }
    
    /**
     * Deletes user from database
     * @return true if user was deleted
     * @throws SQLException 
     */
    public boolean deleteProfile() throws SQLException {
        PreparedStatement psDecks = connection.prepareStatement("DELETE FROM DECKS WHERE profile_id = ?");
        psDecks.setInt(1, id);
        psDecks.execute();
        PreparedStatement psProfiles = connection.prepareStatement("DELETE FROM PROFILES WHERE profile_id = ? AND name = ?");
        psProfiles.setInt(1, id);
        psProfiles.setString(2, name);
        return psProfiles.executeUpdate() != 0;
    }
    
    public Deck loadDeck() throws SQLException {
        Deck deck = new Deck();
        PreparedStatement ps = connection.prepareStatement("SELECT classname, count FROM DECKS JOIN CARDS USING (card_id) WHERE profile_id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < rs.getInt("count"); i++) {
                try {
                    deck.addCard((Playable)Class.forName(rs.getString("classname")).newInstance());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(DeckBuilderSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return deck;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public static class DoesntExistsException extends Exception {
        
        public DoesntExistsException() {
        }
    }
    
}
