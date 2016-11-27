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

    public void createCardPool() {

        for (int i = 0; i < 7; i++) {
            Eagle eagle = new Eagle();
            Fox fox = new Fox();
            cardPool.add(eagle);
            cardPool.add(fox);
        }

        for (int i = 0; i < 6; i++) {
            Wolf wolf = new Wolf();
            cardPool.add(wolf);
        }

        for (int i = 0; i < 4; i++) {
            Bear bear = new Bear();
            cardPool.add(bear);
        }

        for (int i = 0; i < 5; i++) {
            Curse curse = new Curse();
            Blessing blessing = new Blessing();
            EnergyDrain energyDrain = new EnergyDrain();
            cardPool.add(curse);
            cardPool.add(blessing);
            cardPool.add(energyDrain);
        }

        VaultOverclocking vaultOverclocking = new VaultOverclocking();
        cardPool.add(vaultOverclocking);

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
