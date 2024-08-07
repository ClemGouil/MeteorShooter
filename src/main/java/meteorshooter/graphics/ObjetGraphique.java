package meteorshooter.graphics;

import javafx.scene.Node;
import meteorshooter.game.ObjetPhysique;

public abstract class ObjetGraphique {
    
    protected ObjetPhysique objetObserve;
    protected Node sprite;
    
    public void update(){
        this.sprite.setTranslateX(this.objetObserve.getX());
        this.sprite.setTranslateY(this.objetObserve.getY());
    }

    public Node getSprite() {
        return this.sprite;
    }


}
