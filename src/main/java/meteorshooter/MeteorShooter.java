package meteorshooter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import meteorshooter.game.Controleur;
import meteorshooter.graphics.menu.GameMenu;
import meteorshooter.graphics.menu.GameOverMenu;

public class MeteorShooter extends Application {

    private GameMenu gameMenu;
    private GameOverMenu gameOverMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Controleur controleur = new Controleur(stage);

        // Créez une instance de GameMenu et passez le contrôleur
        gameMenu = new GameMenu(controleur);

        // Créez une instance de GameOverMenu et passez le contrôleur
        gameOverMenu = new GameOverMenu(controleur, 0);
        
        controleur.setGameMenu(gameMenu);
        controleur.setGameOverMenu(gameOverMenu);

        Scene scenemenu = new Scene(gameMenu, 1920, 1080);

        Scene sceneovermenu = new Scene(gameOverMenu, 1920, 1080);


        controleur.SetSceneMenu(scenemenu);
        controleur.SetSceneGameOver(sceneovermenu);

        controleur.commuter(scenemenu);
        stage.setTitle("Meteor Shooter");
        stage.setScene(scenemenu);
        stage.show();
    }
}
