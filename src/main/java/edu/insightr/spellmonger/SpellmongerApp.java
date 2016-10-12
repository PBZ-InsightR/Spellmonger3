package edu.insightr.spellmonger;


//import javafx.scene.control.RadioMenuItem;

import org.apache.log4j.Logger;

import java.util.ArrayList;
//import java.util.List;


/**
 * Created by Natiassa on 25/09/2016.
 * il manque les consctructeurs vide pour les ours,aigle ....
 * c'est pour ça qu'il y a des erreurs.
 */
public class SpellmongerApp {
    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Player> playerList = new ArrayList<>(2);
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


    public void drawCard(Player currentPlayer, Player opponent, Deck cardPool, Deck displayCard)
    {
        currentPlayer.increaseEnergy();//On augmente l'energy du joueur
        Card currentCard = cardPool.get(0);

        logger.info(currentPlayer.toString() + " draw a "+ currentCard.toString());

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
                opponent.setLifePoint(opponent.getLifePoint()-currentCard.getEffect());
                logger.info(currentPlayer.toString()+" cast a ritual that deals 3 damages to "+opponent.toString());
            }
            else
            {
                if(opponent.getEnergy() >2 )
                {
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + currentCard.getEffect());
                    opponent.setEnergyPoint(opponent.getEnergy() - currentCard.getEffect());
                    logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 2 energies from " + opponent.toString());
                }
                else if(opponent.getEnergy() == 1)
                {
                    currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + 1);
                    opponent.setEnergyPoint(opponent.getEnergy() -1);
                    logger.info(currentPlayer.toString() + " cast a drain energy ritual that takes 1 energie from " + opponent.toString());
                }
                else if(opponent.getEnergy() == 0)
                {
                    logger.info(currentPlayer.toString() + " cast a drain energy ritual but " + opponent.toString() + " have no energy to steal");
                }
            }
        }
        cardPool.remove(0);
        displayCard.add(currentCard);
    }

    @Override
    public String toString()
    {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
