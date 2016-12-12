package castlewars.scenes;

import javafx.fxml.Initializable;
import javafx.scene.Scene;

/**
 *
 * @author Kukuksumusu
 */
public abstract class BaseSceneController implements Initializable{
    
    public abstract Scene buildScene() throws Exception;
    
}
