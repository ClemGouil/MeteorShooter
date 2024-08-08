package meteorshooter.game;

import java.util.ArrayList;
import java.util.List;

import meteorshooter.game.trajectoires.Trajectoire;
import meteorshooter.graphics.ObjetGraphique;
import meteorshooter.graphics.VaisseauEnnemiGraphique;

public class VaisseauEnnemi extends ObjetPhysique {

    private List<VaisseauEnnemiGraphique> observers;
    protected static double FIRE_DELAY = 500;
    protected static double playerSpeed = 0.5;

    protected static int VIE_MAX = 5;

    private Cooldown fireCooldown;

    private HealthBar healthBar;

    public VaisseauEnnemi(double x, double y) {
        super(x, y);
        this.fireCooldown = new Cooldown(FIRE_DELAY);
        this.observers = new ArrayList<VaisseauEnnemiGraphique>();

        this.healthBar = new HealthBar(VIE_MAX, x, y + 10);
    }

    public void addObserver(VaisseauEnnemiGraphique v) {
        this.observers.add(v);
    }

    @Override
    public void translate(double dx, double dy) {
        super.translate(dx, dy);

        this.healthBar.setX(this.x);
        this.healthBar.setY(this.y);

        this.observers.forEach(vg -> {
            vg.update();
        });

        this.healthBar.getObservers().forEach(observer -> {
            observer.update();
        });
    }

    // TODO: changer le type de retour en liste, car le vaisseau ne peut renvoyer
    // qu'un seul projectile par frame
    // Liste utile pour les différents shot types + dans le cas où le jeu freeze
    // pendant plus longtemps que 2 * FIRE_DELAY
    public ProjectileEnnemi fire(float delta) {
        if (this.fireCooldown.update(delta)) {
            return new ProjectileEnnemi(this.x, this.y - 20);

        } else {
            return null;
        }
    }

    public HealthBar getHealthBar(){
        return this.healthBar;
    }

    @Override
    public ObjetGraphique getObjetGraphiqueAssocie() {
        return new VaisseauEnnemiGraphique(this);
    }
}