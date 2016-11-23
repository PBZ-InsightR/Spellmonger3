package edu.insightr.spellmonger;

class Curse extends Ritual {

    Curse() {
        effect = 3;
        energyCost = 3;
    }

    void effect(Player opponent) {
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
