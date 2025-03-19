package com.example.rolitgame.View.aantalSpelersSelecter;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AantalSpelersView extends BorderPane {
    private Button tweeSpelers;
    private Button drieSpelers;
    private Button vierSpelers;
    private Button returnBtn;
    private Text titleLabel;

    public AantalSpelersView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        titleLabel = new Text("Kies het aantal spelers");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.getStyleClass().add("text");  // Add this line
        tweeSpelers = new Button("2 Spelers");
        drieSpelers = new Button("3 Spelers");
        vierSpelers = new Button("4 Spelers");
        returnBtn = new Button("Return");
    }

    private void layoutNodes() {
        VBox vbox = new VBox(20, titleLabel, tweeSpelers, drieSpelers, vierSpelers, returnBtn);
        vbox.setAlignment(Pos.CENTER);
        setCenter(vbox);
    }

    public Button getTweeSpelersButton() {
        return tweeSpelers;
    }

    public Button getDrieSpelersButton() {
        return drieSpelers;
    }

    public Button getVierSpelersButton() {
        return vierSpelers;
    }

    public Button getReturnBtn() {
        return returnBtn;
    }
}
