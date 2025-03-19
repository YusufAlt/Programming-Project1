package com.example.rolitgame;

import com.example.rolitgame.View.startScherm.StartSchermPresenter;
import com.example.rolitgame.View.startScherm.StartSchermView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;

import static javafx.application.Platform.exit;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize your view and presenter
        StartSchermView startSchermUI = new StartSchermView();
        StartSchermPresenter startSchermPresenter = new StartSchermPresenter(startSchermUI);

        // Create the scene and set the CSS stylesheet
        Scene sceneStart = new Scene(startSchermUI, 800, 600);
        sceneStart.getStylesheets().add("index.css");



        // Set up the stage
        primaryStage.setScene(sceneStart);
        primaryStage.setTitle("Rolit Startscherm");
        primaryStage.setMaximized(true); // Maximize the window
        primaryStage.show();
    }
}
