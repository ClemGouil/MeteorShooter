package meteorshooter.game;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Scene;
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
    private String Langue;


    public JeuNiveau(Stage stage, Controleur c, long tempsNiveau, texte dialoguesDebut, texte dialoguesFin) {
        this.stage = stage;
        this.controleur = c;
        this.tempsNiveau = tempsNiveau;
        this.dialoguesDebut = dialoguesDebut;
        this.dialoguesFin = dialoguesFin;
        this.Langue = "Francais";
        
        initializeGame();
        showIntroDialogues();
    }

    private void initializeGame() {
        System.out.println("ici");
        mainPane = new BorderPane();
        gameCore = new GameCore();
        gameLoop = new GameLoop(gameCore);
        // gameLoop.start();
        VueGameplay vueGameplay = new VueGameplay(gameCore, mainPane);
        mainPane.setPrefSize(800, 600);

        Scene scenejeu = new Scene(mainPane);
        stage.addEventHandler(KeyEvent.KEY_PRESSED, evt -> {
            gameCore.addKey(evt.getCode());
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED, evt -> {
            gameCore.removeKey(evt.getCode());
        });

        controleur.setSceneJeu(scenejeu);
        controleur.commuter(scenejeu);
        System.out.println("initialiser");
    }

    private void showIntroDialogues() {

       Dialogue DialogueDebut = new Dialogue(dialoguesDebut.GetTexte(Langue));
       DialogueDebut.setTranslateX(400);
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
                showEndDialogues();
            }
        };
        timer.schedule(endLevelTask, tempsNiveau);
    }

    private void showEndDialogues() {
        System.err.println("erreur");
        Platform.runLater(() -> { // Assurez-vous que les modifications se font sur le thread JavaFX
            Dialogue dialogueFin = new Dialogue(dialoguesFin.GetTexte(Langue));
            System.err.println("Dialogue créé");
    
            mainPane.getChildren().add(dialogueFin);
            
            dialogueFin.setOnMouseClicked(evt -> {
                gameLoop.stop();
                mainPane.getChildren().remove(dialogueFin);
                endLevel();
            });
        });
    }

    private void endLevel() {
        Platform.runLater(() -> {
            System.out.println("Niveau terminé !");
            controleur.commuter(controleur.getSceneMenu());
            System.out.println("Changement fait");
        });
    }
}