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

        for (int i = 0; i < 3; i++) {
            Eagle eagle = new Eagle();
            cardPool.add(eagle);
            Lizard lizard = new Lizard();
            cardPool.add(lizard);
        }

        for(int i = 0; i <4 ; i++){
            Fox fox = new Fox();
            cardPool.add(fox);
            Wolf wolf = new Wolf();
            cardPool.add(wolf);
            EnergyDrain energyDrain= new EnergyDrain();
            cardPool.add(energyDrain);
        }

        for(int i = 0; i < 5; i++)
        {
            Bear bear = new Bear();
            cardPool.add(bear);

            Blessing blessing = new Blessing();
            Curse curse = new Curse();
            cardPool.add(blessing);
            cardPool.add(curse);
        }
            Rat rat = new Rat();
            cardPool.add(rat);
            Leviathan leviathan=new Leviathan();
            cardPool.add(leviathan);
            Dragoon dragoon = new Dragoon();
            cardPool.add(dragoon);
            VaultOverclocking vaultOverclocking = new VaultOverclocking();
            cardPool.add(vaultOverclocking);
            Kraken kraken = new Kraken();
            cardPool.add(kraken);



        for(int i = 0; i <2 ; i++){
            Snake snake = new Snake();
            cardPool.add(snake);
        }




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
