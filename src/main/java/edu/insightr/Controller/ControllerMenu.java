package edu.insightr.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable, ControlledScreen {
    ScreensController myController;

    @FXML
    public TextField login1, login2;
    public CheckBox IA_LV1, IA_LV2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    public void isIA_LV1() {
        disabledName();
        IA_LV2.setSelected(false);
    }

    public void isIA_LV2() {
        disabledName();
        IA_LV1.setSelected(false);
    }

    private void disabledName(){
        if(IA_LV1.isSelected() || IA_LV2.isSelected())
            login2.setDisable(true);
        else
            login2.setDisable(false);
    }

    public void goToPlay() {
        if (IA_LV1.isSelected()) {
            myController.addData("IA_LV1", "true");
            myController.addData("IA_LV2", "false");
            myController.addData("NamePlayer1", login1.getText());
            myController.addData("NamePlayer2", "IA_LV1");
        } else if (IA_LV2.isSelected()) {
            myController.addData("IA_LV1", "false");
            myController.addData("IA_LV2", "true");
            myController.addData("NamePlayer1", login1.getText());
            myController.addData("NamePlayer2", "IA_LV2");
        }
        else {
            myController.addData("IA_LV1", "false");
            myController.addData("IA_LV2", "false");
            myController.addData("NamePlayer1", login1.getText());
            myController.addData("NamePlayer2", login2.getText());
        }
        myController.loadScreen(Main.Play_ID, Main.Play_FILE);
        myController.setScreen(Main.Play_ID);
    }

    public void goToScore() {
        myController.loadScreen(Main.Score_ID, Main.Score_FILE);
        myController.setScreen(Main.Score_ID);
    }

}
