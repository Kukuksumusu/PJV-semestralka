package castlewars.scenes;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Kukuksumusu
 */
public class ProfileSelectSceneBuilder extends BaseSceneBuilder {

    @Override
    public Scene buildScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profileSelectScene.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("javafx_cviceni/layout.css");
        return scene;
    }
    
}
