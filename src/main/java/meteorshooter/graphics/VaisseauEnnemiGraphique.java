package meteorshooter.graphics;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import meteorshooter.game.VaisseauEnnemi;

public class VaisseauEnnemiGraphique extends ObjetGraphiqueImage {

    private static Image imgVaisseauEnnemie;
    public VaisseauEnnemiGraphique(VaisseauEnnemi vaisseauEnnemie) {
        this.objetObserve = vaisseauEnnemie;
        this.objetObserve.addObserver(this);

        
        try {
            // Charger l'image
            InputStream is = Files.newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/VaisseauEnnemie2.png"));
            imgVaisseauEnnemie = new Image(is);
            this.sprite = new ImageView(imgVaisseauEnnemie);

            // Définir le viewport
            Rectangle2D viewport = new Rectangle2D(0, 0, 200, 200); // La taille du viewport doit correspondre à la taille de l'image si vous voulez l'afficher entièrement

            // Appliquer le viewport
            this.sprite.setViewport(viewport);

            // Ajuster la taille de l'ImageView
            this.sprite.setFitWidth(170); // Largeur du rectangle dans lequel l'image sera affichée
            this.sprite.setFitHeight(108); // Hauteur du rectangle dans lequel l'image sera affichée

            // Configurer le redimensionnement et l'orientation
            this.sprite.setPreserveRatio(true);
            this.sprite.setRotate(180);
            this.sprite.setScaleY(-1);
            this.sprite.setFitWidth(180);
            this.sprite.setFitHeight(120);

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.update();
    }

}
