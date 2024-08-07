package meteorshooter.game.Arme_vaisseau;

import meteorshooter.game.Vaisseau;

public class Arme_laser extends Arme {
    private static final double SPEED_BULLET = 0;
    private static final double FIRE_RT = 500;
    private static final int DMG_BASE = 10;

    public Arme_laser() {
        super(DMG_BASE, SPEED_BULLET, FIRE_RT);
    }
}
