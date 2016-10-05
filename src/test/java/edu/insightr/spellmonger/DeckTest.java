package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Abdel on 05/10/2016.
 */
public class DeckTest {

    @Test
    public void testSize() throws Exception {
        Deck d = new Deck("Test");
        Assert.assertEquals(70.0, d.size(), 0.1);

        Deck d2 = new Deck();
        Assert.assertEquals(0.0, d2.size(), 0.1);

        ArrayList<Card> temp= new ArrayList<>();
        Bear b=new Bear();
        temp.add(b);
        temp.add(new Bear());
        temp.add(new Bear());
        temp.add(new Curse());
        temp.add(new Wolf());
        Deck d3=new Deck(temp);
        Assert.assertEquals(temp.size(), d3.size());
    }

    @Test
    public void testGetCardPool() {
        Deck d=new Deck("Test");
        Assert.assertEquals(d.size(),d.getCardPool().size());
    }

    @Test
    public void testGet(){
        ArrayList<Card> temp= new ArrayList<>();
        Bear b=new Bear();
        temp.add(b);
        temp.add(new Bear());
        temp.add(new Bear());
        temp.add(new Curse());
        temp.add(new Wolf());
        Deck d3=new Deck(temp);
        Assert.assertEquals(b,d3.get(0));
    }





}