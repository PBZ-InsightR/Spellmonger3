package edu.insightr.Controller;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.spellmonger.*;
import org.junit.Assert;


public class IAStepDefs {
    private SpellmongerApp game;
    private Player opponent;
    private Player IA;
    @Given("^a player VS IA$")
    public void twoPlayersHaveNoCards() {
        game=new SpellmongerApp("IA","Opponenet");
        opponent = game.getPlayer(1);
        IA = game.getPlayer(0);
        opponent.getPlayerCreature().clear();
        IA.getPlayerCreature().clear();
        IA.getHand().clear();
        opponent.getHand().clear();
        IA.setEnergyPerTurn(0);
    }

    @When("^IA have (\\d+) energy$")
    public void iaHaveEnergy(int energy) throws Throwable {
        IA.setEnergyPerTurn(energy);
    }

    @When("^component have nothing in the game, and IA have a fox and bear in his hand$")
    public void componentHaveNothingInTheGameAndIAHaveAFoxAndBearInHisHand() throws Throwable {
        IA.addToHand(new Fox());
        IA.addToHand(new Bear());
        game.playCardIA_LV2(IA,opponent);
    }

    @Then("^IA plays the bear")
    public void iaPlaysTheBearAndThePlayerLosePointsOfLife() throws Throwable {
        Assert.assertEquals(IA.getHand().size(),1);
        Assert.assertEquals(IA.getHand().get(0) instanceof Fox,true);
        Assert.assertEquals(IA.getPlayerCreature().size(),1);
        Assert.assertEquals(IA.getPlayerCreature().get(0) instanceof Bear,true);
    }

    @Then("^IA plays the fox$")
    public void iaPlaysTheFox() throws Throwable {
        Assert.assertEquals(IA.getHand().size(),1);
        Assert.assertEquals(IA.getHand().get(0) instanceof Bear,true);
        Assert.assertEquals(IA.getPlayerCreature().size(),1);
        Assert.assertEquals(IA.getPlayerCreature().get(0) instanceof Fox,true);
    }

    @When("^component have a dragon in his creatures, and IA have a eagle and bear in his hand$")
    public void componentHaveADragonInHisCreaturesAndIAHaveAEagleAndBearInHisHand() throws Throwable {
        opponent.getPlayerCreature().add(new Dragoon());
        IA.addToHand(new Eagle());
        IA.addToHand(new Bear());
        game.playCardIA_LV2(IA,opponent);
    }

    @Then("^IA plays the eagle$")
    public void iaPlaysTheEagle() throws Throwable {
        Assert.assertEquals(IA.getHand().size(),1);
        Assert.assertEquals(IA.getHand().get(0) instanceof Bear,true);
        Assert.assertEquals(IA.getPlayerCreature().size(),1);
        Assert.assertEquals(IA.getPlayerCreature().get(0) instanceof Eagle,true);
    }

    @When("^component have a bear in his creatures, and IA have energy drain in his hand$")
    public void componentHaveABearInHisCreaturesAndIAHaveEnergyDrainInHisHand() throws Throwable {
        opponent.getPlayerCreature().add(new Bear());
        IA.addToHand(new EnergyDrain());
        game.playCardIA_LV2(IA,opponent);
    }

    @Then("^IA plays the energy drain$")
    public void iaPlaysTheEnergyDrain() throws Throwable {
        Assert.assertEquals(IA.getHand().size(),0);
        Assert.assertEquals(IA.getEnergy(),2);
    }

    @When("^component have a bear in his creatures, and IA have fox and bear in his hand$")
    public void componentHaveABearInHisCreaturesAndIAHaveFoxAndBearInHisHand() throws Throwable {
        opponent.getPlayerCreature().add(new Bear());
        IA.addToHand(new Fox());
        IA.addToHand(new Bear());
        game.playCardIA_LV1(IA,opponent);
    }

    @When("^component have a Dragon in his creatures, and IA have fox and bear in his hand$")
    public void componentHaveADragonInHisCreaturesAndIAHaveFoxAndBearInHisHand() throws Throwable {
        opponent.getPlayerCreature().add(new Dragoon());
        IA.addToHand(new Fox());
        IA.addToHand(new Bear());
        game.playCardIA_LV1(IA,opponent);
    }

    @When("^component have a Dragon in his creatures, and IA have energy drain and dragon in his hand$")
    public void componentHaveADragonInHisCreaturesAndIAHaveEnergyDrainAndDragonInHisHand() throws Throwable {
        opponent.getPlayerCreature().add(new Dragoon());
        IA.addToHand(new EnergyDrain());
        IA.addToHand(new Dragoon());
        game.playCardIA_LV1(IA,opponent);
    }

    @Then("^IA plays the card energy drain$")
    public void iaPlaysTheCardEnergyDrain() throws Throwable {
        Assert.assertEquals(IA.getHand().size(),1);
        Assert.assertEquals(IA.getHand().get(0) instanceof Dragoon,true);
    }
}
