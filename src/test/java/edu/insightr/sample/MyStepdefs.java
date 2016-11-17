package edu.insightr.sample;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import edu.insightr.spellmonger.Player;
import edu.insightr.spellmonger.SpellmongerApp;
import org.junit.Assert;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
public class MyStepdefs {
    private SpellmongerApp game;

    @Given("^first player name is \"([^\"]*)\"$")
    public void is(String playerName) throws Throwable {
         game = new SpellmongerApp(new Player(playerName), new Player("Player2"));
    }

    @Then("^game player one is \"([^\"]*)\"$")
    public void gamePlayerOneIs(String expectedName) throws Throwable {
        Assert.assertEquals(expectedName, game.getPlayer(0).getName());
    }
}
