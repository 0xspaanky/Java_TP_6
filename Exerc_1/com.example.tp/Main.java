package com.example.tp;

public class Main {
    public static void main(String[] args) {

        Forme[] formes = new Forme[] {
            new Cercle("Rouge", 5),
            new Rectangle("Bleu", 4, 2.5),
            new Triangle("Vert", 6, 3),
            new Carre("Jaune", 4),
            new Forme("Noir")
        };

        System.out.println("=== DESSIN DES FORMES (Polymorphisme) ===");
        for(Forme f : formes) {
            f.dessiner(); // liaison dynamique
        }

        System.out.println("\n=== LISTE DES AIRES ===");
        for(Forme f : formes) {
            System.out.println(f.getClass().getSimpleName() +
                " => Aire : " + f.aire());
        }
    }
}
