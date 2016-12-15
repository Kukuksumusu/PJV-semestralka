package castlewars.scenes;

import castlewars.Launcher;
import javafx.fxml.Initializable;

/**
 * Abstract class for all controllers
 * @author Kukuksumusu
 */
public abstract class BaseSceneController implements Initializable{
    
    protected Launcher application;
    
    public void setApp(Launcher app) {
        application = app;
    }
}
