package edu.insightr.spellmonger;

/**
 * Created by dufou on 25/09/2016.
 */
public class Bear extends Creature {

    public Bear() {
        effect = 3;
        lifePoints = effect;
    }

    @Override
    public String toString() {
        return "Bear : life Point :"+lifePoints+" strength : "+effect;
    }

    public String getname(){return "Bear";}
}

