package com.example.rolitgame.View.startScherm;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartSchermView extends BorderPane {

    private Button startButton;
    private Button quitButton;
    private Text titleLabel;

    public StartSchermView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        titleLabel = new Text("Welkom bij Rolit!");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.getStyleClass().add("text");  // Add this line
        startButton = new Button("Start");
        quitButton = new Button("Quit");
    }

    private void layoutNodes() {
        VBox vbox = new VBox(20, titleLabel, startButton, quitButton);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
