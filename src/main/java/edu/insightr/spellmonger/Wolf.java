package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Wolf extends Creature {

    private int effect;

    public Wolf() {
        this.effect = 2;
    }

    @Override
    public String toString() {
        return "Wolf : ";
    }
}