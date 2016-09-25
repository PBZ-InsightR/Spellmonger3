package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Bear extends Creature {

    private int effect;

    public Bear() {
        this.effect = 3;
    }

    @Override
    public String toString() {
        return "Bear";
    }
}
