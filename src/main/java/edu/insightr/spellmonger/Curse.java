package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Curse extends Ritual {

    public Curse()
    {
        super.effect = 3;
    }

    @Override
    public String toString()
    {
        return "Curse";
    }
}
