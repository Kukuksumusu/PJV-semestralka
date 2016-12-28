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
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author Kukuksumusu
 */
public class DeckBuilderSceneController extends BaseSceneController {

    @FXML
    private FlowPane flowPane;

    private List<Spinner> spinners;
    private Connection conn;
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
            conn = application.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT card_id, classname, count FROM CARDS LEFT JOIN DECKS USING (card_id) WHERE profile_id is NULL or profile_id = ?");
            ps.setInt(1, application.getPlayer().getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString("classname"));
                AnchorPane cardPane = new AnchorPane();
                cardPane.setPrefSize(100, 200);
                TitledPane card = CardPaneBuilder.buildPane((Playable)Class.forName("castlewars.playable." + rs.getString("classname")).newInstance(), null, 0, cardPane);
                cardPane.getChildren().add(card);
                Spinner<Integer> spinner = new Spinner<>(0, 5, rs.getInt("count"));
                spinner.setId(rs.getString("card_id"));
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
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE DECKS SET count = ? WHERE profile_id = ? AND card_id = ?");
            ps.setInt(2, application.getPlayer().getId());
            for (Spinner spinner : spinners) {
                System.out.println(spinner.getId() + ": " + spinner.getValue());
                ps.setInt(1, (int) spinner.getValue());
                ps.setInt(3, Integer.parseInt(spinner.getId()));
                int res = ps.executeUpdate();
                if (res == 0) {
                    PreparedStatement insert = conn.prepareStatement("INSERT INTO DECKS (profile_id, card_id, count) VALUES ?,?,?");
                    insert.setInt(1, (int) spinner.getValue());
                    insert.setInt(2, application.getPlayer().getId());
                    insert.setInt(3, Integer.parseInt(spinner.getId()));
                    insert.execute();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeckBuilderSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
