package edu.insightr.spellmonger;

import org.apache.log4j.Logger;

import java.util.Random;

class VaultOverclocking extends Enchantment {

    private boolean activeEffect;
    private static final Logger logger = Logger.getLogger(VaultOverclocking.class);

    VaultOverclocking() {
        energyCost = 4;
        activeEffect = false;
    }

    boolean getActiveEffect() {
        return activeEffect;
    }

    void setActiveEffect(boolean active) {
        activeEffect = active;
    }

    public void getEffect(Player player) {
        Random rand = new Random();
        int nbRand = rand.nextInt(99);
        if (player.getVaultOverclockingOnOff()) {
            if (nbRand > 34) {
                player.setEnergyPoint(1);
                logger.info("Vault OverClocking burn all " + player.getName() + "'s");
            } else {
                player.setEnergyPoint(player.getEnergy() + 1);
                logger.info("Vault Overclocking add 1 energy to " + player.getName());
            }
        }
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
