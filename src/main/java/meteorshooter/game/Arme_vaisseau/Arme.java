package meteorshooter.game.Arme_vaisseau;

import meteorshooter.game.Cooldown;
import meteorshooter.game.Projectile;
import meteorshooter.game.Vaisseau;

public abstract class Arme {
    private int damage;
    private double bullet_speed;
    private double fire_rate;
    protected Cooldown fireCooldown;
    
    protected Vaisseau vaisseau;


    public Arme(int dmg, double bllt_spd, double fr_rt) {
        this.damage = dmg;
        this.bullet_speed = bllt_spd;
        this.fire_rate = fr_rt;
        this.vaisseau = vaisseau;
        
        this.fireCooldown = new Cooldown(this.fire_rate);
    }



    public int getDamage() {
        return this.damage;
    }

    public double getFireRate() {
        return this.fire_rate;
    }

    public double getBulletSpeed() {
        return this.bullet_speed;
    }

    public void buff(int dmg, double bllt_spd, double fr_rt){
        this.damage += dmg;
        this.bullet_speed += bllt_spd;
        this.fire_rate += fr_rt;
    }

    // TODO: changer le type de retour en liste, car le vaisseau ne peut renvoyer
    // qu'un seul projectile par frame
    // Liste utile pour les différents shot types + dans le cas où le jeu freeze
    // pendant plus longtemps que 2 * FIRE_DELAY
    public Projectile fire(float delta) {
        if (this.fireCooldown.update(delta)) {
            return new Projectile(this.vaisseau.getX(), this.vaisseau.getY() - 20, this.getBulletSpeed());

        } else {
            return null;
        }
    }

    public void setVaisseau(Vaisseau v){
        this.vaisseau = v;
    }
}