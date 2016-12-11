package edu.insightr.spellmonger;


public class Dragoon extends Creature{

    public Dragoon(){
        effect = 4;
        lifePoints = effect;
        energyCost = 4;
        capacity=Capacity.FLYING.toString();
    }

    @Override
    public String toString() {
        return "Dragoon : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Dragoon";
    }



}
