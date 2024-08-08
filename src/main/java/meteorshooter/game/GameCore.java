package meteorshooter.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.input.KeyCode;
import meteorshooter.game.Arme_vaisseau.Arme;
import meteorshooter.game.Arme_vaisseau.Arme_canon;
import meteorshooter.game.trajectoires.TrajectoireRectiligne;
import meteorshooter.graphics.HealthBarGraphique;
import meteorshooter.graphics.ObjetGraphique;
import meteorshooter.graphics.VaisseauEnnemiGraphique;
import meteorshooter.graphics.VueGameplay;

public class GameCore {

    private List<KeyCode> pressedKeys;
    private List<Projectile> projectiles;
    private List<ProjectileEnnemi> projectilesEnnemis;
    private List<Meteorite> meteorites;
    private List<VaisseauEnnemi> vaisseauxEnnemis;

    // private final static double METEORITE_DELAY = 500;
    // private double meteoriteCooldown = 0;

    private Cooldown meteoriteCooldown = new Cooldown(2000);

    // private final static double VAISSEAUENNEMIE_DELAY = 1500;
    // private double vaisseauEnnemiCooldown = 0;

    private Cooldown vaisseauEnnemiCooldown = new Cooldown(5000);

    private static Random random = new Random();

    private Vaisseau vaisseauJoueur;

    public static final int PLAYFIELD_WIDTH = 1920;
    public static final int PLAYFIELD_HEIGHT = 1080;

    private List<VueGameplay> vueGameplayObservers;

    // private VaisseauEnnemie vaisseauEnnemie;
    // private VaisseauEnnemieGraphique vaisseauGraphiqueEnnemie;

    private Score score; // le score de la partie en cours //Achille
    private int multipl = 1; // le multiplicateur courant //Achille
    private Arme arme_base;

    public GameCore() {

        this.pressedKeys = new ArrayList<KeyCode>();

        this.projectiles = new ArrayList<Projectile>();
        this.projectilesEnnemis = new ArrayList<ProjectileEnnemi>();
        this.meteorites = new ArrayList<Meteorite>();
        this.vaisseauxEnnemis = new ArrayList<VaisseauEnnemi>();

        this.arme_base = new Arme_canon();
        this.vaisseauJoueur = new Vaisseau(1920/2, 1080-100, arme_base);

        this.vueGameplayObservers = new ArrayList<VueGameplay>();

        this.score = new Score(0); // Achille

        // this.vaisseauEnnemie = new VaisseauEnnemie(100, 100);

        // this.vaisseauGraphiqueEnnemie = new
        // VaisseauEnnemieGraphique(vaisseauEnnemie);
        // this.objetGraphiques.add(vaisseauGraphiqueEnnemie);
    }

