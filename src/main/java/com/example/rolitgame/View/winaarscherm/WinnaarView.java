package com.example.rolitgame.View.winaarscherm;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.example.rolitgame.klassen.HighScore;

public class WinnaarView extends BorderPane {

    private Label winnaarLabel;
    private Label highScoresLabel;

    public WinnaarView() {
        initiliaseNodes();
        layoutNodes();
    }


    public void initiliaseNodes() {
        winnaarLabel = new Label();
        highScoresLabel = new Label();  // Initialiseer het label voor de high scores
    }


    public void layoutNodes() {
        VBox layout = new VBox(10);
        layout.getChildren().addAll(winnaarLabel, highScoresLabel);
        this.setCenter(layout); // Make sure the layout is added to the center of the BorderPane
    }

    public Label getWinnaarLabel() {
        return winnaarLabel;
    }

    public Label getHighScoresLabel() {
        return highScoresLabel;
    }
}
