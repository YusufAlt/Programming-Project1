package com.example.rolitgame.klassen;

import java.io.*;
import java.util.*;

public class HighScore {

    public HighScore() {

    }

    // Method to save the high scores to a file
    public void bewaarHighScores(String naam, int score, int aantalSpelers) {
        String bestandsNaam = "highscores_" + aantalSpelers + "speler.txt";
        List<String> highScores = laadHighScores(bestandsNaam); // Load existing high scores from the file

        // een nieuwe score zetten bij de textbestand
        String dataScore = naam + ";" + score;
        highScores.add(dataScore);

        // score op juiste orde
        Collections.sort(highScores, (a, b) -> {
            int scoreA = Integer.parseInt(a.split(";")[1]);
            int scoreB = Integer.parseInt(b.split(";")[1]);
            return Integer.compare(scoreB, scoreA);
        });

        // Ensure the list only contains the top 5 scoresA
        if (highScores.size() > 5) {
            highScores = highScores.subList(0, 5);
        }

        try (BufferedWriter schrijf = new BufferedWriter(new FileWriter(bestandsNaam))) {
            for (String lijn : highScores) {
                schrijf.write(lijn);
                schrijf.newLine();
            }
        } catch (IOException e) {
            System.out.println("Probleem met opslaan van de highscores: " + e.getMessage());
        }
    }

    /**
     * Om de highscores op te halen van text bestand*/
    public List<String> laadHighScores(String bestandsNaam) {
        List<String> highScores = new ArrayList<>();
        File bestand = new File(bestandsNaam);

        if (!bestand.exists()) {
            return highScores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(bestandsNaam))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) {
            System.out.println("Probleem met laden van highscores: " + e.getMessage());
        }

        return highScores;
    }


    public void toonHighScores(int aantalSpelers) {
        String bestandsNaam = "highscores_" + aantalSpelers + "speler.txt";
        List<String> highScores = laadHighScores(bestandsNaam);

        System.out.println("Top 5 High Scores voor " + aantalSpelers + " spelers:");
        for (String score : highScores) {
            String[] scoreParts = score.split(";");
            String naam = scoreParts[0];
            String scoreValue = scoreParts[1];
            System.out.println("Speler: " + naam + " - Score: " + scoreValue);
        }
    }
}
