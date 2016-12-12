package castlewars;

import castlewars.scenes.ProfileSelectSceneBuilder;
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
        ProfileSelectSceneBuilder pssb = new ProfileSelectSceneBuilder();
        stage.setScene(pssb.buildScene());
        stage.show();
    }
    
}
