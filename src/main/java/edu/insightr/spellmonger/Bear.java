package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Bear extends Creature {

    public Bear() {
        super.effect = 3;
    }

    @Override
    public String toString() {
        return "Bear "+ super.toString();
    }
}

