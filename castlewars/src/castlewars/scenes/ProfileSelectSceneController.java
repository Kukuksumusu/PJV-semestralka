package castlewars.scenes;

import castlewars.User;
import castlewars.fxmlPaths;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author Jakub
 */
public class ProfileSelectSceneController extends BaseSceneController {
    
    @FXML
    private VBox vBoxProfiles;
    
    @FXML
    private Button newProfile;
    
    @FXML
    private Button selectProfile;

    private ToggleGroup profiles;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
   
    @Override
    public void postInit() {
        try {
            profiles = new ToggleGroup();
            Connection conn = application.getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM PROFILES").executeQuery();
            while (rs.next()) {
                addProfileButton(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Adds toggle button for selection of profile and X button for deleting it
     * @param name 
     */
    private void addProfileButton(String name) {
        HBox pane = new HBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        
        ToggleButton btn = new ToggleButton(name);
        btn.setToggleGroup(profiles);
        btn.setSelected(true);
        btn.setFont(newProfile.getFont()); //use the same font as the other buttons are using
        pane.getChildren().add(btn);
        
        Button delete = new Button("X");
        delete.setFont(newProfile.getFont());
        delete.setOnAction(this::handleDelete);
        delete.setId(name);
        delete.getStyleClass().add("delete");
        pane.getChildren().add(delete);
        vBoxProfiles.getChildren().add(pane);
    }
    /**
     * Handles creating new profile
     */
    public void handleCreate() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Name input");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent((String name) -> {
            try {
                createNewProfile(name);
            } catch (SQLException ex) {
                Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AlreadyExistsException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Can not create profile");
                alert.setHeaderText("Profile '" + name + "' already exists");
                alert.showAndWait();
                return;
            }
            addProfileButton(name);
        });
    }
    /**
     * Handles deletion of profile
     * @param event 
     */
    private void handleDelete(ActionEvent event) {
        String deleteID = ((Button)event.getSource()).getId();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Do you really want to delete profile " + deleteID + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                if (new User(application.getConnection(), deleteID).deleteProfile()) {
                    application.replaceSceneContent(fxmlPaths.PROFILE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    /**
     * Handles selection of profile
     * @param event
     */
    public void handleSelect(Event event) {
        try {
            if(profiles.getSelectedToggle() == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("No profile selected");
                alert.setHeaderText("Please select a profile");
                alert.showAndWait();
                return;
            }
            application.setPlayer(new User(application.getConnection(), ((ToggleButton)profiles.getSelectedToggle()).getText()));
            application.replaceSceneContent(fxmlPaths.MENU);
        } catch (SQLException ex) {
            Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Creates new profile with default deck (every card possible x2)
     * @param name
     * @throws SQLException
     * @throws castlewars.scenes.ProfileSelectSceneController.AlreadyExistsException 
     */
    private void createNewProfile(String name) throws SQLException, AlreadyExistsException {
        Connection connection = application.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO PROFILES (name) VALUES (?)");
        ps.setString(1, name);
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new AlreadyExistsException();
        }
        //get the profile id
        ps = connection.prepareStatement("SELECT profile_id FROM PROFILES WHERE name = ?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new SQLException("Couldn't load id when creating profile");
        }
        int id = rs.getInt("profile_id");
        //get all cards and insert the deck
        ps = connection.prepareStatement("INSERT INTO DECKS (profile_id, card_id, count) VALUES (?,?,2)");
        ps.setInt(1, id);
        Statement s = connection.createStatement();
        rs = s.executeQuery("SELECT card_id FROM CARDS");
        while (rs.next()){
            ps.setInt(2, rs.getInt("card_id"));
            ps.execute();
        }
    }

    private static class AlreadyExistsException extends Exception {

        public AlreadyExistsException() {
        }
    }
}
