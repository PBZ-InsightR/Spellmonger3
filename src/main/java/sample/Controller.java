package sample;


import edu.insightr.spellmonger.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import org.apache.log4j.Logger;


public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class);

    private SpellmongerApp game;
    private Player player1;
    private Player player2;
    @FXML
    private Text name1, life_points1, name2, life_points2;
    public Pane discard1, discard2;
    public ScrollPane list_creatures1, list_creatures2, hand1, hand2;
    public Button button_play1, button_play2, deck1, deck2;


    @FXML
    public void initialize() {
        update();
        button_play1.setDisable(false);
        button_play2.setDisable(true);
    }

    public Controller() {
        player1 = new Player("Valentin");
        player2 = new Player("Natacha");
        game = new SpellmongerApp(player1, player2);
    }

    public void draw1(){
        if (player1.size() == 0) player1.reCreateCardPool();
        player1.addToHand(player1.getCards().get(0));
        player1.getCards().remove(0);
        update();
    }
    public void draw2(){
        if (player2.size() == 0) player2.reCreateCardPool();
        player2.addToHand(player2.getCards().get(0));
        player2.getCards().remove(0);
        update();
    }
    public void attack1(int index) {
        game.PlayCard(player1, player2, player1.getHand(), index, player1.getDiscards());
        if (!player1.isDead()) {
            player1.attack(player2);
        }
        button_play1.setDisable(true);
        button_play2.setDisable(false);
        update();
    }
    public void attack2(int index) {
        game.PlayCard(player2, player1, player2.getHand(), index, player2.getDiscards());
        Card lastElementDiscard= player2.getDiscards().get(player2.getDiscards().size()-1);
        if( lastElementDiscard instanceof Bear)
        {

        }else if( lastElementDiscard instanceof Wolf){

        }else if(lastElementDiscard instanceof Eagle){

        }else if(lastElementDiscard instanceof Ritual){

        }
        if (!player2.isDead()) {
            player2.attack(player1);
        }
        button_play1.setDisable(false);
        button_play2.setDisable(true);
        update();
    }

    public void update() {
        if (player1.isDead() || player2.isDead()) {
            button_play1.setDisable(true);
            button_play2.setDisable(true);
        }
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());

        HBox content = new HBox();
        list_creatures1.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : player1.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } // fox a ajouter, quand le modèle ajoutera la classe!

            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
        }

        content = new HBox(); // contenu de la liste de créature 1
        list_creatures2.setContent(content); // on le met dans la Pane concu pour
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : player2.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } // fox a ajouter, quand le modèle ajoutera la classe!
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
        }

        // hands


        content = new HBox();
        hand1.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        int index = 0;  // pour obtenir l'index quand il va choisir la carte a joué ( utilisé dans le hand pas la)
        for (Card c : player1.getHand()) {
            Rectangle rectangleBis = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangleBis.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangleBis.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangleBis.setFill(new ImagePattern(img));
            } // fox a ajouter, quand le modèle ajoutera la classe!
            else if (c instanceof Ritual) {
                rectangleBis.setFill(Color.BLACK);
            }
            rectangleBis.setLayoutY(10);
            content.getChildren().add(rectangleBis);
            // obtenir l'index du rectangle qu'il choisit
            int index1 = index;
            rectangleBis.setOnMouseClicked(t -> {
                attack1(index1);
            });
            index++;
        }


        content = new HBox();
        hand2.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        index = 0;
        for (Card c : player2.getHand()) {
            Rectangle rectangleBis = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("resources/images/Spellmonger_Bear.png");
                rectangleBis.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("resources/images/Spellmonger_Eagle.png");
                rectangleBis.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("resources/images/Spellmonger_Wolf.png");
                rectangleBis.setFill(new ImagePattern(img));
            } // fox a ajouter, quand le modèle ajoutera la classe!
            else {
                rectangleBis.setFill(Color.BLACK);
            }

            rectangleBis.setLayoutY(10);
            content.getChildren().add(rectangleBis);
            // obtenir l'index du rectangle qu'il choisit
            int index2 = index;
            rectangleBis.setOnMouseClicked(t -> {
                attack2(index2);
            });
            index++;
        }
    }
}
