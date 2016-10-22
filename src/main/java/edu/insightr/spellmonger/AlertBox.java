package edu.insightr.spellmonger;


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


public class AlertBox {

    public static void displayDebugging(String title, String message) {
        Stage messageBox = new Stage();
        messageBox.initStyle(StageStyle.TRANSPARENT);

        messageBox.initModality(Modality.APPLICATION_MODAL);
        messageBox.setTitle(title);
        messageBox.setMinWidth(250);
        messageBox.setMinHeight(150);

        Label l = new Label(message);
        l.setTextFill(Color.WHITE);

        Button close = new Button("Close");
        close.setOnAction(actionEvent -> messageBox.close());

        VBox container = new VBox(10);
        container.getChildren().add(l);
        container.getChildren().add(close);
        container.setAlignment(Pos.CENTER);
        container.setMinWidth(350);
        container.setMinHeight(200);

        // fill background with java
        BackgroundSize size = new BackgroundSize(350, 200, false, false, false, false);
        BackgroundImage myBI = new BackgroundImage(new Image("images/tempBubble.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        container.setBackground(new Background(myBI));


        Scene scene = new Scene(container);
        messageBox.setScene(scene);
        messageBox.showAndWait();
    }

    public static void displayGame(String title, String message) {
        Stage messageBox = new Stage();
        messageBox.initStyle(StageStyle.TRANSPARENT);

        messageBox.initModality(Modality.APPLICATION_MODAL);
        messageBox.setTitle(title);
        messageBox.setMinWidth(250);
        messageBox.setMinHeight(150);

        Label l = new Label(message);
        l.setTextFill(Color.WHITE);

        Button close = new Button("Close");
        close.setOnAction(actionEvent -> messageBox.close());

        VBox container = new VBox(10);
        container.getChildren().add(l);
        container.getChildren().add(close);
        container.setAlignment(Pos.CENTER);
        container.setMinWidth(350);
        container.setMinHeight(200);

        // fill background with java
        BackgroundSize size = new BackgroundSize(350, 200, false, false, false, false);
        BackgroundImage myBI = new BackgroundImage(new Image("images/tempBubble.png"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size);
        container.setBackground(new Background(myBI));


        Scene scene = new Scene(container);
        messageBox.setScene(scene);
        messageBox.showAndWait();
    }

    public static void displayError(String title, String message) {
        Stage messageBox = new Stage();

        messageBox.initModality(Modality.APPLICATION_MODAL);
        messageBox.setTitle(title);
        messageBox.setMinWidth(300);
        messageBox.setMinHeight(150);

        Label l = new Label(message);
        l.setTextFill(Color.BLACK);

        Button close = new Button("Close");
        close.setOnAction(actionEvent -> messageBox.close());

        Image errorImage = new Image("images/error.png");
        ImageView errorImageContainer = new ImageView();
        errorImageContainer.setImage(errorImage);
        errorImageContainer.setFitWidth(35);
        errorImageContainer.setFitHeight(35);


        HBox container = new HBox(20);
        container.getChildren().addAll(errorImageContainer, l);
        container.setAlignment(Pos.CENTER);

        VBox sub_container = new VBox(15);
        sub_container.getChildren().addAll(container,close);
        sub_container.setAlignment(Pos.CENTER);
        sub_container.setMinWidth(150);

        Scene scene = new Scene(sub_container);
        messageBox.setScene(scene);
        messageBox.showAndWait();
    }
}
