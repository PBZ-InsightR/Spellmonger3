package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Blessing extends Ritual
{
    private int effect;

    public Blessing() {
        this.effect = -3;
    }

    @Override
    public String toString() {
        return "Blessing ";
    }
}
