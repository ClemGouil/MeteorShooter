package meteorshooter.graphics;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import meteorshooter.game.Score;

public class ScoreAffichage {

    private Text text;
    private Group root;
    private Score objetObserve;

    public ScoreAffichage(Score score) {
        this.objetObserve = score;
        this.objetObserve.addObserverScore(this);
    
        text = new Text("Score : " + score.get_score());
        text.setFont(text.getFont().font(20));
        text.setFill(Color.BLACK);
        text.setTranslateY(1080 - 10);
        text.setTranslateX(100 - (text.getBoundsInLocal().getWidth()/2));

        Rectangle rct = new Rectangle(200, 40);
        rct.setOpacity(1);
        rct.setFill(Color.BLUE);
        rct.setTranslateY(1080 - 40);
        rct.setTranslateX(0);
        
        //this.gamePane.setAlignment(Pos.CENTER);
        this.root = new Group();
        this.root.getChildren().addAll(rct, text);

        // this.scorePane.setOnMouseEntered(event -> {
        // });

        // this.scorePane.setOnMouseExited(event -> {
        // });
    }

    public void update(){
        this.text.setText("Score : " + this.objetObserve.get_score());
    }

    public Node getSprite(){
        return this.root;
    }
}