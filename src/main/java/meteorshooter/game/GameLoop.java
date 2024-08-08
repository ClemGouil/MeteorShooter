package meteorshooter.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import meteorshooter.graphics.menu.GameOverMenu;

public class GameLoop extends AnimationTimer {

    private float lastFrameTime;
    private GameCore gameCore;
    private Controleur controleur;

    public GameLoop(GameCore core, Controleur controleur) {
        this.gameCore = core;
        this.controleur = controleur;
    }

    @Override
    public void handle(long now) {

        float msSinceLastFrame = (float) ((now - lastFrameTime) / 1e6);
        // On limite le temps d'une frame à 1000ms pour éviter les débordements au
        // démarrage
        msSinceLastFrame = Math.min(1000, msSinceLastFrame);

        lastFrameTime = now;

        this.gameCore.update(msSinceLastFrame);

        if (this.gameCore.isGameOver()) {
            this.stop();  // Arrête l'animation
            showGameOverScreen();  // Affiche l'écran de fin de jeu ou toute autre action
        }
    }

    private void showGameOverScreen() {
        // Implémentez cette méthode pour afficher l'écran de fin de jeu
        GameOverMenu gameovermenu= new GameOverMenu(controleur,gameCore.get_score().get_score2());
        Scene sceneGameOver = new Scene(gameovermenu, 1920, 1080);
        controleur.setGameOverMenu(gameovermenu);
        controleur.SetSceneGameOver(sceneGameOver);
        controleur.commuter(controleur.getSceneGameOver());
        System.out.println("Game Over!");
        // Par exemple, vous pouvez utiliser une fenêtre de dialogue ou une vue spécifique
    }

}
