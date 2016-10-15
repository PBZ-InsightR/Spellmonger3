package sample;

import edu.insightr.spellmonger.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import javafx.util.Duration;

public class Controller {
    private Player player1;
    private Player player2;
    private SpellmongerApp game;


    @FXML
    private Text name1, life_points1, name2, life_points2;
    public Pane hand1, discard1, hand2, discard2;
    public ScrollPane list_creatures1, list_creatures2;
    public Button button_play1, button_play2;

    @FXML
    public void initialize() {
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());
        button_play1.setDisable(false);
        button_play2.setDisable(true);
    }

    public Controller() {
        player1 = new Player("Valentin");
        player2 = new Player("Natacha");
        game = new SpellmongerApp(player1, player2);
    }

    public void attack1() {
        if (player1.size() == 0) player1.reCreateCardPool();
        game.drawCard(player1, player2, player1.getCards(), player2.getDiscards());
        if (!player1.isDead()) {
            player1.attack(player2);
        }
        button_play1.setDisable(true);
        button_play2.setDisable(false);
        update();
    }

    public void attack2() {
        if (player1.size() == 0) player1.reCreateCardPool();
        game.drawCard(player2, player1, player2.getCards(), player1.getDiscards());
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
        content.setSpacing(10);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : player1.getPlayerCreature()) {
            Label l = new Label(c.getName());
            l.setPrefSize(100, 100);
            l.setLayoutY(10);
            l.setStyle("-fx-background-color: green");
            content.getChildren().add(l);
        }

        HBox content2 = new HBox();
        list_creatures2.setContent(content2);
        content2.setSpacing(10);
        content2.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : player2.getPlayerCreature()) {
            Label l = new Label(c.getName());
            l.setPrefSize(100, 100);
            l.setStyle("-fx-background-color: green");
            content2.getChildren().add(l);
        }
    }
}
