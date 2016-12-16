package castlewars.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
    private final String USERNAME = "testovaci";
    private final String PASSWORD = "heslo";
    private final Connection connection;

    /**
     * 
     * @throws SQLException 
     */
    public Database() throws SQLException {
        EmbeddedDataSource eds = new EmbeddedDataSource();
        eds.setDatabaseName(URL + "/database");
        eds.setCreateDatabase("create");
        eds.setUser(USERNAME);
        eds.setPassword(PASSWORD);
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
            Statement s = connection.createStatement();
            s.executeUpdate("CREATE TABLE PROFILES"
                        + "(id INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "name VARCHAR(50),"
                        + "UNIQUE (name),"
                        + "PRIMARY KEY (id))");
        }
    }
}
