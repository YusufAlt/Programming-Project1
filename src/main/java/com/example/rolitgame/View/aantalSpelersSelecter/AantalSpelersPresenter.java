package com.example.rolitgame.View.aantalSpelersSelecter;

import com.example.rolitgame.View.naamSelecter.SpelersNamenPresenter;
import com.example.rolitgame.View.naamSelecter.SpelersNamenView;
import com.example.rolitgame.View.startScherm.StartSchermPresenter;
import com.example.rolitgame.View.startScherm.StartSchermView;
import com.example.rolitgame.model.ModelSpel;

public class AantalSpelersPresenter {
    private final AantalSpelersView aantalSpelersView;

    public AantalSpelersPresenter(AantalSpelersView aantalSpelersUI) {
        this.aantalSpelersView = aantalSpelersUI;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        aantalSpelersView.getTweeSpelersButton().setOnMouseClicked(event -> {
            // Ga naar de spelersnamen pagina met 2 spelers
            SpelersNamenView spelersNamenView = new SpelersNamenView(2);
            ModelSpel modelSpel = new ModelSpel();
            new SpelersNamenPresenter(spelersNamenView, 2,modelSpel);

            // Verander de scene naar SpelersNamenView
            aantalSpelersView.getScene().setRoot(spelersNamenView);
        });

        aantalSpelersView.getDrieSpelersButton().setOnMouseClicked(event -> {
            // Ga naar de spelersnamen pagina met 3 spelers
            SpelersNamenView spelersNamenView = new SpelersNamenView(3);
            ModelSpel modelSpel = new ModelSpel();
            new SpelersNamenPresenter(spelersNamenView, 3,modelSpel);

            // Verander de scene naar SpelersNamenView
            aantalSpelersView.getScene().setRoot(spelersNamenView);
        });

        aantalSpelersView.getVierSpelersButton().setOnMouseClicked(event -> {
            // Ga naar de spelersnamen pagina met 4 spelers
            SpelersNamenView spelersNamenView = new SpelersNamenView(4);
            ModelSpel modelSpel = new ModelSpel();
            new SpelersNamenPresenter(spelersNamenView, 4,modelSpel);

            // Verander de scene naar SpelersNamenView
            aantalSpelersView.getScene().setRoot(spelersNamenView);
        });

        aantalSpelersView.getReturnBtn().setOnAction(event -> {
            // Ga terug naar het startscherm
            StartSchermView startSchermView = new StartSchermView();
            new StartSchermPresenter(startSchermView);

            // Verander de view naar StartSchermView zonder stage.show()
            aantalSpelersView.getScene().setRoot(startSchermView);
        });
    }

    private void updateView() {

    }
}

