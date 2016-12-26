/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars.scenes;

import castlewars.playable.Archer;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Kukuksumusu
 */
public class DeckBuilderSceneController extends BaseSceneController {

    @FXML
    private FlowPane flowPane;

    private List<Spinner> spinners;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinners = new LinkedList<>();
    }    

    @Override
    public void postInit() {
        flowPane.prefWrapLengthProperty().bind(application.getStage().widthProperty());
        for (int i = 0; i < 10; i++) {
            AnchorPane cardPane = new AnchorPane();
            TitledPane card = CardPaneBuilder.buildPane(new Archer(), null, 0);
            CardPaneBuilder.setCardToFill(card);
            cardPane.getChildren().add(card);
            cardPane.setPrefSize(100, 200);   
            Spinner<Integer> spinner = new Spinner<>(0, 5, 0);
            spinner.setId("Archer");
            spinner.setEditable(false);//true would require manual control on change
            spinners.add(spinner);
            VBox menuItem = new VBox(cardPane, spinner);
            flowPane.getChildren().add(menuItem);
        }
    }
    /**
     * Hander for submit
     * @param event 
     */
    @FXML
    private void submitHandle(ActionEvent event) {
        for (Spinner spinner : spinners) {
            System.out.println(spinner.getId() + ": " + spinner.getValue());
        }
    }
    
}
