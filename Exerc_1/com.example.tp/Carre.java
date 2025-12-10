package com.example.tp;

public class Carre extends Forme {
    private double cote;

    public Carre(String couleur, double cote) {
        super(couleur);
        this.cote = cote;
    }

    @Override
    public void dessiner() {
        System.out.println("Carré couleur=" + couleur +
            ", cote=" + cote +
            ", aire=" + aire());
    }

    @Override
    public double aire() {
        return cote * cote;
    }

    @Override
    public String toString() {
        return "Carre{couleur=" + couleur + ", cote=" + cote + "}";
    }
}
