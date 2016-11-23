package edu.insightr.sample;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import edu.insightr.spellmonger.Curse;
import edu.insightr.spellmonger.Eagle;
import edu.insightr.spellmonger.Player;
import edu.insightr.spellmonger.Wolf;
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

    @Then("^player(\\d+) is added$")
    public void playerIsAdded(int arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    //_________________________________________EAGLE_______________________________________________


    public Eagle eagle;


    @Given("^I create an eagle$")
    public void CreateEagle() throws Throwable {
        eagle = new Eagle();
    }

    @When("^I draw an eagle$")
    public void iDrawAnEagle() throws Throwable {
        System.out.println("eagle");
    }

    @Then("^The eagle has (\\d+) effect$")
    public void theEagleHasEffect(int arg0) throws Throwable {
        Assert.assertEquals(1, eagle.getEffect());
    }

    @Then("^The eagle capacity is \"([^\"]*)\"$")
    public void theEagleCapacityIs(String arg0) throws Throwable {
        Assert.assertEquals("Flying", eagle.getCapacity());
    }

    @Then("^The eagle lifePoints is effect$")
    public void theEagleLifePointsIsEffect() throws Throwable {
        Assert.assertEquals(1, eagle.getLifePoints());
    }

    @Then("^The eagle cost (\\d+) energy$")
    public void theEagleCostEnergy(int arg0) throws Throwable {
        Assert.assertEquals(1, eagle.getEnergyCost());
    }


//________________________WOLF___________________________________________________________

    public Wolf wolf;

    @Given("^I create a wolf$")
    public void iCreateAWolf() throws Throwable {
        wolf = new Wolf();
    }

    @Then("^The wolf has (\\d+) effect$")
    public void theWolfHasEffect(int arg0) throws Throwable {
        Assert.assertEquals(2, wolf.getEffect());
    }

    @Then("^the wolf lifePoints is effect$")
    public void theWolfLifePointsIsEffect() throws Throwable {
        Assert.assertEquals(2, wolf.getLifePoints());
    }

    @Then("^the wolf cost (\\d+) energy$")
    public void theWolfCostEnergy(int arg0) throws Throwable {
        Assert.assertEquals(2, wolf.getEnergyCost());
    }

    @When("^I draw a wolf$")
    public void iDrawAWolf() throws Throwable {
        System.out.println("wolf");
    }

    //_______________________________Curse______________________________________________________

    public Curse curse;
    public Player playercursed;
    int playerlife;
    int playerlife2;

    @Given("^I create a curse$")
    public void iCreateACurse() throws Throwable {
        curse = new Curse();
        playercursed = new Player("test");
        playerlife = playercursed.getLifePoint();
    }

    @When("^i draw a card curse$")
    public void iDrawACardCurse() throws Throwable {
        System.out.println("curse = " + curse);

    }

    @Then("^The curse has (\\d+) effect$")
    public void theCurseHasEffect(int arg0) throws Throwable {
        Assert.assertEquals(arg0, curse.getEffect());
    }

    @Then("^the curse cost (\\d+) energy$")
    public void theCurseCostEnergy(int arg0) throws Throwable {
        Assert.assertEquals(arg0, curse.getEnergyCost());
    }

}