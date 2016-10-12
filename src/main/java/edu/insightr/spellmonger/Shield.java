package edu.insightr.spellmonger;

public class Shield extends Ritual {

    public Shield() {
        super.effect = 5;
    }

    @Override
    public String toString() {
        return "Shield " + super.toString();
    }
}
