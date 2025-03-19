package com.example.rolitgame.klassen;

import java.util.Scanner;

public class Speler {
    private String naam;
    private Kleur kleur;
    private int score;
    private Scanner input;


    public Speler(String naam, Kleur kleur) {
        this.naam = naam;
        this.kleur = kleur;
       // this.score = score;
        input = new Scanner(System.in);
    }

    public void verhoogScore(int aantal){
        if (aantal > 0){
            this.score += aantal;
            System.out.println(naam + ": " + score);
        }
        else {
            throw new IllegalArgumentException("Score mag niet negatief!");
        }
    }


    public void verlaagScore(int aantal){
        if (aantal > 0){
            this.score = Math.max(0, this.score - aantal);
            System.out.println(naam + ": " + score);
        }else {
            throw new IllegalArgumentException("Score mag niet negatief!");
        }
    }

    public void resetScore(){
        this.score = 0;
        System.out.println("Alle scores zijn gereset!!!");
    }

    public String geefJeNaam(String naam) {
        naam = input.nextLine();
        if (naam.isEmpty()) {
            throw new IllegalArgumentException("Geen naam ingegeven");
        }
        else {
            return naam;
        }
    }

    public String getNaam() {
        return naam;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public int getScore() {
        return score;
    }
}
