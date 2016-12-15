package castlewars;

import castlewars.scenes.BaseSceneController;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Jakub
 */
public class Launcher extends Application {

    private Stage stage;
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
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        replaceSceneContent(fxmlPaths.PROFILE.getPath());
        stage.show();
    }
    
        /**
         * Replaces current scene with one specified in given fxml
         * @param fxml path to fxml
         * @throws Exception 
         */
        private void replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Launcher.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Launcher.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        ((BaseSceneController) loader.getController()).setApp(this);
    }
}
