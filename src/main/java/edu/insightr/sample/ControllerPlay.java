package edu.insightr.sample;


import edu.insightr.spellmonger.*;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
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

import java.util.HashMap;


public class ControllerPlay implements ControlledScreen{

    ScreensController myController;


    private static final Logger logger = Logger.getLogger(ControllerPlay.class);
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



    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
        initialize();
    }

    @FXML
    public void initialize() {
        String nameP1 = "Player1";
        String nameP2 = "Player2";
        if(myController != null) {
            if(!myController.getData("NamePlayer1").equals(""))
                nameP1 = myController.getData("NamePlayer1");
            if(!myController.getData("NamePlayer2").equals(""))
                nameP2 = myController.getData("NamePlayer2");
        }

        game = new SpellmongerApp(new Player(nameP1), new Player(nameP2));
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
        drawCard(player1);
        if (!player1.canPlay())
            pass_player_1();
    }

    public void draw_player_2() {
        drawCard(player2);
        if (!player2.canPlay())
            pass_player_2();
    }

    public void pass(Player current, Player opponent){
        current.attack(opponent);
        turnFinished(current);
        boolean choix = true;
        if(current==player2) choix = false;
        deck1.setDisable(choix);
        pass1.setDisable(choix);
        deck2.setDisable(!choix);
        pass2.setDisable(!choix);
        if(myController.getData("isPlayer2").equals("false") && current == player2)
            play(-1, current, opponent);
    }
    public void pass_player_1() {
        pass(player1,player2);
    }

    public void pass_player_2() {
        pass(player2,player1);
    }

    private void drawCard(Player player) {
        if (player.getHand().size() < 5) { // Numa
            if (player.size() == 0) player.reCreateCardPool(); // Numa
            player.addToHand(player.getCards().get(0)); // Numa
            player.getCards().remove(0); // Numa
            deck1.setDisable(true);
            deck2.setDisable(true);
            update();
        } else  //Numa
        {
            AlertBox.displayError("Error", "You cannot have more than 5 cards in your hand");
        }
    }

    private void turnFinished(Player current) {
        turnPlayer = game.nextPLayer(current);
        turnPlayer.increaseEnergy();
        update();
    }

    public void play(int index, Player current, Player oppenent) {
        Button deck=deck2;
        if (!current.isDead()) {
            if(myController.getData("isPlayer2").equals("false") && current == player2)
                game.playCardIA(current, oppenent, current.getHand(), current.getDiscards());
            else
                game.playCard(current, oppenent, current.getHand(), index, current.getDiscards());
            update();
            if(current==player1) deck = deck1;
            if (!current.canPlay()&& deck.isDisabled()) pass(current,oppenent);
        }
    }

    public void update() {
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());
        if (player1.isDead() || player2.isDead()) {
            deck1.setDisable(true);
            deck2.setDisable(true);
            pass1.setDisable(true);
            pass2.setDisable(true);
            AlertBox.displayGame("Game over", "le jeu est fini");
        }

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
                Bounds boundsInScene = rectangle.localToScene(rectangle.getBoundsInLocal());
                double x = boundsInScene.getMinX();
                double y = boundsInScene.getMaxY()-newRectangle.getHeight();
                double translation = split.getItems().get(0).getLayoutBounds().getHeight();

                if (daddy.equals(split.getItems().get(1))) { // si c'est le player 2
                    y = boundsInScene.getMinY();
                    y = y - translation-5;
                }
                newRectangle.setLayoutX(x);
                newRectangle.setLayoutY(y);
                newRectangle.setFill(new ImagePattern(img));
                if (!daddy.getChildren().contains(newRectangle)) {
                    daddy.getChildren().add(newRectangle);
                }
            });

            newRectangle.setOnMouseExited(t -> daddy.getChildren().remove(newRectangle));

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
            if(myController != null){
            if(myController.getData("isPlayer2").equals("false") && current == player2 )return ;}
            int index1 = index;
            if (turnPlayer.equals(current) && !player1.isDead() && !player2.isDead()) {
                Rectangle newRectangle = new Rectangle(150, 180);
                rectangle.setOnMouseEntered(t -> {
                    Bounds boundsInScene = rectangle.localToScene(rectangle.getBoundsInLocal());
                    double x = boundsInScene.getMinX();
                    double y = boundsInScene.getMinY();
                    double translation = split.getItems().get(0).getLayoutBounds().getHeight();

                    if (daddy.equals(split.getItems().get(1))) {
                        y = boundsInScene.getMaxY();
                        y = y - translation;
                        y = y - newRectangle.getHeight();
                    }
                    newRectangle.setLayoutX(x);
                    newRectangle.setLayoutY(y);
                    newRectangle.setFill(new ImagePattern(img));
                    if (!daddy.getChildren().contains(newRectangle)) {
                        daddy.getChildren().add(newRectangle);
                    }
                });

                newRectangle.setOnMouseExited(t -> daddy.getChildren().remove(newRectangle));

                newRectangle.setOnMouseClicked(t -> {
                    logger.info("CLICKED");
                    play(index1, current, oppenent);
                    newRectangle.setDisable(true);
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


