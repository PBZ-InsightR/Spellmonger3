package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Abdel on 05/10/2016.
 */
public class BlessingTest {
    @Test
    public void testBlessingEffect()  throws Exception
    {
        Blessing bless = new Blessing();
        Assert.assertEquals(3, bless.getEffect());
    }

}