package castlewars;

import castlewars.scenes.BaseSceneController;
import castlewars.database.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.derby.jdbc.EmbeddedDataSource;

/**
 *
 * @author Jakub
 */
public class Launcher extends Application {

    private Stage stage;
    
    private Database db;
    
    private Connection connection;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //create database connection
        db = new Database();
        connection = db.getConnection();
        stage = primaryStage;
        replaceSceneContent(fxmlPaths.PROFILE.getPath());
        stage.show();
    }
    
        /**
         * Replaces current scene with one specified in given fxml
         * @param fxml path to fxml
         * @throws Exception 
         */
        public void replaceSceneContent(String fxml) throws Exception {
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
        BaseSceneController sceneController = (BaseSceneController) loader.getController();
        sceneController.setApp(this);
        sceneController.postInit();
        
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        
    }

    public Connection getConnection() {
        return connection;
    }

    public Stage getStage() {
        return stage;
    }
    
    
}
