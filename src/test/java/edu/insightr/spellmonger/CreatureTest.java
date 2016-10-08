package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

public class CreatureTest {
    @Test
    public void testCreatureCompareTo() throws Exception {
        Bear bear1 = new Bear();
        Bear bear2 = new Bear();
        Eagle eagle = new Eagle();

        Assert.assertEquals(-1, bear1.compareTo(eagle));
        Assert.assertEquals(0, bear1.compareTo(bear2));
        Assert.assertEquals(1, eagle.compareTo(bear1));
    }
}