package castlewars.scenes;

import castlewars.User;
import castlewars.fxmlPaths;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


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
        btn.setFont(newProfile.getFont()); //use the same font as the other buttons are using
        pane.getChildren().add(btn);
        
        Button delete = new Button("X");
        delete.setFont(newProfile.getFont());
        delete.setOnAction(this::handleDelete);
        delete.setId(name);
        pane.getChildren().add(delete);
        vBoxProfiles.getChildren().add(pane);
    }
    /**
     * Handles creating new profile
     */
    public void handleCreate() {
        System.out.println("creating new");
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Name input");
        dialog.setHeaderText("Please enter your name.");
        //dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent((String name) -> {
            try {
                User user = new User(application.getConnection(), name);
                user.writeToDb();
            } catch (SQLException ex) {
                Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (User.AlreadyExistsException e) {
                System.out.println("Profile with this name already exists");
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
        System.out.println(deleteID);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Do you really want to delete profile " + deleteID + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                if (new User(application.getConnection(), deleteID).deleteProfile()) {
                    application.replaceSceneContent(fxmlPaths.PROFILE.getPath());
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
}
