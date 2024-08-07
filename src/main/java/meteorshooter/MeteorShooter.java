package meteorshooter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import meteorshooter.game.Controleur;
import meteorshooter.graphics.menu.GameMenu;

public class MeteorShooter extends Application {

    private GameMenu gameMenu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Controleur controleur = new Controleur(stage);

        // Créez une instance de GameMenu et passez le contrôleur
        gameMenu = new GameMenu(controleur);
        
        // Définir le jeu dans le contrôleur
        controleur.setGameMenu(gameMenu);

        // Créez la scène avec GameMenu comme racine
        Scene scenemenu = new Scene(gameMenu, 800, 600);

        // Configurer le contrôleur pour utiliser cette scène
        controleur.SetSceneMenu(scenemenu);
        controleur.commuter(scenemenu);
        stage.setScene(scenemenu);
        stage.show();
    }
}
