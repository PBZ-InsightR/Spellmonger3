package edu.insightr.spellmonger;

public abstract class Card {

    protected int effect;

    public Card() {
    }

    public int getEffect() {
        return effect;
    }


    @Override
    public String toString() {
        return "Effect = " + this.effect;
    }

    public String getName() {
        return "Card";
    }
}
