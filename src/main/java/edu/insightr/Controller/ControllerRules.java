package edu.insightr.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ControllerRules implements ControlledScreen {

    private ScreensController myController;
    @FXML
    public TextArea theRules;
    public Rectangle theImage;

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
        initialize();
    }

    private void initialize() {
        theRules.setEditable(false);
        theRules.setMaxHeight(400);
        theRules.setMinWidth(400);
        theRules.setText("But du jeu :\n" +
                "Réduire la vie de votre adversaire à zéro pour gagner la partie.\n" +
                "\nPrincipes de jeu :\n" +
                "•\tChaque joueur possède un deck de 40 cartes. Au début chaque joueur commence \navec 2 cartes en mains pioché dans le deck.\n" +
                "•\tLa ressource utilisée dans le jeu est l’énergie. Vous débutez la partie avec un point d’énergie, \n à chaque tour vous recevrez un point d’énergie supplémentaire. L’énergie est rechargée au début de chaque tour.\n" +
                "•\tLe coût en énergie d’une carte est représenté en haut à droite de la carte. \n" +
                "•\tOn peut jouer plusieurs cartes par tour tant qu’on a l’énergie nécessaire.\n" +
                "•\tIl y a trois types de carte, Créature, Sort et Enchantement .\n" +
                "\n\nCréatures :\n" +
                "•\tLorsqu’une créature est jouée elle est invoquée sur le plateau. A la fin du tour l’ensemble des créatures attaquent.\n" +
                "•\tSi aucune créature se trouve sur le plateau adverse elles  attaquent l’adversaire directement, sinon une créature attaque en priorité  la créature avec le plus de défense qu’elle peut tuer sinon elle tape dans une autre créature.\n" +
                "•\tUne créature a une force d’attaque (représenté dans un rond  rouge à gauche) et une force de défense (représenté dans un bouclier bleu à droite).\n" +
                "•\tSi la force d’attaque de la créature est supérieur ou égale à la  force de défense de l’a créature adverse alors elle la tue et inversement.\n" +
                "\n\nCaractéristiques créature :\n" +
                "•\tFlying : Attaque directement d’adversaire sans prendre  en compte les créatures sur le plateau.\n" +
                "•\tDeathtouch : Tue toute créature qu’il attaque même si la  défense est supérieure à l’attaque de cette créature.\n" +
                "•\tCatch : Bloque une créature qui a la capacité Flying.\n" +
                "\n\nSorts :\n" +
                "Lorsqu’un sort est joué, il attaque l'adversaire ou béni le joueur directement." +
                "\n\nCaractéristiques Sorts :\n" +
                "•\tBlessing : Béni le joueur et lui faire gagner 3 points de vie.\n" +
                "•\tCurse : Attaque l'adversaire et lui faire perdre 3 points de vie.\n" +
                "•\tEnergy Drain : Offre  2 énérgies au joueur, et en vole deux à l’adversaire.\n" +
                "\n\nEnchantement  :\n" +
                "Lorsqu’un enchantement est joué, le joueur a deux possiblité:\n" +
                "•\tpremière : Il est 2 énergies en plus pendant son tour (65% de chance).\n" +
                "•\tseconde : Il n'aura le droit qu'à une seule énergie pendant son tour (35% de chance).\n"
        );


        Image img = new Image("images/rules.jpg");
        theImage.setFill(new ImagePattern(img));
    }


    //passer a la vue de Play
    public void goToPlay() {
        myController.loadScreen(Main.Play_ID, Main.Play_FILE); // charger le fichier Controller.fxml
        myController.setScreen(Main.Play_ID); // activer la vue Play
    }

    public void goToMenu() {
        myController.setScreen(Main.Menu_ID);// activer la vue Menu sans load vu qu'il es déja dans la Map screens
    }
}
