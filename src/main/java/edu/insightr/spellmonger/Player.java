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
    private ArrayList<Creature> playerCreature;

    public Player(String name)
    {
        this.playerCreature = new ArrayList<Creature>();
        this.name = name;
        lifePoint = 20;
    }

    public void DrawCard(Player currentPlayer, Player opponent, List<Card> cardPool, List<Card> displayCard)
    {
        Card currentCard = cardPool.get(0); //Recupere la premiere carte de la pile
        cardPool.remove(0);
        displayCard.add(currentCard);

        logger.info(currentPlayer.toString() + " draw a "+ currentCard);

        if (currentCard instanceof Creature) {
            currentPlayer.playerCreature.add((Creature) currentCard);
            opponent.lifePoint = opponent.GetLifePoint() - currentPlayer.playerCreature.size();
            logger.info("The " + currentPlayer.playerCreature.size() + " creatures of " + currentPlayer.toString() + " attack and deal " + currentPlayer.playerCreature.size() + " damages to its opponent");
        }
        if (currentCard instanceof Ritual) {

            opponent.lifePoint = opponent.GetLifePoint() - currentPlayer.playerCreature.size() - 3;
            logger.info("The " + currentPlayer.playerCreature.size() + " creatures of " + currentPlayer.toString() + " attack and deal " + currentPlayer.playerCreature.size() + " damages to its opponent");
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

    @Override
    public String toString()
    {
        return "Player " + name + "(" + GetLifePoint() + "pv) ";
    }

}
