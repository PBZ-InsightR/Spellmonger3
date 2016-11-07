package edu.insightr.sample;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import javafx.scene.control.TextField;
import org.junit.Assert;
import org.junit.runner.RunWith;


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
}
