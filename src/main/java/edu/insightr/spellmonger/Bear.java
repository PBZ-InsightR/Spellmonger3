package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Bear extends Creature {
    private int lifePoint;
    public Bear() {
        super.effect = 3;
        lifePoint = super.effect;
    }

    @Override
    public String toString() {
        return "Bear : life Point :"+lifePoint+" strength : "+effect;
    }
}

