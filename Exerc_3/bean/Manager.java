package ma.projet.bean;

import ma.projet.Personne;

public class Manager extends Personne {
    public Manager(String nom, double salaireBase) {
        super(nom, salaireBase);
    }

    @Override
    public double calculerSalaire() {
        return salaireBase * 1.30; // +30%
    }
}
