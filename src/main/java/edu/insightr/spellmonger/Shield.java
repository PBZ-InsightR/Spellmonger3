package edu.insightr.spellmonger;

/**
 * Created by Numa on 11/10/2016.
 */
public class Shield extends Ritual {

    public Shield(){
        super.effect = 5;
    }

    @Override
    public String toString(){
        return "Shield " + super.toString();
    }
}
