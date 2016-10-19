package edu.insightr.spellmonger;

import org.junit.Assert;
import org.junit.Test;

public class EnchantmentTest {
    @Test
    public void EnchantmentEnergyCost() throws Exception {
        Enchantment enchantment = new Enchantment();
        Assert.assertEquals(0,enchantment.getEnergyCost());
    }
}
