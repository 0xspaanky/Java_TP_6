package com.example.tp;

public class Forme {
    protected String couleur;

    public Forme(String couleur) {
        this.couleur = couleur;
    }

    // Méthode polymorphe
    public void dessiner() {
        System.out.println("Dessiner une forme de couleur " + couleur);
    }

    // Méthode polymorphe calcul aire
    public double aire() {
        return 0.0; // générique
    }

    @Override
    public String toString() {
        return "Forme{couleur=" + couleur + "}";
    }
}
