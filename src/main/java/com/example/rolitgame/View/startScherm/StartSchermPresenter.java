package com.example.rolitgame.View.startScherm;

import com.example.rolitgame.View.aantalSpelersSelecter.AantalSpelersPresenter;
import com.example.rolitgame.View.aantalSpelersSelecter.AantalSpelersView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class StartSchermPresenter {

    private StartSchermView startSchermView;

    public StartSchermPresenter(StartSchermView startSchermUI) {
        this.startSchermView = startSchermUI;
        addEventHandlers();
    }

    private void addEventHandlers() {
        startSchermView.getStartButton().setOnAction(event -> {
            AantalSpelersView aantalSpelersView = new AantalSpelersView();
            new AantalSpelersPresenter(aantalSpelersView);
            startSchermView.getScene().setRoot(aantalSpelersView);

        });

        startSchermView.getQuitButton().setOnAction(event -> {
            System.exit(0);
        });


    }

}
