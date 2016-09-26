package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public abstract class Creature extends Card

{
    protected String capacity;
    public Creature()
    {
    }
    public String getCapacity()
    {
        return capacity;
    }
}