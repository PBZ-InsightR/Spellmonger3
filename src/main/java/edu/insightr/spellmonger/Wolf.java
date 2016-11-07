package edu.insightr.spellmonger;

class Wolf extends Creature {

    Wolf() {
        effect = 2;
        lifePoints = effect;
        energyCost = 2;
    }

    @Override
    public String toString() {
        return "Wolf : life Point : " + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Wolf";
    }
}