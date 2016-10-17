package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlessingTest {
    @Test
    public void testBlessingEffect() throws Exception {
        Blessing bless = new Blessing();
        Assert.assertEquals(3, bless.getEffect(), 0.0);
        Assert.assertEquals(3.0, ((Card) bless).getEffect(), 0.0);
    }
}