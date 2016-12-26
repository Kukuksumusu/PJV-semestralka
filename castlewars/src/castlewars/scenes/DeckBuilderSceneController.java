/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castlewars.scenes;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import castlewars.playable.*;

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
        try {
            flowPane.prefWrapLengthProperty().bind(application.getStage().widthProperty());
            Connection conn = application.getConnection();
            ResultSet rs = conn.prepareStatement("SELECT * FROM CARDS").executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("classname"));
                AnchorPane cardPane = new AnchorPane();
                cardPane.setPrefSize(100, 200);
                TitledPane card = CardPaneBuilder.buildPane((Playable)Class.forName("castlewars.playable." + rs.getString("classname")).newInstance(), null, 0, cardPane);
                cardPane.getChildren().add(card);
                Spinner<Integer> spinner = new Spinner<>(0, 5, 0);
                spinner.setId("Archer");
                spinner.setEditable(false);//true would require manual control on change
                spinners.add(spinner);
                VBox menuItem = new VBox(cardPane, spinner);
                flowPane.getChildren().add(menuItem);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DeckBuilderSceneController.class.getName()).log(Level.SEVERE, null, ex);
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
