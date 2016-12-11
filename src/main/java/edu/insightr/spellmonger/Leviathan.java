package edu.insightr.spellmonger;


public class Leviathan extends Creature {
    Leviathan(){
        energyCost=4;
        effect = 5;
        lifePoints=4;
    }

    @Override
    public String toString() {
        return "Leviathan : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Leviathan";
    }
}
