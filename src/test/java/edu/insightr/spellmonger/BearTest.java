package edu.insightr.spellmonger;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Abdel on 05/10/2016.
 */
public class BearTest {
    @Test
    public void testBearEffect()  throws Exception {
        Bear bear = new Bear();
        Assert.assertEquals(3, bear.getEffect());
        Assert.assertEquals(3,((Card)bear).getEffect());
    }

    @Test
    public void testBearLifePoints() throws Exception {
        Bear bear = new Bear();
        Assert.assertEquals(3.0,bear.getLifePoints(),0.0);
    }
}