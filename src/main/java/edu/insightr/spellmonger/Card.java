package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
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

    public String getName(){return "Card";}
}
