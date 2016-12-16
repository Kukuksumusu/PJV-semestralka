package castlewars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that represents user/profile
 * @author Jakub
 */
public class User {
    private final String name;
    private int id;
    private final Connection connection;

    public User(Connection connection, String name) throws SQLException {
        this.name = name;
        this.connection = connection;
        PreparedStatement ps = connection.prepareStatement("SELECT id FROM PROFILES WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            this.id = rs.getInt("id");
        }
    }
   
    public void writeToDb() throws SQLException, AlreadyExistsException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO PROFILES (name) VALUES (?)");
        ps.setString(1, name);
        try {
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new AlreadyExistsException();
        }
    }

    public static class AlreadyExistsException extends Exception {

        public AlreadyExistsException() {
        }
    }
    
}
