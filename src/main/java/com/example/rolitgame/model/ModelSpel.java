package com.example.rolitgame.model;

import com.example.rolitgame.View.plaats.PlaatsPresenter;
import com.example.rolitgame.View.plaats.PlaatsView;
import com.example.rolitgame.View.winaarscherm.WinnaarPresenter;
import com.example.rolitgame.View.winaarscherm.WinnaarView;
import com.example.rolitgame.klassen.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

import static com.example.rolitgame.klassen.Constants.BOARD_SIZE;

/**
 * @author Ali, Yusuf
 */
public class ModelSpel {
    private SpelBord spelBord;
    private PlaatsView plaatsView;
    private WinnaarView winnaarView = new WinnaarView();
    private PlaatsPresenter plaatsPresenter;
    private int huidigeSpeler = 1; // Start met speler 1
    private int totaalSpelers;
    private Map<Integer, Kleur> spelersKleuren;
    private Map<Kleur, String> spelersNamen;
    private Stack<int[]> stapHistory = new Stack<>();
    private int aantalZetten = 0;




    public ModelSpel() {
        spelBord = new SpelBord(BOARD_SIZE);
    }

    public ModelSpel(SpelBord spelBord, int aantalSpelers) {
        this.spelBord = spelBord;
        this.totaalSpelers = aantalSpelers;
        this.spelersKleuren = new HashMap<>();
        this.spelersNamen = new HashMap<>();
        bepaalSpelerKleuren();
    }

    public void bepaalSpelerKleuren() {
        Kleur[] mogelijkeKleuren = {Kleur.ROOD, Kleur.BLAUW, Kleur.GROEN, Kleur.GEEL};
        for (int i = 0; i < totaalSpelers; i++) {
            spelersKleuren.put(i + 1, mogelijkeKleuren[i]);
        }
    }

    public void bepaalSpelersNamen(List<String> namen) {
        Kleur[] mogelijkeKleuren = {Kleur.ROOD, Kleur.BLAUW, Kleur.GROEN, Kleur.GEEL};

        for (int i = 0; i < namen.size(); i++) {
            spelersNamen.put(mogelijkeKleuren[i], namen.get(i));
        }
    }


