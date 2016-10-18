package edu.insightr.spellmonger;

class Blessing extends Ritual {

    Blessing() {
        effect = 3;
        energyCost = 3;
    }

    @Override
    public String toString()

    {
        return "Blessing " + super.toString();
    }

    @Override
    public String getName() {
        return "Blessing";
    }
}
