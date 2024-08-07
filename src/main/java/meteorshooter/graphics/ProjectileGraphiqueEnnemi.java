package meteorshooter.graphics;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import meteorshooter.App;
import meteorshooter.game.ProjectileEnnemi;

public class ProjectileGraphiqueEnnemi extends ObjetGraphiqueImage {
    // TODO: Adapter le Sprite selon le type de Projectile //
    // Utiliser un viewport sur la spritesheet
    // Rendre la spritesheet static

    private final static String pathProjectile = App.class.getResource("assets/bullets.png").toString();
    private final static Image bulletSpritesheet = new Image(pathProjectile, 512, 512, true, true);

    public ProjectileGraphiqueEnnemi(ProjectileEnnemi pe) {
        this.objetObserve = pe;
        this.objetObserve.addObserver(this);

        this.sprite = new ImageView(bulletSpritesheet);

        Rectangle2D viewport = new Rectangle2D(0, 32, 16, 16);

        this.sprite.setPreserveRatio(true);
        this.sprite.setViewport(viewport);
        this.sprite.setFitWidth(16);
        this.sprite.setFitHeight(16);

        this.update();
    }

}