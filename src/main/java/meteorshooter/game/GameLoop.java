package meteorshooter.game;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private float lastFrameTime;
    private GameCore gameCore;

    public GameLoop(GameCore core) {
        this.gameCore = core;
    }

    @Override
    public void handle(long now) {

        float msSinceLastFrame = (float) ((now - lastFrameTime) / 1e6);
        // On limite le temps d'une frame à 1000ms pour éviter les débordements au
        // démarrage
        msSinceLastFrame = Math.min(1000, msSinceLastFrame);

        lastFrameTime = now;

        this.gameCore.update(msSinceLastFrame);
    }

}
