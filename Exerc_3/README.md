# Exercise 3: Person, Developer and Manager with Generics

![Exercise 3 Diagram](./image.png)

## Objective
Combine inheritance, abstract classes, and generics to process lists of objects from different subclasses using a uniform method. This exercise demonstrates how abstract classes force implementation contracts while generics provide type-safe collection handling.

## Description
This exercise creates an employee management system using an abstract base class with concrete implementations for different employee types. The system uses Java generics to create flexible, type-safe methods that can work with lists of any Person subclass.

## UML Class Diagram

```
       Personne (abstract)
       ├─ nom : String
       ├─ salaireBase : double
       ├─ calculerSalaire(): double   (abstract)
       └─ affiche() : void  (concrete)

    Developpeur              Manager
    └─ calculerSalaire()     └─ calculerSalaire()
       (+10%)                     (+30%)
```

## Package Structure

```
ma.projet/
├─ Personne.java          (abstract base class)
├─ Utils.java             (generic utility methods)
└─ TestPersonnes.java     (test program)

ma.projet.bean/
├─ Developpeur.java       (concrete subclass)
└─ Manager.java           (concrete subclass)
```

## Class Structure

### Personne (Abstract Base Class)
Located in `ma.projet` package

**Attributes:**
- `nom` (protected final String): Person's name
- `salaireBase` (protected final double): Base salary

**Constructor:**
```java
public Personne(String nom, double salaireBase) {
    this.nom = nom;
    this.salaireBase = salaireBase;
}
```

**Abstract Method:**
```java
/**
 * Calculates effective salary based on person type.
 * @return net salary
 */
public abstract double calculerSalaire();
```
**Must be implemented by all subclasses!**

**Concrete Method:**
```java
/**
 * Displays role, name and calculated salary.
 */
public void affiche() {
    System.out.printf(
        "Je suis %s, salaire = %.2f%n",
        nom, calculerSalaire()
    );
}
```

### Developpeur (Developer)
Located in `ma.projet.bean` package

**Salary Calculation:** Base salary + 10% bonus

**Constructor:**
```java
public Developpeur(String nom, double salaireBase) {
    super(nom, salaireBase);
}
```

**Implementation:**
```java
@Override
public double calculerSalaire() {
    return salaireBase * 1.10;  // +10%
}
```

**Example:**
- Base: 2000.0
- Calculated: 2200.0 (2000 × 1.10)

### Manager
Located in `ma.projet.bean` package

**Salary Calculation:** Base salary + 30% bonus

**Constructor:**
```java
public Manager(String nom, double salaireBase) {
    super(nom, salaireBase);
}
```

**Implementation:**
```java
@Override
public double calculerSalaire() {
    return salaireBase * 1.30;  // +30%
}
```

**Example:**
- Base: 3000.0
- Calculated: 3900.0 (3000 × 1.30)

## Generic Utility Method

### Utils.java
Located in `ma.projet` package

```java
package ma.projet;

import java.util.List;

public class Utils {
    /**
     * Lists any list of Personne objects or subclasses.
     * Uses bounded wildcard for flexibility.
     *
     * @param personnes list of Personne or any subclass
     */
    public static void listerPersonnes(List<? extends Personne> personnes) {
        for (Personne p : personnes) {
            p.affiche();  // Polymorphic call
        }
    }
}
```

### Generic Type Explanation

#### `List<? extends Personne>`
**Bounded Wildcard (Upper Bound)**

**What it means:**
- "A list of some type that extends Personne"
- Can be `List<Personne>`, `List<Developpeur>`, `List<Manager>`, etc.

**Why use it:**
- Accepts any subclass list
- Type-safe at compile time
- Flexible and reusable

**Example Usage:**
```java
List<Personne> equipe = new ArrayList<>();
List<Developpeur> devs = new ArrayList<>();
List<Manager> managers = new ArrayList<>();

// All valid calls
Utils.listerPersonnes(equipe);
Utils.listerPersonnes(devs);
Utils.listerPersonnes(managers);
```

## Test Program

### TestPersonnes.java
Located in `ma.projet` package

```java
package ma.projet;

import ma.projet.bean.Developpeur;
import ma.projet.bean.Manager;
import java.util.*;

public class TestPersonnes {
    public static void main(String[] args) {
        // Create heterogeneous list
        List<Personne> equipe = new ArrayList<>();
        equipe.add(new Developpeur("Ali", 2000));
        equipe.add(new Manager("Hamid", 3000));
        equipe.add(new Developpeur("Hanane", 2200));

        // Display each person via generic method
        Utils.listerPersonnes(equipe);
    }
}
```

## Expected Output

```
Je suis Ali, salaire = 2200.00
Je suis Hamid, salaire = 3900.00
Je suis Hanane, salaire = 2420.00
```

**Calculation Details:**
- Ali: 2000 × 1.10 = 2200
- Hamid: 3000 × 1.30 = 3900
- Hanane: 2200 × 1.10 = 2420

## Compilation & Execution

```bash
# Compile all files
javac ma/projet/*.java ma/projet/bean/*.java

# Run test program
java ma.projet.TestPersonnes
```

## Key Concepts Demonstrated

### 1. Abstract Classes
```java
public abstract class Personne {
    public abstract double calculerSalaire();  // No implementation
}
```

**Purpose:**
- Defines contract that subclasses must implement
- Cannot be instantiated directly
- Can have both abstract and concrete methods

**Benefits:**
- Enforces consistent interface
- Provides common behavior (affiche())
- Guarantees all subclasses have calculerSalaire()

