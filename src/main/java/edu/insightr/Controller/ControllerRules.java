package edu.insightr.Controller;


import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRules implements ControlledScreen{

    ScreensController myController;

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    //passer a la vue de Play
    public void goToPlay(){
        myController.loadScreen(Main.Play_ID,Main.Play_FILE); // charger le fichier Controller.fxml
        myController.setScreen(Main.Play_ID); // activer la vue Play
    }
    public void goToMenu(){
        myController.setScreen(Main.Menu_ID);// activer la vue Menu sans load vu qu'il es déja dans la Map screens
    }
}
