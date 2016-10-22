package edu.insightr.spellmonger;

public class Curse extends Ritual {

    public Curse() {
        effect = 3;
        energyCost = 3;
    }

    @Override
    public String toString() {
        return "Curse " + super.toString();
    }

    @Override
    public String getName() {
        return "Curse";
    }
}
