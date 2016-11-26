package edu.insightr.spellmonger;

import edu.insightr.sample.ControllerPlay;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by badre on 19/10/2016.
 */
public class ControllerTest {
    @Test
    public void draw1Test() throws Exception{
        ControllerPlay c = new ControllerPlay();
        int nb_hand = c.getPlayer1().getHand().size();
        c.draw_player_1();
        Assert.assertEquals(nb_hand,c.getPlayer1().getHand().size(),0.0);

    }
    @Test
    public void draw2Test() throws Exception {

    }
}
