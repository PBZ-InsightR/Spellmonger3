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

    public SpellmongerApp game;
    public Player player1;
    public Player player2;
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
        player1 = new Player("Valentin");
        player2 = new Player("Natacha");
        game = new SpellmongerApp(player1, player2);
    }

    public void draw1() {
        if (player1.size() == 0) player1.reCreateCardPool();
        player1.addToHand(player1.getCards().get(0));
        player1.getCards().remove(0);
        update();
        deck1.setDisable(true);
        deck2.setDisable(true);
    }

    public void draw2() {
        if (player2.size() == 0) player2.reCreateCardPool();
        player2.addToHand(player2.getCards().get(0));
        player2.getCards().remove(0);
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
        if (player1.isDead() || player2.isDead()) {
            deck1.setDisable(true);
            deck2.setDisable(true);
        }
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());
        // creatures sur la piste
        listCreatureContents(player1, list_creatures1);
        listCreatureContents(player2, list_creatures2);
        // hands
        hands(player1, player2, discard1, hand1, deck2);
        hands(player2, player1, discard2, hand2, deck1);
        // discard
        discards(player1, discard1);
        discards(player2, discard2);
    }

    // les creatures en piste des joueurs
    public void listCreatureContents(Player p, ScrollPane scroll) {
        HBox content = new HBox();
        scroll.setContent(content);
        content.setSpacing(20);
        content.setPadding(new Insets(10, 10, 10, 10));
        for (Card c : p.getPlayerCreature()) {
            Rectangle rectangle = new Rectangle(100, 120);
            if (c instanceof Bear) {
                Image img = new Image("images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Fox) {
                Image img = new Image("images/Spellmonger_Fox.png");
                rectangle.setFill(new ImagePattern(img));
            }

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
            if (c instanceof Bear) {
                Image img = new Image("images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Eagle) {
                Image img = new Image("images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Wolf) {
                Image img = new Image("images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Fox) {
                Image img = new Image("images/Spellmonger_Fox.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof EnergyDrain) {
                Image img = new Image("images/Spellmonger_EnergyDrain.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Curse) {
                Image img = new Image("images/Spellmonger_Curse.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof Blessing) {
                Image img = new Image("images/Spellmonger_Blessing.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (c instanceof VaultOverclocking) {
                Image img = new Image("images/Spellmonger_VaultOver.png");
                rectangle.setFill(new ImagePattern(img));
            }

            rectangle.setLayoutY(10);
            content.getChildren().add(rectangle);
            // obtenir l'index du rectangle qu'il choisit
            int index1 = index;
            if (current.getHand().size() == 3 && !player1.isDead() && !player2.isDead()) rectangle.setOnMouseClicked(t -> {
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

            if (lastCard instanceof Bear) {
                Image img = new Image("images/Spellmonger_Bear.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastCard instanceof Wolf) {
                Image img = new Image("images/Spellmonger_Wolf.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastCard instanceof Eagle) {
                Image img = new Image("images/Spellmonger_Eagle.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastCard instanceof Fox) {
                Image img = new Image("images/Spellmonger_Fox.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastCard instanceof EnergyDrain) {
                Image img = new Image("images/Spellmonger_EnergyDrain.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastCard instanceof Curse) {
                Image img = new Image("images/Spellmonger_Curse.png");
                rectangle.setFill(new ImagePattern(img));
            } else if (lastCard instanceof Blessing) {
                Image img = new Image("images/Spellmonger_Blessing.png");
                rectangle.setFill(new ImagePattern(img));
            }else if (lastCard instanceof VaultOverclocking) {
                Image img = new Image("images/Spellmonger_VaultOver.png");
                rectangle.setFill(new ImagePattern(img));
            }
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