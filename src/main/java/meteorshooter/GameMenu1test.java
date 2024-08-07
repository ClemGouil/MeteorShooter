package meteorshooter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu1test extends Application {

    private GameMenu gameMenu;

    private static Random random = new Random();

    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;

    private final static String pathtitle = App.class.getResource("assets/Title.png").toString();
    private final static Image title = new Image(pathtitle);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stageMenu) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        InputStream is = Files.newInputStream(Paths.get(
                "./src/main/resources/meteorshooter/assets/FondMenu.png"));
        Image img = new Image(is);
        is.close();

        ImageView imgview = new ImageView(img);
        imgview.setPreserveRatio(true);
        imgview.setFitHeight(600);

        gameMenu = new GameMenu();

        root.getChildren().addAll(imgview, gameMenu);
        root.getChildren().addAll(imgview, gameMenu);

        Scene scene = new Scene(root);

        stageMenu.setScene(scene);
        stageMenu.show();

    }

    private class GameMenu extends Parent {
        private List<Etfilante> etfilantes;

        private final static double ETFILANTE_DELAY = 500;
        private double etfilanteCooldown = 0;

        public GameMenu() {
            // https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html
            VBox menu0 = new VBox(10);
            VBox menuOptions = new VBox(10);
            HBox menuNiveau = new HBox(10);

            menu0.setTranslateX(200);
            menu0.setTranslateY(250);

            menuOptions.setTranslateX(200);
            menuOptions.setTranslateY(250);

            menuNiveau.setTranslateX(200);
            menuNiveau.setTranslateY(250);

            final int offset = 400;

            menuOptions.setTranslateX(offset);
            menuNiveau.setTranslateX(offset);

            buttonMenu buttModeHistoire = new buttonMenu("Mode Histoire");
            buttModeHistoire.setOnMouseClicked(event -> {
                getChildren().add(menuNiveau);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menuNiveau);
                tt1.setToX(menu0.getTranslateX());
                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            buttonMenu buttModeInfini = new buttonMenu("Mode Infini");
            buttModeInfini.setOnMouseClicked(event -> {
            });

            buttonMenu buttOptions = new buttonMenu("Options");
            buttOptions.setOnMouseClicked(event -> {
                getChildren().add(menuOptions);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menuOptions);
                tt1.setToX(menu0.getTranslateX());
                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menu0);
                });
            });

            buttonMenu buttMusique = new buttonMenu("Musique");
            buttMusique.setOnMouseClicked(event -> {
            });

            buttonMenu buttExplosion = new buttonMenu("Explosion");
            buttExplosion.setOnMouseClicked(event -> {
            });

            buttonMenu buttBack = new buttonMenu("Retour");
            buttBack.setOnMouseClicked(event -> {
                getChildren().add(menu0);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menuOptions);
                tt.setToX(menuOptions.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), menu0);
                tt1.setToX(menuOptions.getTranslateX());
                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(menuOptions);
                });

            });

            buttonMenuNiveau buttNiveau1 = new buttonMenuNiveau("Niveau 1");
            buttNiveau1.setOnMouseClicked(event -> {
            });

            buttonMenuNiveau buttNiveau2 = new buttonMenuNiveau("Niveau 2");
            buttNiveau2.setOnMouseClicked(event -> {
            });

            buttonMenuNiveau buttNiveau3 = new buttonMenuNiveau("Niveau 3");
            buttNiveau3.setOnMouseClicked(event -> {
            });

            buttonMenuNiveau buttNiveau4 = new buttonMenuNiveau("Niveau 4");
            buttNiveau4.setOnMouseClicked(event -> {
            });

            buttonMenuNiveau buttNiveau5 = new buttonMenuNiveau("Niveau 5");
            buttNiveau5.setOnMouseClicked(event -> {
            });

            // ajout du titre //
            ImageView Title = new ImageView(title);
            Title.setPreserveRatio(true);
            Title.setFitWidth(500);
            Title.setX(10.0);
            Title.setY(10.0);

            menu0.getChildren().addAll(buttModeHistoire, buttModeInfini, buttOptions, Title);

            menuOptions.getChildren().addAll(buttMusique, buttExplosion, buttBack);

            menuNiveau.getChildren().addAll(buttNiveau1, buttNiveau2, buttNiveau3, buttNiveau4, buttNiveau5);

            getChildren().addAll(menu0);

            while (true) {
                if (etfilanteCooldown != 0) {
                    etfilanteCooldown = etfilanteCooldown - 1;
                } else {
                    double randomX = random.nextDouble() * SCREEN_WIDTH;
                    double randomY = random.nextDouble() * SCREEN_HEIGHT;
                    Etfilante etfilante = new Etfilante(randomX, randomY);
                    this.etfilantes.add(etfilante);

                    // Tanslation
                    TranslateTransition translateTransition = new TranslateTransition();
                    translateTransition.setDuration(Duration.millis(1000));
                    translateTransition.setNode(etfilante.getsprite());
                    translateTransition.setByY(300);
                    translateTransition.setByX(300);
                    // Fade transition
                    FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), etfilante.getsprite());
                    fadeInTransition.setFromValue(0.0);
                    fadeInTransition.setToValue(1.0);
                    fadeInTransition.setAutoReverse(true);
                    // Animation
                    fadeInTransition.play();
                    translateTransition.play();
                    menu0.getChildren().add(etfilante.getsprite());

                    etfilanteCooldown = ETFILANTE_DELAY;
                }

            }

        }

    }

    private static class buttonMenu extends StackPane {
        private Text text;

        public buttonMenu(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle rct = new Rectangle(400, 30);
            rct.setOpacity(0.6);
            rct.setFill(Color.BLACK);
            rct.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER);
            getChildren().addAll(rct, text);

            setOnMouseEntered(event -> {
                rct.setTranslateX(10);
                text.setTranslateX(10);
                rct.setFill(Color.WHITE);
                text.setFill(Color.BLACK);

            });

            setOnMouseExited(event -> {
                rct.setTranslateX(0);
                text.setTranslateX(0);
                rct.setFill(Color.BLACK);
                text.setFill(Color.WHITE);

            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());
            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }

    }

    private static class buttonMenuNiveau extends StackPane {

        private Text text;

        public buttonMenuNiveau(String name) {

            text = new Text(name);
            text.setFont(text.getFont().font(15));
            text.setFill(Color.WHITE);

            try (InputStream is1 = Files
                    .newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/planete.png"))) {
                Image img = new Image(is1);
                is1.close();

                ImageView imgview = new ImageView(img);
                imgview.setFitWidth(65);
                imgview.setFitHeight(65);

                setAlignment(Pos.CENTER);
                getChildren().addAll(imgview, text);

                setOnMouseEntered(event -> {
                });

                setOnMouseExited(event -> {
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());
            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }

    }

}
