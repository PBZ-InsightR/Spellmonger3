package sample;

import edu.insightr.spellmonger.*;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

import org.apache.log4j.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.geometry.*;
public class Controller {
    final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private Player player1 ;
    private Player player2 ;
    private SpellmongerApp game ;


    @FXML
    private Label J1,J2;
    public Pane hand1;
    public ScrollPane cards1,cards2;
    public Button b1,b2;


    public Controller(){
            player1 = new Player("Valentin");
            player2 = new Player("Natacha");
            game = new SpellmongerApp(player1,player2); 
    }


    public void attack1(){
        if(player1.size()==0) player1.reCreateCardPool();
        game.drawCard(player1,player2,player1.getCards(),player2.getDiscards());
        if(!player1.isDead())
            player1.attack(player2);
        update();
    }


    public void attack2(){
        if(player1.size()==0) player1.reCreateCardPool();
        game.drawCard(player2,player1,player2.getCards(),player1.getDiscards());
        if(!player2.isDead())
            player2.attack(player1);
        update();
    }

    public void update() {
        if(player1.isDead()) b1.setDisable(true);
        if(player2.isDead()) b2.setDisable(true);
        J1.setText("\t"+player1.getName()+"\n Life point : " + player1.getLifePoint() + "\n Energy : " + player1.getEnergy());
        J2.setText("\t"+player2.getName()+"\n Life point : " + player2.getLifePoint() + "\n Energy : " + player2.getEnergy());

        HBox content = new HBox();
        cards1.setContent(content);
        content.setSpacing(10);
        content.setPadding(new Insets(10, 10, 10, 10));
        for(Card c:player1.getPlayerCreature()) {
            Label l = new Label(c.getName());
            l.setPrefSize(100, 100);
            l.setLayoutY(10);
            l.setStyle("-fx-background-color: green");
            content.getChildren().add(l);
        }

        HBox content2 = new HBox();
        cards2.setContent(content2);
        content2.setSpacing(10);
        content2.setPadding(new Insets(10, 10, 10, 10));
        for(Card c:player2.getPlayerCreature()) {
            Label l = new Label(c.getName());
            l.setPrefSize(100, 100);
            l.setStyle("-fx-background-color: green");
            content2.getChildren().add(l);
        }
    }
}
