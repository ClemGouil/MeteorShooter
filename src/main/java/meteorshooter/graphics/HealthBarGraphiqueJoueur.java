package meteorshooter.graphics;

import meteorshooter.game.GameCore;
import meteorshooter.game.HealthBar;

public class HealthBarGraphiqueJoueur extends HealthBarGraphique {
    
    private static final int padding = 10;
    private static final int X = GameCore.PLAYFIELD_WIDTH - (int)BASE_WIDTH - padding;
    private static final int Y = GameCore.PLAYFIELD_HEIGHT - (int)BASE_HEIGHT - padding;
    

    public HealthBarGraphiqueJoueur(HealthBar healthBar) {
        super(healthBar);
        this.root.setTranslateX(X);
        this.root.setTranslateY(Y);
    }

    @Override
    public void update() {
        this.healthBarRect.setWidth(this.observedHealthBar.etatHealth() * BASE_WIDTH);
    }
}
