package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

public class SpellmongerAppTest {

    @Test
    public void testNextPlayer() {
        SpellmongerApp game = new SpellmongerApp("Luc", "Naat");
        Player p1 =game.getPlayer(0);
        Player p2 = game.getPlayer(1);
        Assert.assertEquals(game.nextPLayer(p1), p2); // next player of p1 is p2
        Assert.assertEquals(game.nextPLayer(p2), p1); // next player of p2 is p1
    }

    @Test
    public void testDrawCard() {
        SpellmongerApp game = new SpellmongerApp("Luc", "Nat");;
        Player current = game.getPlayer(0);
        Player oppenent = game.getPlayer(1);
        int sizeCard = current.getCards().size();
        current.drawCard();
        int indexChoisi=0;
        game.playCard(current, oppenent,indexChoisi); // juste la premiere par défaut
        Assert.assertEquals(sizeCard - 1, current.getCards().size()); // card pool decreased
    }
}