package meteorshooter.game;

import meteorshooter.graphics.ObjetGraphique;
import meteorshooter.graphics.ProjectileCanonGraphique;

public class ProjectileCanon extends Projectile {

    public ProjectileCanon(double x, double y, double verticalSpeed) {
        super(x, y, verticalSpeed);
    }

    @Override
    public ObjetGraphique getObjetGraphiqueAssocie() {
        return new ProjectileCanonGraphique(this);
    }

}
