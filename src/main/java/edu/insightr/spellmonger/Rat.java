package edu.insightr.spellmonger;

public class Rat extends Creature{
    Rat(){
        energyCost=1;
        effect=1;
        lifePoints=effect;
        capacity="DeathTouch";
    }

    @Override
    public String toString() {
        return "Rat : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Rat";
    }
}