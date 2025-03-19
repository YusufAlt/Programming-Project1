package com.example.rolitgame.View.plaats;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import static com.example.rolitgame.klassen.Constants.BOARD_SIZE;
import javafx.scene.layout.*;

public class PlaatsView extends BorderPane {
    private Button[][] buttons;
    private Button aboutButton;
    private GridPane gridPane;
    private Label huidigeSpelerLabel;
    private ImageView huidigeSpelerImageView;
    private Label rankingLabel;
    private VBox bottomBox;
    private Button undoButton;
    private Label aantalZettenLabel;

    public PlaatsView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        buttons = new Button[BOARD_SIZE][BOARD_SIZE];
        aboutButton = new Button("Help");
        undoButton = new Button("Undo");
        gridPane = new GridPane(); // GridPane aanmaken
        huidigeSpelerLabel = new Label("Huidige Speler: ");
        huidigeSpelerImageView = new ImageView(new Image("button_rood.png"));
        rankingLabel = new Label("Ranking: ");
        aantalZettenLabel = new Label("Aantal Zetten: 0");


        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(80, 80);
                buttons[i][j].setGraphic(new ImageView(new Image("button_leeg.png")));
                buttons[i][j].setCursor(Cursor.HAND);

                gridPane.add(buttons[i][j], j, i);  // Knoppen toevoegen aan GridPane
            }
        }
        bottomBox = new VBox(rankingLabel,huidigeSpelerImageView,aantalZettenLabel);
    }

    private void layoutNodes() {
        HBox aboutBox = new HBox(aboutButton,undoButton);
        HBox rankingBox = new HBox(rankingLabel);


        aboutBox.setStyle("-fx-padding: 10; -fx-alignment: left;");
        gridPane.setAlignment(Pos.CENTER);

        this.setTop(aboutBox);
        this.setCenter(gridPane);
        this.setRight(rankingBox);
        this.setLeft(bottomBox);

    }

    public void toonFoutMelding(String bericht) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fout");
        alert.setHeaderText(null);
        alert.setContentText(bericht);
        alert.showAndWait();
    }

    public void updateHuidigeSpelerAfbeelding(String kleur) {
        String imagePath = "button_" + kleur + ".png"; // Zorg dat je de juiste afbeeldingen hebt
        getHuidigeSpelerImageView().setImage(new Image(imagePath));
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public Button getAboutButton() {
        return aboutButton;
    }

    public Label getHuidigeSpelerLabel() {
        return huidigeSpelerLabel;
    }

    public ImageView getHuidigeSpelerImageView() {
        return huidigeSpelerImageView;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Label getRankingLabel() {
        return rankingLabel;
    }

    public Label getAantalZettenLabel() {
        return aantalZettenLabel;
    }
}