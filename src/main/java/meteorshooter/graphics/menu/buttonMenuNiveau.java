package meteorshooter.graphics.menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class buttonMenuNiveau extends StackPane {

    private Text text;

    public buttonMenuNiveau(String name, String name_image) {

        text = new Text(name);
        text.setFont(text.getFont().font(15));
        text.setFill(Color.WHITE);

        try (InputStream is1 = Files
                .newInputStream(Paths.get("./src/main/resources/meteorshooter/assets/" + name_image))) {
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