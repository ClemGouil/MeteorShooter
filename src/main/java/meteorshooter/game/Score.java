package meteorshooter.game;

import java.util.ArrayList;
import java.util.List;
import meteorshooter.graphics.ScoreAffichage;

public class Score{

    //Système de points : 
    //1) Pour chaque vaisseau ennemi éliminé, proportionnel au niveau du vaisseau éliminé
    //    -> points ajoutés directement
    //2) Pour chaque projectile/météorite évité de près
    //    -> points ajoutés directement
    //3) Multiplicateur de points en cas d'enchainement de tirs réussis
    //    -> multiplicateur affiché en direct
    //4) Points en fonction du temps total de survie
    //    -> points ajoutés en fin de partie

    public static final int BASE_ELIM = 1000; //le score de base pour une elimination d'un ennemi
    public static final int BASE_ESQ = 400; //le score de base pour une esquive de près

    private int score; //le score courant
    protected List<ScoreAffichage> observers;

    public Score(int score){
        this.score = score;
        this.observers = new ArrayList<ScoreAffichage>();
    }

    public void addObserverScore(ScoreAffichage obj) {
        this.observers.add(obj);
    }

    public List<ScoreAffichage> getObserversScore() {
        return this.observers;
    }

    public void update_observers() {
        this.observers.forEach(observer -> {
            observer.update();
        });
    }
    
    /**Donne les points correspondant à l'élimination d'un vaisseau ennemi
    * @param puissance la puissance du vaisseau éliminé
    * @param multipl le multiplicateur courant
     */
    public void points_elim_vaisseau_ennemi(int puissance, int multipl) {
        int tampon = this.score;
        this.score = tampon + multipl*puissance*BASE_ELIM;
        update_observers();
    }

    /**Donne les points correspondant à l'esquive de proche d'un objet ennemi (missile ou météorite)
     */
    public void points_esquive() {
        int tampon = this.score;
        this.score = tampon + BASE_ESQ;
        update_observers();
    }

    public void points_esquive_m() {
        int tampon = this.score;
        this.score = tampon + 1;
        update_observers();
    }

    /** Initialiser le score */
    public void init_score() {
        this.score = 0;
        update_observers();
    }

    /** Mettre à jour le multiplicateur 
    *@param multipl le multiplicateur courant
    *@param touche booleen disant si le tir a touché un vaisseau
    */
    public double maj_multipl(int multipl, boolean touche) {
        if (touche) { 
            int tampon = multipl;
            return tampon * 2;
        }
        else {
            return 1;
        }
    }

    /** Donner le nombre de points correspondant au temps total de survie
    * @param tps le temps total de survie en secondes
     */
     public int pts_tps_survie(int tps) {
         return 30 * tps;
     }

     /** Ajouter un nombre de point donné en argument au score
     * @param X le nombre de points que l'on souhaite ajouter
      */
    public void ajouter(int X) {
        int tampon = this.score;
        this.score = tampon + X;
        update_observers();
    }

    /** Donner le score courant */
    public String get_score() {
        return String.valueOf(this.score);
    }

    public int get_score2() {
        return this.score;
    }

    public void setScore(int X) {
        this.score = X;
        update_observers();
    }
}

//Quand il y aura une fin -> gérer les points liés au temps de survie

