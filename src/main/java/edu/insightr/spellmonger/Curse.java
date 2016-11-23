package edu.insightr.spellmonger;

public class Curse extends Ritual {

    public Curse() {
        effect = 3;
        energyCost = 3;
    }

    public void effect(Player opponent) {
        opponent.setLifePoint(opponent.getLifePoint() - this.getEffect());
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
