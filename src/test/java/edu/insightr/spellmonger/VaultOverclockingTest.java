package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VaultOverclockingTest {
    @Test
    public void testVaultCost() throws Exception {
        VaultOverclocking VaultOverclock = new VaultOverclocking();
        Assert.assertEquals(4, VaultOverclock.getEnergyCost());

    }
    @Test
    public void testVaultActivated() throws Exception {
        VaultOverclocking VaultOverclock = new VaultOverclocking();
        VaultOverclock.setActiveEffect(true);
        Assert.assertEquals(true, VaultOverclock.getActiveEffect());
    }

}