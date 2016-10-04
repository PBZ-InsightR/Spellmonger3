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


    public static void drawCard(Player currentPlayer, Player opponent, Cards cardPool, Cards displayCard)
    {

        currentPlayer.increaseEnergy(); //on augemente l'energy du joueur actuel a chaque tour
        Card currentCard = cardPool.get(0); //recuperation de la première carte de la pile dans le deck joueur
        cardPool.remove(0); //suppression de cette carte dans le deck joueur
        displayCard.add(currentCard); //ajout de cette carte dans le deck display

        logger.info(currentPlayer.toString() + " draw a "+ currentCard);

        if (currentCard instanceof Creature) { //Si une carte creature est tirée
            currentPlayer.addPlayerCreature(currentCard); //ajout de la creature au deck creature du joueur
            currentPlayer.sortCreatures(); //tri des decks creatures des deux joueurs par ordre de dégats des creatures
            opponent.sortCreatures();

            for (int i = 0; i < currentPlayer.getPlayerCreature().size(); i++) //chaque creature du joueur attaque
            {
                if(opponent.getPlayerCreature().get(i) != null) //une creature adverse s'il y'en a une
                {
                    int degat = currentPlayer.getPlayerCreature().get(i).getEffect() - opponent.getPlayerCreature().get(i).getEffect();
                    if(degat>0) //la creature est plus forte que celle de l'adversaire
                    {
                        opponent.removePlayerCreature(opponent.getPlayerCreature().get(i)); //la creature adverse meurt
                    }
                    else if(degat<0) //la creature est moins forte
                    {
                        currentPlayer.removePlayerCreature(currentPlayer.getPlayerCreature().get(i)); //la creature du joueur meurt
                    }
                    else //elles sont égales
                    {
                        opponent.removePlayerCreature(opponent.getPlayerCreature().get(i)); //elles meurent toutes les deux
                        currentPlayer.removePlayerCreature(currentPlayer.getPlayerCreature().get(i));
                    }
                }
                else //le joueur adverse sinon
                {
                    int opponentLife = (opponent.getLifePoint() - currentPlayer.getPlayerCreature().get(i).getEffect());
                    opponent.setLifePoint(opponentLife);
                    logger.info("The player "+ currentPlayer.toString() +"'s creature attack and deal " + currentPlayer.getPlayerCreature().get(i).getEffect() + " damages to its opponent");
                }
            }
        }
        if (currentCard instanceof Ritual) {  //Si une carte rituel est tirée
            if(currentCard instanceof Blessing)
            {
                int currentLife = (currentPlayer.getLifePoint() + currentCard.getEffect());
                currentPlayer.setLifePoint(currentLife);
                logger.info("The ritual add 3pv to " + currentPlayer.toString());
            }
            else if(currentCard instanceof Curse)
            {
                int currentLife = (opponent.getLifePoint() + currentCard.getEffect());
                opponent.setLifePoint(currentLife);
                logger.info(currentPlayer.toString() + " cast a ritual that deals 3 damages to " + opponent.toString());
            }
            else if(currentCard instanceof EnergyDrain) {
                int currentEnergyLanceur = (currentPlayer.getEnergy() + currentCard.getEffect());
                int currentEnergyReceveur = (opponent.getEnergy() - currentCard.getEffect());
                currentPlayer.setEnergyPoint(currentEnergyLanceur);
                opponent.setEnergyPoint(currentEnergyReceveur);
                logger.info(currentPlayer.toString() + " cast a drain ritual that takes 2 energies from " + opponent.toString() + " and adds them to him");
            }
        }
    }


    @Override
    public String toString()
    {
        return playerList.get(0).toString() + " VS " + playerList.get(1).toString();
    }
}
