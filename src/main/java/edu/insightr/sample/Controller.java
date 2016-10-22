package edu.insightr.sample;


import edu.insightr.spellmonger.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import org.apache.log4j.Logger;


public class Controller {
    private static final Logger logger = Logger.getLogger(Controller.class);
    public SpellmongerApp game;
    private Player turnPlayer;
    private Player player1;
    private Player player2;
    @FXML
    public Text name1, life_points1, name2, life_points2;
    public Pane discard1, discard2;
    public ScrollPane list_creatures1, list_creatures2, hand1, hand2;
    public Button deck1, deck2, pass1, pass2;
    public AnchorPane player1_dad, player2_dad;
    public SplitPane split;


    @FXML
    public void initialize() {
        game = new SpellmongerApp(new Player("Valentin"), new Player("Natacha"));
        player1 = game.getPlayer(0);
        player2 = game.getPlayer(1);
        turnPlayer = player1;
        player1.increaseEnergy();
        update();
        deck1.setDisable(false);
        deck2.setDisable(true);
        pass2.setDisable(true);
        pass1.setDisable(false);
    }

    public void draw_player_1() {
        if (!drawCard(player1))
            deck1.setDisable(true);
        if (!player1.canPlay())
            pass_player_1();
    }

    public void draw_player_2() {
        if (!drawCard(player2))
            deck2.setDisable(true);
        if (!player2.canPlay())
            pass_player_2();
    }

    public void pass_player_1() {
        turnFinished(player1, deck2);
        pass1.setDisable(true);
        pass2.setDisable(false);
    }

    public void pass_player_2() {
        turnFinished(player2, deck1);
        pass2.setDisable(true);
        pass1.setDisable(false);
    }

    private boolean drawCard(Player player) {
        boolean result = true;
        if (player.getHand().size() < 5) { // Numa
            if (player.size() == 0) player.reCreateCardPool(); // Numa
            player.addToHand(player.getCards().get(0)); // Numa
            player.getCards().remove(0); // Numa
            deck1.setDisable(true);
            deck2.setDisable(true);
            update();
        } else  //Numa
        {
            AlertBox.displayError("Error", "You cannot have ore than 5 cards in your hand");
            result = false;
        }
        return result;
    }

    private void turnFinished(Player current, Button deckOpp) {
        turnPlayer = game.nextPLayer(current);
        deckOpp.setDisable(false);
        game.nextPLayer(current).increaseEnergy();
        update();
    }

    public void attack(int index, Player current, Player oppenent) {
        if (!current.isDead()) {
            game.playCard(current, oppenent, current.getHand(), index, current.getDiscards());
            current.attack(oppenent);
            update();
        }
    }

    public void update() {
        if (player1.isDead() || player2.isDead()) {
            deck1.setDisable(true);
            deck2.setDisable(true);
        }
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());
        // creatures sur la piste
        listCreatureContents(player1, list_creatures1, player1_dad);
        listCreatureContents(player2, list_creatures2, player2_dad);
        // hands
        hands(player1, player2, hand1, player1_dad);
        hands(player2, player1, hand2, player2_dad);
        // discard
        discards(player1, discard1);
        discards(player2, discard2);
    }

    public void listCreatureContents(Player p, ScrollPane scroll, AnchorPane daddy) {
        HBox content = new HBox();
        scroll.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : p.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_" + c.getName() + ".png");
            rectangle.setFill(new ImagePattern(img));
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            Rectangle newRectangle = new Rectangle(150, 180);
            rectangle.setOnMouseEntered(t -> {
                newRectangle.setLayoutX(scroll.getLayoutX() + rectangle.getLayoutX());
                if (daddy.equals(split.getItems().get(0))) {
                    newRectangle.setLayoutY(scroll.getLayoutY() - newRectangle.getHeight() + 10);
                } else {
                    newRectangle.setLayoutY(scroll.getLayoutY() + scroll.getHeight() - 10);
                }
                newRectangle.setFill(new ImagePattern(img));
                daddy.getChildren().add(newRectangle);
            });

            rectangle.setOnMouseExited(t -> {
                daddy.getChildren().remove(newRectangle);
            });

        }
    }

    public void hands(Player current, Player oppenent, ScrollPane hand, AnchorPane daddy) {
        HBox content = new HBox();
        hand.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        int index = 0;  // pour obtenir l'index quand il va choisir la carte a joué ( utilisé dans le hand pas la)
        for (Card c : current.getHand()) {
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_" + c.getName() + ".png");
            rectangle.setFill(new ImagePattern(img));
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            int index1 = index;
            if (turnPlayer.equals(current) && !player1.isDead() && !player2.isDead()) {
                Rectangle newRectangle = new Rectangle(150, 180);
                rectangle.setOnMouseEntered(t -> {
                    newRectangle.setLayoutX(hand.getLayoutX() + rectangle.getLayoutX());
                    if (daddy.equals(split.getItems().get(0))) {
                        newRectangle.setLayoutY(hand.getLayoutY() + hand.getHeight() - 10);
                    } else {
                        newRectangle.setLayoutY(hand.getLayoutY() - newRectangle.getHeight() + 10);
                    }
                    newRectangle.setFill(new ImagePattern(img));
                    daddy.getChildren().add(newRectangle);
                });

                rectangle.setOnMouseExited(t -> {
                    daddy.getChildren().remove(newRectangle);
                });

                rectangle.setOnMouseClicked(t -> {
                    attack(index1, current, oppenent);
                });


            }
            index++;
        }

    }

    public void discards(Player current, Pane discard) {
        if (current.getDiscards().size() != 0) {
            discard.setVisible(true);
            Card lastCard = current.getDiscards().get(current.getDiscards().size() - 1);
            Rectangle rectangle = new Rectangle(100, 120);
            discard.getChildren().add(rectangle);
            Image img = new Image("images/Spellmonger_" + lastCard.getName() + ".png");
            rectangle.setFill(new ImagePattern(img));

        } else {
            discard.setVisible(false);
        }
    }

    public void InfoCard() {
        Tooltip tooltip = new Tooltip("Salut le monde !");
        deck1.setTooltip(tooltip);
    }
}


