package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public abstract class Card {
    private int effect;

    public Card(int effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "Effect = "+this.effect;
    }
}
