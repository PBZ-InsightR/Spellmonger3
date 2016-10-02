package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Abdel on 02/10/2016.
 */
public class Cards {

    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Card> cardPool= new ArrayList<>();;

    public Cards()
    {
    }
    public Cards(String name)
    {
        createCardPool();
        logger.info(name+"'s cardpool created");
    }

    public Cards( ArrayList<Card> other)
    {
        cardPool= new ArrayList<>(other);
    }

    public ArrayList<Card> getCardPool()
    {
        return cardPool;
    }

    public int size()
    {
        return cardPool.size();
    }

    public Card get(int index)
    {
        return cardPool.get(index);
    }

    public void add(Card c)
    {
         cardPool.add(c);
    }

    public Card remove(int index)
    {
        return cardPool.remove(index);
    }

    public void createCardPool()
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
                int spell = rand3.nextInt(3);
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
                if(spell==3)//EnergyDrain
                {
                    EnergyDrain drain = new EnergyDrain();
                    this.cardPool.add(drain);
                }
            }
        }
    }

    public void clearCards()
    {
        cardPool.clear();
    }



}
