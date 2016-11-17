package edu.insightr.spellmonger;


public class Fox extends Creature {

   public Fox() {
        effect = 1;
        lifePoints = effect;
        energyCost = 1;
    }

    @Override
    public String toString() {
        return "Fox : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Fox";
    }
}
