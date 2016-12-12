package castlewars;

import castlewars.scenes.ProfileSelectSceneController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Jakub
 */
public class Launcher extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //connect to database
        //load from database
        //call login "Presenter"
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProfileSelectSceneController pssc = new ProfileSelectSceneController();
        stage.setScene(pssc.buildScene());
        stage.show();
    }
    
}
