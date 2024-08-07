package meteorshooter.game.trajectoires;

public interface Trajectoire {

    /**
     * @param t Le temps écoulé depuis le début de la trajectoire
     * @return la position x du point de la trajectoire au temps donné
     */
    public double getX(double t);

    /**
     * @param t Le temps écoulé depuis le début de la trajectoire
     * @return la position y du point de la trajectoire au temps donné
     */
    public double getY(double t);

    /**
     * @return L'abscisse du point de départ de la trajectoire
     */
    public double getStartX();

    /**
     * @return L'ordonnée du point de départ de la trajectoire
     */
    public double getStartY();

    /**
     * @return L'abscisse du point d'arrivée de la trajectoire
     */
    public double getEndX();

    /**
     * @return L'ordonnée du point d'arrivée de la trajectoire
     */
    public double getEndY();

    /**
     * @return Le temps de parcours total de la trajectoire
     */
    public double getTempsTotal();
}
