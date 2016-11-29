package edu.insightr.spellmonger;


public abstract class Ritual extends Card{

    public Ritual() {
        energyCost = 0;
    }



    public void setEffect(Player current, Player opponent){
        if(this instanceof Blessing){
            current.setLifePoint(current.getLifePoint()+this.getEffect());
        }else if(this instanceof Curse){
            opponent.setLifePoint(opponent.getLifePoint()-this.getEffect());
        }else if(this instanceof EnergyDrain){
            current.setEnergyPoint(current.getEnergy()+this.getEffect());
            opponent.setLifePoint(opponent.getEnergy()-this.getEffect());
        }
    }


}
