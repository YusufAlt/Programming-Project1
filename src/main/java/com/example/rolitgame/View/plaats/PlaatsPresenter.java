package com.example.rolitgame.View.plaats;

import com.example.rolitgame.View.about.AboutPresenter;
import com.example.rolitgame.View.about.AboutView;
import com.example.rolitgame.View.winaarscherm.WinnaarPresenter;
import com.example.rolitgame.View.winaarscherm.WinnaarView;
import com.example.rolitgame.model.ModelSpel;
import com.example.rolitgame.klassen.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;


public class PlaatsPresenter {
    private PlaatsView plaatsView;
    private SpelBord spelbord;
    private Map<Integer, Kleur> spelersKleuren;
    private ModelSpel model;


    public PlaatsPresenter(PlaatsView plaatsView, SpelBord spelbord, int aantalSpelers) {
        this.plaatsView = plaatsView;
        this.spelbord = spelbord;
        this.model = new ModelSpel(spelbord, aantalSpelers);
        this.model.setPlaatsView(plaatsView);
        addEventHandlers();
        updateView();
    }

    public void addEventHandlers() {
        Button[][] buttons = plaatsView.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int finalI = i, finalJ = j;
                buttons[i][j].setOnAction(e -> {
                    if (model.isGeldigeZet(finalI, finalJ)) {
                        model.plaatsKleurVeranderen(finalI, finalJ);
                        model.setAantalZetten(model.getAantalZetten()+1);
                        updateView();
                        model.toonRanking();
                        model.updateHuidigeSpelerAfbeelding();
                        model.updateAantalZetten();
                    } else {
                        plaatsView.toonFoutMelding("Verkeerde zet!");
                    }
                });
            }
        }

        plaatsView.getAboutButton().setOnAction(e -> {
            AboutView aboutView = new AboutView();
            new AboutPresenter(aboutView, model);
            Stage aboutStage = new Stage();
            aboutStage.setTitle("About Rolit");
            aboutStage.setWidth(600);
            aboutStage.setHeight(380);
            aboutStage.initOwner(plaatsView.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.setScene(new Scene(aboutView));
            aboutStage.showAndWait();
        });

        plaatsView.getUndoButton().setOnAction(e -> {
            model.undoLaatsteZet(); // Voer undo uit
            updateView(); // Werk de weergave bij
            model.toonRanking(); // Werk de ranking opnieuw bij
            model.updateAantalZetten();
        });

    }

    public void updateView() {
        Button[][] buttons = plaatsView.getButtons();

        for (int i = 0; i < spelbord.getGrootte(); i++) {
            for (int j = 0; j < spelbord.getGrootte(); j++) {
                Kleur kleur = spelbord.getPlaats(i, j).getKleur();
                String imagePath = spelbord.getKleurImagePath(kleur);
                buttons[i][j].setGraphic(new ImageView(new Image(imagePath)));
            }
        }

        // Check if the game is over
        if (model.checkGameOver()) {
            // Assuming there's an instance of HighScore
            HighScore highScore = new HighScore();
            WinnaarView winnaarView = new WinnaarView();
            model.showWinner();  // Show the winner and high score
        }
    }

}