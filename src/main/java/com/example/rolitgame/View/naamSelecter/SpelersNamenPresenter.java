package com.example.rolitgame.View.naamSelecter;

import com.example.rolitgame.View.aantalSpelersSelecter.AantalSpelersPresenter;
import com.example.rolitgame.View.aantalSpelersSelecter.AantalSpelersView;
import com.example.rolitgame.View.plaats.PlaatsPresenter;
import com.example.rolitgame.View.plaats.PlaatsView;
import com.example.rolitgame.klassen.SpelBord;
import com.example.rolitgame.model.ModelSpel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class SpelersNamenPresenter {
    private final SpelersNamenView spelersNamenView;
    private final int aantalSpelers;
    private ModelSpel model;

    public SpelersNamenPresenter(SpelersNamenView spelerNamenView, int aantalSpelers,ModelSpel model) {
        this.spelersNamenView = spelerNamenView;
        this.aantalSpelers = aantalSpelers;
        this.model = model;
        addEventHandlers();
    }

    private void addEventHandlers() {
        spelersNamenView.getStartSpelButton().setOnAction(event -> {
            // Verzamel namen uit de tekstvelden
            List<String> namen = spelersNamenView.getNaamVelden().stream()
                    .map(TextField::getText)
                    .collect(Collectors.toList());

            // Controleer of er dubbele namen zijn
            if (model.hasEmptyFields(namen)) {
                spelersNamenView.showEmptyFieldsAlert();
            } else if (model.hasDuplicateNames(namen)) {
                // Controleer of er lege tekstvelden zijn
                spelersNamenView.showDuplicateNamesAlert();
            } else {
                // Ga naar het spelbord met namen
                PlaatsView plaatsView = new PlaatsView();
                new PlaatsPresenter(plaatsView, new SpelBord(8), aantalSpelers);

                // Wijzig de scene naar PlaatsView
                Stage stage = (Stage) spelersNamenView.getScene().getWindow();
                Scene scene = new Scene(plaatsView, stage.getWidth(), stage.getHeight());
                scene.getStylesheets().add("plaats.css");
                stage.setScene(scene);
                stage.setMaximized(true);
            }
        });

        spelersNamenView.getReturnBtn().setOnAction(event -> {
            // Ga terug naar het startscherm
            AantalSpelersView aantalSpelersView = new AantalSpelersView();
            new AantalSpelersPresenter(aantalSpelersView);

            // Verander de view naar StartSchermView zonder stage.show()
            spelersNamenView.getScene().setRoot(aantalSpelersView);
        });

        spelersNamenView.getNaamVelden().forEach(textField -> {
            textField.setOnMouseMoved(event -> {
                // Geef feedback over welk tekstveld de gebruiker heeft geselecteerd
                textField.setStyle("-fx-border-color: red;");  // Verander de randkleur bij hover
            });
        });
    }


}
