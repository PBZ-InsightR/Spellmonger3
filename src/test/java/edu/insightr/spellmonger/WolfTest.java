package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

public class WolfTest {
    @Test
    public void testWolfEffect() throws Exception {
        Wolf wolf = new Wolf();
        Assert.assertEquals(2, wolf.getEffect());
        Assert.assertEquals(2, ((Card) wolf).getEffect());
    }

    @Test
    public void testLifePoints() throws Exception {
        Wolf wolf = new Wolf();
        Assert.assertEquals(2.0, wolf.getLifePoints(), 0.0);
    }

}