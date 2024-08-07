package meteorshooter.graphics.menu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class buttonMenu extends StackPane {
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

    public void setText(String newText) {
        text.setText(newText);
    }
}