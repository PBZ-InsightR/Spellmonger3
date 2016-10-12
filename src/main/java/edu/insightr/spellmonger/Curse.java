package edu.insightr.spellmonger;

public class Curse extends Ritual {

    public Curse() {
        effect = 3;
    }

    @Override
    public String toString() {
        return "Curse " + super.toString();
    }

    public String getname() {
        return "Curse";
    }
}
