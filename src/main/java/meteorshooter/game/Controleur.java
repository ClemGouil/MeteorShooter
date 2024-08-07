package meteorshooter.game;

import java.util.List;

import javafx.scene.Scene;
import javafx.stage.Stage;
import meteorshooter.graphics.menu.GameMenu;
import meteorshooter.graphics.menu.texte;

public class Controleur {

    private GameMenu gamemenu;
    private Stage stage;
    private Scene sceneMenu;
    private Scene sceneJeu;

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
    public void setGameMenu(GameMenu gamemenu) {
      this.gamemenu = gamemenu;
  }
    // Méthode pour initialiser et démarrer un nouveau niveau
    public void startNewLevel(long tempsNiveau, texte dialoguesDebut, texte dialoguesFin) {
        JeuNiveau jeuNiveau = new JeuNiveau(stage, this,tempsNiveau, dialoguesDebut, dialoguesFin);
    }
}

