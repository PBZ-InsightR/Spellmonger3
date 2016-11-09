package edu.insightr.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML
    public TextField login1, login2;
    public CheckBox isPlayer2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void isPlayer2() {
        login2.setDisable(!login2.isDisable());
    }

    public void goToPlay() {
        if (isPlayer2.isSelected()) {
            myController.addData("isPlayer2","false");
        } else {
            myController.addData("isPlayer2","true");
        }
        myController.addData("NamePlayer1",login1.getText());
        myController.addData("NamePlayer2", login2.getText());
        myController.loadScreen(Main.Play_ID,Main.Play_FILE);
        myController.setScreen(Main.Play_ID);
    }

    public void goToScore() {
        myController.loadScreen(Main.Score_ID,Main.Score_FILE);
        myController.setScreen(Main.Score_ID);
    }

}
