package edu.insightr.spellmonger;

public class EnergyDrain extends Ritual {

    public EnergyDrain() {
        effect = 2;
    }

    @Override
    public String toString() {
        return "EnergyDrain " + super.toString();
    }

    @Override
    public String getName() {
        return "EnergyDrain";
    }
}
