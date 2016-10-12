package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class PlayerTest {
    @Test
    public final void reCreateCardPoolTest() {
        Player player = new Player("Paul");
        player.getCards().clearCards();
        Assert.assertEquals(0, player.getCards().size());
        Deck discard = player.getDiscards();
        discard.clearCards();
        discard.add(new Wolf());
        player.reCreateCardPool();
        Assert.assertEquals("reCreateCardPoolTest", 1, player.getCards().size());
    }

    @Test
    public final void attackTest() {

        Player player = new Player("Toto");
        Player opponent = new Player("Titi");

        player.getCards().clearCards();
        opponent.getCards().clearCards();

        List<Creature> playerCreatures = player.getPlayerCreature();
        List<Creature> opponentCreatures = opponent.getPlayerCreature();


        //Test d'une attaque avec 1 carte chacun et dont la carte est de même force

        Bear ours1 = new Bear();
        player.addPlayerCreature(ours1);
        Assert.assertEquals("J'ai une carte ours chez le player", 1, playerCreatures.size());

        Bear ours2 = new Bear();
        opponent.addPlayerCreature(ours2);
        Assert.assertEquals("J'ai une carte ours chez l'opponent", 1, opponentCreatures.size());


        player.attack(opponent);
        Assert.assertEquals("Attaque force égale avec une carte chacun", 0, playerCreatures.size());
        Assert.assertEquals("Attaque force égale avec une carte chacun", 0, opponentCreatures.size());

        //Test d'une attaque par des créatures différentes
        Bear ours3 = new Bear();
        player.addPlayerCreature(ours3);
        Assert.assertEquals("J'ai une carte ours chez le player", 1, playerCreatures.size());

        Eagle aigle1 = new Eagle();
        opponent.addPlayerCreature(aigle1);
        Assert.assertEquals("J'ai une carte aigle chez l'adversaire", 1, opponentCreatures.size());

        //Test d'une attaque avec 1 cartes chacun et dont les cartes sont de forces différentes
        player.attack(opponent);
        Assert.assertEquals("Attaque force différente avec 1 cartes chacun créature player plus forte", 1, playerCreatures.size());
        Assert.assertEquals("Attaque force différente avec 1 cartes chacun, créature adversaire moins forte", 0, opponentCreatures.size());

        //Test d'une attaque avec 1 carte chez le player et rien chez l'adversaire
        player.attack(opponent);
        Assert.assertEquals("Attaque force différente avec 1 cartes player", 1, playerCreatures.size());
        Assert.assertEquals("Attaqque force différente avec 0 carte adversaire ", 17, opponent.getLifePoint());

        //Test lorsque le player n'a plus de carte mais que l'adversaire en a une
        playerCreatures.remove(0);
        Assert.assertEquals("Je n'ai pas de carte ", 0, playerCreatures.size());

        Wolf loup1 = new Wolf();
        opponent.addPlayerCreature(loup1);
        Assert.assertEquals("J'ai une carte loup chez l'adversaire", 1, opponentCreatures.size());


        //Test d'une attaque avec 0 carte chez le player mais aucune chez l'adversaire
        player.attack(opponent);
        //Etant donné qu'il y a un break, test si ce qu'on a au début, on l'a a la fin
        Assert.assertEquals("Attaque force différente, 0 carte chez le player", 0, playerCreatures.size());
        Assert.assertEquals("Attaque force différente avec une carte chez l'adversaire", 1, opponentCreatures.size());

        //Test d'une attaque avec 1 carte chez le player moins forte que celle de l'adversaire
        Eagle aigle4 = new Eagle();
        player.addPlayerCreature(aigle4);
        Assert.assertEquals("J'ai une carte aigle chez le player", 1, opponentCreatures.size());

        player.attack(opponent);
        Assert.assertEquals("Attaque force différente, 1 carte chez le player moins forte", 0, playerCreatures.size());
        Assert.assertEquals("Attaque force différente, 1 carte chez l'adversaire plus forte", 1, opponentCreatures.size());


    }


}