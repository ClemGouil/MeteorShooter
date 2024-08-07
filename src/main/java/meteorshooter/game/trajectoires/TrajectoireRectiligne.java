package meteorshooter.game.trajectoires;

public class TrajectoireRectiligne implements Trajectoire {
    
    // Point de départ
    private double startX;
    private double startY;

    // Point d'arrivée
    private double endX;
    private double endY;

    protected double tempsTotal;

    /**
     * Constructeur de TrajectoireRectiligne
     * @param startX 
     * @param startY
     * @param endX
     * @param endY
     * @param tempsFinal le temps de parcours de la trajectoire en secondes
     */
    public TrajectoireRectiligne(double startX, double startY, double endX, double endY, double tempsTotal){

        this.startX = startX;
        this.startY = startY;

        this.endX = endX;
        this.endY = endY;

        this.tempsTotal = tempsTotal;
    }

    protected double getTimeRatio(double tempsEcoule){
        double timeRatio = tempsEcoule/this.tempsTotal;
        timeRatio = Math.min(1, Math.max(0, timeRatio));
        return timeRatio;
    }

    @Override
    public double getX(double tempsEcoule) {
        return this.startX + (this.endX - this.startX)*getTimeRatio(tempsEcoule);
    }

    @Override
    public double getY(double tempsEcoule) {
        return this.startY + (this.endY - this.startY)*getTimeRatio(tempsEcoule);
    }

    @Override
    public double getStartX() {
        return this.startX;
    }

    @Override
    public double getStartY() {
        return this.startY;
    }

    @Override
    public double getEndX() {
        return this.endX;
    }

    @Override
    public double getEndY() {
        return this.endY;
    }

    @Override
    public double getTempsTotal() {
        return this.tempsTotal;
    }

}
