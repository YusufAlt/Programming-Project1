package com.example.rolitgame.klassen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Spel {
    private SpelBord bord;
    private Kleur[][] kleurBord;
    private List <Speler> spelers;
    private Map<Integer, Kleur> spelersKleuren;
    private int huidigeSpelerIndex;
    private int totaalSpelers;
    private boolean spelActief;
    private Scanner scanner;

    public Spel(List<Speler> spelers, SpelBord bord) {
        this.bord = bord;
        this.spelers = spelers;
        this.huidigeSpelerIndex = huidigeSpelerIndex;
        this.spelActief = spelActief;
        kleurBord = new Kleur[8][8];
        scanner = new Scanner(System.in);
    }

    public void startSpel(){
        int aantalSpelers = vraagAantalSpelers(scanner);
        spelers = new ArrayList<Speler>();

        Kleur[] kiesKleuren = {Kleur.ROOD, Kleur.BLAUW, Kleur.GROEN, Kleur.GEEL};

        for (int i = 0; i < aantalSpelers; i++) {
            System.out.println("geef je naam speler " + (i + 1));
            String naam = vraagSpelerNaam(i + 1);
            Speler speler = new Speler(naam, kiesKleuren[i]);
            spelers.add(speler);
        }

        huidigeSpelerIndex = 0;
        spelActief = true;
        bord.toonBord();

        volgendeBeurt();
    }

    public static int vraagAantalSpelers(Scanner scanner) {
        int aantalSpelers;
            System.out.print("Kies aantal spelers (2 of 3 of 4): ");
        while (true){
            String invoer = scanner.nextLine();
            try{
                aantalSpelers = Integer.parseInt(invoer);
                if (aantalSpelers >= 2 && aantalSpelers <= 4) {
                    break;
                }else {
                    System.out.println("Er kan enkel gespeeld worden met 2, 3 of 4 spelers");
                }
            }catch (NumberFormatException e){
                System.out.println("Alleen cijfers!!! Er kan enkel gespeeld worden met 2, 3 of 4 spelers");
            }
        }

        return aantalSpelers;
    }

    private String vraagSpelerNaam(int i) {
        String naam;
        naam = scanner.nextLine();
        Kleur kleur = kleurBord[huidigeSpelerIndex][i];
       // return spelers.get(huidigeSpelerIndex).getNaam();
        return naam;
    }

    public void volgendeBeurt(){
        if (isSpelVoorbij()){
            toonWinnaar(spelers);
            return;
        }

        huidigeSpelerIndex = (huidigeSpelerIndex + 1) % spelers.size();

        System.out.println("nu aan de beurt: " + spelers.get(huidigeSpelerIndex).getNaam());
    }


    public Speler getHuidigeSpeler(){
        return spelers.get(huidigeSpelerIndex);
    }

    public boolean isSpelVoorbij(){
        for (int i = 0; i < bord.getMAX_PLAATSEN(); i++) {
            for (int j = 0; j < bord.getMAX_PLAATSEN(); j++) {
                if (kleurBord[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }

    public void toonWinnaar(List<Speler> spelers){
        if (spelers.isEmpty()){
            System.out.println("Geen speler nog actief");
            return;
        }

        int hoogsteScore = 0;
        List<Speler> winaar = new ArrayList<>();

        for (Speler speler : spelers) {
            if (speler.getScore() > hoogsteScore){
                hoogsteScore = speler.getScore();
                winaar.clear(); // oude winnaar verwijderen
                winaar.add(speler);
            } else if (speler.getScore() == hoogsteScore) {
                winaar.add(speler); // gelijk spel
            }
        }

        if (winaar.size() == 1){
            System.out.println("Speler " + winaar.get(0).getNaam() + " met score " + hoogsteScore + " heeft gewonen");
        } else {
            System.out.println("Gelijkspel! winnaars zijn: ");
            for (Speler speler : winaar) {
                System.out.println(speler.getNaam() + " ");
            }
            System.out.println("elk heeft " + hoogsteScore + " punten");
        }
    }

    public void upgradeRanking(){
        spelers.sort((a, b) -> Integer.compare(a.getScore(), b.getScore()));

        System.out.println("ranking");
        for (Speler speler : spelers) {
            System.out.println(speler.getNaam() + ": " + speler.getScore());
        }
    }

    public void verhoogScoreUpdate(Speler speler, int aantal){
        speler.verhoogScore(aantal);
        upgradeRanking();
    }
}
