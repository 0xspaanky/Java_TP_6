# TP_5 - Polymorphism, Abstract Classes, and Generics

This repository contains 3 Java exercises focusing on polymorphism, abstract classes, dynamic binding, and Java generics.

## Repository Structure

```
TP_5/
├── Exerc_1/    # Shape Drawing System
├── Exerc_2/    # Multimedia Library Management
└── Exerc_3/    # Person, Developer and Manager with Generics
```

## Exercises Overview

### Exercise 1: Shape Drawing System
Demonstrates fundamental polymorphism with geometric shapes.

**Key Concepts:**
- Basic polymorphism
- Method overriding
- Dynamic binding (late binding)
- Heterogeneous arrays

**Classes:**
- `Forme` (Base shape)
- `Cercle` (Circle with radius)
- `Rectangle` (Width and height)
- `Triangle` (Base and height)

**Features:**
- Single array holds all shape types
- Uniform `dessiner()` method call
- Each shape draws differently
- Demonstrates dynamic method resolution

**Example:**
```java
Forme[] formes = {
    new Cercle("Rouge", 5.0),
    new Rectangle("Bleu", 4.0, 3.0),
    new Triangle("Vert", 6.0, 2.5)
};

for (Forme f : formes) {
    f.dessiner();  // Correct version called automatically
}
```

---

### Exercise 2: Multimedia Library Management
Advanced polymorphism with multiple methods and dynamic arrays.

**Key Concepts:**
- Polymorphism with multiple methods
- Dynamic array expansion
- Special case handling (live streams)
- Heterogeneous collections

**Classes:**
- `Media` (Base media)
- `Audio` (Duration in seconds)
- `Video` (Duration + resolution)
- `LiveStream` (URL, duration = -1)
- `MediaLibrary` (Dynamic container)

**Features:**
- Both `lire()` and `getDuree()` polymorphic
- Library auto-expands when full
- Total duration calculation (excludes live streams)
- Realistic media management

**Example:**
```java
MediaLibrary lib = new MediaLibrary();
lib.add(new Audio("Podcast", 1800));
lib.add(new Video("Tutorial", 900, "1080p"));
lib.add(new LiveStream("Concert", "http://..."));

lib.playAll();  // Polymorphic playback
System.out.println(lib.totalDuration());  // Smart calculation
```

---

### Exercise 3: Person, Developer and Manager with Generics
Combines abstract classes, polymorphism, and Java generics.

**Key Concepts:**
- Abstract classes and methods
- Forced implementation contracts
- Java generics with bounded wildcards
- PECS principle (Producer Extends, Consumer Super)

**Classes:**
- `Personne` (Abstract base with abstract `calculerSalaire()`)
- `Developpeur` (10% salary bonus)
- `Manager` (30% salary bonus)
- `Utils` (Generic utility methods)

**Features:**
- Abstract method forces implementation
- Generic method accepts any Person subclass list
- Type-safe collection handling
- Polymorphic salary calculation

**Example:**
```java
List<Personne> equipe = new ArrayList<>();
equipe.add(new Developpeur("Ali", 2000));
equipe.add(new Manager("Hamid", 3000));

// Generic method works with any Person list
Utils.listerPersonnes(equipe);
```

---

## Progressive Learning Path

### Level 1: Basic Polymorphism (Exercise 1)
- **Concept:** Variable type vs. object type
- **Mechanism:** Dynamic method invocation
- **Pattern:** Single method override (`dessiner()`)
- **Difficulty:** Beginner

### Level 2: Advanced Polymorphism (Exercise 2)
- **Concept:** Multiple method overrides
- **Mechanism:** Dynamic binding with special cases
- **Pattern:** Container with polymorphic operations
- **Difficulty:** Intermediate

### Level 3: Abstraction + Generics (Exercise 3)
- **Concept:** Abstract contracts + type parameters
- **Mechanism:** Forced implementation + bounded wildcards
- **Pattern:** Generic algorithms on heterogeneous collections
- **Difficulty:** Advanced

## Core Concepts Explained

