
package castlewars.scenes;

import castlewars.fxmlPaths;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Kukuksumusu
 */
public class MenuSceneController extends BaseSceneController {

    @FXML
    private Label loggedAsLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @Override
    public void postInit() {
        loggedAsLabel.setText(application.getPlayer().getName());
    }

    @FXML
    private void startGameHandle(ActionEvent event) {
        try {

            ChoiceDialog<String> dialog = new ChoiceDialog<>("Beginner", "Beginner", "Easy");
            dialog.setTitle("Difficulty");
            dialog.setHeaderText(null);
            dialog.setContentText("Choose difficulty:");

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                int difficulty = 0;
                switch (dialog.getSelectedItem()) {
                    case "Beginner": difficulty = 0; break;
                    case "Easy": difficulty = 1; break;
                }
                application.getGameController().setDifficulty(difficulty);
                application.replaceSceneContent(fxmlPaths.GAME);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(MenuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deckBuilderHandle(ActionEvent event) {
        try {
            application.replaceSceneContent(fxmlPaths.DECK_BUILDER);
        } catch (Exception ex) {
            Logger.getLogger(MenuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeProfileHandle(ActionEvent event) {
        try {
            application.replaceSceneContent(fxmlPaths.PROFILE);
        } catch (Exception ex) {
            Logger.getLogger(MenuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void instructionsHandle(ActionEvent event) {
        try {
            application.replaceSceneContent(fxmlPaths.INSTRUCTIONS);
        } catch (Exception ex) {
            Logger.getLogger(MenuSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
