package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Bear extends Creature {
    public Bear(int effect) {
        super(effect);
    }

    @Override
    public String toString() {
        return "Bear :"+super.toString();
    }
}
