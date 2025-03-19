package com.example.rolitgame.klassen;



import java.util.HashMap;
import java.util.Map;

import static com.example.rolitgame.klassen.Constants.BOARD_SIZE;

public class SpelBord {
    private Plaats[][] bord;  // Een 2D array van Plaats-objecten
    private final int MAX_PLAATSEN = 8;

    public SpelBord(int grootte) {
        // Initialiseer het bord met dezelfde breedte en hoogte
        bord = new Plaats[grootte][grootte];
        initializeBord();

        // Vul het bord met Plaats-objecten
        for (int i = 0; i < grootte; i++) {
            for (int j = 0; j < grootte; j++) {
                bord[i][j] = new Plaats(i, j);  // Gebruik de constructor met parameters
            }
        }
        startKleurPosities();
    }

    private void startKleurPosities() {
        setPlaatsKleur(3, 3, Kleur.ROOD);
        setPlaatsKleur(3, 4, Kleur.GEEL);
        setPlaatsKleur(4, 3, Kleur.BLAUW);
        setPlaatsKleur(4, 4, Kleur.GROEN);
    }

    private void setPlaatsKleur(int x, int y, Kleur kleur) {
        bord[x][y].setKleur(kleur);
    }

    // Methode om een specifieke plaats op het bord te verkrijgen
    public Plaats getPlaats(int x, int y) {
        // Controleer of de coördinaten binnen de grenzen van het bord vallen
        if (x >= 0 && x < bord.length && y >= 0 && y < bord[0].length) {
            return bord[x][y];  // Retourneer het Plaats-object op de opgegeven locatie
        } else {
            return null;  // Of gooi een foutmelding, afhankelijk van je wensen
        }
    }

    private void initializeBord() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i == 3 && j == 3)) bord[i][j] = new Plaats(Kleur.ROOD);
                else if ((i == 3 && j == 4)) bord[i][j] = new Plaats(Kleur.GEEL);
                else if ((i == 4 && j == 3)) bord[i][j] = new Plaats(Kleur.BLAUW);
                else if ((i == 4 && j == 4)) bord[i][j] = new Plaats(Kleur.GROEN);
                else bord[i][j] = new Plaats(Kleur.LEEG);
            }
        }
    }


    public boolean isAlleZetGebruikt() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (bord[i][j].getKleur() == Kleur.LEEG){
                    return false;
                }
            }
        }
        return true;
    }

    public Map<Kleur, Integer> berekenScore(){
        Map<Kleur, Integer> score = new HashMap<Kleur, Integer>();
        for (Kleur kleur : Kleur.values()) {
            score.put(kleur, 0);
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Kleur kleur = bord[i][j].getKleur();
                score.put(kleur, score.get(kleur) + 1);
            }
        }
        return score;
    }

    public Plaats balPlaatsen(int x, int y) {
        for (int i = 0; i < MAX_PLAATSEN; i++) {
            for (int j = 0; j < MAX_PLAATSEN; j++) {
                if (this.bord[i][j].getX() == x && this.bord[i][j].getY() == y) {
                    return this.bord[i][j];
                }
            }
        }
        return null;
    }

    public void toonBord(){
        for (int i = 0; i < MAX_PLAATSEN; i++) {
            for (int j = 0; j < MAX_PLAATSEN; j++) {
                bord[i][j] = new Plaats();
            }
        }
    }

    public int getGrootte() {
        return BOARD_SIZE;
    }


    public String getKleurImagePath(Kleur kleur) {
        return switch (kleur) {
            case ROOD -> "button_rood.png";
            case BLAUW -> "button_blauw.png";
            case GROEN -> "button_groen.png";
            case GEEL -> "button_geel.png";
            default -> "button_leeg.png";
        };
    }

    public int getMAX_PLAATSEN() {
        return MAX_PLAATSEN;
    }

}
