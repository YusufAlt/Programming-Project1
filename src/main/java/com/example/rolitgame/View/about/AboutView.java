package com.example.rolitgame.View.about;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class AboutView extends BorderPane {
    private Button okeBtn;
    private Text text1;
    private Text text2;
    private Text text3;
    private Text text4;
    private Text text5;
    private Label leeg;
    private ImageView bordFoto;

    public AboutView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        okeBtn = new Button("Oké");
        okeBtn.setPrefWidth(40);
        okeBtn.setPrefHeight(30);
        text1 = new Text("Rolit is een strategisch bordspel met 2 tot 4 spelers.\nHet doel is om zoveel mogelijk ballen in jouw \nkleur te hebben tegen het einde van het spel.");
        leeg = new Label("");
        text2 = new Text("1. Startopstelling: Het spel begint met vier gekleurde \n    ballen in het midden van het bord.");
        text3 = new Text("2. Om de beurt spelen: Elke speler legt een nieuwe \n    bal op het bord in zijn kleur.");
        text4 = new Text("3. Ballen overnemen: Als je een of meerdere stenen \n    van een tegenstander insluit tussen jouw stenen \n    (horizontaal, verticaal of diagonaal), moet de \n    tegenstander die stenen omdraaien naar jouw kleur.");
        text5 = new Text("4. Einde van het spel: Wanneer het bord vol is of geen \n    zetten meer mogelijk zijn, wint de speler met de \n    meeste stenen in zijn kleur.");
        bordFoto = new ImageView(new Image("werking_rolit.png"));
    }

    private void layoutNodes() {
        Label titel = new Label("Hoe speel je Rolit");
        titel.setFont(new Font("Arial-Bold", 15));

        VBox infoBox = new VBox(text1, leeg, text2, text3, text4, text5);
        VBox fotoBox = new VBox(bordFoto);
        HBox hBox = new HBox(infoBox, fotoBox);
        hBox.setStyle("-fx-padding: 10;");

        titel.setStyle("-fx-font-weight: bold; -fx-padding: 10");
        okeBtn.setStyle("-fx-alignment: center;");

        setTop(titel);
        setCenter(hBox);
        setBottom(okeBtn);
    }


    public Button getOkeBtn() {
        return okeBtn;
    }
}
