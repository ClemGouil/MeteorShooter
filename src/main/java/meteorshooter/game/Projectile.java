package meteorshooter.game;

import meteorshooter.graphics.ObjetGraphique;
import meteorshooter.graphics.ProjectileGraphique;

public class Projectile extends ObjetPhysique {

    private static final double BASE_SPEED = -1;

    public Projectile(double x, double y) {
        this(x, y, BASE_SPEED);
    }

    public Projectile(double x, double y, double verticalSpeed){
        super(x, y, 0, verticalSpeed);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public ObjetGraphique getObjetGraphiqueAssocie() {
        return new ProjectileGraphique(this);
    }

}