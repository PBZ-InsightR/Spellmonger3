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

    public void drawCard(Player currentPlayer, Player opponent, Cards cardPool, Cards displayCard)
    {

        currentPlayer.energy++;
        Card currentCard = cardPool.get(0);
        cardPool.remove(0);
        displayCard.add(currentCard);

        logger.info(currentPlayer.toString() + " draw a "+ currentCard);

        if (currentCard instanceof Creature) {
            currentPlayer.playerCreature.add((Creature) currentCard);
            opponent.lifePoint = opponent.getLifePoint() - currentPlayer.playerCreature.size();
            logger.info("The player "+ currentPlayer.toString() +" has " + currentPlayer.energy +" energys and its " + currentPlayer.playerCreature.size() + " creatures attacks and deals " + currentPlayer.playerCreature.size() + " damages to its opponent");
        }
        if (currentCard instanceof Ritual) {
            opponent.lifePoint = opponent.getLifePoint() - currentPlayer.playerCreature.size() - 3;
            logger.info("The " + currentPlayer.playerCreature.size() + " creatures of " + currentPlayer.toString() + " attack and deal " + currentPlayer.playerCreature.size() + " damages to its opponent");
            logger.info(currentPlayer.toString() + " cast a ritual that deals 3 damages to " + opponent.toString());
        }
    }

    public void sortCreatures()
    {
        Collections.sort(playerCreature);
    } // a utiliser dans le systeme d'attaque

    public boolean isDead()
    {
        return lifePoint<=0;
    }

    public int getLifePoint()
    {
        return lifePoint;
    }

    public void winner()
    {
        logger.info(this.toString()+" is the winner!!!\n");
    }

    @Override
    public String toString()
    {
        return " " + name + "(" + getLifePoint() + "pv) ";
    }


}
