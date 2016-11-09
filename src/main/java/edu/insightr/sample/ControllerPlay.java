package edu.insightr.sample;


import edu.insightr.spellmonger.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import org.apache.log4j.Logger;


public class ControllerPlay implements ControlledScreen {

    ScreensController myController;


    private static final Logger logger = Logger.getLogger(ControllerPlay.class);
    public SpellmongerApp game;
    public Player turnPlayer;
    private Player player1;
    private Player player2;
    @FXML
    public Text name1, life_points1, name2, life_points2;
    public Pane discard1, discard2;
    public ScrollPane list_creatures1, list_creatures2, hand1, hand2;
    public Button deck1, deck2, pass1, pass2;
    public SplitPane split;
    public Pane mainPane,Player1,Player2;

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
        hand1.setVvalue(hand1.getVmax());
        hand1.setHvalue(hand1.getHmax());
        if (!player1.canPlay()) {
            AlertBox.displayDebugging("Energy issue", player1.getName() + ",you cannot play any of your cards!",Player1.getLayoutX(),Player1.getLayoutY());
            pass_player_1();
        }
    }

    public void draw_player_2() {
        drawCard(player2);
        if (!player2.canPlay()) {
            AlertBox.displayDebugging("Energy issue", player2.getName() + ",you cannot play any of your cards!",Player2.getLayoutX(),Player2.getLayoutY());
            pass_player_2();
        }
    }

    private void pass(Player current, Player opponent) {
        current.attack(opponent);
        turnFinished(current);
        boolean choix = true;
        if (current.equals(player2)) choix = false;
        deck1.setDisable(choix);
        pass1.setDisable(choix);
        deck2.setDisable(!choix);
        pass2.setDisable(!choix);
        if (myController.getData("isPlayer2").equals("false") && current == player2)
            play(-1, current, opponent);
    }

    public void pass_player_1() {
        pass(player1, player2);
    }

    public void pass_player_2() {
        pass(player2, player1);
    }

    private void drawCard(Player player) {
        if (player.canDraw()) { // Numa
            player.drawCard();
            deck1.setDisable(true);
            deck2.setDisable(true);
            update();
        } else
        {
            if(player.equals(player1)){
                AlertBox.displayDebugging("Error", "You cannot have more than 5 cards in your hand",Player1.getLayoutX(),Player1.getLayoutY());
            }
            else {
                AlertBox.displayDebugging("Error", "You cannot have more than 5 cards in your hand",Player2.getLayoutX(),Player2.getLayoutY());
            }
        }
    }

    private void turnFinished(Player current) {
        turnPlayer = game.nextPLayer(current);
        turnPlayer.increaseEnergy();
        update();
    }

    private void play(int index, Player current, Player oppenent) {
        Button deck = deck2;
        if (!current.isDead()) {
            if (myController.getData("isPlayer2").equals("false") && current == player2)
                game.playCardIA(current, oppenent, current.getHand(), current.getDiscards());
            else
                game.playCard(current, oppenent, current.getHand(), index, current.getDiscards());
            update();
            if (current == player1) deck = deck1;
            if (!current.canPlay() && deck.isDisabled()){
                if(current==player1){
                    AlertBox.displayDebugging("Energy issue", current.getName() + ",you cannot play any of your cards!",Player1.getLayoutX(),Player1.getLayoutY());
                }
                else {
                    AlertBox.displayDebugging("Energy issue", current.getName() + ",you cannot play any of your cards!", Player2.getLayoutX(), Player2.getLayoutY());
                }
                pass(current, oppenent);
            }
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
        listCreatureContents(player1, list_creatures1);
        listCreatureContents(player2, list_creatures2);
        // hands
        hands(player1, player2, hand1);
        hands(player2, player1, hand2);
        // discard
        discards(player1, discard1);
        discards(player2, discard2);
    }

    public void listCreatureContents(Player current, ScrollPane scroll) {
        HBox content = new HBox();
        scroll.setContent(content);
        content.setSpacing(20);
        for (Card c : current.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_" + c.getName() + ".png");
            rectangle.setFill(new ImagePattern(img));
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            if (turnPlayer.equals(current) && !player1.isDead() && !player2.isDead()) {
                Rectangle newRectangle = new Rectangle(250, 300);
                // TODO : no code duplication !
                rectangle.setOnMouseEntered(t -> {
                    newRectangle.setLayoutX(300);
                    newRectangle.setLayoutY(200);
                    newRectangle.setFill(new ImagePattern(img));
                    mainPane.getChildren().add(newRectangle);
                });
                rectangle.setOnMouseExited(t -> mainPane.getChildren().remove(newRectangle));
            }
        }
    }

    public void hands(Player current, Player oppenent, ScrollPane hand) {
        HBox content = new HBox();
        hand.setContent(content);
        content.setSpacing(20);
        int index = 0;  // pour obtenir l'index quand il va choisir la carte a joué ( utilisé dans le hand pas la)
        for (Card c : current.getHand()) {
            Rectangle rectangle = new Rectangle(100, 120);
            String imageOfCard="Spellmonger_"+c.getName();
            if(turnPlayer.equals(oppenent)) imageOfCard="dosCartes_ocre";
            Image img = new Image("images/"+ imageOfCard + ".png");
            rectangle.setFill(new ImagePattern(img));
            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            if (myController != null) {
                if (myController.getData("isPlayer2").equals("false") && current == player2) return;
            }
            int index1 = index;
            if (turnPlayer.equals(current) && !player1.isDead() && !player2.isDead()) {
                Rectangle newRectangle = new Rectangle(250, 300);
                rectangle.setOnMouseEntered(t -> {
                    newRectangle.setLayoutX(300);
                    newRectangle.setLayoutY(200);
                    newRectangle.setFill(new ImagePattern(img));
                    mainPane.getChildren().add(newRectangle);
                });

                rectangle.setOnMouseExited(t -> mainPane.getChildren().remove(newRectangle));

                rectangle.setOnMouseClicked(t -> {
                    play(index1, current, oppenent);
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


