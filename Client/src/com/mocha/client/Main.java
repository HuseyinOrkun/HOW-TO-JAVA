package com.mocha.client;

import com.mocha.client.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by E.Batuhan Kaynak on 30.3.2016.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("How To Java");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);

        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("resources/LoginScreen.fxml"));
        Pane myPane = (Pane)myLoader.load();

        LoginController loginController= (LoginController) myLoader.getController();
        loginController.setPrevStage(primaryStage);

        Scene myScene = new Scene(myPane);
        /*
        String image = Main.class.getResource("resources/images/Theme1.png").toExternalForm();
        myPane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");*/
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
