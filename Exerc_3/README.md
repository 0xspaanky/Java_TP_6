# Exercise 3: Person, Developer and Manager with Generics

![Exercise 3 Diagram](./image.png)

## Objective
Combine inheritance, abstract classes, and generics to process lists of different subclasses uniformly, demonstrating type-safe collection handling with bounded wildcards.

## Key Concepts
- Abstract methods forcing implementation
- Generics with bounded wildcards (`? extends`)
- Type-safe list processing
- PECS principle (Producer Extends, Consumer Super)

## Implementation

### Personne (Abstract Base)
```java
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
        System.out.printf("%s : %.2f€%n", nom, calculerSalaire());
    }
}
```

### Developpeur
```java
package ma.projet.bean;
import ma.projet.Personne;

public class Developpeur extends Personne {
    public Developpeur(String nom, double salaireBase) {
        super(nom, salaireBase);
    }

    @Override
    public double calculerSalaire() {
        return salaireBase * 1.10;  // +10% bonus
    }
}
```

### Manager
```java
package ma.projet.bean;
import ma.projet.Personne;

public class Manager extends Personne {
    public Manager(String nom, double salaireBase) {
        super(nom, salaireBase);
    }

    @Override
    public double calculerSalaire() {
        return salaireBase * 1.30;  // +30% bonus
    }
}
```

### Utils (Generic Methods)
```java
package ma.projet;
import java.util.List;

public class Utils {
    // Bounded wildcard: accepts List<Personne> or any subclass
    public static void afficherListe(List<? extends Personne> liste) {
        for (Personne p : liste) {
            p.affiche();
        }
    }

    public static double sommeSalaires(List<? extends Personne> liste) {
        double total = 0;
        for (Personne p : liste) {
            total += p.calculerSalaire();
        }
        return total;
    }
}
```

## Usage Example
```java
package ma.projet;
import ma.projet.bean.*;
import java.util.*;

public class TestPersonnes {
    public static void main(String[] args) {
        List<Developpeur> devs = Arrays.asList(
            new Developpeur("Alice", 3000),
            new Developpeur("Bob", 3500)
        );

        List<Manager> managers = Arrays.asList(
            new Manager("Charlie", 5000),
            new Manager("Diana", 5500)
        );

        System.out.println("=== Développeurs ===");
        Utils.afficherListe(devs);
        System.out.printf("Total: %.2f€%n", Utils.sommeSalaires(devs));

        System.out.println("\n=== Managers ===");
        Utils.afficherListe(managers);
        System.out.printf("Total: %.2f€%n", Utils.sommeSalaires(managers));
    }
}
```

## Expected Output
```
=== Développeurs ===
Alice : 3300.00€
Bob : 3850.00€
Total: 7150.00€

=== Managers ===
Charlie : 6500.00€
Diana : 7150.00€
Total: 13650.00€
```

## Compilation & Execution
```bash
javac ma/projet/*.java ma/projet/bean/*.java
java ma.projet.TestPersonnes
```

## Generics Explained

### Bounded Wildcards
```java
// Accepts List<Personne>, List<Developpeur>, List<Manager>
public static void afficherListe(List<? extends Personne> liste) {
    // Can READ as Personne
    for (Personne p : liste) {
        p.affiche();
    }
}
```

### Why Not Just List\<Personne\>?
```java
// Won't work!
List<Developpeur> devs = new ArrayList<>();
List<Personne> personnes = devs;  // Compile error!

// Works with wildcard
void process(List<? extends Personne> liste) {
    // Accepts List<Developpeur>
}
```

### PECS Principle
- **Producer Extends:** `List<? extends T>` when reading (producing values)
- **Consumer Super:** `List<? super T>` when writing (consuming values)

## Extensions
- Add `Stagiaire` class with different calculation
- Implement salary increase methods
- Add department grouping
- Create generic `maxSalaire()` method
- Add `Comparable` for sorting by salary
