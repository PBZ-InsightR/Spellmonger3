package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;


public class FoxTest {
    @Test
    public void testFoxEffect() throws Exception {
        Fox fox = new Fox();
        Assert.assertEquals(1, fox.getEffect());
    }

    @Test
    public void testFoxLifePoints() throws Exception {
        Fox fox = new Fox();
        Assert.assertEquals(1.0, fox.getEffect(), 0.0);
    }

}