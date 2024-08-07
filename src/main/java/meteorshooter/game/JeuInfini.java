package meteorshooter.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import meteorshooter.graphics.VueGameplay;

public class JeuInfini {

    public JeuInfini(Stage stage){

        BorderPane mainPane = new BorderPane();

        GameCore gameCore = new GameCore();

            GameLoop gameLoop = new GameLoop(gameCore);
            gameLoop.start();

            VueGameplay vueGameplay = new VueGameplay(gameCore, mainPane);

            mainPane.setPrefSize(800, 600);

            Scene scenejeu = new Scene(mainPane);

            stage.addEventHandler(KeyEvent.KEY_PRESSED, evt -> {
                gameCore.addKey(evt.getCode());
            });

            stage.addEventHandler(KeyEvent.KEY_RELEASED, evt -> {
                gameCore.removeKey(evt.getCode());
            });
            
            stage.setScene(scenejeu);
    }
    
}
