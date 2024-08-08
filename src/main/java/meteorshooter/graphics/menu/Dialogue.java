package meteorshooter.graphics.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Dialogue extends StackPane {

     private TextFlow textFlow;

    public Dialogue(String texte) {
        textFlow = new TextFlow();
        Text text = new Text(texte);
        text.setFont(text.getFont().font(18));
        text.setFill(Color.WHITE);
        textFlow.getChildren().add(text);


        Rectangle rct = new Rectangle(1920, 300);
        rct.setOpacity(0.6);
        rct.setFill(Color.web("#2E2E2E"));

        setAlignment(Pos.CENTER);
        getChildren().addAll(rct, text);

        setOnMouseEntered(event -> {
        });

        setOnMouseExited(event -> {
        });

    }
}
