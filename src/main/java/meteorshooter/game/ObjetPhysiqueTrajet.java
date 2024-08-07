package meteorshooter.game;

import meteorshooter.game.trajectoires.Trajectoire;

public abstract class ObjetPhysiqueTrajet extends ObjetPhysique {

    private Trajectoire trajectoire;

    private double lifetime;

    public ObjetPhysiqueTrajet(Trajectoire trajectoire){
        super(trajectoire.getStartX(), trajectoire.getStartY());

        this.trajectoire = trajectoire;
        this.lifetime = 0;
    }

    public ObjetPhysiqueTrajet(double hitboxRadius, Trajectoire trajectoire) {
        super(trajectoire.getStartX(), trajectoire.getStartY(), hitboxRadius);

        this.trajectoire = trajectoire;
        this.lifetime = 0;
    }

    @Override
    public void update(float delta) {
        
        this.lifetime += delta/1000;
        
        this.x = this.trajectoire.getX(this.lifetime);
        this.y = this.trajectoire.getY(this.lifetime);

        super.update(delta);
    }

    public Trajectoire getTrajectoire(){
        return this.trajectoire;
    }

    public double getLifetime() {
        return this.lifetime;
    }
    
}
