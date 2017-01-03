/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars.scenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Kukuksumusu
 */
public class InstructionsSceneController extends BaseSceneController {

    @FXML
    private TextFlow textFlow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Text t = new Text("The goal of the game is to either build 100 storeys castle or destroy opponents one.\n" +
                "You can achieve this by playing cards. Every card has its cost (upper part of card) and its effect (bottom part of card).\n\n" +
                "Both castles have 3 types of resources (used to play cards) - bricks, weapons and crystals.\n" + 
                "And 3 types of resource producers - builders, weaponsmiths and mages. Every resource producer makes 1 of given resource per turn.\n" +
                "Castle also has its height and height of its wall. The wall is used to block opponents attacks.\n" +
                "(e.g. if you have wall of height 3 and castle of height 10, and opponent uses 'Knight' (attack 4)" + 
                ", the wall will block first 3 damage and your castle will only take 1 damage. So you will be left with wall 0 and castle 9.)\n\n" +
                "Every turn consist of you getting your resource growth and playing (or discarding) a card.\n" +
                "You have to play or discard EXACTLY one card every turn, so plan accordingly. :)\n" +
                "PS: to dicard you click the card you want to discard, while holding 'CTRL'");
        textFlow.getChildren().add(t);
    }    

    @Override
    public void postInit() {
    }
    
}
