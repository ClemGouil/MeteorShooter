package meteorshooter.graphics.menu;

import java.nio.file.Paths;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import meteorshooter.game.GameCore;
import meteorshooter.game.GameLoop;
import meteorshooter.graphics.VueGameplay;
import meteorshooter.graphics.*;


public class GameOver extends Parent {
    private Stage stage;
    private boolean musiqueON;
    private String Langue;

    public GameOver(Stage stage) {

        Media sound = new Media(Paths.get("./src/main/resources/meteorshooter/assets/Higan_Retour.mp3").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        musiqueON = true;
        Langue = "Francais";

        this.stage = stage;
        VBox menuGO = new VBox(10);
        VBox menu0 = new VBox(10);

        menuGO.setTranslateX(200);
        menuGO.setTranslateY(250);

        menu0.setTranslateX(200);
        menu0.setTranslateY(250);

        final int offset = 400;

        menu0.setTranslateX(offset);

        buttonMenu retButtonMenu = new buttonMenu("Retour au Menu");
        retButtonMenu.setOnMouseClicked(event -> {
            TransitionMenu(menuGO, menu0, offset);
        });

        buttonMenu buttRecommencer = new buttonMenu("Recommencer le Niveau");
        buttRecommencer.setOnMouseClicked(event -> {
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

        });
    }

    public void TransitionMenu(VBox menu1, VBox menu2,int offset){
        getChildren().add(menu1);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt2.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt3.setToX(menu2.getTranslateX());
            tt2.play();
            tt3.play();

            tt2.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
    }

    public void TransitionMenu(VBox menu1, HBox menu2,int offset){
        getChildren().add(menu1);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt2.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt3.setToX(menu2.getTranslateX());
            tt2.play();
            tt3.play();

            tt2.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
    }

    public void TransitionMenu(HBox menu1, VBox menu2,int offset){
        getChildren().add(menu1);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt2.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt3.setToX(menu2.getTranslateX());
            tt2.play();
            tt3.play();

            tt2.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
    }


}
