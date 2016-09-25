package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Eagle extends Creature {
    public Eagle(int effect) {
        super(effect);
    }

    @Override
    public String toString() {
        return "Eagle :"+super.toString();
    }
}
