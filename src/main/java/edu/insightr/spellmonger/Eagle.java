package edu.insightr.spellmonger;

public class Eagle extends Creature {

    public Eagle() {
        effect = 1;
        capacity = "Flying";
        lifePoints = effect;
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
