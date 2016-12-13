package edu.insightr.spellmonger;

public class VaultOverclocking extends Card {

    public VaultOverclocking() {
        energyCost = 4;
    }

    @Override
    public String toString() {
        return "Vault Overclocking" + super.toString();
    }

    @Override
    public String getName() {
        return "VaultOver";
    }
}
