package edu.insightr.spellmonger;

import java.util.Random;

public class VaultOverclocking extends Enchantment{

    VaultOverclocking(){
        energyCost = 4;
    }

    public void getEffect(Player player){
        Random rand = new Random();
        int nbRand = rand.nextInt (99);

        if(nbRand>34)
        {
            player.setEnergyPoint (1);
        }
        else{
            player.setEnergyPoint (player.getEnergy ()+1);
        }
    }

    @Override
    public String toString(){
        return "Vault Overclocking";
    }
}
