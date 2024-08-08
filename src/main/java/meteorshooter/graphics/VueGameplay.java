package meteorshooter.graphics;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import meteorshooter.game.GameCore;
import meteorshooter.game.trajectoires.TrajectoireRectiligneAllerRetour;


public class VueGameplay {
    
    private VaisseauGraphique vaisseauGraphiqueJoueur;

    private GameCore gameCore;
    private Pane gamePane;

    private ImageView backgroundTexture;

    private double elapsedTime;

    private TrajectoireRectiligneAllerRetour trajectoireBackground; 

    public VueGameplay (GameCore gameCore, Pane gamePane, String imagepath){
        // this.objetGraphiques = new ArrayList<ObjetGraphique>();
        
        this.gameCore = gameCore;
        this.gameCore.getVueGameplayObservers().add(this);

        this.gamePane = gamePane;

        try {
        InputStream is = Files.newInputStream(Paths.get(imagepath));
        Image imgBackGround = new Image(is);
        this.backgroundTexture = new ImageView(imgBackGround);
        this.backgroundTexture.setPreserveRatio(true);
        trajectoireBackground = new TrajectoireRectiligneAllerRetour(
        GameCore.PLAYFIELD_WIDTH - imgBackGround.getWidth(), GameCore.PLAYFIELD_HEIGHT - imgBackGround.getHeight(),
        0, 0, 60);

        } catch (Exception e) {
            e.printStackTrace();
        }


        

        // backgroundTexture.setFitHeight(GameCore.PLAYFIELD_HEIGHT);
        this.gamePane.getChildren().add(backgroundTexture);

        this.elapsedTime = 0;

        this.vaisseauGraphiqueJoueur = new VaisseauGraphique(gameCore.getVaisseauJoueur());
        HealthBarGraphiqueJoueur healthBarGraphiqueJoueur = new HealthBarGraphiqueJoueur(gameCore.getVaisseauJoueur().getHealthBar());
        this.addObjetGraphique(healthBarGraphiqueJoueur);
        this.gamePane.getChildren().add(this.vaisseauGraphiqueJoueur.getSprite());

        // On crée des objets graphiques et on les ajoute à la gamePane pour tous les objets physiques existants dans le jeu
        // cela permet de créer une nouvelle vue qui soit à jour avec le jeu à n'importe quel instant

        this.gameCore.getMeteorites().forEach(meteorite -> {
            MeteoriteGraphique meteoriteGraphique = new MeteoriteGraphique(meteorite);
            this.addObjetGraphique(meteoriteGraphique);
        });

        this.gameCore.getProjectiles().forEach(projectile -> {
            ProjectileGraphique projectileGraphique = new ProjectileGraphique(projectile);
            this.addObjetGraphique(projectileGraphique);
        });

        this.gameCore.getVaisseauxEnnemis().forEach(vaisseauEnnemi -> {
            VaisseauEnnemiGraphique vaisseauEnnemieGraphique = new VaisseauEnnemiGraphique(vaisseauEnnemi);
            this.addObjetGraphique(vaisseauEnnemieGraphique);
            
            HealthBarGraphique healthBarGraphique = new HealthBarGraphique(vaisseauEnnemi.getHealthBar());
            this.addObjetGraphique(healthBarGraphique);
        });

        //ScoreAffichage scoreAffichage = new ScoreAffichage("Score: " + this.gameCore.get_score().get_score()); 
        //this.gamePane.getChildren().add(scoreAffichage);
        ScoreAffichage affichageScore = new ScoreAffichage(this.gameCore.get_score());
        this.addScore(affichageScore);
    }

    // Méthodes permettant au gameCore d'ajouter/retirer des objetsGraphiques aux VueGameplay quand il crée/détruit des objets physiques

    public void addObjetGraphique(ObjetGraphique objetGraphique){
        this.gamePane.getChildren().add(objetGraphique.getSprite());
    }

    public void addScore(ScoreAffichage affichageScore) {
        this.gamePane.getChildren().add(affichageScore.getSprite());
    }

    public void removeObjetGraphique(ObjetGraphique objetGraphique){
        System.out.println("objet supprimé : " + objetGraphique.toString());
        this.gamePane.getChildren().remove(objetGraphique.getSprite());
    }

    public void update(float delta){
        elapsedTime += delta;
        backgroundTexture.setTranslateX(trajectoireBackground.getX(elapsedTime / 1000));
        backgroundTexture.setTranslateY(trajectoireBackground.getY(elapsedTime / 1000));
    }

}
