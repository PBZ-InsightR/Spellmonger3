package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {

    private static final Logger logger = Logger.getLogger(SpellmongerApp.class);
    private ArrayList<Card> cardPool = new ArrayList<>();

    public Deck() {
    }

    public Deck(String name) {
        createCardPool();
        logger.info(name + "'s cardpool created");
    }

    public Deck(ArrayList<Card> other) {
        cardPool = new ArrayList<>(other);
    }

    public ArrayList<Card> getCardPool() {
        return cardPool;
    }

    public int size() {
        return cardPool.size();
    }

    public Card get(int index) {
        return cardPool.get(index);
    }

    public void add(Card c) {
        cardPool.add(c);
    }

    public Card remove(int index) {
        return cardPool.remove(index);
    }

    private void createCardPool() {

        for (int i = 0; i < 3; i++) {
            cardPool.add(new Eagle());
            cardPool.add(new Lizard());
            cardPool.add(new Bear());
            cardPool.add(new Fox());
            cardPool.add(new Wolf());
        }
        for (int i = 0; i < 2; i++) {
            cardPool.add(new Snake());
            cardPool.add(new Dragoon());
            cardPool.add(new Kraken());
            cardPool.add(new Rat());
        }
        cardPool.add(new Leviathan());


        for (int i = 0; i < 5; i++) {
            cardPool.add(new EnergyDrain());
            cardPool.add(new Blessing());
            cardPool.add(new Curse());
        }

        cardPool.add(new VaultOverclocking());
        Collections.shuffle(cardPool);
    }

    public void clearCards() {
        cardPool.clear();
    }

    public Deck clone() {
        Deck d = new Deck();
        d.cardPool = (ArrayList<Card>) this.cardPool.clone();
        return d;
    }
}
