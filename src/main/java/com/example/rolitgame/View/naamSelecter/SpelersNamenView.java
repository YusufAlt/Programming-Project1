package com.example.rolitgame.View.naamSelecter;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SpelersNamenView extends VBox {
    private List<TextField> naamVelden;
    private Button startSpelButton;
    private Button returnBtn;
    private Text titleLabel;

    public SpelersNamenView(int aantalSpelers) {
        initialiseNodes(aantalSpelers);
        layoutNodes();
    }

    // Initialiseert de nodes van de UI
    private void initialiseNodes(int aantalSpelers) {
        naamVelden = new ArrayList<>();
        startSpelButton = new Button("Start Spel");
        returnBtn = new Button("Return");
        titleLabel = new Text("Voer de namen van de spelers in:");
        titleLabel.getStyleClass().add("title-label");
        titleLabel.getStyleClass().add("text");  // Add this line

        for (int i = 0; i < aantalSpelers; i++) {
            TextField naamVeld = new TextField();
            naamVeld.setPromptText("Naam speler " + (i + 1));
            naamVelden.add(naamVeld);
        }
    }

    // Layout de nodes (hoe ze visueel worden gepositioneerd)
    private void layoutNodes() {
        setAlignment(Pos.CENTER);
        setSpacing(10);
        getChildren().add(titleLabel);
        getChildren().addAll(naamVelden);
        getChildren().add(startSpelButton);
        getChildren().add(returnBtn);
    }

    public List<String> getSpelersNamen() {
        List<String> namen = new ArrayList<>();
        for (TextField naamVeld : naamVelden) {
            String naam = naamVeld.getText();
            if (!naam.isEmpty()) {
                namen.add(naam);
            }
        }
        return namen;
    }

    public void showDuplicateNamesAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Dubbele Namen");
        alert.setHeaderText("Er zijn dubbele namen ingevoerd!");
        alert.setContentText("Zorg ervoor dat elke speler een unieke naam heeft.");
        alert.showAndWait();
    }

    public void showEmptyFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Lege Velden");
        alert.setHeaderText("Er zijn lege velden!");
        alert.setContentText("Zorg ervoor dat elk tekstveld is ingevuld.");
        alert.showAndWait();
    }

    // Getter voor de naamvelden
    public List<TextField> getNaamVelden() {
        return naamVelden;
    }

    // Getter voor de start spel knop
    public Button getStartSpelButton() {
        return startSpelButton;
    }

    // Getter voor return knop
    public Button getReturnBtn() {
        return returnBtn;
    }
}
