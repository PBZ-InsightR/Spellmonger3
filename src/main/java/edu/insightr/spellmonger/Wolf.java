package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Wolf extends Creature {

    public Wolf() {
        effect = 2;
        lifePoints = effect;
    }
    @Override
    public String toString() {
        return "Wolf : life Point : "+lifePoints+" strength : " +effect;
    }
    @Override
    public String getName(){return "Wolf";}
}