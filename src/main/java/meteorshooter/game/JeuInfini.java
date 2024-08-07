package meteorshooter.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import meteorshooter.graphics.VueGameplay;

public class JeuInfini {

    private Controleur controleur;
    private Stage stage;
    private BorderPane mainPane;
    private GameCore gameCore;
    private GameLoop gameLoop;

    public JeuInfini(Stage stage, Controleur c){

        this.stage = stage;
        this.controleur = c;
        
        mainPane = new BorderPane();
        gameCore = new GameCore();
        gameLoop = new GameLoop(gameCore);
        gameLoop.start();
        VueGameplay vueGameplay = new VueGameplay(gameCore, mainPane);
        mainPane.setPrefSize(1024, 768);

        Scene scenejeu = new Scene(mainPane);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, evt -> {
            gameCore.addKey(evt.getCode());
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED, evt -> {
            gameCore.removeKey(evt.getCode());
        });

        controleur.setSceneJeu(scenejeu);
        controleur.commuter(scenejeu);
    }
    
}
