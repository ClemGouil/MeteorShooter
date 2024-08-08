package meteorshooter.graphics.menu;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import meteorshooter.game.Controleur;


public class GameOverMenu extends Parent {

    private Stage stage;
    private Controleur controleur; 
    private buttonMenu returnToMenuButton;
    private int score; 

    public GameOverMenu(Controleur c, int s) {

        this.controleur = c;
        this.score = s;

        try {
            InputStream is = Files.newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/FondMenu.png"));
            Image img = new Image(is);
            is.close();
            ImageView imgview = new ImageView(img);
            imgview.setFitWidth(1920);
            imgview.setFitHeight(1080);
            getChildren().add(imgview);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Création des composants
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setTextFill(Color.WHITE);
        gameOverLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");

        Label scoreLabel = new Label("Score : " + score );
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setStyle("-fx-font-size: 40px;");

        returnToMenuButton = new buttonMenu("Retourner au menu");
        returnToMenuButton.setOnMouseClicked(event -> {
            controleur.commuter(controleur.getSceneMenu());
        });

        // Organisation des composants dans une VBox
        VBox layout = new VBox(20);
        layout.getChildren().addAll(gameOverLabel, scoreLabel, returnToMenuButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        centerMenu(layout);

        // Ajouter le layout à l'instance Parent
        getChildren().add(layout);
        
    }

    private void centerMenu(VBox menu) {
        menu.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            double menuWidth = newBounds.getWidth();
            double menuHeight = newBounds.getHeight();
            double containerWidth = 1920;
            double containerHeight = 1080;
            double offsetX = (containerWidth - menuWidth) / 2;
            double offsetY = (containerHeight - menuHeight) / 2;
            menu.setTranslateX(offsetX);
            menu.setTranslateY(offsetY);
        });
    }

}