### Polymorphism
The ability of objects of different types to be accessed through the same interface.

**Key Points:**
- **Compile-time type:** Variable declaration type
- **Runtime type:** Actual object type
- **Dynamic binding:** Method resolved at runtime
- **Benefit:** Write generic, extensible code

**Example:**
```java
Forme f = new Cercle("Rouge", 5.0);
// Compile-time type: Forme
// Runtime type: Cercle
f.dessiner();  // Calls Cercle.dessiner()
```

### Abstract Classes
Classes that cannot be instantiated and may contain abstract methods.

**Purpose:**
- Define contracts that subclasses must implement
- Provide common functionality
- Force consistent interface

**Example:**
```java
public abstract class Personne {
    // Abstract - must be implemented
    public abstract double calculerSalaire();

    // Concrete - inherited by all
    public void affiche() { ... }
}
```

### Java Generics

#### Bounded Wildcards
**Upper Bound (`? extends T`):**
```java
List<? extends Personne> personnes
```
- Accepts lists of Personne or any subclass
- Can read as Personne
- Cannot add (except null)
- **Use:** When reading from collection

**Lower Bound (`? super T`):**
```java
List<? super Developpeur> list
```
- Accepts lists of Developpeur or any superclass
- Can add Developpeur objects
- Can only read as Object
- **Use:** When writing to collection

#### PECS Principle
**Producer Extends, Consumer Super**
- **Producer** (reading): Use `? extends T`
- **Consumer** (writing): Use `? super T`

## Comparison Table

| Feature | Exercise 1 | Exercise 2 | Exercise 3 |
|---------|-----------|-----------|-----------|
| Inheritance Type | Concrete | Concrete | Abstract |
| Methods Override | 1 (`dessiner`) | 2 (`lire`, `getDuree`) | 1 (`calculerSalaire`) |
| Container | Array | Dynamic Array | List (Java Collections) |
| Special Features | Basic shapes | Live streams (-1) | Generics |
| Complexity | Simple | Moderate | Complex |
| Java Features | Basic OOP | Polymorphism | Abstract + Generics |

## Dynamic Binding Explained

### What is Dynamic Binding?
Method resolution happens at **runtime** based on actual object type.

### How It Works
1. Compiler checks method exists in declared type
2. At runtime, JVM looks at actual object type
3. Finds and invokes the correct overridden method

### Example
```java
Media m = new Video("Tutorial", 900, "1080p");
m.lire();  // Calls Video.lire() at runtime

// Compile time: Media has lire()? ✓
// Runtime: m is Video, call Video.lire()
```

### Benefits
- Write code once, works for all subtypes
- Add new types without modifying existing code
- Flexible and maintainable architecture

## Technologies
- **Language:** Java 8+
- **Concepts:** Polymorphism, Abstract Classes, Generics
- **Patterns:** Dynamic binding, Type parameters, Bounded wildcards

## Compilation & Execution

### Standard Structure (Exercises 1, 2)
```bash
# Navigate to exercise directory
cd Exerc_X

# Compile
cd src
javac com/example/tp/*.java

# Run
java com.example.tp.Main
```

### Multi-Package (Exercise 3)
```bash
# Navigate to Exerc_3
cd Exerc_3

# Compile multiple packages
javac ma/projet/*.java ma/projet/bean/*.java

# Run
java ma.projet.TestPersonnes
```

## Learning Objectives

### Fundamental Concepts
- Understanding polymorphism and dynamic binding
- Overriding methods with `@Override`
- Heterogeneous collections
- Protected access for inheritance

### Intermediate Concepts
- Abstract classes and methods
- Forced implementation patterns
- Dynamic array management
- Special case handling in polymorphic code

### Advanced Concepts
- Java generics with type parameters
- Bounded wildcards (extends, super)
- PECS principle application
- Combining abstraction with generics

## Key Design Principles

### Open/Closed Principle
**Open for extension, closed for modification**

```java
// Add new shape - no Main modification needed
public class Ellipse extends Forme {
    @Override
    public void dessiner() { ... }
}

// Existing loop continues to work
for (Forme f : formes) {
    f.dessiner();
}
```

