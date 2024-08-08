package meteorshooter.game;

import java.util.ArrayList;
import java.util.List;

import meteorshooter.graphics.HealthBarGraphique;

public class HealthBar {
    
    private double x;
    private double y;

    private int maxHealth;
    private int currentHealth;

    private List<HealthBarGraphique> observers;



    public HealthBar(int vie, int maxVie, double x, double y){

        this.x = x;
        this.y = y;

        this.maxHealth = maxVie;
        this.currentHealth = vie;

        this.observers = new ArrayList<HealthBarGraphique>();
    }

    public HealthBar(int maxVie, double x, double y) {
        this(maxVie, maxVie, x, y);
    }

    public HealthBar(int vie, int maxVie) {
        this(vie, maxVie, 10, 10);
    }

    public HealthBar(int maxVie) {
        this(maxVie, maxVie, 10, 10);
    }

    public int getCurrentHealth(){
        return currentHealth;
    }

    public void gainVie(int health) {
        assert (health >= 0);
        if (this.currentHealth + health <= maxHealth) {
            this.currentHealth = this.currentHealth + health;
        } else {
            this.currentHealth = this.maxHealth;
        }
        
        this.observers.forEach(observer -> {
            observer.update();
        });

    }

    public boolean perteVie(int health) {
        assert (health >= 0);
        boolean vivant = true;

        if (0 < this.currentHealth - health) {
            this.currentHealth = this.currentHealth - health;
        } else {
            vivant = false;
            this.currentHealth = 0;
        }

        this.observers.forEach(observer -> {
            observer.update();
        });

        return vivant;
    }

    public float etatHealth() {
        return (float) currentHealth / (float) maxHealth;
    }

    public void addObserver(HealthBarGraphique healthBarGraphique){
        this.observers.add(healthBarGraphique);
    }

    public List<HealthBarGraphique> getObservers(){
        return this.observers;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }
    public void setX(double newx){
        this.x=newx;
    }

    public void setY(double newy){
        this.y=newy;
    }

}
