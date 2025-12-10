package com.example.tp;

public class Cercle extends Forme {
    private double rayon;

    public Cercle(String couleur, double rayon) {
        super(couleur);
        this.rayon = rayon;
    }

    @Override
    public void dessiner() {
        System.out.println("Cercle couleur=" + couleur +
            ", rayon=" + rayon + 
            ", aire=" + aire());
    }

    @Override
    public double aire() {
        return Math.PI * rayon * rayon;
    }

    @Override
    public String toString() {
        return "Cercle{couleur=" + couleur + ", rayon=" + rayon + "}";
    }
}
