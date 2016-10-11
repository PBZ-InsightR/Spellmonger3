package edu.insightr.spellmonger;

/**
 * Created by Quentin on 26/09/2016.
 */
public class EnergyDrain extends Ritual {

    public EnergyDrain() {
        effect = 2;
    }

    @Override
    public String toString() {
        return "EnergyDrain "+super.toString();
    }
    public String getname(){return "EnergyDrain";}
}
