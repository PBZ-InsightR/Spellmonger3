package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml") );

        primaryStage.setTitle("Hello World");
        Scene s = new Scene(root, 800, 700);
        s.getStylesheets().add("style");
        primaryStage.setScene(s); 
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