### Liskov Substitution Principle
**Subtypes must be substitutable for base types**

```java
// Any Media can be used where Media is expected
Media m1 = new Audio(...);
Media m2 = new Video(...);
Media m3 = new LiveStream(...);

// All valid, all work correctly
m1.lire();
m2.lire();
m3.lire();
```

### Dependency Inversion Principle
**Depend on abstractions, not concretions**

```java
// Depend on abstract Personne
public static void listerPersonnes(
    List<? extends Personne> personnes
) {
    // Works for any Personne subclass
}
```

## Common Patterns Demonstrated

### Template Method Pattern
```java
// Exercise 3
public abstract class Personne {
    public void affiche() {
        // Template method
        System.out.printf("Je suis %s, salaire = %.2f%n",
            nom, calculerSalaire());  // Abstract method
    }
    public abstract double calculerSalaire();
}
```

### Strategy Pattern
```java
// Exercise 2
class MediaLibrary {
    public void playAll() {
        for (Media m : items) {
            m.lire();  // Strategy selected at runtime
        }
    }
}
```

## Best Practices

### 1. Always Use @Override
```java
@Override
public void dessiner() {
    // Compiler catches typos and signature errors
}
```

### 2. Prefer Composition for Behavior
```java
// Good for "has-a" relationships
class Video extends Media {
    private Resolution resolution;  // Composition
}
```

### 3. Use Abstract Classes for IS-A + Common Code
```java
public abstract class Personne {
    // Common code
    public void affiche() { ... }

    // Force implementation
    public abstract double calculerSalaire();
}
```

### 4. Apply PECS for Generics
```java
// Reading (producing)
void process(List<? extends Media> media) { ... }

// Writing (consuming)
void add(List<? super Audio> media) { ... }
```

## Common Pitfalls to Avoid

### 1. Type Checking Instead of Polymorphism
```java
// Bad
if (m instanceof Audio) {
    ((Audio)m).lire();
} else if (m instanceof Video) {
    ((Video)m).lire();
}

// Good
m.lire();  // Let polymorphism handle it
```

### 2. Forgetting Abstract Method Implementation
```java
// Compile error if not implemented
public class Developpeur extends Personne {
    // Must implement calculerSalaire()
}
```

### 3. Unsafe Casting
```java
// Dangerous
Cercle c = (Cercle) forme;  // May throw ClassCastException

// Safe
if (forme instanceof Cercle) {
    Cercle c = (Cercle) forme;
}
```

### 4. Adding to Extends Wildcard
```java
// Compile error
void bad(List<? extends Media> list) {
    list.add(new Audio(...));  // Can't add!
}
```

## Verification Tips

For all exercises:
- [ ] Polymorphic calls invoke correct methods
- [ ] Cannot instantiate abstract classes (Exerc 3)
- [ ] All abstract methods implemented
- [ ] Generic methods accept appropriate types
- [ ] No ClassCastException errors
- [ ] Output matches specifications

## Extensions and Projects

### Combined Project Ideas
1. **Media Library with Shapes**
   - Store thumbnails as shapes
   - Polymorphic rendering

2. **Employee Media Creator**
   - Developers create Audio tutorials
   - Managers create Video presentations
   - Use both hierarchies together

3. **Generic Shape Library**
   - Apply generics to shape collections
   - Type-safe shape operations

## Documentation
Each exercise directory contains:
- `README.md`: Detailed specifications with examples
- `subject.txt`: Original French requirements
- Java source files: Complete implementations
- `image.png`: UML or concept diagrams

---

**Course:** Advanced Object-Oriented Programming with Java
**Institution:** FST
**Focus:** Polymorphism, Abstract Classes, and Generics
**Difficulty:** Intermediate to Advanced

## Key Takeaways

1. **Polymorphism** enables writing generic, flexible code
2. **Dynamic binding** resolves methods at runtime based on object type
3. **Abstract classes** enforce implementation contracts
4. **Generics** provide type-safe, reusable algorithms
5. Combining these concepts creates powerful, maintainable systems
