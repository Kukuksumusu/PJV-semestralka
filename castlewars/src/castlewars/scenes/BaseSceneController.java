package castlewars.scenes;

import castlewars.Launcher;
import javafx.fxml.Initializable;

/**
 * Abstract class for all controllers
 * @author Kukuksumusu
 */
public abstract class BaseSceneController implements Initializable{
    
    protected Launcher application;
    
    /**
     * Sets application 
     * @param app 
     */
    public void setApp(Launcher app) {
        application = app;
    }
    
    /**
     * Initializes scene after the actual controller class was initialized (e.g. already has application)
     */
    public abstract void postInit();
}
