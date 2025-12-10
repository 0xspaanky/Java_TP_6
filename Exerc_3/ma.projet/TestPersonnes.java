package ma.projet;

import ma.projet.bean.Developpeur;
import ma.projet.bean.Manager;

import java.util.*;

public class TestPersonnes {
    public static void main(String[] args) {
        List<Personne> equipe = new ArrayList<>();
        equipe.add(new Developpeur("Ali", 2000));
        equipe.add(new Manager("Hamid", 3000));
        equipe.add(new Developpeur("Hanane", 2200));

        // Appel de la méthode générique
        Utils.listerPersonnes(equipe);
    }
}
