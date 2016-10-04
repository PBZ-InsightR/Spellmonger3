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
    public void setPlayerCreature(ArrayList<Creature> newOne)
    {
        playerCreature=new ArrayList<>(newOne);
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


    public void  PlayerAttack(Player opponent)
    {
        ArrayList<Creature> newOne= new ArrayList<>(this.playerCreature);
        ArrayList<Creature> newoneOppenet= new ArrayList<>(opponent.getPlayerCreature());


        for (int i = 0; i < this.getPlayerCreature().size(); i++) {

            if (!this.getPlayerCreature().isEmpty() && !opponent.getPlayerCreature().isEmpty())// si il y'a une creature des deux coté du board
            {
                int degat = this.getPlayerCreature().get(i).getEffect() - opponent.getPlayerCreature().get(i).getEffect(); // recup des dégats la diff entre la force des deux creatures

                if (degat == 0) // si les deux créature ont la même force les deux meurt
                {
                    newOne.remove(i);
                    newoneOppenet.remove(i);
                    logger.info(this.toString() + " " + this.getPlayerCreature().get(i).toString() + " and " + opponent.toString() + " " + opponent.getPlayerCreature().get(i).toString() + " have the same strength and die both ");
                } else if (degat > 0)// si la creature du joueur courant est plus forte elle tue celle de l'adversaire
                {
                    newoneOppenet.remove(i);
                    logger.info(this.toString() + " " + this.getPlayerCreature().get(i).toString() + " still alive and " + opponent.toString() + " " + opponent.getPlayerCreature().get(i).toString() + "die");
                } else // si la creature de l'opposant est plus forte elle tue celle du joueur courant
                {
                    newOne.remove(i);
                    logger.info(opponent.toString() + " " + opponent.getPlayerCreature().get(i).toString() + " still alive and " + this.toString() + " " + this.getPlayerCreature().get(i).toString() + "die");
                }
            } else if (!this.getPlayerCreature().isEmpty() && opponent.getPlayerCreature().isEmpty())// si le board de l'opposant ne contient plus de creature les creatures du joueur courant attaque l'opposant
            {
                opponent.setLifePoint(opponent.getLifePoint() - this.getPlayerCreature().get(i).getEffect());

            } else// si le joueur courant n'a plus de creature et qu'il en reste à l'opposant l'attaque du joueur s'arrête
            {
                break;
            }
        }

        this.setPlayerCreature(newOne);
        opponent.setPlayerCreature(newoneOppenet);
    }



    @Override
    public String toString()
    {
        return " " + name + "(" + getLifePoint() + "pv|" + getEnergy() + "energy)";
    }
}
