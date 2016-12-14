package edu.insightr.Controller;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.insightr.spellmonger.*;
import org.junit.Assert;

public class ModelStepdefs {
    private Player current;
    private Player opponent;
    private SpellmongerApp game;



    @Given("^the game is lauched$")
    public void theGameIsLauched() throws Throwable {
        game = new SpellmongerApp("current", "opponent");
        current = game.getPlayer(0);
        opponent = game.getPlayer(1);
    }

    @Then("^they have both (\\d+) life points, (\\d+) energy, and (\\d+) cards in hands$")
    public void theyHaveBothLifePointsEnergyAndCardsInHands(int arg0, int arg1, int arg2) throws Throwable {
       Assert.assertEquals(current.getLifePoint(),20);
       Assert.assertEquals(opponent.getLifePoint(),20);
       Assert.assertEquals(current.getEnergy(),0);
       Assert.assertEquals(opponent.getEnergy(),0);
       Assert.assertEquals(current.getHand().size(),2);
       Assert.assertEquals(opponent.getHand().size(),2);
    }


    @Given("^a player have 5 cards in hand$")
    public void aPlayerHaveCardsInHand() throws Throwable {
        for(int i=0;i<3;i++)
            if(current.canDraw()) current.getHand().add(new Eagle());
    }

    @When("^he wanna draw another one$")
    public void heWannaDrawAnotherOne() throws Throwable {
        if(current.canDraw())
            current.getHand().add(new Eagle());
    }

    @Then("^he cannot draw another card$")
    public void heCannotDrawAnotherCard() throws Throwable {
        Assert.assertEquals(current.getHand().size(),5);
    }

    @Given("^a player who  has 10 energy$")
    public void aPlayerWhoHasEnergy() throws Throwable {
        for(int i=0;i<10;i++)current.increaseEnergy();
    }

    @When("^it's his turn, he wanna gain another energy$")
    public void itSHisTurnHeWannaGainAnotherEnergy() throws Throwable {
        current.increaseEnergy();
    }

    @Then("^he cannot gain another energy$")
    public void heCannotGainAnotherEnergy() throws Throwable {
        Assert.assertEquals(current.getEnergy(),10);
    }

    @When("^he play  an energy drain$")
    public void hePlayAnEnergyDrain() throws Throwable {
        current.getHand().clear();
        current.getHand().add(new EnergyDrain());
        current.setEnergyPerTurn(current.getEnergy());
        game.playCard(current,opponent,0);
    }

    @Then("^he have 12 energy now$")
    public void heHaveEnergyNow() throws Throwable {
        Assert.assertEquals(current.getEnergy(),12);
    }
}