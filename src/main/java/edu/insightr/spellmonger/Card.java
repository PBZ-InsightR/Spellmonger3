package edu.insightr.spellmonger;

public abstract class Card {

    int effect;
    protected int energyCost;

    public int getEffect() {
        return effect;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public String getTypeCard(){
        if(this instanceof Ritual)
            return "Ritual";
        else if(this instanceof Enchantment)
            return "Enchantment";
        else
            return "Creature";
    }

    @Override
    public String toString() {
        return "Effect = " + this.effect;
    }

    public String getName() {
        return "Abstract";
    }
}
