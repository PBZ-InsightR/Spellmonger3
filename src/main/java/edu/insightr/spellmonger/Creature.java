package edu.insightr.spellmonger;

public abstract class Creature extends Card implements Comparable<Creature> {
    protected String capacity;
    protected int lifePoints;

    public Creature() {
        capacity = "";
        lifePoints = 0;
    }

    public String getCapacity() {
        return capacity;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    @Override
    public String getName() {
        return "Creature";
    }

    @Override
    public int compareTo(Creature other) {
        Integer obj1 = this.effect;
        Integer obj2 = new Integer(other.effect);
        return obj2.compareTo(obj1); // pour que ce soit décroissant
    }// compareTo pour ordonner les creatures, et créér le systeme d'attaque entre creatures
}