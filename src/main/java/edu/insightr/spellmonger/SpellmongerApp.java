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
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Player> playerList = new ArrayList<Player>(2);
    private int counter;

    public SpellmongerApp(Player player1,Player player2){
        playerList.add(player1);
        playerList.add(player2);
        Cards cardsPlayer1 = new Cards("cardsPlayer1");
        cardsPlayer1.createCardPool();
        Cards cardsPlayer2 = new Cards("cardsPlayer2");
        cardsPlayer2.createCardPool();

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


    public void drawCard(Player currentPlayer, Player opponent,Cards cardPool, Cards displayCard)
    {
        currentPlayer.increaseEnergy();//On augmente l'energy du joueur
        Card currentCard = cardPool.get(0);

        logger.info(currentPlayer.toString() + " draw a"+ currentCard.toString());

        if(currentCard instanceof Creature)
        {
            currentPlayer.addPlayerCreature(currentCard);
            currentPlayer.sortCreatures();
        }
        else if(currentCard instanceof Ritual)
        {
            if(currentCard instanceof Blessing)
            {
                currentPlayer.setLifePoint(currentPlayer.getLifePoint()+currentCard.getEffect());
                logger.info("The Blessing Ritual add 3 Life Point to "+currentPlayer.toString());
            }
            else if(currentCard instanceof Curse)
            {
                opponent.setLifePoint(opponent.getLifePoint()+currentCard.getEffect());
                logger.info(currentPlayer.toString()+" cast a ritual that deals 3 damages to "+opponent.toString());
            }
            else
            {
                currentPlayer.setEnergyPoint(currentPlayer.getEnergy()+currentCard.getEffect());
                opponent.setEnergyPoint(opponent.getEnergy() - currentCard.getEffect());
                logger.info(currentPlayer.toString()+" cast a drain energy ritual that takes 2 energies from "+opponent.toString());
            }
        }
        cardPool.remove(0);
        displayCard.add(currentCard);
    }

    public void  PlayerAttack(Player currentPlayer, Player opponent)
    {
        logger.info("\n\n\n"+currentPlayer.getPlayerCreature().size());
        for(int i = 0; i < currentPlayer.getPlayerCreature().size(); i++)
        {
            int j = 0;
            int k = 0;
            if(currentPlayer.getPlayerCreature().get(j) != null && opponent.getPlayerCreature().get(k) != null)// si il y'a une creature des deux coté du board
            {
                int degat = currentPlayer.getPlayerCreature().get(j).effect - opponent.getPlayerCreature().get(k).effect; // recup des dégats la diff entre la force des deux creatures

                if(degat == 0) // si les deux créature ont la même force les deux meurt
                {
                    currentPlayer.getPlayerCreature().remove(j);
                    opponent.getPlayerCreature().remove(k);
                    logger.info(currentPlayer.toString()+" "+ currentPlayer.getPlayerCreature().get(i).toString()+ " and "+opponent.toString()+" "+opponent.getPlayerCreature().get(i).toString()+" have the same strength and die both ");
                }
                else if(degat > 0)// si la creature du joueur courant est plus forte elle tue celle de l'adversaire
                {
                    opponent.getPlayerCreature().remove(k);
                    logger.info(currentPlayer.toString() + " "+ currentPlayer.getPlayerCreature().get(i).toString()+" still alive and "+opponent.toString()+" "+opponent.getPlayerCreature().get(i).toString() + "die");
                    k++;
                }
                else // si la creature de l'opposant est plus forte elle tue celle du joueur courant
                {
                    currentPlayer.getPlayerCreature().remove(j);
                    logger.info(opponent.toString() + " "+ opponent.getPlayerCreature().get(i).toString()+" still alive and "+currentPlayer.toString()+" "+currentPlayer.getPlayerCreature().get(i).toString() + "die");
                    j++;
                }
            }
            else if(currentPlayer.getPlayerCreature().get(j) != null && opponent.getPlayerCreature().get(k) == null)// si le board de l'opposant ne contient plus de creature les creatures du joueur courant attaque l'opposant
            {
                opponent.setLifePoint(opponent.getLifePoint() - currentPlayer.getPlayerCreature().get(j).effect);
                j++;
            }
            else// si le joueur courant n'a plus de creature et qu'il en reste à l'opposant l'attaque du joueur s'arrête
            {
                break;
            }
        }
    }


    @Override
    public String toString()
    {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
