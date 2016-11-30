package edu.insightr.spellmonger;

public class Lizard extends Creature{

    Lizard(){
        effect = 3;
        lifePoints = 2;
        energyCost = 3;
        capacity="";
    }

    @Override
    public String toString() {
        return "Lizard : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Lizard";
    }
}
