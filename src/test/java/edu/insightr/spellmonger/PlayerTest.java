package edu.insightr.spellmonger;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by futura on 05/10/2016.
 */
public class PlayerTest {

        @Test
        public final void reCreateCardPoolTest(){
            final Logger logger = Logger.getLogger(SpellmongerApp.class);
            Player player = new Player("Paul");
            player.getCards().clearCards();
            Assert.assertEquals(0,player.getCards().size());  // first one expected, second one what you have.
            //logger.info(player.getCards().size());
            player.reCreateCardPool();
            Assert.assertEquals("reCreateCardPoolTest",70,player.getCards().size());
        }

        @Test
        public final void playerAttackTest(){

            Player player = new Player("Ahmed");
            Player opponent = new Player("Moussa");

            player.getCards().clearCards();
            opponent.getCards().clearCards();

            Deck deckPlayer = player.getCards();
            List<Card> cardsPlayer = deckPlayer.getCardPool();
            List<Creature> playerCreatures = player.getPlayerCreature();

            Bear ours1= new Bear();
            player.addPlayerCreature(ours1);
            Assert.assertEquals("J'ai une carte ours chez le player",1,playerCreatures.size());

            Deck deckOppenent = opponent.getCards();
            List<Card> cardsOppenent = deckOppenent.getCardPool();
            List<Creature> opponentCreatures = opponent.getPlayerCreature();

            Bear ours2= new Bear();
            opponent.addPlayerCreature(ours2);
            Assert.assertEquals("J'ai une carte ours chez l'opponent",1,opponentCreatures.size());

            //Test d'une attaque avec 1 carte chacun et dont la carte est de même force(test qui ne marche pas)
            //player.attack(opponent);
            //Assert.assertEquals("Attaque force égale avec une carte chacun",0,cardsPlayer.size());
            //Assert.assertEquals("Attaque force égale avec une carte chacun",0,opponentCreatures.size());*/

            //Ajout d'une 2eme créature de même force
            Eagle aigle1= new Eagle();
            player.addPlayerCreature(aigle1);
            Assert.assertEquals("J'ai une carte ours et une carte aigle chez le player",2,playerCreatures.size());

            Eagle aigle2= new Eagle();
            opponent.addPlayerCreature(aigle2);
            Assert.assertEquals("J'ai une carte ours et aigle chez l'opponent",2,opponentCreatures.size());

            //Test d'une attaque avec 2 cartes chacun et dont les cartes sont de même force
            player.attack(opponent);
            Assert.assertEquals("Attaque force égale avec 2 cartes chacun",1,playerCreatures.size());
            Assert.assertEquals("Attaque force égale avec 2 cartes chacun",1,opponentCreatures.size());

            //Test d'une attaque par des créatures différentes
            //Vider les 2 listes
            player.getCards().clearCards();
            opponent.getCards().clearCards();
            Assert.assertEquals("J'ai une carte loup chez le player",0,cardsPlayer.size());

            Wolf loup1= new Wolf();
            player.addPlayerCreature(loup1);
            //Assert.assertEquals("J'ai une carte loup chez le player",1,playerCreatures.size());


            opponent.addPlayerCreature(aigle1);
            Assert.assertEquals("J'ai une carte aigle chez l'adversaire",1,opponentCreatures.size());

            //Test d'une attaque avec 1 carte chacun et dont la carte est de force différente
            player.attack(opponent);
            Assert.assertEquals("Attaque force égale avec une carte chacun",1,playerCreatures.size());
            Assert.assertEquals("Attaque force égale avec une carte chacun",0,opponentCreatures.size());

        }


}