package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ValentinDuph on 25/09/2016.
 */
public class Player {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private String name;
    private int lifePoint;
    private int energy;
    private ArrayList<Creature> playerCreature;
    private Cards cardPool;
    private Cards discardPool;

    public Player(String name)
    {
        this.playerCreature = new ArrayList<>();
        this.cardPool= new Cards(name); // créer une cardPool avec des valeurs (voir constructeur de la classe Cards)
        this.discardPool=new Cards(); // créer une cardPoll vide (voir constructeur de la classe Cards)
        this.name = name;
        this.lifePoint = 20;
        this.energy = 0;

    }

    public Cards getCards()
    {
        return  cardPool;
    }

    public Cards getDiscards()
    {
        return  discardPool;
    }

    public void reCreateCardPool() // quand la cardPool d'un joueur est finie, on la renouvelle
    {
        cardPool = new Cards(discardPool.getCardPool());
        discardPool.clearCards();
    }

    public int size()
    {
        return cardPool.size();
    }

    public void addPlayerCreature(Card creature)
    {
        playerCreature.add((Creature) creature);
    }

    public void removePlayerCreature(Card creature)
    {
        if(playerCreature.contains(creature))
        {
            playerCreature.remove(creature);
        }
    }

    public void sortCreatures()
    {
        Collections.sort(playerCreature);
    } //utilisé dans le systeme d'attaque

    public ArrayList<Creature> getPlayerCreature()
    {
        return playerCreature;
    }

    public boolean isDead()
    {
        return lifePoint<=0;
    }

    public int getLifePoint()
    {
        return lifePoint;
    }

    public void setLifePoint(int life)
    {
        lifePoint = life;
    }

    public int getEnergy()
    {
        return energy;
    }

    public void setEnergyPoint(int ene) { energy = ene; }

    public void increaseEnergy()
    {
        energy++;
    }

    public void winner()
    {
        logger.info(this.toString()+" is the winner!!!\n");
    }





    @Override
    public String toString()
    {
        return " " + name + "(" + getLifePoint() + "pv|" + getEnergy() + "energy)";
    }
}
