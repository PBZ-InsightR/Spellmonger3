package edu.insightr.Controller;


import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class AlertBox {


    static void displayDebugging(String title, String message,double X,double Y) {
        Stage messageBox = new Stage();
        messageBox.initStyle(StageStyle.TRANSPARENT);

        messageBox.initModality(Modality.APPLICATION_MODAL);
        messageBox.setTitle(title);
        messageBox.setMinWidth(250);
        messageBox.setMinHeight(150);
        messageBox.setX(X);
        messageBox.setY(Y);

        Label l = new Label(message);
        l.setTextFill(Color.WHITE);
        l.setStyle("-fx-font: 20 'Sitka text';");

        VBox container = new VBox(10);
        container.getChildren().add(l);
        container.setAlignment(Pos.CENTER);
        container.setMinWidth(350);
        container.setMinHeight(200);

        // fill background with java
        BackgroundSize size = new BackgroundSize(350, 200, false, false, false, false);
        BackgroundImage myBI = new BackgroundImage(new Image("images/tempBubble.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        container.setBackground(new Background(myBI));

        // fadeIn
        FadeTransition fade = new FadeTransition(Duration.seconds(1), container);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
        Scene scene = new Scene(container);
        messageBox.setScene(scene);
        // transparence
        scene.setFill(null);
        messageBox.initStyle(StageStyle.TRANSPARENT);

        // close auto
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished( event -> messageBox.close() );
        delay.play();
        // show popup
        messageBox.showAndWait();
    }

    static void displayGame(String message) {
        Stage messageBox = new Stage();
        messageBox.initStyle(StageStyle.TRANSPARENT);

        messageBox.initModality(Modality.APPLICATION_MODAL);
        messageBox.setMinWidth(350);
        messageBox.setMinHeight(200);

        Label l = new Label(message);
        l.setTextFill(Color.WHITE);
        l.setStyle("-fx-font: 14 'Sitka text';" +
                "-fx-text-alignment: center;");

        VBox container = new VBox(10);
        container.getChildren().add(l);
        container.setAlignment(Pos.CENTER);
        container.setMinWidth(350);
        container.setMinHeight(200);

        // fill background with java
        BackgroundSize size = new BackgroundSize(350, 200, false, false, false, false);
        BackgroundImage myBI = new BackgroundImage(new Image("images/tempBubble.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        container.setBackground(new Background(myBI));

        // fadeIn
        FadeTransition fade = new FadeTransition(Duration.seconds(1), container);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);
        fade.play();
        Scene scene = new Scene(container);
        messageBox.setScene(scene);
        // transparence
        scene.setFill(null);
        messageBox.initStyle(StageStyle.TRANSPARENT);

        // close auto
        PauseTransition delay = new PauseTransition(Duration.seconds(4));
        delay.setOnFinished( event -> messageBox.close() );
        delay.play();
        // show popup
        messageBox.show();
    }
}
