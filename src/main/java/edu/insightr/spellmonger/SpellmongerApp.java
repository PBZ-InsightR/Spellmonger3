package edu.insightr.spellmonger;


//import javafx.scene.control.RadioMenuItem;

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


    private ArrayList<Card> cardPool;
    private ArrayList<Card> discardPool;
    private Map<Player, Integer> playerList = new HashMap<>(2);

    public SpellmongerApp(Player player1,Player player2){

        this.cardPool = new ArrayList<Card>();
        this.discardPool = new ArrayList<Card>();
        //Player fisrtPlayer = new Player("Bob");
        //Player secondPlayer= new Player("Alice");
        playerList.put(player1,1);
        playerList.put(player2,2);
    }

    public ArrayList<Card> getCardPool()
    {
        return cardPool;
    }

    public ArrayList<Card> getDiscardPool()
    {
        return discardPool;
    }

    public void CreateCardPool()
    {
        for (int i = 0; i < 70; i++) {
            Random rand = new Random();
            int choix = rand.nextInt(2);
            if(choix == 0)//creature
            {
                Random rand2 = new Random();
                int type = rand2.nextInt(3);
                if(type==0)//eagle
                {
                    Eagle aigle= new Eagle();
                    this.cardPool.add(aigle);
                }
                if(type==1)//wolf
                {
                    Wolf loup= new Wolf();
                    this.cardPool.add(loup);
                }
                if(type==2)//bear
                {
                    Bear ours= new Bear();
                    this.cardPool.add(ours);
                }

            }
            if(choix==1)//ritual
            {
                Random rand3 = new Random();
                int spell = rand3.nextInt(2);
                if(spell==0)//curse
                {
                    Curse malediction = new Curse();
                    this.cardPool.add(malediction);
                }
                if(spell==1)//blessing
                {
                    Blessing soin = new Blessing();
                    this.cardPool.add(soin);
                }
            }
        }
    }

}