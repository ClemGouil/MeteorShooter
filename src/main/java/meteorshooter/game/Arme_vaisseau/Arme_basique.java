package meteorshooter.game.Arme_vaisseau;

import meteorshooter.game.Vaisseau;

public class Arme_basique extends Arme {
    private static final double SPEED_BULLET = -1;
    private static final double FIRE_RT = 50;
    private static final int DMG_BASE = 1;

    public Arme_basique() {
        super(DMG_BASE, SPEED_BULLET, FIRE_RT);
    }
}
