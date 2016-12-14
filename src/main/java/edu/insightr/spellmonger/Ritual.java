package edu.insightr.spellmonger;


public abstract class Ritual extends Card{

     Ritual() {
        energyCost = 0;
    }

     public void attackRitual(Player current, Player opponent){
        if(this instanceof Blessing){
            current.setLifePoint(current.getLifePoint()+this.getEffect());
        }else if(this instanceof Curse){
            opponent.setLifePoint(opponent.getLifePoint()-this.getEffect());
        }else if(this instanceof EnergyDrain){
            current.setEnergyPoint(current.getEnergy()+this.getEffect());
            opponent.setEnergyPoint(opponent.getEnergy()-this.getEffect());
        }
    }


}
