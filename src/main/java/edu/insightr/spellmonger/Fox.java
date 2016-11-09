package edu.insightr.spellmonger;


class Fox extends Creature {

    Fox() {
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
