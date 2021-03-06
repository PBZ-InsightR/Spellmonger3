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

    public String getTypeCard(){
        String result;
        if(this instanceof Ritual)
            result = TypeOfCard.RITUAL.toString();
        else if(this instanceof VaultOverclocking)
            result = TypeOfCard.VAULTOVERCLOCKING.toString();
        else
            result = TypeOfCard.CREATURE.toString();
        return result;
    }

    @Override
    public String toString() {
        return "Effect = " + this.effect;
    }

    public String getName() {
        return "Abstract";
    }

}
