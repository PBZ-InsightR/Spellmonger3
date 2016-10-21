package edu.insightr.sample;


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

    public SpellmongerApp game;

    @FXML
    public Text name1, life_points1, name2, life_points2;
    public Pane discard1, discard2;
    public ScrollPane list_creatures1, list_creatures2, hand1, hand2;
    public Button deck1, deck2;


    @FXML
    public void initialize() {
        update();
        deck1.setDisable(false);
        deck2.setDisable(true);
    }

    public Controller() {
        game = new SpellmongerApp(new Player("Valentin"),new Player("Natacha"));
    }

    public void draw1() {
        if (game.getPlayer(0).size() == 0) game.getPlayer(0).reCreateCardPool();
        game.getPlayer(0).addToHand(game.getPlayer(0).getCards().get(0));
        game.getPlayer(0).getCards().remove(0);
        update();
        deck1.setDisable(true);
        deck2.setDisable(true);
    }

    public void draw2() {
        if (game.getPlayer(1).size() == 0) game.getPlayer(1).reCreateCardPool();
        game.getPlayer(1).addToHand(game.getPlayer(1).getCards().get(0));
        game.getPlayer(1).getCards().remove(0);
        update();
        deck1.setDisable(true);
        deck2.setDisable(true);
    }

    // attaque entre creatures des deux joueurs
    public void attack(int index, Player current, Player oppenent) {
        if (!current.isDead()) {
            game.playCard(current, oppenent, current.getHand(), index, current.getDiscards());
            current.attack(oppenent);
            update();
        }
    }

    //met le jeu  jour apres chaque attack, pioche, etc..
    public void update() {
        if (game.getPlayer(0).isDead() || game.getPlayer(1).isDead()) {
            deck1.setDisable(true);
            deck2.setDisable(true);
        }
        name1.setText("\t" + game.getPlayer(0).getName());
        life_points1.setText("Life point : " + game.getPlayer(0).getLifePoint() + "\n Energy : " + game.getPlayer(0).getEnergy());
        name2.setText("\t" + game.getPlayer(1).getName());
        life_points2.setText("Life point : " + game.getPlayer(1).getLifePoint() + "\n Energy : " + game.getPlayer(1).getEnergy());
        // creatures sur la piste
        listCreatureContents(game.getPlayer(0), list_creatures1);
        listCreatureContents(game.getPlayer(1), list_creatures2);
        // hands
        hands(game.getPlayer(0), game.getPlayer(1), discard1, hand1, deck2);
        hands(game.getPlayer(1), game.getPlayer(0), discard2, hand2, deck1);
        // discard
        discards(game.getPlayer(0), discard1);
        discards(game.getPlayer(1), discard2);
    }


    // les creatures en piste des joueurs
    public void listCreatureContents(Player p, ScrollPane scroll) {
        HBox content = new HBox();
        scroll.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : p.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_"+c.getName()+".png");
            rectangle.setFill(new ImagePattern(img));

            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
        }
    }

    // la main courante des joueurs
    public void hands(Player current, Player oppenent, Pane discard, ScrollPane hand, Button deckOpp) {
        HBox content = new HBox();
        hand.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        int index = 0;  // pour obtenir l'index quand il va choisir la carte a joué ( utilisé dans le hand pas la)
        for (Card c : current.getHand()) {
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_"+c.getName()+".png");
            rectangle.setFill(new ImagePattern(img));

            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            // obtenir l'index du rectangle qu'il choisit
            int index1 = index;
            if (current.getHand().size() == 3 && !game.getPlayer(0).isDead() && !game.getPlayer(1).isDead()) rectangle.setOnMouseClicked(t -> {
                attack(index1, current, oppenent);
                deckOpp.setDisable(false);
            });
            index++;
        }
    }

    // le discard des joueurs
    public void discards(Player current, Pane discard) {
        if (current.getDiscards().size() != 0) {
            discard.setVisible(true); // la discard apparait une fois qu'il a des cartes dedans
            Card lastCard = current.getDiscards().get(current.getDiscards().size() - 1);
            Rectangle rectangle = new Rectangle(100, 120);
            discard.getChildren().add(rectangle);
            Image img = new Image("images/Spellmonger_"+lastCard.getName()+".png");
            rectangle.setFill(new ImagePattern(img));

        } else { // premier tour (quand il n'y a pas de discard)
            discard.setVisible(false);
        }
    }

    public void InfoCard()
    {
        Tooltip tooltip = new Tooltip("Salut le monde !");
        deck1.setTooltip(tooltip);
    }
}