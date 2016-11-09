package edu.insightr.spellmonger;

class Eagle extends Creature {

    Eagle() {
        effect = 1;
        capacity = "Flying";
        lifePoints = effect;
        energyCost = 1;
    }

    @Override
    public String toString() {
        return "Eagle " + super.toString();
    }

    @Override
    public String getName() {
        return "Eagle";
    }
}
