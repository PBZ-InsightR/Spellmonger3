package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {

    @Test
    public void testSize() throws Exception {
        Deck d = new Deck("Test");
        Assert.assertEquals(40.0, d.size(), 0.1);

        Deck d2 = new Deck();
        Assert.assertEquals(0.0, d2.size(), 0.1);

        ArrayList<Card> temp = new ArrayList<>();
        Bear b = new Bear();
        temp.add(b);
        temp.add(new Bear());
        temp.add(new Bear());
        temp.add(new Curse());
        temp.add(new Wolf());
        Deck d3 = new Deck(temp);
        Assert.assertEquals(temp.size(), d3.size(), 0.1);
    }

    @Test
    public void testGetValue() {
        ArrayList<Card> temp = new ArrayList<>();
        Bear b = new Bear();
        temp.add(b);
        temp.add(new Bear());
        temp.add(new Bear());
        temp.add(new Curse());
        temp.add(new Wolf());
        Deck d3 = new Deck(temp);
        Assert.assertEquals(b, d3.get(0)); // check if the first item was the bear b
    }

    @Test
    public void testGetCardPool() {
        Deck d = new Deck("Test");
        ArrayList<Card> cards = d.getCardPool();
        for (int i = 0; i < cards.size(); i++) {
            Assert.assertEquals(cards.get(i), d.get(i));
        }
    }

    @Test
    public void testAdd() {
        ArrayList<Card> test = new ArrayList<>();
        test.add(new Bear());
        test.add(new Bear());
        test.add(new Bear());
        test.add(new Curse());
        test.add(new Wolf());
        Deck d3 = new Deck(test);
        Bear b = new Bear();
        d3.add(b);
        Assert.assertEquals(b, d3.get(d3.size() - 1)); // check if last one is the Bear b
    }

    @Test
    public void testRemove() {
        ArrayList<Card> test = new ArrayList<>();
        test.add(new Bear());
        test.add(new Bear());
        test.add(new Bear());
        test.add(new Curse());
        test.add(new Wolf());
        Bear b = new Bear();
        test.add(b);
        test.add(new Wolf());
        Deck d3 = new Deck(test);
        d3.remove(d3.size() - 1);
        Assert.assertEquals(b, d3.get(d3.size() - 1)); // check if last one is actually yhe Bear b (the Wolf have been deleted
    }

    @Test
    public void testClear() {
        Deck d = new Deck("Test");
        d.clearCards();
        Assert.assertEquals(0.0, d.size(), 0.1);
    }


}