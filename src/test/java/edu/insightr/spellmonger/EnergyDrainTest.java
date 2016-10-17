package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnergyDrainTest {
    @Test
    public void testEnergyDrainEffect() throws Exception {
        EnergyDrain drain = new EnergyDrain();
        Assert.assertEquals(2, drain.getEffect(), 0.0);
        Assert.assertEquals(2.0, ((Card) drain).getEffect(), 0.0);
    }

}