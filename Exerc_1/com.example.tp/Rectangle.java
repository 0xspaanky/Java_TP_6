package com.example.tp;

public class Rectangle extends Forme {
    private double largeur, hauteur;

    public Rectangle(String couleur, double largeur, double hauteur) {
        super(couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner() {
        System.out.println("Rectangle couleur=" + couleur +
            ", largeur=" + largeur +
            ", hauteur=" + hauteur +
            ", aire=" + aire());
    }

    @Override
    public double aire() {
        return largeur * hauteur;
    }

    @Override
    public String toString() {
        return "Rectangle{couleur=" + couleur + 
            ", largeur=" + largeur + 
            ", hauteur=" + hauteur + "}";
    }
}
