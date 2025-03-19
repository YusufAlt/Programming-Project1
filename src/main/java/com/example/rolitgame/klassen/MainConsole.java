package com.example.rolitgame.klassen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainConsole {
    public static void main(String[] args) {
        int aantalSpelers;
        Scanner scanner = new Scanner(System.in);

        List<Speler> spelers = new ArrayList<>();
        SpelBord bord = new SpelBord(8);

        Spel spel = new Spel(spelers, bord);

        spel.startSpel();

       // aantalSpelers = vraagAantalSpelers(scanner);


        Kleur[] alleKleuren = Kleur.values();

      /*  for (int i = 0; i < aantalSpelers; i++) {
            String naam;
            System.out.println("geef je naam speler " + (i + 1) + ':');
            naam = scanner.nextLine();
            spelers.add(new Speler(naam, alleKleuren[i]));
        }*/


        while (!spel.isSpelVoorbij()){
            spel.volgendeBeurt();
        }

        spel.toonWinnaar(spelers);
    }


}
