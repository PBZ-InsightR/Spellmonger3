package edu.insightr.sample;


import edu.insightr.spellmonger.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import javafx.animation.TranslateTransition;
import javafx.animation.FadeTransition;

import java.util.Objects;


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
    public Pane mainPane, Player1, Player2, energy_player1_bckgrd, energy_player2_bckgrd;

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
        game = new SpellmongerApp(nameP1,nameP2);
        player1 = game.getPlayer(0);
        player2 = game.getPlayer(1);
        turnPlayer = player1;
        player1.increaseEnergy();
        player1.setEnergyPerTurn(player1.getEnergy());
        update();
        deck1.setDisable(false);
        deck2.setDisable(true);
        pass2.setDisable(true);
        pass1.setDisable(false);
    }

    public void initializeTest(){
        String nameP1 = "Player1";
        String nameP2 = "Player2";
        game = new SpellmongerApp(nameP1,nameP2);
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
        drawCard(player1, hand1,deck1);
    }

    public void draw_player_2() {
        drawCard(player2, hand2,deck2);
    }

    private void drawCard(Player player, ScrollPane hand, Button deck) {
        if (player.canDraw()) {
            TransitionDeck_Hand(player,deck, hand);
            player.drawCard();
            deck1.setDisable(true);
            deck2.setDisable(true);
            hand.setVvalue(hand.getVmax());
            hand.setHvalue(hand.getHmax());
            update();
            if (!player.canPlay()) {
                if (player == player1) {
                    TransitionAletreEnergy(Player1, energy_player1_bckgrd);
                } else {
                    if (isIA) {
                        PauseTransition delay = new PauseTransition(Duration.seconds(3));
                        delay.setOnFinished(event -> pass_player_2());
                        delay.play();
                    } else {
                        TransitionAletreEnergy(Player2, energy_player2_bckgrd);
                    }
                }
            }
        } else {
            if (player.equals(player1)) {
                AlertBox.displayDebugging("Error", "You cannot have more than \n      5 cards in your hand", 520, 290);
            } else {
                if (!isIA)
                    AlertBox.displayDebugging("Error", "You cannot have more than \n      5 cards in your hand", 520, 290);
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
        if (player.canDraw()) {
            player.drawCard();
        }
    }

    //Permet de switcher de joueur actuel
    private void pass(Player current, Player opponent, ScrollPane hand) {
        current.attackCreatures(opponent);
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

    //Est executé lorsque le player1 clic sur fin
    public void pass_player_1() {
        pass(player1, player2, hand1);
        if (isIA)
            whenIA();
    }

    //Est executé lorsque le player2 clic sur fin
    public void pass_player_2() {
        pass(player2, player1, hand2);
    }

    //Controle de l'IA
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

    //Appelé à la fin d'un tour
    private void turnFinished(Player current) {
        turnPlayer = game.nextPLayer(current);
        if(current.getVaultOverclockingOnOff()) {
            turnPlayer.vaultOverclockingActiveEffect();
        }
        turnPlayer.increaseEnergy();
        turnPlayer.setEnergyPerTurn(turnPlayer.getEnergy());
        update();
    }


    private void play(int index, Player current, Player oppenent) {
        Button deck = deck2;
        if (!current.isDead()) {
            if (isIA && current == player2){
                AlertBox.displayGame("IA",game.playCardIA(current, oppenent));
                pass_player_2();
            }
            else if (!game.playCard(current, oppenent, index)){
                if(current == player1)
                    TransitionAletreEnergy(Player1, energy_player1_bckgrd);
                else
                    TransitionAletreEnergy(Player2, energy_player2_bckgrd);
            }
            update();
            if (current == player1) deck = deck1;
            if (!current.canPlay() && deck.isDisabled()) {
                if (current == player1) {
                    //TransitionAletreEnergy(Player1, energy_player1_bckgrd);
                } else {
                    if (!isIA) {
                        //TransitionAletreEnergy(Player2, energy_player2_bckgrd);
                    }
                }
            }
        }
    }


    //Refresh les valeurs
    private void update() {
        name1.setText("\t" + player1.getName());
        life_points1.setText("Life point : " + player1.getLifePoint());
        energy_player1.setText("Energy : " + player1.getEnergyPerTurn() + " / " + player1.getEnergy());
        name2.setText("\t" + player2.getName());
        life_points2.setText("Life point : " + player2.getLifePoint());
        energy_player2.setText("Energy : " + player2.getEnergyPerTurn() + " / " + player2.getEnergy());
        if (player1.isDead() || player2.isDead()) {
            deck1.setDisable(true);
            deck2.setDisable(true);
            pass1.setDisable(true);
            pass2.setDisable(true);
            if (player1.winner(player2)) {
                JsonTools.updateJsonFile(myController.getData("NamePlayer1"), true);
                JsonTools.updateJsonFile(myController.getData("NamePlayer2"), false);
            } else {
                JsonTools.updateJsonFile(myController.getData("NamePlayer2"), true);
                JsonTools.updateJsonFile(myController.getData("NamePlayer1"), false);
            }
            AlertBox.displayGame("Game over", "The game is over");
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

    //Affichage des creatures sur le terrain
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

    //Affichage des créatures dans la main du joueur
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

    //Agrandi la carte lors du passage souris
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

    //Supprime la carte agrandi apres passage
    private void eventExit(Rectangle rectangle, Rectangle newRectangle) {
        rectangle.setOnMouseExited(t -> mainPane.getChildren().remove(newRectangle));
    }

    //Gere les evenements de clic souris
    private void eventClick(Rectangle rectangle, Player current, Player oppenent, int playerChoice) {
        rectangle.setOnMouseClicked(t -> {
            Card card = current.getHand().get(playerChoice);
            ScrollPane hand = hand2;
            ScrollPane list_creatures = list_creatures2;
            Pane discard = discard2;
            Pane player_pane = Player2;
            Pane energy_pane = energy_player2_bckgrd;
            if(current == player1){
                list_creatures = list_creatures1;
                discard = discard1;
                hand = hand1;
                player_pane = Player1;
                energy_pane = energy_player1_bckgrd;
            }
            //TransitionAfterAttack(current,playerChoice);
            if(card.getTypeCard() == "Creature"){
                TransitionHand_ListCreatures(current, player_pane, energy_pane, list_creatures, playerChoice);
            }
            else if(card.getTypeCard() == "Ritual"){
                TransitionHand_Discard(current, player_pane, energy_pane, hand, discard, playerChoice);
            }
            else if(card.getTypeCard() == "Enchantment") {
                TransitionHand_Discard(current, player_pane, energy_pane, hand, discard, playerChoice);
            }
            play(playerChoice, current, oppenent);
        });
    }

    //Boutton permettant de retourner au menu
    public void backToMenu() {
        myController.loadScreen(Main.Menu_ID, Main.Menu_FILE);
        myController.setScreen(Main.Menu_ID);
    }

    //Boutton permettant de redémarrer la partie
    public void backToPlay() {
        myController.loadScreen(Main.Play_ID, Main.Play_FILE);
        myController.setScreen(Main.Play_ID);
    }

    //Boutton permettant d'ouvrir les scores
    public void backToScore() {
        myController.loadScreen(Main.Score_ID, Main.Score_FILE);
        myController.setScreen(Main.Score_ID);

    }

    private void TransitionAletreEnergy(Pane player_pane, Pane energy_pane){
        Rectangle rectangle = new Rectangle(150,50);
        Stop[] stops = new Stop[] { new Stop(0, Color.RED), new Stop(1, Color.WHITE)};
        LinearGradient gp = new LinearGradient(0, 0, 10, 24, true, CycleMethod.NO_CYCLE, stops);
        rectangle.setFill(gp);
        rectangle.setArcHeight(18);
        rectangle.setArcWidth(18);
        rectangle.setLayoutX(energy_pane.getLayoutX());
        rectangle.setLayoutY(energy_pane.getLayoutY());
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(600), rectangle);
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(0.8f);
        fadeTransition.setCycleCount(4);
        fadeTransition.setAutoReverse(true);
        player_pane.getChildren().add(rectangle);
        fadeTransition.play();
    }

    private void TransitionForAll(Rectangle rectangle, double layoutXFrom, double layoutXTo, double layoutYFrom, double layoutYTo){
        mainPane.getChildren().add(rectangle);
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(800), rectangle);
        translateTransition.setFromX(layoutXFrom);
        translateTransition.setToX(layoutXTo);
        translateTransition.setFromY(layoutYFrom);
        translateTransition.setToY(layoutYTo);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(800), rectangle);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);
        translateTransition.play();
        fadeTransition.play();
        rectangle.setDisable(true);
        Rectangle newRectangle = new Rectangle(10, 10);
        eventExit(rectangle, newRectangle);
    }

    //Transition de la pioche vers la main
    private void TransitionDeck_Hand(Player current, Button deck, ScrollPane hand){
        int sizeOfHand = current.getHand().size();
        Card lastCardOfHand = current.getCards().get(0);
        double layoutXTransitionFrom = deck.getLayoutX();
        double layoutXTransitionTo;
        double layoutYTransition;
        if(sizeOfHand == 0){
            layoutXTransitionTo = hand.getLayoutX();
        }
        else {
            layoutXTransitionTo = hand.getLayoutX() + 120;
        }
        Rectangle rectangle = new Rectangle(100, 120);
        Image img = new Image("images/Spellmonger_"+ lastCardOfHand.getName() +".png");
        rectangle.setFill(new ImagePattern(img));
        if(current == player1){
            layoutYTransition = 62;
        }
        else{
            layoutYTransition = 545;
        }
        TransitionForAll(rectangle, layoutXTransitionFrom, layoutXTransitionTo, layoutYTransition, layoutYTransition);
    }

    //Transition carte de la main vers le plateau
    private void TransitionHand_ListCreatures(Player current, Pane player_pane, Pane energy_pane, ScrollPane listCreatures, int playerChoice){
        Card cardSelected = current.getHand().get(playerChoice);
        int sizeOfListCreatures = current.getPlayerCreature().size();
        double layoutXTransitionFrom;
        double layoutXTransitionTo = listCreatures.getLayoutX();;
        double layoutYTransitionFrom;
        double layoutYTransitionTo;
        if(cardSelected.getEnergyCost() <= current.getEnergyPerTurn()){
            if(sizeOfListCreatures == 0)
                layoutXTransitionTo = listCreatures.getLayoutX();
            else if(sizeOfListCreatures == 1)
                layoutXTransitionTo = listCreatures.getLayoutX() + 120;
            else if(sizeOfListCreatures == 2)
                layoutXTransitionTo = listCreatures.getLayoutX() + 240;
            else
                layoutXTransitionTo = listCreatures.getLayoutX() + 360;

            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_"+ cardSelected.getName() +".png");
            rectangle.setFill(new ImagePattern(img));
            if(current == player1){
                layoutXTransitionFrom = hand1.getLayoutX();
                layoutYTransitionFrom = 62;
                layoutYTransitionTo = 217;
            }
            else{
                layoutXTransitionFrom = hand2.getLayoutX();
                layoutYTransitionFrom = 545;
                layoutYTransitionTo = 385;
            }
            TransitionForAll(rectangle, layoutXTransitionFrom, layoutXTransitionTo, layoutYTransitionFrom, layoutYTransitionTo);
        }
        else
            TransitionAletreEnergy(player_pane, energy_pane);
    }

    //Transition carte de la main vers discard
    private void TransitionHand_Discard(Player current, Pane player_pane, Pane energy_pane, ScrollPane hand, Pane discard, int playerChoice){
        Card cardSelected = current.getHand().get(playerChoice);
        double layoutXTransitionFrom = hand.getLayoutX();
        double layoutXTransitionTo = discard.getLayoutX();
        double layoutYTransition;
        if(cardSelected.getEnergyCost() <= current.getEnergyPerTurn()){
            Rectangle rectangle = new Rectangle(100, 120);
            Image img = new Image("images/Spellmonger_"+ cardSelected.getName() +".png");
            rectangle.setFill(new ImagePattern(img));
            if(current == player1){
                layoutYTransition = 62;
            }
            else{
                layoutYTransition = 545;
            }
            TransitionForAll(rectangle, layoutXTransitionFrom, layoutXTransitionTo, layoutYTransition, layoutYTransition);
        }
        else
            TransitionAletreEnergy(player_pane, energy_pane);
    }

    /*
    private void TransitionAfterAttack(Player current,int playerChoice){
        if(current == player1) {
            Card card = current.getHand().get(playerChoice);
            if(Objects.equals(card.getTypeCard(), "Creature"))
                TransitionHand_ListCreatures(player1, list_creatures1, playerChoice);
            else
                TransitionHand_Discard(player1, hand1, discard1, playerChoice);
        }
        else {
            Card card = current.getHand().get(playerChoice);
            if (Objects.equals(card.getTypeCard(), "Creature"))
                TransitionHand_ListCreatures(player2, list_creatures2, playerChoice);
            else
                TransitionHand_Discard(player2, hand2, discard2, playerChoice);
        }
    }
    */
}


