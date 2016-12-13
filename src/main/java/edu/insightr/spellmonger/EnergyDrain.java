package edu.insightr.spellmonger;

public class EnergyDrain extends Ritual {

    public EnergyDrain() {
        effect = 2;
        energyCost = 3;
    }

    public void effect(Player currentPlayer, Player opponent) {
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
