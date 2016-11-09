package edu.insightr.spellmonger;

public abstract class Card {

    int effect;
    protected int energyCost;

    public Card() {
        // TODO : why an empty construtor is necessary ?
    }

    public int getEffect() {
        return effect;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    @Override
    public String toString() {
        return "Effect = " + this.effect;
    }

    public String getName() {
        return "Abstract";
    }
}
