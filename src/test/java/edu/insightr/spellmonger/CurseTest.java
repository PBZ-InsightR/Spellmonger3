package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Abdel on 05/10/2016.
 */
public class CurseTest {
    @Test
    public void testCurseEffect()  throws Exception
    {
        Curse curse = new Curse();
        Assert.assertEquals(3.0, curse.getEffect(),0.0);
        Assert.assertEquals(3.0,((Card)curse).getEffect(),0.0);
    }

}