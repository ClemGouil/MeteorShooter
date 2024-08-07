package meteorshooter;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class HealthBarTest extends Application {
    protected int maxHealth;
    protected int currentHealth;
    protected double x;
    protected double y;

    public HealthBarTest(int vie, int maxVie, double x, double y) {
        assert (vie <= maxVie);
        this.maxHealth = maxVie;
        this.currentHealth = vie;
        this.x = x;
        this.y = y;
    }

    public HealthBarTest(int vie, int maxVie) {
        assert (vie <= maxVie);
        this.maxHealth = maxVie;
        this.currentHealth = vie;
        this.x = 10;
        this.y = 10;
    }

    public HealthBarTest(int maxVie) {
        this.maxHealth = maxVie;
        this.currentHealth = maxVie;
        this.x = 10;
        this.y = 10;
    }

    public void gainVie(int health) {
        assert (health >= 0);
        if (this.currentHealth + health <= maxHealth) {
            this.currentHealth = this.currentHealth + health;
        } else {
            this.currentHealth = this.maxHealth;
        }
    }

    public boolean perteVie(int health) {
        assert (health >= 0);
        boolean vivant = true;
        if (0 <= this.currentHealth - health) {
            this.currentHealth = this.currentHealth - health;
        } else {
            vivant = false;
            this.currentHealth = 0;
        }
        return vivant;
    }

    public float etatHealth() {
        return (float) currentHealth / maxHealth;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // FXMLLoader loader = new FXMLLoader();
        // loader.setLocation(HealthBarMain.class.getResource("Barre2vie.fxml"));
        // BorderPane bp = loader.load();
        Group root = new Group();
        Rectangle healthBar = new Rectangle(150.0 * this.etatHealth(), 25.0, Color.GREEN);
        Rectangle healthBarBackground = new Rectangle(150.0, 25.0, Color.RED);
        // Position des rectangles
        healthBarBackground.setX(x);
        healthBarBackground.setY(y);
        healthBar.setX(x);
        healthBar.setY(y);
        // Faire des arrondi sur les angles (pour que ce soit moins degueu)
        healthBarBackground.setArcHeight(10);
        healthBarBackground.setArcWidth(10);
        healthBar.setArcHeight(10);
        healthBar.setArcWidth(10);
        // DoubleProperty healthPercentage = new SimpleDoubleProperty(1.0);
        // DoubleBinding b1 = healthBar.widthProperty().multiply(healthPercentage); //un
        // observer, pourra etre utile un jour
        root.getChildren().add(healthBarBackground);
        root.getChildren().add(healthBar);
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
