package meteorshooter.game;

import javafx.scene.Scene;
import javafx.stage.Stage;
import meteorshooter.graphics.menu.GameMenu;
import meteorshooter.graphics.menu.GameOverMenu;
import meteorshooter.graphics.menu.texte;

public class Controleur {

    private GameMenu gamemenu;
    private GameOverMenu gameovermenu;
    private Stage stage;
    private Scene sceneMenu;
    private Scene sceneJeu;
    private Scene sceneGameOver;

    public Controleur(Stage stage) {
        this.stage = stage;
    }

    public void commuter(Scene nouvelleScene) {
        stage.setScene(nouvelleScene);
    }

    public Scene getSceneMenu() {
        return sceneMenu;
    }

    public void SetSceneMenu(Scene sceneMenu) {
      this.sceneMenu = sceneMenu;
  }

    public void setSceneJeu(Scene sceneJeu) {
        this.sceneJeu = sceneJeu;
    }

    public Scene getSceneJeu() {
        return sceneJeu;
    }

    public GameMenu getGameMenu() {
      return gamemenu;
    }

    public GameOverMenu getGameOverMenu() {
        return gameovermenu;
    }

    public void setGameOverMenu(GameOverMenu gameovermenu) {
        this.gameovermenu = gameovermenu;
    }

    public void setGameMenu(GameMenu gamemenu) {
      this.gamemenu = gamemenu;
  }
    // Méthode pour initialiser et démarrer un nouveau niveau
    public void startNewLevel(long tempsNiveau, texte dialoguesDebut, texte dialoguesFin, String langue, String imagePath) {
        JeuNiveau jeuNiveau = new JeuNiveau(stage, this,tempsNiveau, dialoguesDebut, dialoguesFin,langue,imagePath);
    }

    public void startLevelInfini() {
        JeuInfini jeuInfini = new JeuInfini(stage, this);
    }

    public Scene getSceneGameOver() {
        return sceneGameOver;
    }

    public void SetSceneGameOver(Scene sceneGameOver) {
      this.sceneGameOver = sceneGameOver;
  }
}

