package meteorshooter.game.Arme_vaisseau;

import meteorshooter.game.Projectile;
import meteorshooter.game.ProjectileCanon;

public class Arme_canon extends Arme {
    private static final double SPEED_BULLET = -2;
    private static final double FIRE_RT = 200;
    private static final int DMG_BASE = 4;

    public Arme_canon() {
        super(DMG_BASE, SPEED_BULLET, FIRE_RT);
    }

    @Override
    public Projectile fire(float delta) {
        if (this.fireCooldown.update(delta)) {
            return new ProjectileCanon(this.vaisseau.getX(), this.vaisseau.getY() - 20, this.getBulletSpeed());

        } else {
            return null;
        }
    }
}