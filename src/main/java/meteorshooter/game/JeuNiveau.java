package meteorshooter.game;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import meteorshooter.graphics.VueGameplay;
import meteorshooter.graphics.menu.Dialogue;
import meteorshooter.graphics.menu.texte;

public class JeuNiveau {

    private Controleur controleur;
    private Timer timer;
    private Stage stage;
    private long tempsNiveau; // Temps pour le niveau en millisecondes 
    private BorderPane mainPane;
    private GameCore gameCore;
    private GameLoop gameLoop;
    private texte dialoguesDebut; // Dialogues au début du niveau
    private texte dialoguesFin; // Dialogues à la fin du niveau
    private String langue;


    public JeuNiveau(Stage stage, Controleur c, long tempsNiveau, texte dialoguesDebut, texte dialoguesFin, String langue) {
        this.stage = stage;
        this.controleur = c;
        this.tempsNiveau = tempsNiveau;
        this.dialoguesDebut = dialoguesDebut;
        this.dialoguesFin = dialoguesFin;
        this.langue = langue;
        
        initializeGame();
        showIntroDialogues();
    }

    private void initializeGame() {

        mainPane = new BorderPane();
        gameCore = new GameCore();
        gameLoop = new GameLoop(gameCore,controleur);
        // gameLoop.start();
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

    private void showIntroDialogues() {
        Dialogue DialogueDebut = new Dialogue(dialoguesDebut.GetTexte(langue));
        DialogueDebut.setTranslateX(512);
        DialogueDebut.setTranslateY(400);
        mainPane.getChildren().add(DialogueDebut);

        DialogueDebut.setOnMouseClicked(evt -> {
            mainPane.getChildren().remove(DialogueDebut);
            startLevel();
        });
    }

    private void startLevel() {
        gameLoop.start();
        startLevelTimer();
    }

    private void startLevelTimer() {
        timer = new Timer();
        TimerTask endLevelTask = new TimerTask() {
            @Override
            public void run() {
                gameLoop.stop();
                showEndDialogues();
            }
        };
        timer.schedule(endLevelTask, tempsNiveau);
    }

    private void showEndDialogues() {
        Platform.runLater(() -> {
            Dialogue dialogueFin = new Dialogue(dialoguesFin.GetTexte(langue));
            System.err.println("Dialogue créé");
            dialogueFin.setTranslateX(512);
            dialogueFin.setTranslateY(400);

            try {
            InputStream is = Files.newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/Victory.png"));
            Image winnerimg = new Image(is);
            is.close();
            ImageView imgview = new ImageView(winnerimg);
            imgview.setFitWidth(400);
            imgview.setFitHeight(120);

            imgview.setLayoutX((mainPane.getWidth() - imgview.getFitWidth()) / 2);
            imgview.setLayoutY(100);

            mainPane.getChildren().add(imgview);

            } catch (Exception e) {
                e.printStackTrace();
            }

            
            mainPane.getChildren().add(dialogueFin);
            
            dialogueFin.setOnMouseClicked(evt -> {
                mainPane.getChildren().remove(dialogueFin);
                endLevel();
            });
        });
    }

    private void endLevel() {
        Platform.runLater(() -> {
            controleur.commuter(controleur.getSceneMenu());
        });
    }
}