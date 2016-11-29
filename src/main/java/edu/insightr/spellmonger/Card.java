package edu.insightr.spellmonger;

public abstract class Card  {

    protected int effect;
    protected int energyCost;

    public int getEffect() {
        return effect;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    @Override
    public String toString() {
        return "Effect = " + this.effect;
    }

    public String getName() {
        return "Abstract";
    }

   public boolean playCard(Player current){
       if(this.getEnergyCost()<=current.getEnergyPerTurn()){
           current.setEnergyPerTurn(current.getEnergyPerTurn()-this.getEnergyCost());
           return true;
       }else{
           return false;
       }

    }
}
