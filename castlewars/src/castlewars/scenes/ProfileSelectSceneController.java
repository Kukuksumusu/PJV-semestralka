package castlewars.scenes;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
            ToggleGroup profiles = new ToggleGroup();
            Connection conn = application.getConnection();
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PROFILES");
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Font font = newProfile.getFont();
                ToggleButton btn = new ToggleButton(rs.getString("NAME"));
                btn.setToggleGroup(profiles);
                btn.setFont(font);
                vBoxProfiles.getChildren().add(btn);
            }
            //and so on, and so forth
        } catch (SQLException ex) {
            Logger.getLogger(ProfileSelectSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createHandler() {
        System.out.println("creating new");
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Name input");
        dialog.setHeaderText("Please enter your name.");
        //dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> System.out.println("Your name: " + name));
    }
    
}
