package ma.projet.bean;

import ma.projet.Personne;

public class Developpeur extends Personne {
    public Developpeur(String nom, double salaireBase) {
        super(nom, salaireBase);
    }

    @Override
    public double calculerSalaire() {
        return salaireBase * 1.10; // +10%
    }
}