    public boolean isGeldigeZet(int x, int y) {
        if (spelBord.getPlaats(x, y).getKleur() != Kleur.LEEG) {
            System.out.println("Zet niet geldig: vak is bezet.");
            return false;
        }

        int[][] omgevingen = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] ints : omgevingen) {
            int nx = x + ints[0];
            int ny = y + ints[1];

            if (nx >= 0 && nx < spelBord.getGrootte() && ny >= 0 && ny < spelBord.getGrootte()) {
                if (spelBord.getPlaats(nx, ny).getKleur() != Kleur.LEEG) {
                    System.out.println("Geldige zet: er ligt een bal in de omgevingen.");
                    return true;
                }
            }
        }
        System.out.println("Zet niet geldig: geen bal in de omgeving.");
        return false;
    }

    public void plaatsKleurVeranderen(int x, int y) {
        if (!isGeldigeZet(x, y)) {
            return;
        }

        Kleur kleur = spelersKleuren.get(huidigeSpeler);
        toonHuidigeSpelerKleur(kleur);

        // Sla de zet op in de stack voordat je het bord bijwerkt
        stapHistory.push(new int[]{x, y, kleur.ordinal()});

        // Zet de kleur op het bord
        spelBord.getPlaats(x, y).setKleur(kleur);

        System.out.println("Speler " + huidigeSpeler + " zet een " + kleur + " bal op: " + x + ", " + y);

        checkLijnKleurBal(x, y, kleur);

        if (!spelBord.isAlleZetGebruikt()) {
            huidigeSpeler = (huidigeSpeler % totaalSpelers) + 1;
            System.out.println("Beurt: Speler " + huidigeSpeler);
        }
        checkGameOver();
    }

    public void updateRanking(String ranking) {
        plaatsView.getRankingLabel().setText("Ranking: \n" + ranking);
    }

    public void updateAantalZetten(int aantalZetten) {
        plaatsView.getAantalZettenLabel().setText("Aantal Zetten: " + aantalZetten);
    }

    public void toonHuidigeSpelerKleur(Kleur kleur) {
        kleur = spelersKleuren.get(huidigeSpeler);
    }


    public void checkLijnKleurBal(int x, int y, Kleur kleur) {
        int[][] omgevingen = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] richting : omgevingen) {
            List<int[]> eenKleurLijn = new ArrayList<>();
            int nx = x + richting[0];
            int ny = y + richting[1];
            boolean bevatLegePlek = false;

            while (nx >= 0 && nx < BOARD_SIZE && ny >= 0 && ny < BOARD_SIZE) {
                Plaats plaats = spelBord.getPlaats(nx, ny);
                if (plaats.getKleur() == kleur) {
                    if (!bevatLegePlek) {
                        for (int[] ints : eenKleurLijn) {
                            spelBord.getPlaats(ints[0], ints[1]).setKleur(kleur);
                        }
                    }
                    break;
                } else if (plaats.getKleur() == Kleur.LEEG) {
                    bevatLegePlek = true;
                }
                eenKleurLijn.add(new int[]{nx, ny});
                nx += richting[0];
                ny += richting[1];
            }
        }
    }

    public void undoLaatsteZet() {
        if (!stapHistory.isEmpty()) {
            int[] laatsteZet = stapHistory.pop();
            int x = laatsteZet[0];
            int y = laatsteZet[1];

            // Zet de plaats terug naar leeg
            spelBord.getPlaats(x, y).setKleur(Kleur.LEEG);

            // Verminder het aantal zetten
            aantalZetten--;

            // Zet de speler terug naar de vorige
            huidigeSpeler = (huidigeSpeler - 2 + totaalSpelers) % totaalSpelers + 1;

            System.out.println("Undo uitgevoerd: Zet op " + x + ", " + y + " verwijderd.");
        } else {
            System.out.println("Geen zetten om ongedaan te maken.");
        }
    }


    public void setPlaatsView(PlaatsView plaatsView) {
        this.plaatsView = plaatsView;
    }

    public Map<Kleur, Integer> getAantalBolletjesPerSpeler() {
        Map<Kleur, Integer> aantalBolletjes = new HashMap<>();

        // Loop door het bord en tel de bolletjes per kleur
        for (int i = 0; i < spelBord.getGrootte(); i++) {
            for (int j = 0; j < spelBord.getGrootte(); j++) {
                Kleur kleur = spelBord.getPlaats(i, j).getKleur();

                if (kleur != Kleur.LEEG) {  // Veronderstel dat 'LEEG' een speciale kleur is die geen speler is
                    aantalBolletjes.put(kleur, aantalBolletjes.getOrDefault(kleur, 0) + 1);
                }
            }
        }

        return aantalBolletjes;
    }

    public String getHuidigeSpelerKleur() {
        Kleur kleur = spelersKleuren.get(huidigeSpeler);
        return kleur.toString();  // Of gebruik een andere manier om de kleur te verkrijgen
    }

    public boolean checkGameOver() {
        // Check if all spots are filled or 60 moves have been made
        if (spelBord.isAlleZetGebruikt()) {
            return true;
        }
        return false;
    }

    public int getAantalSpelers() {
        return totaalSpelers;
    }

    public boolean hasDuplicateNames(List<String> namen) {
        long uniqueCount = namen.stream().distinct().count();
        return uniqueCount < namen.size();  // Als het aantal unieke namen kleiner is dan het totale aantal, zijn er duplicaten
    }

    // Methode om te controleren of er lege tekstvelden zijn
    public boolean hasEmptyFields(List<String> namen) {
        return namen.stream().anyMatch(String::isEmpty);  // Controleer of een van de namen leeg is
    }

    public void toonRanking() {
        Map<Kleur, Integer> bolletjesPerKleur = getAantalBolletjesPerSpeler();

        // Sorteer de spelers op basis van het aantal bolletjes (aflopend)
        List<Map.Entry<Kleur, Integer>> rankedSpelers = new ArrayList<>(bolletjesPerKleur.entrySet());
        rankedSpelers.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Bouw de ranking op als tekst met spelersnamen
        StringBuilder ranking = new StringBuilder();
        for (int i = 0; i < rankedSpelers.size(); i++) {
            Kleur kleur = rankedSpelers.get(i).getKey();
            Integer aantalBolletjesPerSpeler = rankedSpelers.get(i).getValue();
            String spelerNaam = spelersNamen.getOrDefault(kleur, kleur.toString());
            ranking.append(String.format("%d. %s: %d bolletjes\n", i + 1, spelerNaam, aantalBolletjesPerSpeler));
        }

        updateRanking(ranking.toString());
    }

    public void updateHuidigeSpelerAfbeelding() {
        // Verkrijg de kleur van de huidige speler
        String huidigeSpelerKleur = getHuidigeSpelerKleur();  // Dit moet je in je model hebben
        plaatsView.updateHuidigeSpelerAfbeelding(huidigeSpelerKleur);  // Werk de afbeelding bij
    }

    public int getAantalZetten() {
        return aantalZetten;
    }

    public void setAantalZetten(int aantalZetten) {
        this.aantalZetten = aantalZetten;
    }

    public void updateAantalZetten() {
        updateAantalZetten(aantalZetten);
    }


    public void showWinner() {
        Stage winnerStage = new Stage();
        WinnaarView winnaarView = new WinnaarView();
        WinnaarPresenter winnaarPresenter = new WinnaarPresenter(winnaarView,this);


        winnerStage.setTitle("Game Over - Winner");
        winnerStage.setScene(new Scene(winnaarView, 300, 200));
        winnerStage.show();
    }

    public void toonWinnaar(WinnaarView winnaarView, HighScore highScore) {
        Map<Kleur, Integer> bolletjesPerSpeler = getAantalBolletjesPerSpeler();

        Kleur winnaar = null;
        int maxBolletjes = 0;
        for (Map.Entry<Kleur, Integer> entry : bolletjesPerSpeler.entrySet()) {
            if (entry.getValue() > maxBolletjes) {
                winnaar = entry.getKey();
                maxBolletjes = entry.getValue();
            }
        }

        String winnaarNaam = spelersNamen.getOrDefault(winnaar, winnaar.toString());
        String winnaarInfo = "Winner: " + winnaarNaam + "\nBolletjes: " + maxBolletjes;
        updateWinnerInfo(winnaarInfo);
        winnaarView.getWinnaarLabel().setText(winnaarInfo);

        // Sla de score op in de high score lijst
        highScore.bewaarHighScores(winnaarNaam, maxBolletjes, getAantalSpelers());
        showHighScores(getAantalSpelers());
    }

    public void updateWinnerInfo(String winnaarInfo) {

        winnaarView.getWinnaarLabel().setText(winnaarInfo);
    }

    // Methode om de high scores weer te geven
    public void showHighScores(int aantalSpelers) {
        HighScore highScore = new HighScore();
        highScore.toonHighScores(aantalSpelers);  // Toon de top 5 scores voor het juiste aantal spelers

        // Voeg de high scores toe aan het label
        winnaarView.getHighScoresLabel().setText("Top 5 High Scores:");

        // Laad de high scores voor het juiste aantal spelers
        for (String score : highScore.laadHighScores("highscores_" + aantalSpelers + "spelers.txt")) {
            winnaarView.getHighScoresLabel().setText(winnaarView.getHighScoresLabel().getText() + "\n" + score);
        }
    }

    public void checkAndShowWinner() {
        if (checkGameOver()) {
            showWinner();
        }
    }
}
