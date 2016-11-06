package edu.insightr.spellmonger;


public abstract class Ritual extends Card {

    public Ritual() {
        energyCost = 0;
    }

    public void playRitual(Player current, Player opponent) {
        if (this instanceof Blessing) {
            current.setLifePoint(current.getLifePoint() + this.getEffect());
            current.setStackEnergy(current.getStackEnergy() - this.getEnergyCost());
        } else if (this instanceof Curse) {
            opponent.setLifePoint(opponent.getLifePoint() - this.getEffect());
            current.setStackEnergy(current.getStackEnergy() - this.getEnergyCost());
        } else {
            current.setEnergyPoint(current.getEnergy() + this.getEffect());
            opponent.setEnergyPoint(opponent.getEnergy() - this.getEffect());
            current.setStackEnergy(current.getStackEnergy() - this.getEnergyCost());
        }

    }

}
