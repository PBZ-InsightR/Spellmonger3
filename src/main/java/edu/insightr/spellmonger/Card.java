package edu.insightr.spellmonger;

public abstract class Card {

    protected int effect;
    public int energyCost;

    public Card() {
    }

    public int getEnergyCost(){return energyCost;}
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
