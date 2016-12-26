package castlewars.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 * class that handles database
 * @author Kukuksumusu
 */
public class Database {
    private final String URL = ".";
    private final Connection connection;
    /**
     * array of all cards
     */
    private final String[] cardClassNames = {"Archer"};

    /**
     * 
     * @throws SQLException 
     */
    public Database() throws SQLException {
        EmbeddedDataSource eds = new EmbeddedDataSource();
        eds.setDatabaseName(URL + "/database");
        eds.setCreateDatabase("create");
        connection = eds.getConnection();
        init();
    }
    /**
     * 
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }
    /**
     * Initializes database tables if they don't exist
     * @throws SQLException 
     */
    private void init() throws SQLException {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "PROFILES", null);
        if (!rs.next()) {
            System.out.println("Creating Database");
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE PROFILES"
                        + "(profile_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "name VARCHAR(50),"
                        + "UNIQUE (name),"
                        + "PRIMARY KEY (profile_id))");
        }
        rs = md.getTables(null, null, "CARDS", null);
        if(!rs.next()) {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE CARDS"
                        + "(card_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "classname VARCHAR(50),"
                        + "UNIQUE (classname),"
                        + "PRIMARY KEY (card_id))");
            insertCards();
        }
        rs = md.getTables(null, null, "DECKS", null);
        if(!rs.next()) {
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE DECKS"
                        + "(profile_id INT NOT NULL,"
                        + "card_id INT NOT NULL,"
                        + "count INT NOT NULL,"
                        + "FOREIGN KEY (profile_id) "
                            + "REFERENCES PROFILES (profile_id),"
                        + "FOREIGN KEY (card_id)" 
                            + " REFERENCES CARDS (card_id))");
        }
    }

    private void insertCards() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO CARDS (classname) VALUES (?)");
        for (String cardClassName : cardClassNames) {
            ps.setString(1, cardClassName);
            ps.execute();
        }
    }
}
