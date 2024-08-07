package meteorshooter.game;

import java.util.ArrayList;
import java.util.List;

import meteorshooter.graphics.ObjetGraphique;

public abstract class ObjetPhysique {

    protected double x;
    protected double y;

    protected double dx;
    protected double dy;

    protected final static double BASE_HITBOX_RADIUS = 10;
    protected double hitboxRadius;

    protected List<ObjetGraphique> observers;

    public ObjetPhysique(double x, double y) {
        this.x = x;
        this.y = y;

        this.dx = 0;
        this.dy = 0;

        this.hitboxRadius = BASE_HITBOX_RADIUS;

        this.observers = new ArrayList<ObjetGraphique>();
    }

    public ObjetPhysique(double x, double y, double hitboxRadius) {
        this(x, y);
        this.hitboxRadius = hitboxRadius;
    }

    public ObjetPhysique(double x, double y, double dx, double dy) {
        this(x, y);
        this.dx = dx;
        this.dy = dy;
    }


    public ObjetPhysique(double x, double y, double dx, double dy, double hitboxRadius) {
        this(x, y, dx, dy);
        this.hitboxRadius = hitboxRadius;
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;

        this.observers.forEach(observer -> {
            observer.update();
        });
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public boolean collidesWith(ObjetPhysique other) {
        return (this.hitboxRadius + other.hitboxRadius > distance(this.x, this.y, other.x, other.y));
    }

    /**
     * Indique si le vaisseau frôle un objet physique
     * 
     * @param other l'objet physique
     */
    public boolean frole(ObjetPhysique other) {
        return (this.hitboxRadius + other.hitboxRadius + 10 > distance(this.x, this.y, other.x, other.y) && this.hitboxRadius + other.hitboxRadius < distance(this.x, this.y, other.x, other.y)); 
                                                                                                         
    }

    public void addObserver(ObjetGraphique obj) {
        this.observers.add(obj);
    }

    public List<ObjetGraphique> getObservers() {
        return this.observers;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getHitboxRadius() {
        return this.hitboxRadius;
    }

    public void setSpeed(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update(float delta) {
        this.x += delta * this.dx;
        this.y += delta * this.dy;

        this.observers.forEach(observer -> {
            observer.update();
        });
    }

    public abstract ObjetGraphique getObjetGraphiqueAssocie();

}
