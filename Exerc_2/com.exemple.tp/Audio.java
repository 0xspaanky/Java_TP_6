package com.example.tp;

public class Audio extends Media {
    private int duree; // secondes

    public Audio(String titre, int duree) {
        super(titre);
        this.duree = duree;
    }

    @Override
    public void lire() {
        System.out.println("Lecture audio : " + titre);
    }

    @Override
    public int getDuree() {
        return duree;
    }
}
