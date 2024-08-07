package meteorshooter.graphics;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class ObjetGraphiqueImage extends ObjetGraphique {

    protected ImageView sprite;

    @Override
    public void update() {
        // On retranche la moitié de la hauteur et de la largeur d'une image à sa position pour la centrer sur sa position
        this.sprite.setTranslateX(objetObserve.getX() - sprite.getFitWidth() / 2);
        this.sprite.setTranslateY(objetObserve.getY() - sprite.getFitHeight() / 2);
    }

    @Override
    public Node getSprite() {
        return this.sprite;
    }

}
