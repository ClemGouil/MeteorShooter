package meteorshooter.graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import meteorshooter.App;
import meteorshooter.game.VaisseauEnnemi;

public class VaisseauEnnemiGraphique extends ObjetGraphiqueImage {

    private final static String pathVaisseauEnnemie = App.class.getResource("assets/Vaisseaux_nv1.png").toString();
    private static Image imageVaisseauEnnemie = new Image(pathVaisseauEnnemie, 500, 300, true, true);

    public VaisseauEnnemiGraphique(VaisseauEnnemi vaisseauEnnemie) {
        this.objetObserve = vaisseauEnnemie;
        this.objetObserve.addObserver(this);

        this.sprite = new ImageView(imageVaisseauEnnemie);

        Rectangle2D viewport = new Rectangle2D(23, 12, 170, 108);

        this.sprite.setPreserveRatio(true);
        this.sprite.setViewport(viewport);

        this.sprite.setPreserveRatio(true);
        this.sprite.setFitHeight(170);
        this.sprite.setFitWidth(108);
        this.sprite.setScaleY(-1);

        this.update();
    }

}