### 2. Polymorphism with Abstract Methods
```java
for (Personne p : equipe) {
    p.affiche();  // Calls concrete method
    // Which calls abstract calculerSalaire()
    // Correct implementation invoked at runtime
}
```

### 3. Java Generics - Bounded Wildcards

#### Upper Bound (`? extends T`)
```java
List<? extends Personne> personnes
```
- **Accepts:** Lists of Personne or any subclass
- **Read:** Can read as Personne
- **Write:** Cannot add (except null)

**Use case:** When you need to read from a collection

#### Example with Different Lists
```java
// All valid
List<Developpeur> devs = new ArrayList<>();
devs.add(new Developpeur("Jean", 2500));
Utils.listerPersonnes(devs);  // Works!

List<Manager> mgrs = new ArrayList<>();
mgrs.add(new Manager("Marie", 3500));
Utils.listerPersonnes(mgrs);  // Works!
```

### 4. Protected Access
- `nom` and `salaireBase` are protected
- Accessible to subclasses
- Not accessible to outside classes

### 5. Final Fields
```java
protected final String nom;
protected final double salaireBase;
```
- Cannot be modified after construction
- Ensures immutability of core data

## Advanced Generic Concepts

### Lower Bound (? super T)
```java
public static void ajouterDeveloppeurs(
    List<? super Developpeur> list
) {
    list.add(new Developpeur("New Dev", 2000));
}
```
- **Accepts:** Lists of Developpeur or any superclass
- **Read:** Can only read as Object
- **Write:** Can add Developpeur objects

**Use case:** When you need to write to a collection

### PECS Principle
**Producer Extends, Consumer Super**

- **Extends** (`? extends T`): Use when reading (producing) from collection
- **Super** (`? super T`): Use when writing (consuming) to collection

## Verification Checklist
- [ ] Cannot instantiate Personne directly
- [ ] All subclasses implement calculerSalaire()
- [ ] Generic method accepts different list types
- [ ] Polymorphism works (correct salary calculated)
- [ ] Output format matches expected
- [ ] Salary calculations are accurate

## Possible Extensions

### 1. Add Seniority Factor
```java
public abstract class Personne {
    protected int anciennete;  // Years of service

    public abstract double calculerSalaire();

    // Bonus based on seniority
    protected double bonusAnciennete() {
        return salaireBase * (anciennete * 0.02);  // 2% per year
    }
}
```

### 2. Add Intern Class
```java
public class Stagiaire extends Personne {
    public Stagiaire(String nom, double salaireBase) {
        super(nom, salaireBase);
    }

    @Override
    public double calculerSalaire() {
        return salaireBase;  // No bonus
    }
}
```

### 3. Sorting by Salary
```java
public class Personne implements Comparable<Personne> {
    @Override
    public int compareTo(Personne autre) {
        return Double.compare(
            this.calculerSalaire(),
            autre.calculerSalaire()
        );
    }
}
```

### 4. Statistics Methods
```java
public class Utils {
    public static double salaireMoyen(
        List<? extends Personne> personnes
    ) {
        return personnes.stream()
            .mapToDouble(Personne::calculerSalaire)
            .average()
            .orElse(0.0);
    }

    public static Personne salaireLePlusEleve(
        List<? extends Personne> personnes
    ) {
        return personnes.stream()
            .max(Comparator.comparingDouble(
                Personne::calculerSalaire))
            .orElse(null);
    }
}
```

### 5. Department Management
```java
public class Departement {
    private String nom;
    private List<Personne> employes = new ArrayList<>();

    public void ajouterEmploye(Personne p) {
        employes.add(p);
    }

    public double masseSalariale() {
        return employes.stream()
            .mapToDouble(Personne::calculerSalaire)
            .sum();
    }
}
```

### 6. Salary History
```java
public abstract class Personne {
    private List<Double> historiqueBase = new ArrayList<>();

    public void augmenterSalaire(double pourcentage) {
        historiqueBase.add(salaireBase);
        salaireBase *= (1 + pourcentage / 100);
    }
}
```

## Common Mistakes to Avoid

### 1. Trying to Instantiate Abstract Class
```java
// Compile error!
Personne p = new Personne("Test", 1000);

// Correct
Personne p = new Developpeur("Test", 1000);
```

### 2. Forgetting @Override
```java
// Typo - creates new method instead of overriding
public double calculeSalaire() { ... }

// Correct with @Override
@Override
public double calculerSalaire() { ... }
```

### 3. Adding to Extends Wildcard
```java
public static void ajout(List<? extends Personne> list) {
    list.add(new Developpeur("Test", 2000));  // Compile error!
}
```
**Why:** Compiler doesn't know exact type

### 4. Not Calling super()
```java
// May miss initialization
public Developpeur(String nom, double salaire) {
    this.nom = nom;  // Compile error - final not initialized
}

// Correct
public Developpeur(String nom, double salaire) {
    super(nom, salaire);
}
```

## Files
- `Personne.java`: Abstract base class
- `Developpeur.java`: Developer with 10% bonus
- `Manager.java`: Manager with 30% bonus
- `Utils.java`: Generic utility methods
- `TestPersonnes.java`: Test program
- `subject.txt`: Complete exercise specifications

## Learning Outcomes
- Understanding abstract classes and methods
- Forced implementation contracts
- Java generics with bounded wildcards
- PECS principle (Producer Extends, Consumer Super)
- Type-safe collection handling
- Combining polymorphism with generics
