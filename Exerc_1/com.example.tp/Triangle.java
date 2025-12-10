package com.example.tp;

public class Triangle extends Forme {
    private double base, hauteur;

    public Triangle(String couleur, double base, double hauteur) {
        super(couleur);
        this.base = base;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner() {
        System.out.println("Triangle couleur=" + couleur +
            ", base=" + base +
            ", hauteur=" + hauteur +
            ", aire=" + aire());
    }

    @Override
    public double aire() {
        return (base * hauteur) / 2;
    }

    @Override
    public String toString() {
        return "Triangle{couleur=" + couleur + 
            ", base=" + base + 
            ", hauteur=" + hauteur + "}";
    }
}
