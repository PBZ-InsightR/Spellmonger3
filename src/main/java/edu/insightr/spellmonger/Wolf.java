package edu.insightr.spellmonger;

public class Wolf extends Creature {

    public Wolf() {
        effect = 2;
        lifePoints = effect;
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