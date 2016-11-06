package edu.insightr.spellmonger;

class Bear extends Creature {

    Bear() {
        effect = 3;
        lifePoints = effect;
        energyCost = 3;
    }

    @Override
    public String toString() {
        return "Bear : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Bear";
    }
}

