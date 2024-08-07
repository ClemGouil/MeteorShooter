package meteorshooter.graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import meteorshooter.MeteorShooter;
import meteorshooter.game.ProjectileCanon;

public class ProjectileCanonGraphique extends ObjetGraphiqueImage {
    
    private final static String pathProjectile = MeteorShooter.class.getResource("assets/bullets.png").toString();
    private final static Image bulletSpritesheet = new Image(pathProjectile, 512, 512, true, true);

    public ProjectileCanonGraphique(ProjectileCanon p) {
        this.objetObserve = p;
        this.objetObserve.addObserver(this);

        this.sprite = new ImageView(bulletSpritesheet);
        this.sprite.setCache(true);
        this.sprite.setCacheHint(CacheHint.SPEED);

        Rectangle2D viewport = new Rectangle2D(420, 4, 24, 24);

        this.sprite.setPreserveRatio(true);
        this.sprite.setViewport(viewport);
        this.sprite.setFitWidth(24);
        this.sprite.setFitHeight(24);

        this.update();
    }

}
