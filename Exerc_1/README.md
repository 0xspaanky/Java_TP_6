# Exercise 1: Shape Drawing System

![Exercise 1 Diagram](./image.png)

## Objective
Learn polymorphism in Java by creating a superclass `Forme` and subclasses that override the `dessiner()` method, demonstrating dynamic binding.

## Key Concepts
- Method overriding with `@Override`
- Polymorphism (one interface, multiple implementations)
- Dynamic binding at runtime
- Heterogeneous arrays

## Implementation

### Forme (Base Class)
```java
package com.example.tp;

public class Forme {
    protected String couleur;

    public Forme(String couleur) {
        this.couleur = couleur;
    }

    public void dessiner() {
        System.out.println("Dessiner une forme de couleur " + couleur);
    }
}
```

### Cercle (Circle)
```java
public class Cercle extends Forme {
    private double rayon;

    public Cercle(String couleur, double rayon) {
        super(couleur);
        this.rayon = rayon;
    }

    @Override
    public void dessiner() {
        System.out.println("Dessiner un cercle de couleur " + couleur +
                           " et de rayon " + rayon);
    }
}
```

### Rectangle
```java
public class Rectangle extends Forme {
    private double largeur, hauteur;

    public Rectangle(String couleur, double largeur, double hauteur) {
        super(couleur);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner() {
        System.out.println("Dessiner un rectangle de couleur " + couleur +
                           " (" + largeur + "x" + hauteur + ")");
    }
}
```

### Triangle
```java
public class Triangle extends Forme {
    private double base, hauteur;

    public Triangle(String couleur, double base, double hauteur) {
        super(couleur);
        this.base = base;
        this.hauteur = hauteur;
    }

    @Override
    public void dessiner() {
        System.out.println("Dessiner un triangle de couleur " + couleur +
                           " (base=" + base + ", hauteur=" + hauteur + ")");
    }
}
```

## Usage Example
```java
public class Main {
    public static void main(String[] args) {
        Forme[] formes = {
            new Cercle("rouge", 5.0),
            new Rectangle("bleu", 10.0, 4.0),
            new Triangle("vert", 6.0, 3.0)
        };

        for (Forme f : formes) {
            f.dessiner();  // Polymorphic call
        }
    }
}
```

## Expected Output
```
Dessiner un cercle de couleur rouge et de rayon 5.0
Dessiner un rectangle de couleur bleu (10.0x4.0)
Dessiner un triangle de couleur vert (base=6.0, hauteur=3.0)
```

## Compilation & Execution
```bash
cd src
javac com/example/tp/*.java
java com.example.tp.Main
```

## How Polymorphism Works
```java
Forme f = new Cercle("rouge", 5.0);  // Superclass reference
f.dessiner();  // Calls Cercle's version, not Forme's!
// Java determines method at RUNTIME based on actual object type
```

## Extensions
- Add `calculerAire()` method to all shapes
- Add `Carre` class extending Rectangle
- Add color validation in constructor
- Implement `Comparable` to sort by area
