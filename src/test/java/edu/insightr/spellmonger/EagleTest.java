package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Abdel on 05/10/2016.
 */
public class EagleTest {
    @Test
    public void testEagleEffect() throws Exception {
        Eagle eagle = new Eagle();
        Assert.assertEquals(2,eagle.getEffect());

        Assert.assertEquals(2,((Creature)eagle).getEffect());
    }
}