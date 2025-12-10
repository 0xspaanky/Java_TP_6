package ma.projet;

public abstract class Personne {
    protected final String nom;
    protected final double salaireBase;

    public Personne(String nom, double salaireBase) {
        this.nom = nom;
        this.salaireBase = salaireBase;
    }

    public abstract double calculerSalaire();

    public void affiche() {
        System.out.printf("Je suis %s, salaire = %.2f%n", nom, calculerSalaire());
    }
}
