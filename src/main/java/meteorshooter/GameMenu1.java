package meteorshooter;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import meteorshooter.game.*;
import meteorshooter.graphics.*;
import meteorshooter.graphics.menu.*;

public class GameMenu1 extends Application {

    private GameMenu gameMenu;

    private static GameLoop gameLoop;
    private static GameCore gameCore;
    private static VueGameplay vueGameplay;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        InputStream is = Files.newInputStream(Paths.get(
                "./src/main/resources/meteorshooter/assets/FondMenu.png"));
        Image img = new Image(is);
        is.close();

        ImageView imgview = new ImageView(img);
        imgview.setPreserveRatio(true);
        imgview.setFitHeight(600);

        InputStream is2 = Files.newInputStream(Paths.get(
                "./src/main/resources/meteorshooter/assets/Title.png"));
        Image img2 = new Image(is2);
        is.close();

        ImageView title = new ImageView(img2);
        title.setFitWidth(600);
        title.setFitHeight(125);
        title.setTranslateX(100);
        title.setTranslateY(70);

        gameMenu = new GameMenu(stage);

        root.getChildren().addAll(imgview, gameMenu, title);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

    private class GameMenu extends Parent {

        private Stage stage;
        private boolean musiqueON;
        private String Langue;

        public GameMenu(Stage stage) {

            Media sound = new Media(Paths.get("./src/main/resources/meteorshooter/assets/Higan_Retour.mp3").toUri().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.05);
            mediaPlayer.play();
            musiqueON = true;
            Langue = "Francais";

            this.stage = stage;
            VBox menu0 = new VBox(10);
            VBox menuOptions = new VBox(10);
            HBox menuNiveau = new HBox(10);
            VBox menuLangue =new VBox(10);

            menu0.setTranslateX(200);
            menu0.setTranslateY(250);

            menuOptions.setTranslateX(200);
            menuOptions.setTranslateY(250);

            menuNiveau.setTranslateX(200);
            menuNiveau.setTranslateY(250);

            menuLangue.setTranslateX(200);
            menuLangue.setTranslateY(250);


            final int offset = 400;

            menuOptions.setTranslateX(offset);
            menuNiveau.setTranslateX(offset);
            menuLangue.setTranslateX(offset);

            
            buttonMenu buttModeHistoire = new buttonMenu("Mode Histoire");
            buttModeHistoire.setOnMouseClicked(event -> {
                TransitionMenu(menuNiveau, menu0, offset);
            });

            buttonMenu buttModeInfini = new buttonMenu("Mode Infini");
            buttModeInfini.setOnMouseClicked(event -> {
                BorderPane mainPane = new BorderPane();

                GameCore gameCore = new GameCore();

                GameLoop gameLoop = new GameLoop(gameCore);
                gameLoop.start();

                VueGameplay vueGameplay = new VueGameplay(gameCore, mainPane);

                mainPane.setPrefSize(800, 600);

                Scene scenejeu = new Scene(mainPane);

                stage.addEventHandler(KeyEvent.KEY_PRESSED, evt -> {
                    gameCore.addKey(evt.getCode());
                });

                stage.addEventHandler(KeyEvent.KEY_RELEASED, evt -> {
                    gameCore.removeKey(evt.getCode());
                });
                stage.setScene(scenejeu);
            });

            buttonMenu buttOptions = new buttonMenu("Options");
            buttOptions.setOnMouseClicked(event -> {
                TransitionMenu(menuOptions, menu0, offset);
            });

            buttonMenu buttMusique = new buttonMenu("Musique ON/OFF");
            buttMusique.setOnMouseClicked(event -> {
                if (musiqueON){
                    mediaPlayer.pause();
                    musiqueON = false;
                } else {
                    mediaPlayer.play();
                    musiqueON = true;
                }  
            });

            buttonMenu buttLangue = new buttonMenu("Langue");
            buttLangue.setOnMouseClicked(event -> {
                TransitionMenu(menuLangue, menuOptions, offset);
            });

            buttonMenu buttBack = new buttonMenu("Retour");
            buttBack.setOnMouseClicked(event -> {
                TransitionMenu(menu0, menuOptions, offset);
            });

            buttonMenuNiveau buttNiveau1 = new buttonMenuNiveau("Niveau 1", "planete.png");
            buttNiveau1.setOnMouseClicked(event -> {
                getChildren().remove(menuNiveau);

                texte TexteDialogue0 = new texte("Bonjour commandant, bienvenue à bord de l’Endurance. Je suis AmelIA, l’intelligence artificielle du vaisseau. Je vous seconderai au cours de votre exploration à travers l’univers. \n \nAvant de partir je vous propose de vous familiariser avec les commandes du vaisseau. \nL’espace est immense et plein de dangers, prenez le temps de vous entraîner avant de partir !", "Good afternoon commander, welcome aboard the Endurance. I am AmelIA the ship’s artificial intelligence. I will help you through your journey in the outer space. \n\nBefore we go, you should probably take some time to get familiar with the controls of the ship. \nSpace is huge and hazardous for a novice, take some time before taking off.");
                Dialogue Dialogue0 = new Dialogue(TexteDialogue0.GetTexte(Langue));
                getChildren().add(Dialogue0);
                texte TexteDialogue1 = new texte("Eh bien, je pense qu’il est temps de quitter la Terre.\nAvant de sortir du système solaire, nous allons devoir passer par la ceinture d’astéroïdes.", "Well, I think it is time to fly off the Earth.\nBefore leaving the Solar System, we will have to go through the Asteroid Belt.");
                Dialogue Dialogue1 = new Dialogue(TexteDialogue1.GetTexte(Langue));
                texte TexteDialogue2 = new texte("Nous voilà au delà de la ceinture d’astéroïdes, Jupiter est à notre droite et nous allons bientôt passer \n à proximité de Saturne. Nous prenons de la vitesse et nous serons dans quelques jours au niveau de \n Proxima du Centaure :l’étoile la plus proche de nous", "We have been through the asteroid belt; you can see Jupiter at out right\nand we will go near Saturn and the other gas giants. We will pick up speed and in a few days near\nProxima Centauri: the nearest star from our Solar System.");
                Dialogue Dialogue2 = new Dialogue(TexteDialogue2.GetTexte(Langue));
                Dialogue0.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue0);
                    getChildren().add(Dialogue1);
                });
                Dialogue1.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue1);
                    getChildren().add(Dialogue2);
                });
                Dialogue2.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue2);
                });
            });

            buttonMenuNiveau buttNiveau2 = new buttonMenuNiveau("Niveau 2", "planete.png");
            buttNiveau2.setOnMouseClicked(event -> {
                getChildren().remove(menuNiveau);

                texte TexteDialogue1 = new texte(
                    "Nous somme arrivés dans le système de Proxima Centauri. Attention, nos scanners détectent de la \n vie sur la première des trois planètes autour de l’étoile. Nous allons probablement avoir de la visite,\n tenez vous prêt à tirer.","We are in the system of Proxima Centauri. Be careful, our scanner detects \n life forms on the first of the three planets around the star. We will probably face some alien, be ready to fire at the first move.");
                Dialogue Dialogue1 = new Dialogue(TexteDialogue1.GetTexte(Langue));
                getChildren().add(Dialogue1);
                texte TexteDialogue2 = new texte(
                    "Bravo, vous avez réussi à passer leurs attaques, mais au vu de l’accueil que nous avons reçu ; je vous \npropose de poursuivre notre voyage. Le reste de la galaxie nous attend !", "Well done, you have been through their fire but given how they welcomed us, \nI think it would not be a bad idea to resume our journey. After all, the galaxy awaits!");
                Dialogue Dialogue2 = new Dialogue(TexteDialogue2.GetTexte(Langue));
                Dialogue1.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue1);
                    getChildren().add(Dialogue2);
                });
                Dialogue2.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue2);
                });
            });

            buttonMenuNiveau buttNiveau3 = new buttonMenuNiveau("Niveau 3", "planete.png");
            buttNiveau3.setOnMouseClicked(event -> {
                getChildren().remove(menuNiveau);

                texte TexteDialogue1 = new texte(
                    "Ahhh, voilà un endroit que j’adore : les nébuleuses stellaires. Se sont de vraies pépinière d’étoile,\nelles sont magnifiques à voir. Vous allez etre le premier humain à pouvoir en explorer une de\n l’intérieur ! Le spectacle promet d’être inoubliable.\n \n Attention, je crois que nous avons fait peur à des esprits stellaires, je crois qu’ils vont nous attaquer.", "Well, here is another place I love: a stellar nebula. Those are true nursery for young stars, and they are so wonderful. \nYou know, you are probably one of the first human being who can see a nebula with its very own eyes so enjoy the show! \n\nLook, these kind stellar spirits seem upset, we might have disturbed them.");
                Dialogue Dialogue1 = new Dialogue(TexteDialogue1.GetTexte(Langue));
                getChildren().add(Dialogue1);
                texte TexteDialogue2 = new texte(
                    "Je suis triste de devoir quitter ce fabuleux endroits mais c’est sans aucun doute la meilleure chose à\n faire. Il vaut mieux laisser les créatures locales en paix.", "It is so sad we must leave this place, but I think it is for the better. The local live forms will indeed be happier without us flying around.");
                Dialogue Dialogue2 = new Dialogue(TexteDialogue2.GetTexte(Langue));
                Dialogue1.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue1);
                    getChildren().add(Dialogue2);
                });
                Dialogue2.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue2);
                });
            });

            buttonMenuNiveau buttNiveau4 = new buttonMenuNiveau("Niveau 4", "planete.png");
            buttNiveau4.setOnMouseClicked(event -> {
                getChildren().remove(menuNiveau);

                texte TexteDialogue1 = new texte(
                    "Ce vaisseau est un bijoux technologique commandant, nous avons déjà parcouru plus de 25,000\n année lumière et nous voilà à proximité du trou noir galactique Sagittarius A*. Ce genre d’objets\n céleste est tres complexe a observé avec nos instruments. Je n’ose pas imaginer ce que l’on va\n découvrir...", "This ship truly is the epitome of technology commander, \nwe have travel for more than 25,000 light-year and we are nearly at the centre of our galaxy. \nLook, this is Sagittarius A*, the galactic black hole. This kind of celestial bodies is very hard to detect with light-based detection tool. \nI can’t wait to see what we will discover… ");
                Dialogue Dialogue1 = new Dialogue(TexteDialogue1.GetTexte(Langue));
                getChildren().add(Dialogue1);
                texte TexteDialogue2 = new texte("Mon dieu, cela ne ressemblait à rien de ce que j’aurai pu imaginer !\nFuyons vite avant que d’autres\n de ces entités extra dimensionnelle nous rattrapent !", "Jesus Christ! I could never have dreamed of such horrific creatures.\nLet’s get out of here as soon as possible before those extra dimensional entities caught us up!"
                        );
                Dialogue Dialogue2 = new Dialogue(TexteDialogue2.GetTexte(Langue));
                Dialogue1.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue1);
                    getChildren().add(Dialogue2);
                });
                Dialogue2.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue2);
                });
            });

            buttonMenuNiveau buttNiveau5 = new buttonMenuNiveau("Niveau 5", "planete.png");
            buttNiveau5.setOnMouseClicked(event -> {
                getChildren().remove(menuNiveau);
                texte TexteDialogue1 = new texte(
                    "Regardez derrière nous, la Voie Lactée. Nous sommes enfin sorti de notre galaxie. Ici, tout est\n démesuré, la distance à la galaxie la plus proche Andromède est considérable comparé à tout le\n chemin que nous avons déjà pu parcourir... Attendez, je capte d’étranges signaux provenant de cette\n direction, allons voir !", "Look behind us, this is our galaxy: the Milky Way. \nEverything is unbounded here, the road to the nearest galaxy is far longer than everything we’ve done until now… \nWait, it seems there is a signal coming from this direction.");
                Dialogue Dialogue1 = new Dialogue(TexteDialogue1.GetTexte(Langue));
                getChildren().add(Dialogue1);
                texte TexteDialogue2 = new texte(
                        "Vous avez vu la taille de ces vaisseaux, ces robots disposent sans aucun doute d’une technologie\n bien plus avancé que nous ! Je ne sais pas ce que nous réserve la suite du voyage mais je suis\n persuadé que nous en sommes encore qu’au commencement.", "Have you seen the size of those ship! These robots have probably a much more advanced technology! \nI don’t know what awaits us for the rest of our journey, but I bet we haven’t seen anything yet.");
                Dialogue Dialogue2 = new Dialogue(TexteDialogue2.GetTexte(Langue));
                Dialogue1.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue1);
                    getChildren().add(Dialogue2);
                });
                Dialogue2.setOnMouseClicked(evt -> {
                    getChildren().remove(Dialogue2);
                });
            });

            buttonMenu buttRetour = new buttonMenu("Retour");
            buttRetour.setOnMouseClicked(event -> {
                TransitionMenu(menu0, menuNiveau, offset);
            });

            buttonMenu buttFrancais = new buttonMenu("Francais");
            buttFrancais.setOnMouseClicked(event -> {
                Langue = "Francais";
                TransitionMenu(menuOptions, menuLangue, offset);
            });

            buttonMenu buttAnglais = new buttonMenu("Anglais");
            buttAnglais.setOnMouseClicked(event -> {
                Langue = "Anglais";
                TransitionMenu(menuOptions, menuLangue, offset);
            });


            menu0.getChildren().addAll(buttModeHistoire, buttModeInfini, buttOptions);

            menuOptions.getChildren().addAll(buttMusique, buttLangue, buttBack);

            menuNiveau.getChildren().addAll(buttNiveau1, buttNiveau2, buttNiveau3, buttNiveau4, buttNiveau5,
                    buttRetour);
            menuLangue.getChildren().addAll(buttFrancais, buttAnglais);

            getChildren().addAll(menu0);
        }

        public void TransitionMenu(VBox menu1, VBox menu2, int offset) {
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

        public void TransitionMenu(VBox menu1, HBox menu2, int offset) {
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

        public void TransitionMenu(HBox menu1, VBox menu2, int offset) {
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

    }
}
