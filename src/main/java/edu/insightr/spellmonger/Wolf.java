package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Wolf extends Creature {
    public Wolf(int effect) {
        super(effect);
    }

    @Override
    public String toString() {
        return "Wolf : "+super.toString();
    }
}