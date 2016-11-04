package edu.insightr.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by hope on 01/11/2016.
 */
public class ControllerMenu implements Initializable,ControlledScreen{
    ScreensController myController;

    @FXML
    public TextField Login1,Login2;
    public CheckBox isPlayer2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    public void isPlayer2(){
        System.out.println("AA");
        Login2.setDisable(!Login2.isDisable());
    }

    public void goToPlay(){
        if(isPlayer2.isSelected()) {
            myController.addData("isPlayer2", "false");
        }else{
            myController.addData("isPlayer2", "true");
        }
        myController.addData("NamePlayer1", Login1.getText());
        myController.addData("NamePlayer2",Login2.getText());
        myController.loadScreen(Main.Play_ID,Main.Play_FILE);
        myController.setScreen(Main.Play_ID);
    }

    public void goToScore(){
        myController.loadScreen(Main.Score_ID,Main.Score_FILE);
        myController.setScreen(Main.Score_ID);
    }

}
