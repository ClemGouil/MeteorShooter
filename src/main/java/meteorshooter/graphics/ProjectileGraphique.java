package meteorshooter.graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import meteorshooter.App;
import meteorshooter.game.Projectile;

public class ProjectileGraphique extends ObjetGraphiqueImage {
    // TODO: Adapter le Sprite selon le type de Projectile //
    // Utiliser un viewport sur la spritesheet
    // Rendre la spritesheet static

    private final static String pathProjectile = App.class.getResource("assets/bullets.png").toString();
    private final static Image bulletSpritesheet = new Image(pathProjectile, 512, 512, true, true);

    public ProjectileGraphique(Projectile p) {
        this.objetObserve = p;
        this.objetObserve.addObserver(this);

        this.sprite = new ImageView(bulletSpritesheet);
        this.sprite.setCache(true);
        this.sprite.setCacheHint(CacheHint.SPEED);

        Rectangle2D viewport = new Rectangle2D(96, 64, 16, 16);

        this.sprite.setPreserveRatio(true);
        this.sprite.setViewport(viewport);
        this.sprite.setFitWidth(16);
        this.sprite.setFitHeight(16);

        this.update();
    }

}