package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

public class VaultOverclockingTest {
    @Test
    public void testVaultCost() throws Exception {
        VaultOverclocking VaultOverclock = new VaultOverclocking();
        Assert.assertEquals(4, VaultOverclock.getEnergyCost());

    }

}