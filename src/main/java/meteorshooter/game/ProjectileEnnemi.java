package meteorshooter.game;

import meteorshooter.graphics.ObjetGraphique;
import meteorshooter.graphics.ProjectileGraphiqueEnnemi;

public class ProjectileEnnemi extends ObjetPhysique {

    private static final double BASE_SPEED = 0.5;

    public ProjectileEnnemi(double x, double y) {
        super(x, y, 0, BASE_SPEED);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public ObjetGraphique getObjetGraphiqueAssocie() {
        return new ProjectileGraphiqueEnnemi(this);
    }   

}