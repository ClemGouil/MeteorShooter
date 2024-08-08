package meteorshooter.graphics;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import meteorshooter.game.HealthBar;

public class HealthBarGraphique extends ObjetGraphique {

    protected Group root;

    protected Rectangle healthBarRect;
    protected Rectangle healthBarBackgroundRect;

    protected HealthBar observedHealthBar;

    public static final double BASE_WIDTH = 120.0;
    public static final double BASE_HEIGHT = 10.0;

    public HealthBarGraphique(HealthBar healthBar) {
        
        this.observedHealthBar = healthBar;
        this.observedHealthBar.addObserver(this);

        this.root = new Group();
        this.healthBarRect = new Rectangle(BASE_WIDTH * healthBar.etatHealth(), BASE_HEIGHT, Color.LIMEGREEN);
        this.healthBarBackgroundRect = new Rectangle(BASE_WIDTH, BASE_HEIGHT, Color.RED);


        // Faire des arrondis sur les angles (pour que ce soit moins degueu)
        this.healthBarBackgroundRect.setArcHeight(10);
        this.healthBarBackgroundRect.setArcWidth(10);
        this.healthBarRect.setArcHeight(10);
        this.healthBarRect.setArcWidth(10);

        // DoubleProperty healthPercentage = new SimpleDoubleProperty(1.0);
        // DoubleBinding b1 = healthBar.widthProperty().multiply(healthPercentage); //un
        // observer, pourra etre utile un jour
        this.root.getChildren().add(this.healthBarBackgroundRect);
        this.root.getChildren().add(this.healthBarRect);

        this.sprite = this.root;

        this.update();
    }

    @Override
    public void update() {
        this.healthBarRect.setWidth(this.observedHealthBar.etatHealth() * BASE_WIDTH);

        this.root.setTranslateX(this.observedHealthBar.getX() - BASE_WIDTH / 2);
        this.root.setTranslateY(this.observedHealthBar.getY() - BASE_HEIGHT / 2);
    }
    // @Override
    // public void update(double newx, double newy) {
    //     this.healthBarRect.setWidth(this.observedHealthBar.etatHealth() * BASE_WIDTH);
    //     healthBarRect.setX(newx);
    //     healthBarRect.setX(newy);
    //     healthBarBackgroundRect.setX(newx);
    //     healthBarBackgroundRect.setX(newy);
    //     this.root.setTranslateX(this.observedHealthBar.getX() - BASE_WIDTH / 2);
    //     this.root.setTranslateY(this.observedHealthBar.getY() - BASE_HEIGHT / 2);
    // }
}
