package edu.insightr.sample;


import edu.insightr.spellmonger.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.util.Duration;
import org.apache.log4j.Logger;

import java.util.ArrayList;


public class ControllerPlay implements ControlledScreen {

    ScreensController myController;


    private static final Logger logger = Logger.getLogger(ControllerPlay.class);
    public SpellmongerApp game;
    public Player turnPlayer;
    private Player player1;
    private Player player2;
    private boolean isIA;
    @FXML
    public Text name1, life_points1, energy_player1, name2, life_points2, energy_player2;
    public Pane discard1, discard2;
    public ScrollPane list_creatures1, list_creatures2, hand1, hand2;
    public Button deck1, deck2, pass1, pass2;
    public SplitPane split;
    public Pane mainPane, Player1, Player2;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
        initialize();
    }

    @FXML
    public void initialize() {
        String nameP1 = "Player1";
        String nameP2 = "Player2";
        if (myController != null) {
            if (!myController.getData("NamePlayer1").equals(""))
                nameP1 = myController.getData("NamePlayer1");
            if (!myController.getData("NamePlayer2").equals(""))
                nameP2 = myController.getData("NamePlayer2");
            isIA = myController.getData("isPlayer2").equals("false");
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

    public void initializeTest(){
        String nameP1 = "Player1";
        String nameP2 = "Player2";
        game = new SpellmongerApp(new Player(nameP1), new Player(nameP2));
        player1 = game.getPlayer(0);
        player2 = game.getPlayer(1);
        turnPlayer = player1;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public void draw_player_1() {
        drawCard(player1, hand1);
    }

    public void draw_player_2() {
        drawCard(player2, hand2);
    }

    private void drawCard(Player player, ScrollPane hand) {
        if (player.canDraw()) { // Numa
            player.drawCard();
            deck1.setDisable(true);
            deck2.setDisable(true);
            hand.setVvalue(hand.getVmax());
            hand.setHvalue(hand.getHmax());
            update();
            if (!player.canPlay()) {
                if (player == player1) {
                    AlertBox.displayDebugging("Energy issue", player.getName() + ",you cannot play any of your cards!", Player1.getLayoutX(), Player1.getLayoutY()); //position a changer
                    pass_player_1();
                } else {
                    if (isIA) {
                        PauseTransition delay = new PauseTransition(Duration.seconds(3));
                        delay.setOnFinished(event -> pass_player_2());
                        delay.play();
                    } else {
                        AlertBox.displayDebugging("Energy issue", player.getName() + ",you cannot play any of your cards!", Player1.getLayoutX(), Player1.getLayoutY()); //position a changer
                        pass_player_2();
                    }
                }
            }
        } else {
            if (player.equals(player1)) {
                AlertBox.displayDebugging("Error", "You cannot have more than 5 cards in your hand", Player1.getLayoutX(), Player1.getLayoutY());
            } else {
                if (!isIA)
                    AlertBox.displayDebugging("Error", "You cannot have more than 5 cards in your hand", Player2.getLayoutX(), Player2.getLayoutY());
            }
        }
    }
    public void draw_player_1_test() {
        drawCardTest(player1,hand1);
    }

    public void draw_player_2_test() {
        drawCardTest(player2,hand2);
    }
    public void drawCardTest(Player player,ScrollPane hand){
        if (player.canDraw()) { // Numa
            player.drawCard();

        }
    }

    private void pass(Player current, Player opponent, ScrollPane hand) {
        current.attack(opponent);
        turnFinished(current);
        boolean choice = true;
        if (current.equals(player2)) choice = false;
        deck1.setDisable(choice);
        pass1.setDisable(choice);
        deck2.setDisable(!choice);
        pass2.setDisable(!choice);
        hand.setVvalue(hand.getVmin());
        hand.setHvalue(hand.getHmin());
    }

    public void pass_player_1() {
        pass(player1, player2, hand1);
        if (isIA)
            whenIA();
    }

    public void pass_player_2() {
        pass(player2, player1, hand2);
    }

    private void whenIA() {
        logger.info("on est IA");
        draw_player_2();
        update();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            play(-1, player2, player1);
            update();
        });
        delay.play();
    }

    private void turnFinished(Player current) {
        turnPlayer = game.nextPLayer(current);
        turnPlayer.increaseEnergy();
        update();
    }

    private void play(int index, Player current, Player oppenent) {
        Button deck = deck2;
        if (!current.isDead()) {
            if (isIA && current == player2)
                AlertBox.displayGame("IA",game.playCardIA(current, oppenent));
            else if (!game.playCard(current, oppenent, index))
                AlertBox.displayError("Energy not enough", current.getName() + " hasn't enough energy in his stack");
            update();
            if (current == player1) deck = deck1;
            if (!current.canPlay() && deck.isDisabled()) {
                if (current == player1) {
                    AlertBox.displayDebugging("Energy issue", current.getName() + ",you cannot play any of your cards!", Player1.getLayoutX(), Player1.getLayoutY());
                    pass_player_1();
                } else {
                    if (!isIA) {
                        AlertBox.displayDebugging("Energy issue", current.getName() + ",you cannot play any of your cards!", Player2.getLayoutX(), Player2.getLayoutY());
                    }
                    pass_player_2();
                }
            }
        }
    }

    private void update() {
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint());
        energy_player1.setText("Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint());
        energy_player2.setText("Energy : " + player2.getEnergy());
        if (player1.isDead() || player2.isDead()) {
            deck1.setDisable(true);
            deck2.setDisable(true);
            pass1.setDisable(true);
            pass2.setDisable(true);
            if (player1.winner(player2)) {
                Outils.updateJsonFile(myController.getData("NamePlayer1"), true);
                Outils.updateJsonFile(myController.getData("NamePlayer2"), false);
            } else {
                Outils.updateJsonFile(myController.getData("NamePlayer2"), true);
                Outils.updateJsonFile(myController.getData("NamePlayer1"), false);
            }
           AlertBox.displayGame("Game over", "le jeu est fini");
        }

        // creatures sur la piste
        listCreatureContents(player1, list_creatures1);
        listCreatureContents(player2, list_creatures2);
        // hands
        hands(player1, player2, hand1);
        hands(player2, player1, hand2);
        // discard
        discards(player1, discard1);
        discards(player2, discard2);

    }

    private void listCreatureContents(Player current, ScrollPane scroll) {
        HBox content = new HBox();
        scroll.setContent(content);
        content.setSpacing(20);
        for (Card c : current.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_" + c.getName() + ".png");
            rectangle.setFill(new ImagePattern(img));
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            if (!player1.isDead() && !player2.isDead()) {
                Rectangle newRectangle = new Rectangle(250, 300);
                eventEnter(rectangle, newRectangle, img);
                eventExit(rectangle, newRectangle);
            }
        }
    }

    private void hands(Player current, Player oppenent, ScrollPane hand) {
        HBox content = new HBox();
        hand.setContent(content);
        content.setSpacing(20);
        int index = 0;
        for (Card c : current.getHand()) {
            Rectangle rectangle = new Rectangle(100, 120);
            String imageOfCard = "Spellmonger_" + c.getName();
            if (turnPlayer.equals(oppenent)) imageOfCard = "dosCartes_ocre";
            Image img = new Image("images/" + imageOfCard + ".png");
            rectangle.setFill(new ImagePattern(img));
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            if (turnPlayer.equals(current) && !player1.isDead() && !player2.isDead()) {
                Rectangle newRectangle = new Rectangle(250, 300);
                eventEnter(rectangle, newRectangle, img);
                eventExit(rectangle, newRectangle);
                eventClick(rectangle, current, oppenent, index);
            }
            index++;
        }
    }

    private void discards(Player current, Pane discard) {
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

    private void eventEnter(Rectangle rectangle, Rectangle newRectangle, Image img) {
        rectangle.setOnMouseEntered(t -> {
            newRectangle.setLayoutX(620);
            newRectangle.setLayoutY(203);
            newRectangle.setWidth(230);
            newRectangle.setHeight(310);
            newRectangle.setArcHeight(5);
            newRectangle.setArcWidth(5);
            newRectangle.getStyleClass().add("cartes_ombre"); //Ombre sous les cartes
            newRectangle.setFill(new ImagePattern(img));
            mainPane.getChildren().add(newRectangle);
        });
    }

    private void eventExit(Rectangle rectangle, Rectangle newRectangle) {
        rectangle.setOnMouseExited(t -> mainPane.getChildren().remove(newRectangle));
    }

    private void eventClick(Rectangle rectangle, Player current, Player oppenent, int playerChoice) {
        rectangle.setOnMouseClicked(t -> {
            play(playerChoice, current, oppenent);
        });
    }

    public void backToMenu() {
        myController.loadScreen(Main.Menu_ID, Main.Menu_FILE);
        myController.setScreen(Main.Menu_ID);
    }

    public void backToPlay() {
        myController.loadScreen(Main.Play_ID, Main.Play_FILE);
        myController.setScreen(Main.Play_ID);
    }

    public void backToScore() {
        myController.loadScreen(Main.Score_ID, Main.Score_FILE);
        myController.setScreen(Main.Score_ID);

    }
}

