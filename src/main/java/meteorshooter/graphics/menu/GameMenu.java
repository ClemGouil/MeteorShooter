package meteorshooter.graphics.menu;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import meteorshooter.game.Controleur;


public class GameMenu extends Parent {

    private Stage stage;
    private boolean musiqueON;
    private String langue;
    private Controleur controleur;
    private buttonMenu buttModeHistoire;
    private buttonMenu buttModeInfini;
    private buttonMenu buttOptions;
    private buttonMenu buttMusique;
    private buttonMenu buttLangue;
    private buttonMenu buttBack;
    private buttonMenuNiveau buttNiveau1;
    private buttonMenuNiveau buttNiveau2;
    private buttonMenuNiveau buttNiveau3;
    private buttonMenuNiveau buttNiveau4;
    private buttonMenuNiveau buttNiveau5;
    private buttonMenu buttRetour;
    private buttonMenu buttFrancais;
    private buttonMenu buttAnglais;
    private boolean[] niveauxDeverrouilles;
      

    public GameMenu(Controleur c) {

        this.controleur = c;
        niveauxDeverrouilles = new boolean[5];
        niveauxDeverrouilles[0] = true;

        Media sound = new Media(Paths.get("./src/main/resources/meteorshooter/assets/Higan_Retour.mp3").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        musiqueON = true;
        langue = "Francais";


        try {
            InputStream is = Files.newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/FondMenu.png"));
            Image img = new Image(is);
            is.close();
            ImageView imgview = new ImageView(img);
            imgview.setFitWidth(1920);
            imgview.setFitHeight(1080);

            InputStream is2 = Files.newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/Title.png"));
            Image img2 = new Image(is2);
            is2.close();
            ImageView title = new ImageView(img2);
            title.setFitWidth(600);
            title.setFitHeight(125);
            title.setTranslateX(1920/2 - 600/2);
            title.setTranslateY(70);

            // Ajouter les images au Parent (GameMenu)
            getChildren().addAll(imgview, title);

        } catch (Exception e) {
            e.printStackTrace();
        }


        VBox menu0 = new VBox(10);
        VBox menuOptions = new VBox(10);
        VBox menuNiveau = new VBox(40);
        HBox partMenuNiveau = new HBox(40);
        VBox menuLangue =new VBox(10);

        // Create buttons
        buttModeHistoire = new buttonMenu("Mode Histoire");
        buttModeInfini = new buttonMenu("Mode Infini");
        buttOptions = new buttonMenu("Options");
        buttMusique = new buttonMenu("Musique ON/OFF");
        buttLangue = new buttonMenu("Langue");
        buttBack = new buttonMenu("Retour");
        buttNiveau1 = new buttonMenuNiveau("Niveau 1", "PlaneteNiv1.png");
        buttNiveau2 = new buttonMenuNiveau("Niveau 2", "PlaneteNiv2.png");
        buttNiveau3 = new buttonMenuNiveau("Niveau 3", "PlaneteNiv3.png");
        buttNiveau4 = new buttonMenuNiveau("Niveau 4", "PlaneteNiv4.png");
        buttNiveau5 = new buttonMenuNiveau("Niveau 5", "PlaneteNiv5.png");
        buttRetour = new buttonMenu("Retour");
        buttFrancais = new buttonMenu("Francais");
        buttAnglais = new buttonMenu("Anglais");

        menu0.getChildren().addAll(buttModeHistoire, buttModeInfini, buttOptions);

        menuOptions.getChildren().addAll(buttMusique, buttLangue, buttBack);

        partMenuNiveau.getChildren().addAll(buttNiveau1, buttNiveau2, buttNiveau3, buttNiveau4, buttNiveau5); 
        menuNiveau.getChildren().addAll(partMenuNiveau,buttRetour);
        menuLangue.getChildren().addAll(buttFrancais, buttAnglais);
    
        centerMenu(menu0);
        centerMenu(menuOptions);
        centerMenu(menuNiveau);
        centerMenu(menuLangue);


        final int offset = 400;

        menuOptions.setTranslateX(offset);
        menuNiveau.setTranslateX(offset);
        menuLangue.setTranslateX(offset);

        
        buttModeHistoire.setOnMouseClicked(event -> {
            TransitionMenu(menuNiveau, menu0, offset);
        });

        buttModeInfini.setOnMouseClicked(event -> {
            controleur.startLevelInfini();
        });

        buttOptions.setOnMouseClicked(event -> {
            TransitionMenu(menuOptions, menu0, offset);
        });

        buttMusique.setOnMouseClicked(event -> {
            if (musiqueON){
                mediaPlayer.pause();
                musiqueON = false;
            } else {
                mediaPlayer.play();
                musiqueON = true;
            }  
        });

        buttLangue.setOnMouseClicked(event -> {
            TransitionMenu(menuLangue, menuOptions, offset);
        });

        buttBack.setOnMouseClicked(event -> {
            TransitionMenu(menu0, menuOptions, offset);
        });


        buttNiveau1.setOnMouseClicked(event -> {

            texte dialoguesDebut = new texte("Bonjour commandant, bienvenue à bord de l’Endurance. Je suis AmelIA, l’intelligence artificielle du vaisseau. Je vous seconderai au cours de votre exploration à travers l’univers. \n \nAvant de partir je vous propose de vous familiariser avec les commandes du vaisseau. \nL’espace est immense et plein de dangers, prenez le temps de vous entraîner avant de partir !", "Good afternoon commander, welcome aboard the Endurance. I am AmelIA the ship’s artificial intelligence. I will help you through your journey in the outer space. \n\nBefore we go, you should probably take some time to get familiar with the controls of the ship. \nSpace is huge and hazardous for a novice, take some time before taking off.");
            texte dialoguesFin = new texte("Nous voilà au delà de la ceinture d’astéroïdes, Jupiter est à notre droite et nous allons bientôt passer \n à proximité de Saturne. Nous prenons de la vitesse et nous serons dans quelques jours au niveau de \n Proxima du Centaure :l’étoile la plus proche de nous", "We have been through the asteroid belt; you can see Jupiter at our right\nand we will go near Saturn and the other gas giants. We will pick up speed and in a few days near\nProxima Centauri: the nearest star from our Solar System.");
            
            controleur.startNewLevel(30000,dialoguesDebut, dialoguesFin, langue, ".\\src\\main\\resources\\meteorshooter\\assets\\GalaxieNiveau1.jpg");
            niveauxDeverrouilles[1] = true;
            updateLevelButtons();
            });

        buttNiveau2.setOnMouseClicked(event -> {
            texte dialoguesDebut = new texte(
                "Nous sommes arrivés dans le système de Proxima Centauri. Attention, nos scanners détectent de la \n vie sur la première des trois planètes autour de l’étoile. Nous allons probablement avoir de la visite,\n tenez-vous prêt à tirer.",
                "We are in the system of Proxima Centauri. Be careful, our scanner detects \n life forms on the first of the three planets around the star. We will probably face some alien, be ready to fire at the first move."
            );
            texte dialoguesFin = new texte(
                "Bravo, vous avez réussi à passer leurs attaques, mais au vu de l’accueil que nous avons reçu ; je vous \npropose de poursuivre notre voyage. Le reste de la galaxie nous attend !",
                "Well done, you have been through their fire but given how they welcomed us, \nI think it would not be a bad idea to resume our journey. After all, the galaxy awaits!"
            );
            controleur.startNewLevel(6000, dialoguesDebut, dialoguesFin, langue, ".\\src\\main\\resources\\meteorshooter\\assets\\GalaxieNiveau2.jpg");
            niveauxDeverrouilles[2] = true;
            updateLevelButtons();
        });

        buttNiveau3.setOnMouseClicked(event -> {
            texte dialoguesDebut = new texte(
                "Ahhh, voilà un endroit que j’adore : les nébuleuses stellaires. Se sont de vraies pépinières d’étoiles,\nelles sont magnifiques à voir. Vous allez être le premier humain à pouvoir en explorer une de\n l’intérieur ! Le spectacle promet d’être inoubliable.\n \n Attention, je crois que nous avons fait peur à des esprits stellaires, je crois qu’ils vont nous attaquer.",
                "Well, here is another place I love: a stellar nebula. Those are true nurseries for young stars, and they are so wonderful. \nYou know, you are probably one of the first human being who can see a nebula with its very own eyes so enjoy the show! \n\nLook, these kind stellar spirits seem upset, we might have disturbed them."
            );
            texte dialoguesFin = new texte(
                "Je suis triste de devoir quitter ce fabuleux endroit mais c’est sans aucun doute la meilleure chose à\n faire. Il vaut mieux laisser les créatures locales en paix.",
                "It is so sad we must leave this place, but I think it is for the better. The local life forms will indeed be happier without us flying around."
            );
            controleur.startNewLevel(7000, dialoguesDebut, dialoguesFin, langue, ".\\src\\main\\resources\\meteorshooter\\assets\\GalaxieNiveau3.jpg");
            niveauxDeverrouilles[3] = true;
            updateLevelButtons();
        });

        buttNiveau4.setOnMouseClicked(event -> {
            texte dialoguesDebut = new texte(
                "Ce vaisseau est un bijou technologique commandant, nous avons déjà parcouru plus de 25,000\n année-lumière et nous voilà à proximité du trou noir galactique Sagittarius A*. Ce genre d’objets\n céleste est très complexe à observer avec nos instruments. Je n’ose pas imaginer ce que l’on va\n découvrir...",
                "This ship truly is the epitome of technology commander, \nwe have traveled for more than 25,000 light-years and we are nearly at the center of our galaxy. \nLook, this is Sagittarius A*, the galactic black hole. This kind of celestial bodies is very hard to detect with light-based detection tools. \nI can’t wait to see what we will discover…"
            );
            texte dialoguesFin = new texte(
                "Mon dieu, cela ne ressemblait à rien de ce que j’aurais pu imaginer !\nFuyons vite avant que d’autres\n de ces entités extra dimensionnelles nous rattrapent !",
                "Jesus Christ! I could never have dreamed of such horrific creatures.\nLet’s get out of here as soon as possible before those extra dimensional entities catch us up!"
            );
            controleur.startNewLevel(8000, dialoguesDebut, dialoguesFin, langue, ".\\src\\main\\resources\\meteorshooter\\assets\\GalaxieNiveau4.jpg");
            niveauxDeverrouilles[4] = true;
            updateLevelButtons();
        });

        buttNiveau5.setOnMouseClicked(event -> {
            texte dialoguesDebut = new texte(
                "Regardez derrière nous, la Voie Lactée. Nous sommes enfin sortis de notre galaxie. Ici, tout est\n démesuré, la distance à la galaxie la plus proche Andromède est considérable comparé à tout le\n chemin que nous avons déjà pu parcourir... Attendez, je capte d’étranges signaux provenant de cette\n direction, allons voir !",
                "Look behind us, this is our galaxy: the Milky Way. \nEverything is unbounded here, the road to the nearest galaxy is far longer than everything we’ve done until now… \nWait, it seems there is a signal coming from this direction."
            );
            texte dialoguesFin = new texte(
                "Vous avez vu la taille de ces vaisseaux, ces robots disposent sans aucun doute d’une technologie\n bien plus avancée que nous ! Je ne sais pas ce que nous réserve la suite du voyage mais je suis\n persuadé que nous n’en sommes encore qu’au commencement.",
                "Have you seen the size of those ships! These robots have probably a much more advanced technology! \nI don’t know what awaits us for the rest of our journey, but I bet we haven’t seen anything yet."
            );
            controleur.startNewLevel(9000, dialoguesDebut, dialoguesFin, langue, ".\\src\\main\\resources\\meteorshooter\\assets\\GalaxieNiveau5.jpg");
        });


        buttRetour.setOnMouseClicked(event -> {
            TransitionMenu(menu0, menuNiveau, offset);
        });

        buttFrancais.setOnMouseClicked(event -> {
            langue = "Francais";
            updateButtonLabels();
            TransitionMenu(menuOptions, menuLangue, offset);
        });

        buttAnglais.setOnMouseClicked(event -> {
            langue = "Anglais";
            updateButtonLabels();
            TransitionMenu(menuOptions, menuLangue, offset);
        });

        getChildren().addAll(menu0);
        updateLevelButtons();
    }


    public void TransitionMenu(VBox menu1, VBox menu2,int offset){
        getChildren().add(menu1);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt2.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt3.setToX(menu2.getTranslateX());
            tt2.play();
            tt3.play();

            tt2.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
    }

    public void TransitionMenu(VBox menu1, HBox menu2,int offset){
        getChildren().add(menu1);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt2.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt3.setToX(menu2.getTranslateX());
            tt2.play();
            tt3.play();

            tt2.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
    }

    public void TransitionMenu(HBox menu1, VBox menu2,int offset){
        getChildren().add(menu1);
            TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), menu2);
            tt2.setToX(menu2.getTranslateX() + offset);

            TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt3.setToX(menu2.getTranslateX());
            tt2.play();
            tt3.play();

            tt2.setOnFinished(evt -> {
                getChildren().remove(menu2);
            });
    }

    private void centerMenu(VBox menu) {
        menu.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            double menuWidth = newBounds.getWidth();
            double menuHeight = newBounds.getHeight();
            double containerWidth = 1920;
            double containerHeight = 1080;
            double offsetX = (containerWidth - menuWidth) / 2;
            double offsetY = (containerHeight - menuHeight) / 2;
            menu.setTranslateX(offsetX);
            menu.setTranslateY(offsetY);
        });
    }

    private void centerMenu(HBox menu) {
        menu.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            double menuWidth = newBounds.getWidth();
            double menuHeight = newBounds.getHeight();
            double containerWidth = 1920;
            double containerHeight = 1080;
            double offsetX = (containerWidth - menuWidth) / 2;
            double offsetY = (containerHeight - menuHeight) / 2;
            menu.setTranslateX(offsetX);
            menu.setTranslateY(offsetY);
        });
    }

    private void updateButtonLabels() {
        if (langue.equals("Francais")) {
            buttModeHistoire.setText("Mode Histoire");
            buttModeInfini.setText("Mode Infini");
            buttOptions.setText("Options");
            buttMusique.setText("Musique ON/OFF");
            buttLangue.setText("Langue");
            buttBack.setText("Retour");
            buttNiveau1.setText("Niveau 1");
            buttNiveau2.setText("Niveau 2");
            buttNiveau3.setText("Niveau 3");
            buttNiveau4.setText("Niveau 4");
            buttNiveau5.setText("Niveau 5");
            buttRetour.setText("Retour");
            buttFrancais.setText("Francais");
            buttAnglais.setText("Anglais");
        } else if (langue.equals("Anglais")) {
            buttModeHistoire.setText("Story Mode");
            buttModeInfini.setText("Infinite Mode");
            buttOptions.setText("Options");
            buttMusique.setText("Music ON/OFF");
            buttLangue.setText("Language");
            buttBack.setText("Back");
            buttNiveau1.setText("Level 1");
            buttNiveau2.setText("Level 2");
            buttNiveau3.setText("Level 3");
            buttNiveau4.setText("Level 4");
            buttNiveau5.setText("Level 5");
            buttRetour.setText("Back");
            buttFrancais.setText("French");
            buttAnglais.setText("English");
        }
    }

    private void updateLevelButtons() {
        buttNiveau1.setDisable(!niveauxDeverrouilles[0]);
        buttNiveau2.setDisable(!niveauxDeverrouilles[1]);
        buttNiveau3.setDisable(!niveauxDeverrouilles[2]);
        buttNiveau4.setDisable(!niveauxDeverrouilles[3]);
        buttNiveau5.setDisable(!niveauxDeverrouilles[4]);
    }
    

}