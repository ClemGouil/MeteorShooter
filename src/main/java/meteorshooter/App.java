package meteorshooter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import meteorshooter.game.Controleur;
import meteorshooter.game.GameCore;
import meteorshooter.game.GameLoop;
import meteorshooter.graphics.VueGameplay;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded", "false");
        launch(args);
    }     

    private static GameCore gameCore;
    private static GameLoop gameLoop;
    private Controleur controleur;

    private static VueGameplay vueGameplay;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();

        gameCore = new GameCore();

        gameLoop = new GameLoop(gameCore, controleur);
        gameLoop.start();

        vueGameplay = new VueGameplay(gameCore, mainPane,".\\src\\main\\resources\\meteorshooter\\assets\\Artwork Soleil.png");
        
        mainPane.setPrefSize(800, 600);

        Scene scene = new Scene(mainPane);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            gameCore.addKey(event.getCode());
        });

        stage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            gameCore.removeKey(event.getCode());
        });

        // stage.setScene(new Scene(createContent(), 300, 300));
        stage.setScene(scene);
        stage.show();
    }

}