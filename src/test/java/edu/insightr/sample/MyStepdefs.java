package edu.insightr.sample;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import edu.insightr.spellmonger.Eagle;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.runner.RunWith;
import edu.


@RunWith(Cucumber.class)
public class MyStepdefs {
    ControllerMenu controllerMenu;

    @Given("^first player name is \"([^\"]*)\"$")
    public void is(String playerName) throws Throwable {
        controllerMenu = new ControllerMenu();
        controllerMenu.setScreenParent(new ScreensController());
        controllerMenu.login1 = new TextField();
        controllerMenu.login1.setText(playerName);
    }

    @Then("^game player one is \"([^\"]*)\"$")
    public void gamePlayerOneIs(String expectedName) throws Throwable {
        ScreensController screensController = controllerMenu.myController;
        Assert.assertEquals(expectedName, screensController.getData("NamePlayer1"));
    }

    @When("^the game is launched$")
    public void theGameIsLaunched() throws Throwable {
        controllerMenu.goToPlay();
    }



    @Given("^playerList is null$")
    public void playerIsNull() throws Throwable {

    }

    @When("^SpellMongerApp is lunched$")
    public void SpellMongerAppIsLunched() throws Throwable {
    }

    public Eagle eagle;

    @When("^I create an eagle$")
    public void CreateEagle() throws Throwable {
        eagle = new Eagle();
    }

    @Then("")

}
