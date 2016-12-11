package edu.insightr.spellmonger;

public class Rat extends Creature{
    public Rat(){
        energyCost=1;
        effect=1;
        lifePoints=effect;
        capacity=Capacity.DEATHTOUCH.toString();
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