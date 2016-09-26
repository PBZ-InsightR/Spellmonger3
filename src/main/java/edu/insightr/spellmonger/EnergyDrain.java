package edu.insightr.spellmonger;

/**
 * Created by Quentin on 26/09/2016.
 */
public class EnergyDrain extends Ritual {
    private int effect;
    private int effect2;

    public EnergyDrain() {
        this.effect = -2;
        this.effect2 = 2;
    }

    @Override
    public String toString() {
        return "EnergyDrain ";
    }
}
