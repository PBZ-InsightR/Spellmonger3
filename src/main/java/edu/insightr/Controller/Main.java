package edu.insightr.Controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    static String Menu_ID = "MENU";
    static String Menu_FILE = "/Menu.fxml";

    static String Play_ID = "PLAY";
    static String Play_FILE = "/Game.fxml";

    static String Score_ID = "SCORE";
    static String Score_FILE = "/Scores.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Menu_ID, Menu_FILE);
        mainContainer.setScreen(Menu_ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer.stack);
        primaryStage.getIcons().add(new Image("images/fond.png"));
        primaryStage.setTitle("SpellMongerApp");
        primaryStage.getIcons().add(new Image("images/fond.png"));
        Scene scene = new Scene(root, 890, 690);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
