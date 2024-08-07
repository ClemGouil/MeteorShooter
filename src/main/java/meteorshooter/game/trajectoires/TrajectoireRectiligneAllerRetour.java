package meteorshooter.game.trajectoires;

public class TrajectoireRectiligneAllerRetour extends TrajectoireRectiligne {

    public TrajectoireRectiligneAllerRetour(double startX, double startY, double endX, double endY, double tempsTotal) {
        super(startX, startY, endX, endY, tempsTotal);
    }

    @Override
    public double getX(double tempsEcoule) {

        double timeRatio = tempsEcoule / this.tempsTotal;
        if (timeRatio % 2 < 1) {
            return super.getX(tempsEcoule % this.tempsTotal);
        } else {
            return super.getX(this.tempsTotal - tempsEcoule % this.tempsTotal);
        }

    }

    @Override
    public double getY(double tempsEcoule) {
        
        double timeRatio = tempsEcoule / this.tempsTotal;
        if (timeRatio % 2 < 1) {
            return super.getY(tempsEcoule % this.tempsTotal);
        } else {
            return super.getY(this.tempsTotal - tempsEcoule % this.tempsTotal);
        }

    }

}
