package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Wolf extends Creature {

    private int lifePoint;
    public Wolf() {
        super.effect = 2;
        lifePoint = effect;
    }
    @Override
    public String toString() {
        return "Wolf : life Point : "+lifePoint+" strength : " +effect;
    }
}