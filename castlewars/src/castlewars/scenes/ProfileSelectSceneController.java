package castlewars.scenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


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
        ToggleGroup profiles = new ToggleGroup();
        Font font = newProfile.getFont();
        ToggleButton btn = new ToggleButton("Button A");
        btn.setToggleGroup(profiles);
        btn.setFont(font);
        vBoxProfiles.getChildren().add(btn);
        btn = new ToggleButton("Button B");
        btn.setToggleGroup(profiles);
        btn.setFont(font);
        vBoxProfiles.getChildren().add(btn);
        //and so on, and so forth
    }    
    
}