    public void update(float delta) { // Temps d'une frame en ms
        // System.out.println("Frame Time : " + delta);
        // System.out.println("MCD : " + meteoriteCooldown);
        // System.out.println("Score : " + score.get_score());


        this.vueGameplayObservers.forEach(vueGameplay -> {
            vueGameplay.update(delta);
        });

        this.pressedKeys.forEach(key -> {
            switch (key) {
                case RIGHT:
                case LEFT:
                case UP:
                case DOWN:
                    this.vaisseauJoueur.handleKey(key, delta);
                    break;
                case W:
                    Projectile newProjectile = this.vaisseauJoueur.getArme().fire(delta);
                    if (newProjectile != null)
                        this.addObjetPhysique(newProjectile, this.projectiles);
                    break;
                default:
                    break;
            }
        });

        if (meteoriteCooldown.update(delta)) {
            double randomY1 = random.nextDouble() * PLAYFIELD_HEIGHT;
            double randomY2 = random.nextDouble() * PLAYFIELD_HEIGHT;

            TrajectoireRectiligne trajectoireMeteorite;

            if (random.nextBoolean()) {
                trajectoireMeteorite = new TrajectoireRectiligne(-100, randomY1,
                        PLAYFIELD_WIDTH + 100, randomY2, 10);
            } else {
                trajectoireMeteorite = new TrajectoireRectiligne(PLAYFIELD_WIDTH + 100, randomY1,
                        -100, randomY2, 10);
            }

            this.addObjetPhysique(new Meteorite(trajectoireMeteorite), this.meteorites);
        }

        // TODO : Implémenter une class paterneVaisseauEnnemie pour definir le
        // déplacement des vaisseaux ennemies,
        // Je ne sais pas le faire pour l'instant il faut que je vois avec Maxime.
        if (vaisseauEnnemiCooldown.update(delta)) {
            double randomX1 = random.nextDouble() * PLAYFIELD_WIDTH;
            double randomY1 = 100; // Position de départ
            double randomX2 = random.nextDouble() * PLAYFIELD_WIDTH;
            double randomY2 = PLAYFIELD_HEIGHT; // Position d'arrivée
            TrajectoireRectiligne trajectoireVaisseau = new TrajectoireRectiligne(randomX1, randomY1, randomX2, randomY2, 20);

            addNewVaisseauEnnemie(new VaisseauEnnemi(randomX1, randomY1, trajectoireVaisseau));
        }

        this.vaisseauxEnnemis.forEach(obj -> {
            obj.update(delta);
            ProjectileEnnemi newProjectileEnnemi = obj.fire(delta);
            if (newProjectileEnnemi != null) {
                this.addObjetPhysique(newProjectileEnnemi, this.projectilesEnnemis);
            }
        });

        this.projectilesEnnemis.forEach(projectileEnnemi -> {
            projectileEnnemi.update(delta);

            if (vaisseauJoueur.collidesWith(projectileEnnemi)) {

                removeObjetPhysique(projectileEnnemi, this.projectilesEnnemis);
                boolean test = vaisseauJoueur.getHealthBar().perteVie(1);
            } else if (vaisseauJoueur.frole(projectileEnnemi)) { // Achille
                score.points_esquive(); // Achille
            }
        });

        // TODO: Supprimer les projectiles hors-champ
        // Gestion des collisions pour les projectiles
        this.projectiles.forEach(projectile -> {
            projectile.update(delta);

            meteorites.forEach(meteorite -> {
                if (meteorite.collidesWith(projectile)) {
                    removeObjetPhysique(meteorite, this.meteorites);
                    removeObjetPhysique(projectile, this.projectiles);
                }

                if (vaisseauJoueur.frole(meteorite)) { // Achille
                    score.points_esquive_m(); // Achille
                }
            });

        this.vaisseauxEnnemis.forEach(vaisseauEnnemi -> {
            
            if (vaisseauEnnemi.collidesWith(projectile)) {

                // Si on tue le vaisseau ennemi
                if (!vaisseauEnnemi.getHealthBar().perteVie(vaisseauJoueur.getArme().getDamage())) { //
                    removeVaisseauEnnemie(vaisseauEnnemi);
                    score.points_elim_vaisseau_ennemi(1, multipl); // Achille
                    // multipl = maj_multipl(multipl,true); //Achille
                    // multipl = 2 * multipl;
                }
                removeObjetPhysique(projectile, this.projectiles);

            }
            // if (!(vaisseauEnnemi.collidesWith(projectile))) {
            // multipl = 1;
            // }
            // multipl = maj_multipl(multipl, false); //Achille
        });

        });

        this.meteorites.forEach(meteorite -> {
            meteorite.update(delta);
            if (meteorite.getLifetime() > meteorite.getTrajectoire().getTempsTotal())
                removeObjetPhysique(meteorite, this.meteorites);

            if (meteorite.collidesWith(vaisseauJoueur)) {
                removeObjetPhysique(meteorite, this.meteorites);
                vaisseauJoueur.getHealthBar().perteVie(5);
            }
        });

    }

