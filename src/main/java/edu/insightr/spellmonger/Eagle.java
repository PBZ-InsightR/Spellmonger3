package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Eagle extends Creature {

    private int lifePoint;
    public Eagle() {
        super.effect =1;
        lifePoint = effect;
        capacity = "Flying"; 
    }

    @Override
    public String toString() {
        return "Eagle : life Point : "+lifePoint+" strength : "+effect;
    }
}
