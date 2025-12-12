# TP_6 - Polymorphism and Inheritance

This repository contains 3 exercises focusing on polymorphism, inheritance, method overriding, and generics.

## Exercises Overview

### Exercise 1: Shape Drawing System
Basic polymorphism with method overriding.

**Key Concepts:** Method overriding, dynamic binding, heterogeneous arrays

**Hierarchy:**
```
Forme (base)
  ├─ Cercle
  ├─ Rectangle
  └─ Triangle
```

**Example:**
```java
Forme[] formes = {new Cercle("rouge", 5.0), new Rectangle("bleu", 10, 4)};
for (Forme f : formes) {
    f.dessiner();  // Polymorphic call
}
```

---

### Exercise 2: Multimedia Library
Polymorphism with different media types and special cases.

**Key Concepts:** Method overriding, dynamic arrays, special return values

**Hierarchy:**
```
Media (base)
  ├─ Audio (duree in seconds)
  ├─ Video (duree + resolution)
  └─ LiveStream (duree = -1)
```

**Special Feature:** LiveStream returns -1 for getDuree() (undefined duration)

---

### Exercise 3: Person with Generics
Combining inheritance with generic bounded wildcards.

**Key Concepts:** Abstract classes, generics with `? extends`, PECS principle

**Hierarchy:**
```
Personne (abstract)
  ├─ Developpeur (+10%)
  └─ Manager (+30%)
```

**Generic method:**
```java
public static void afficherListe(List<? extends Personne> liste) {
    // Works with List<Developpeur>, List<Manager>, etc.
}
```

---

## Comparison

| Feature | Exerc_1 | Exerc_2 | Exerc_3 |
|---------|---------|---------|---------|
| Base Type | Concrete class | Concrete class | Abstract class |
| Key Method | dessiner() | lire(), getDuree() | calculerSalaire() |
| Collection | Array | Dynamic array | List with generics |
| Special | Basic override | Special values (-1) | Bounded wildcards |

## Key Concepts

### Polymorphism
```java
// Superclass reference, subclass object
Forme f = new Cercle("rouge", 5.0);
f.dessiner();  // Calls Cercle's version at RUNTIME
```

### Method Overriding
```java
@Override  // Always use this annotation
public void dessiner() {
    // Subclass implementation
}
```

### Dynamic Binding
Java determines which method to call at **runtime** based on actual object type, not reference type.

### Abstract Classes (Exerc_3)
```java
public abstract class Personne {
    public abstract double calculerSalaire();  // Must override
}
```

### Generics with Bounds (Exerc_3)
```java
// Accepts List<Personne> and all subclasses
public static void process(List<? extends Personne> liste) {
    // ...
}
```

## Technologies
- Java 8+
- Polymorphism, Inheritance, Generics

## Compilation
```bash
cd Exerc_X/src
javac com/example/tp/*.java  # or ma/projet/*.java
java com.example.tp.Main
```

## Learning Objectives
- Method overriding with `@Override`
- Polymorphism and dynamic binding
- Heterogeneous collections
- Abstract classes
- Generic bounded wildcards
- PECS principle

---

**Course:** Advanced OOP with Java
**Institution:** FST
**Focus:** Polymorphism and Inheritance
