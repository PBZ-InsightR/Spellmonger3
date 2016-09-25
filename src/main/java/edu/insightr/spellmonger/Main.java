package edu.insightr.spellmonger;

import org.apache.log4j.Logger;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main{
    public static void main(String[] args) {
        Player Player1 = new Player("Valentin");
        Player Player2 = new Player("Natacha");
        SpellmongerApp game = new SpellmongerApp(Player1,Player2);
        game.CreateCardPool();

        while(!Player1.isDead() || !Player2.isDead())
        {
            if(game.getSize()<2)
            {
                game.ReCreateCardPool();
            }
            Player1.DrawCard(Player1,Player2,game.getCardPool(),game.getDiscardPool());
            if(Player1.isDead() || Player2.isDead()) break;
            Player2.DrawCard(Player2,Player1,game.getCardPool(),game.getDiscardPool());
        }

        if(Player1.isDead())
        {
            Player2.winner();
        }
        else
        {
            Player1.winner();
        }
    }


}
