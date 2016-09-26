package edu.insightr.spellmonger;

import org.apache.log4j.Logger;
import sun.font.CreatedFontTracker;

import java.util.List;

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

    public Player(String name) {
        this.playerCreature = new ArrayList<Creature>();
        this.name = name;
        lifePoint = 20; // Comment acceder à cette variable
    }

    public boolean isDead()
    {
        if(lifePoint <= 0){ return true; }
        else{ return false; }
    }

    public ArrayList<Creature> GetPlayerCreature(){ return playerCreature;}

    public void AddCreature(Creature creature)
    {
        playerCreature.add(creature);
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
