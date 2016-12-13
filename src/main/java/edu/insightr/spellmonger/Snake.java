package edu.insightr.spellmonger;

public class Snake extends Creature{

    public Snake(){
        effect = 1;
        lifePoints = 1;
        energyCost = 2;
        capacity=Capacity.DEATHTOUCH.toString();
    }

    @Override
    public String toString() {
        return "Snake : life Point :" + lifePoints + " strength : " + effect;
    }

    @Override
    public String getName() {
        return "Snake";
    }
}
