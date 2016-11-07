package edu.insightr.spellmonger;

class EnergyDrain extends Ritual {

    EnergyDrain() {
        effect = 2;
        energyCost = 3;
    }

    void effect(Player currentPlayer, Player opponent) {
        currentPlayer.setEnergyPoint(currentPlayer.getEnergy() + this.getEffect());
        opponent.setEnergyPoint(opponent.getEnergy() - this.getEffect());
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
