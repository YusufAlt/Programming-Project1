package com.example.rolitgame.klassen;

public class Plaats {
    private int x;
    private int y;
    private boolean vrij;
    private Kleur kleur;

    // Constructor met coördinaten
    public Plaats(int x, int y) {
        this.x = x;
        this.y = y;
        this.kleur = Kleur.LEEG;  // Standaard staat: geen kleur
    }

    public Plaats(Kleur kleur) {
        this.kleur = kleur;
    }

    public Plaats() {
        this.kleur = null;
        this.vrij = true;
    }

    public boolean isVrij() {
        return vrij;
    }

    public void setKleur(Kleur kleur) {
        this.kleur = kleur;
        this.vrij = false;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
