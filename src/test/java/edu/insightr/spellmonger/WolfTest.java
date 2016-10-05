package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Abdel on 05/10/2016.
 */
public class WolfTest {
    @Test
    public void testWolfEffect() throws Exception {
        Wolf wolf = new Wolf();
        Assert.assertEquals(2,wolf.getEffect());

        Assert.assertEquals(2,((Creature)wolf).getEffect());
    }
}