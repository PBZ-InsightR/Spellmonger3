package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Abdel on 05/10/2016.
 */
public class EnergyDrainTest {
    @Test
    public void testEnergyDrainEffect()  throws Exception
    {
        EnergyDrain drain = new EnergyDrain();
        Assert.assertEquals(2, drain.getEffect());
    }

}