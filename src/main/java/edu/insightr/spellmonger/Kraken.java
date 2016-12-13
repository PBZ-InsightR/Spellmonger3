package edu.insightr.spellmonger;


public class Kraken extends Creature{

    public Kraken(){
        effect = 4;
        lifePoints = effect;
        energyCost = 4;
        capacity=Capacity.CATCH.toString();
    }

    @Override
    public String toString() {
        return "Kraken : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Kraken";
    }

}
