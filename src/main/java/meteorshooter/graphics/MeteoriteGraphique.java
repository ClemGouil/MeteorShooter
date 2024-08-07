package meteorshooter.graphics;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import meteorshooter.App;
import meteorshooter.game.Meteorite;

public class MeteoriteGraphique extends ObjetGraphiqueImage {
    // Générer aléatoirement les sprites à partir d'un pool

    // private final static String pathMeteorite =
    // App.class.getResource("assets/sheet.png").toString();
    Random r = new Random();
    int tirage1 = r.nextInt(2);
    int tirage2 = r.nextInt(2);

    private final static String pathMeteorite = App.class.getResource("assets/Meteorites.png").toString();
    private final static Image meteoriteSpritesheet = new Image(pathMeteorite, 1024, 1024, true, true);

    public MeteoriteGraphique(Meteorite m) {
        this.objetObserve = m;
        this.objetObserve.addObserver(this);

        this.sprite = new ImageView(meteoriteSpritesheet);
        this.sprite.setCache(true);
        this.sprite.setCacheHint(CacheHint.SPEED);

        Rectangle2D viewport = new Rectangle2D(550*tirage1, 0, 400, 250);

        this.sprite.setPreserveRatio(true);
        this.sprite.setViewport(viewport);
        this.sprite.setFitWidth(400);
        this.sprite.setFitHeight(250);
        this.sprite.setScaleX(0.5);
        this.sprite.setScaleY(0.5);

        this.update();
    }

    public ImageView getSprite() {
        return this.sprite;
    }

}
