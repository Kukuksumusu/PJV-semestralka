package castlewars.scenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
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
    private ToggleGroup profiles;
    
    @FXML
    private VBox vBoxProfiles;
   
    private final String fxmlPath = "profileSelectScene.fxml";
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleButton btn = new ToggleButton("1");
        btn.setToggleGroup(profiles);
        vBoxProfiles.getChildren().add(btn);
        //and so on, and so forth
    }    

    @Override
    public Scene buildScene() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);   
        //scene.getStylesheets().add("javafx_cviceni/layout.css");
        return scene;     
    }
    
}
