package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Blessing extends Ritual {
    public Blessing(int effect) {
        super(effect);
    }

    @Override
    public String toString() {
        return "Blessing : "+super.toString();
    }
}