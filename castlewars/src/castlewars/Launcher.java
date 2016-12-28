package castlewars;

import castlewars.scenes.BaseSceneController;
import castlewars.database.Database;
import java.io.InputStream;
import java.sql.Connection;
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
    
    private Database db;
    
    private Connection connection;
    
    private GameController gameController;
    
    private User player;
    
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
        gameController = new GameController(this);
        replaceSceneContent(fxmlPaths.PROFILE);
        //replaceSceneContent(fxmlPaths.DECK_BUILDER);
        stage.show();
    }
    
        /**
         * Replaces current scene with one specified in given fxml
         * @param fxml path to fxml
         * @throws Exception 
         */
        public void replaceSceneContent(fxmlPaths fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Launcher.class.getResourceAsStream(fxml.getPath());
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Launcher.class.getResource(fxml.getPath()));
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
        if (fxml.getCSS() != null) {
            scene.getStylesheets().add(fxml.getCSS());
        }
        stage.setScene(scene);
        stage.sizeToScene();
    }

    public Connection getConnection() {
        return connection;
    }

    public GameController getGameController() {
        return gameController;
    }
    
    public Stage getStage() {
        return stage;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }
    
    
}
