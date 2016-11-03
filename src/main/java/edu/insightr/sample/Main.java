package edu.insightr.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static String Menu_ID = "MENU";
    public static String Menu_FILE = "/menu.fxml";

    public static String Play_ID = "PLAY";
    public static String Play_FILE = "/sample.fxml";

    public static String Score_ID = "SCORE";
    public static String Score_FILE = "/Scores.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
       /* Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        primaryStage.setTitle("SpellMongerApp");
        Scene scene = new Scene(root, 400, 300);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Menu_ID,Menu_FILE);

        mainContainer.setScreen(Menu_ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        primaryStage.setTitle("SpellMongerApp");
        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add("/style.css");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}
