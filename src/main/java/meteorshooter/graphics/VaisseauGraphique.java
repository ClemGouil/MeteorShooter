package meteorshooter.graphics;

import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import meteorshooter.App;
import meteorshooter.game.Vaisseau;

public class VaisseauGraphique extends ObjetGraphiqueImage {

    private final static String pathVaisseau = App.class.getResource("assets/playerShip3_red.png").toString();

    public VaisseauGraphique(Vaisseau v) {
        this.objetObserve = v;
        this.objetObserve.addObserver(this);

        Image imageVaisseau = new Image(pathVaisseau, 98, 75, true, true);
        this.sprite = new ImageView(imageVaisseau);
        this.sprite.setCache(true);
        this.sprite.setCacheHint(CacheHint.SPEED);

        this.sprite.setPreserveRatio(true);
        this.sprite.setFitHeight(imageVaisseau.getWidth());
        this.sprite.setFitWidth(imageVaisseau.getHeight());

        this.update();
    }

}
