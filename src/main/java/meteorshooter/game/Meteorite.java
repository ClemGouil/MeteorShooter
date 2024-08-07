package meteorshooter.game;

import java.util.ArrayList;
import java.util.List;

import meteorshooter.game.trajectoires.Trajectoire;
import meteorshooter.graphics.MeteoriteGraphique;
import meteorshooter.graphics.ObjetGraphique;

public class Meteorite extends ObjetPhysiqueTrajet {

    private List<MeteoriteGraphique> observers;
    private static final double HITBOX_RADIUS = 50;

    public Meteorite(Trajectoire trajectoire) {
        super(trajectoire);
        this.hitboxRadius = HITBOX_RADIUS;
        this.observers = new ArrayList<MeteoriteGraphique>();
    }

    public void addObserver(MeteoriteGraphique v){
        this.observers.add(v);
    }

    @Override
    public void translate(double dx, double dy) {
        super.translate(dx, dy);

        this.observers.forEach(vg -> {
            vg.update();
        });
    }

    @Override
    public ObjetGraphique getObjetGraphiqueAssocie() {
        return new MeteoriteGraphique(this);
    }

}