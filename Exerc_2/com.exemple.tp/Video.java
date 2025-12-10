package com.example.tp;

public class Video extends Media {
    private int duree;
    private String resolution;

    public Video(String titre, int duree, String resolution) {
        super(titre);
        this.duree = duree;
        this.resolution = resolution;
    }

    @Override
    public void lire() {
        System.out.println("Lecture vidéo : " + titre + " [" + resolution + "]");
    }

    @Override
    public int getDuree() {
        return duree;
    }
}
