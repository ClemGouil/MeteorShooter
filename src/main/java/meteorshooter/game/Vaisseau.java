package meteorshooter.game;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import meteorshooter.game.Arme_vaisseau.Arme;
import meteorshooter.graphics.ObjetGraphique;
import meteorshooter.graphics.VaisseauGraphique;

public class Vaisseau extends ObjetPhysique {

    protected static double playerSpeed = 0.5;
    protected static double FIRE_DELAY = 50;

    private Arme arme;

    private HealthBar healthBar;
    protected static int VIE_MAX = 20;

    public Vaisseau(double x, double y, Arme arme) {
        super(x, y);
        this.observers = new ArrayList<ObjetGraphique>();
        this.healthBar = new HealthBar(VIE_MAX, x, y + 50);
        this.arme = arme;
        this.arme.setVaisseau(this);
    }

    public void handleKey(KeyCode key, float delta) {

        switch (key) {
            case RIGHT:
                this.translate(delta * playerSpeed, 0);
                break;
            case LEFT:
                this.translate(-delta * playerSpeed, 0);
                break;
            case UP:
                this.translate(0, -delta * playerSpeed);
                break;
            case DOWN:
                this.translate(0, delta * playerSpeed);
                break;
            default:
                break;
        }

    }

    
    public HealthBar getHealthBar(){
        return this.healthBar;
    }
    public Arme getArme() {
        return this.arme;
    }

    @Override
    public ObjetGraphique getObjetGraphiqueAssocie() {
        return new VaisseauGraphique(this);
    }

}
