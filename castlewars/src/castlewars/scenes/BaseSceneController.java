package castlewars.scenes;

import javafx.fxml.Initializable;
import javafx.scene.Scene;

/**
 *
 * @author Kukuksumusu
 */
public abstract class BaseSceneController implements Initializable{
    
  protected final String fxmlPath;

    /**
     * 
     * @param fxmlPath 
     */
    public BaseSceneController(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
    
}
