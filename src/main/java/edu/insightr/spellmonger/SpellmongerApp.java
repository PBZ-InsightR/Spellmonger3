package edu.insightr.spellmonger;


//import javafx.scene.control.RadioMenuItem;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Natiassa on 25/09/2016.
 * il manque les consctructeurs vide pour les ours,aigle ....
 * c'est pour ça qu'il y a des erreurs.
 */
public class SpellmongerApp {

    private ArrayList<Player> playerList = new ArrayList<Player>(2);
    private int counter;

    public SpellmongerApp(Player player1,Player player2){
        playerList.add(player1);
        playerList.add(player2);
        counter=0;
    }

    public Player nextPLayer()
    {
        if(counter==playerList.size()-1) {
            counter = 0;
        }
        else {
            counter++;
        }
            return playerList.get(counter);
    }  // renvoie l'autre joueuer de la partie (pour l'instant il n'y en a que deux, mais si ça augmente, la méthode ne chagera pas!)

    @Override
    public String toString()
    {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
