/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars.scenes;

import castlewars.fxmlPaths;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            application.replaceSceneContent(fxmlPaths.GAME);
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


    
}
