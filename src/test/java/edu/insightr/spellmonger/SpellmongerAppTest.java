package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpellmongerAppTest {

    @Test
    public void testNextPlayer() {
        Player p1 = new Player("Luc");
        Player p2 = new Player("Naat");
        SpellmongerApp game = new SpellmongerApp(p1, p2);
        Player current = p1;
        Assert.assertEquals(game.nextPLayer(), p2); // next player of p1 is p2
        Assert.assertEquals(game.nextPLayer(), p1); // next player of p2 is p1
    }

    public void testDrawCard() {
        Player p1 = new Player("Luc");
        Player p2 = new Player("Naat");
        SpellmongerApp game = new SpellmongerApp(p1, p2);
        Player current = p1;
        Player oppenent = p2;
        int sizeCard = current.getCards().size() - 1;
        int sizeDiscard = current.getCards().size() + 1;
        int sizePlayersCreature = current.getPlayerCreature().size();
        int currentLifePoints = current.getLifePoint();
        int oppenentLifePoints = oppenent.getLifePoint();
        int currentEnergy = current.getEnergy();
        int oppenentEnergy = oppenent.getEnergy();
        Card c = current.getCards().get(0);

        game.drawCard(current, oppenent, current.getCards(), current.getDiscards());

        Assert.assertEquals(sizeCard - 1, current.getCards().size()); // card pool decreased
        Assert.assertEquals(sizeDiscard - 1, current.getDiscards().size()); // discard pool increased
        Assert.assertEquals(c, oppenent.getDiscards().get(0)); // check if the first card in the ex-deck pool, is the same of the first one in discard deck
        if (c instanceof Creature) {
            Assert.assertEquals(current.getPlayerCreature().size(), sizePlayersCreature + 1); // check if playerCreatures has increased
            Assert.assertEquals(c, current.getPlayerCreature().get(sizePlayersCreature)); // check if the last item in the playerCreautres is the card
        }
        if (c instanceof Blessing) {

            Assert.assertEquals(current.getLifePoint(), currentLifePoints + 3); // check if player's Life points inscreased  by 3
        }
        if (c instanceof Curse) {
            Assert.assertEquals(oppenent.getLifePoint(), oppenentLifePoints - 3); // check if oppenent's Life points dicreased  by 3
        }
        if (c instanceof EnergyDrain) {
            Assert.assertEquals(oppenent.getEnergy(), oppenentEnergy - 2); // check if current's energy points increased  by 2
            Assert.assertEquals(current.getEnergy(), currentEnergy + 2); // check if oppenent's energy points discreased  by 2
        }


    }
}