package edu.insightr.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;

class ScreensController {

    StackPane stack;
    private HashMap<String, Node> screens = new HashMap<>(); // stock des Screen afin de basculer entre eux
    private HashMap<String, String> data = new HashMap<>(); // data est une HashMap pour stocker les données comme nom player ..etc

    ScreensController() {
        stack = new StackPane();
    }

    //Data
    void addData(String key, String value) {
        data.put(key, value);
    }

    String getData(String key) {
        return data.get(key);
    }

    private void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    // charger un fichier fxml et lajouter dans les screens
    boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new
                    FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = myLoader.load();
            ControlledScreen myScreenControler = myLoader.getController();
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //activer un screen avec son ID pour passer d'une vue a une autre
    boolean setScreen(final String name) {

        if (screens.get(name) != null) { //screen loaded
            final DoubleProperty opacity = stack.opacityProperty();

            //Is there is more than one screen
            if (!stack.getChildren().isEmpty()) {
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000),
                                t -> {
                                    //remove displayed screen
                                    stack.getChildren().remove(0);
                                    //add new screen
                                    stack.getChildren().add(0, screens.get(name));
                                    Timeline fadeIn = new Timeline(
                                            new KeyFrame(Duration.ZERO,
                                                    new KeyValue(opacity, 0.0)),
                                            new KeyFrame(new Duration(800),
                                                    new KeyValue(opacity, 1.0)));
                                    fadeIn.play();
                                }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                //no one else been displayed, then just show
                stack.setOpacity(0.0);
                stack.getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500),
                                new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!\n");
            return false;
        }

    }
}
