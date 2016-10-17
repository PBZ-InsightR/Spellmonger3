package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

public class EagleTest {
    @Test
    public void testEagleEffect() throws Exception {
        Eagle eagle = new Eagle();
        Assert.assertEquals(1, eagle.getEffect());
        Assert.assertEquals(1, ((Card) eagle).getEffect());
    }

    @Test
    public void testLifePoints() throws Exception {
        Eagle eagle = new Eagle();
        Assert.assertEquals(1.0, eagle.getLifePoints(), 0.0);
    }

    @Test
    public void testCapacity() {
        Eagle eagle = new Eagle();
        Assert.assertEquals("Flying", eagle.getCapacity());
    }
}