    private void addNewVaisseauEnnemie(VaisseauEnnemi ve) {
        this.vaisseauxEnnemis.add(ve);
        VaisseauEnnemiGraphique veg = new VaisseauEnnemiGraphique(ve);
        this.addToAllVueGraphiques(veg);

        HealthBarGraphique healthBarGraphique = new HealthBarGraphique(ve.getHealthBar());
        this.addToAllVueGraphiques(healthBarGraphique);
    }

    private void removeVaisseauEnnemie(VaisseauEnnemi ve) {

        ve.getHealthBar().getObservers().forEach(observer -> {
            this.removeFromAllVueGraphiques(observer);
        });

        ve.getObservers().forEach(observer -> {
            this.removeFromAllVueGraphiques(observer);
        });

        this.vaisseauxEnnemis.remove(ve);
    }

    private <T extends ObjetPhysique> void addObjetPhysique(T obj, List<T> list) {
        list.add(obj);
        this.addToAllVueGraphiques(obj.getObjetGraphiqueAssocie());
    }

    private <T extends ObjetPhysique> void removeObjetPhysique(T obj, List<T> list) {
        list.remove(obj);
        obj.getObservers().forEach(observer -> {
            this.removeFromAllVueGraphiques(observer);
        });
    }

    // private void addNewMeteorite(Meteorite m) {
    // this.meteorites.add(m);
    // MeteoriteGraphique mg = new MeteoriteGraphique(m);
    // this.addToAllVueGraphiques(mg);
    // }

    // private void removeMeteorite(Meteorite m) {
    // m.getObservers().forEach(observer -> {
    // this.removeFromAllVueGraphiques(observer);
    // });
    // this.meteorites.remove(m);
    // }

    // private void addNewProjectile(Projectile p) {
    // this.projectiles.add(p);
    // ProjectileGraphique pg = new ProjectileGraphique(p);
    // this.addToAllVueGraphiques(pg);
    // }

    // private void addNewProjectileEnnemi(ProjectileEnnemi pe) {
    // this.projectilesEnnemis.add(pe);
    // ProjectileGraphiqueEnnemi pge = new ProjectileGraphiqueEnnemi(pe);
    // this.addToAllVueGraphiques(pge);
    // }

    // private void removeProjectile(Projectile p) {
    // p.getObservers().forEach(observer -> {
    // this.removeFromAllVueGraphiques(observer);
    // });
    // this.projectiles.remove(p);
    // }

    // private void removeProjectileEnnemi(ProjectileEnnemi pe) {
    // pe.getObservers().forEach(observer -> {
    // this.removeFromAllVueGraphiques(observer);
    // });
    // this.projectilesEnnemis.remove(pe);
    // }

    private void addToAllVueGraphiques(ObjetGraphique objetGraphique) {
        this.vueGameplayObservers.forEach(vueGameplay -> {
            vueGameplay.addObjetGraphique(objetGraphique);
        });
    }

    private void removeFromAllVueGraphiques(ObjetGraphique objetGraphique) {
        this.vueGameplayObservers.forEach(vueGameplay -> {
            vueGameplay.removeObjetGraphique(objetGraphique);
        });
    }

    public void addKey(KeyCode code) {
        if (!this.pressedKeys.contains(code))
            this.pressedKeys.add(code);
    }

    public void removeKey(KeyCode code) {
        if (this.pressedKeys.contains(code))
            this.pressedKeys.remove(code);
    }

    public Vaisseau getVaisseauJoueur() {
        return this.vaisseauJoueur;
    }

    public List<VueGameplay> getVueGameplayObservers() {
        return this.vueGameplayObservers;
    }

    public List<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public List<ProjectileEnnemi> getProjectileEnnemis() {
        return this.projectilesEnnemis;
    }

    public List<Meteorite> getMeteorites() {
        return this.meteorites;
    }

    public List<VaisseauEnnemi> getVaisseauxEnnemis() {
        return this.vaisseauxEnnemis;
    }

    public Score get_score() {
        return this.score;
    }

    public boolean isGameOver() {
        return this.vaisseauJoueur.getHealthBar().getCurrentHealth() <= 0;
    }
}
