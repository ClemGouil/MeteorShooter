package meteorshooter;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Etfilante {
    private final static String pathetfilante = App.class.getResource("assets/Etoilefilante.png").toString();
    private final static Image etfilantespritesheet = new Image(pathetfilante, 1000, 3000, true, true);

    private ImageView sprite;

    private double x;
    private double y;

    public Etfilante(double randomX, double randomY) {
        this.sprite =  new ImageView(etfilantespritesheet);
        this.x = randomX;
        this.y = randomY;

        Rectangle2D viewport = new Rectangle2D(0, 1000, 400, 400);

        this.sprite.setPreserveRatio(true);
        this.sprite.setViewport(viewport);
        this.sprite.setFitWidth(50);

    }

    public double getX(){
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public ImageView getsprite(){
        return this.sprite;
    }
}