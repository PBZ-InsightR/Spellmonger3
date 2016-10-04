package edu.insightr.spellmonger;

import java.util.Comparator;

/**
 * Created by dufou on 25/09/2016.
 */
public abstract class Creature extends Card implements Comparable<Creature>

{
    protected String capacity;
    public Creature()
    {
    }

    public String getCapacity()
    {
        return capacity;
    }

    @Override
    public int compareTo(Creature other){
        Integer obj1 = new Integer(this.effect);
        Integer obj2 = new Integer(other.effect);
        return obj2.compareTo(obj1); // pour que ce soit décroissant
    }// compareTo pour ordonner les creatures, et créér le systeme d'attaque entre creatures
}