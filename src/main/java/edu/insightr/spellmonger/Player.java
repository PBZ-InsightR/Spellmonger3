package edu.insightr.spellmonger;

import org.apache.log4j.Logger;
import sun.font.CreatedFontTracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ValentinDuph on 25/09/2016.
 */
public class Player {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private String name;
    private int lifePoint;
    private int energy;
    private ArrayList<Creature> playerCreature;

    public Player(String name)
    {
        this.playerCreature = new ArrayList<Creature>();
        this.name = name;
        lifePoint = 20; // Comment acceder à cette variable
        energy = 0;

    }

    public void DrawCard(Player currentPlayer, Player opponent, List<Card> cardPool, List<Card> displayCard)
    {
        currentPlayer.energy++;
        Card currentCard = cardPool.get(0); //Recupere la premiere carte de la pile
        cardPool.remove(0);
        displayCard.add(currentCard);

        logger.info(currentPlayer.toString() + " draw a "+ currentCard);

        if (currentCard instanceof Creature) {
            currentPlayer.playerCreature.add((Creature) currentCard);
            opponent.lifePoint = opponent.GetLifePoint() - currentPlayer.playerCreature.size();
            logger.info("The player "+ currentPlayer.toString() +" has " + currentPlayer.energy +" energys and its " + currentPlayer.playerCreature.size() + " creatures attacks and deals " + currentPlayer.playerCreature.size() + " damages to its opponent");
        }
        if (currentCard instanceof Ritual) {

            opponent.lifePoint = opponent.GetLifePoint() - currentPlayer.playerCreature.size() - 3;
            logger.info("The player "+ currentPlayer.toString() +" has " + currentPlayer.energy +"energys and its " + currentPlayer.playerCreature.size() + " creatures attacks and deals " + currentPlayer.playerCreature.size() + " damages to its opponent");
            logger.info(currentPlayer.toString() + " cast a ritual that deals 3 damages to " + opponent.toString());
        }
    }

    public boolean isDead()
    {
        if(lifePoint <= 0){ return true; }
        else{ return false; }
    }

    public int GetLifePoint()
    {
        return lifePoint;
    }

    public void winner()
    {
        logger.info(this.name+" is the winner!!!\n");
    }
    @Override
    public String toString()
    {
        return "Player " + name + "(" + GetLifePoint() + "pv) ";
    }

}
