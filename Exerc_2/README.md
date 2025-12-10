# Exercise 2: Multimedia Library Management

![Exercise 2 Diagram](./image.png)

## Objective
Implement polymorphism in Java by designing a Media class hierarchy with subclasses (Audio, Video, LiveStream), each overriding the `lire()` and `getDuree()` methods. Store these heterogeneous objects in a single array and invoke these methods uniformly to demonstrate dynamic binding.

## Description
This exercise creates a comprehensive multimedia library system that manages different types of media (audio, video, live streams) using polymorphism. The system demonstrates how different media types can be handled uniformly while maintaining their specific behaviors.

## UML Class Diagram

```
   +-------------+           +-----------------+
   |   Media     |◄───────── |     Audio       |
   +-------------+           +-----------------+
   | - titre     |           | - duree : int   |
   +-------------+           +-----------------+
   | + lire()    |           | + lire()        |
   | + getDuree()|           | + getDuree()    |
   +------+------+           +-----------------+
          ▲
          |           +-----------------+
          |           |     Video       |
          |           +-----------------+
          |           | - duree : int   |
          |           | - resolution    |
          |           +-----------------+
          |           | + lire()        |
          |           | + getDuree()    |
          |           +-----------------+
          |
          |           +-----------------+
          |           |   LiveStream    |
          |           +-----------------+
          |           | - url : String  |
          |           +-----------------+
          |           | + lire()        |
          |           | + getDuree()    |
          |           +-----------------+
   +------------------+
   |  MediaLibrary    |
   +------------------+
   | - items[]        |
   | - count : int    |
   +------------------+
   | + add(Media)     |
   | + playAll()      |
   | + totalDuration()|
   +------------------+
```

## Class Structure

### Media (Base Class)
Located in `com.example.tp` package

**Attributes:**
- `titre` (protected String): Media title

**Constructor:**
```java
public Media(String titre)
```

**Methods:**
- `lire()`: Generic playback method
  - Default: "Lecture de : [titre]"
- `getDuree()`: Returns duration in seconds
  - Default: returns 0

### Audio (Audio File)

**Attributes:**
- Inherits `titre` from Media
- `duree` (private int): Duration in seconds

**Constructor:**
```java
public Audio(String titre, int duree)
```

**Overridden Methods:**
```java
@Override
public void lire() {
    System.out.println("Lecture audio : " + titre);
}

@Override
public int getDuree() {
    return duree;
}
```

### Video (Video File)

**Attributes:**
- Inherits `titre` from Media
- `duree` (private int): Duration in seconds
- `resolution` (private String): Video resolution (e.g., "1080p", "4K")

**Constructor:**
```java
public Video(String titre, int duree, String resolution)
```

**Overridden Methods:**
```java
@Override
public void lire() {
    System.out.println("Lecture vidéo : " + titre
        + " [" + resolution + "]");
}

@Override
public int getDuree() {
    return duree;
}
```

### LiveStream (Live Streaming)

**Attributes:**
- Inherits `titre` from Media
- `url` (private String): Stream URL

**Constructor:**
```java
public LiveStream(String titre, String url)
```

**Overridden Methods:**
```java
@Override
public void lire() {
    System.out.println("Démarrage du flux en direct : "
        + titre + " – " + url);
}

@Override
public int getDuree() {
    return -1;  // Duration undetermined for live streams
}
```

**Special Behavior:** Returns -1 for duration (live streams have no fixed duration)

### MediaLibrary (Library Manager)

**Attributes:**
- `items` (private Media[]): Dynamic array of media items
- `count` (private int): Current number of items

**Constructor:**
```java
public MediaLibrary()
```
- Initializes array with capacity of 4

**Methods:**

#### add(Media m)
Adds media to library, expanding array if necessary.
```java
public void add(Media m) {
    if (count == items.length) {
        Media[] tmp = new Media[items.length * 2];
        System.arraycopy(items, 0, tmp, 0, items.length);
        items = tmp;
    }
    items[count++] = m;
}
```

#### playAll()
Plays all media using polymorphism.
```java
public void playAll() {
    System.out.println("=== Lecture de la bibliothèque ===");
    for (int i = 0; i < count; i++) {
        items[i].lire();  // Polymorphic call
    }
}
```

#### totalDuration()
Calculates total duration, ignoring live streams (-1).
```java
public int totalDuration() {
    int sum = 0;
    for (int i = 0; i < count; i++) {
        int d = items[i].getDuree();
        if (d > 0) sum += d;  // Ignore -1 (live streams)
    }
    return sum;
}
```

## Example Usage

```java
public class Main {
    public static void main(String[] args) {
        MediaLibrary lib = new MediaLibrary();

        // Add various media types
        lib.add(new Audio("Podcast Java", 1800));        // 30 min
        lib.add(new Video("Tutoriel UML", 900, "1080p")); // 15 min
        lib.add(new LiveStream("Concert en direct",
                               "http://live.example.com"));
        lib.add(new Audio("Musique Classique", 2400));   // 40 min

        // Play all media polymorphically
        lib.playAll();

        // Calculate total duration
        System.out.println();
        System.out.println("Durée totale (sec) : "
            + lib.totalDuration());
    }
}
```

## Expected Output

```
=== Lecture de la bibliothèque ===
Lecture audio : Podcast Java
Lecture vidéo : Tutoriel UML [1080p]
Démarrage du flux en direct : Concert en direct – http://live.example.com
Lecture audio : Musique Classique

Durée totale (sec) : 5100
```

**Note:** Total is 1800 + 900 + 2400 = 5100 seconds (live stream ignored)

## Compilation & Execution

```bash
# From src/ directory
cd src
javac com/example/tp/*.java
java com.example.tp.Main
```

## Key Concepts Demonstrated

### 1. Polymorphism with Multiple Methods
- Both `lire()` and `getDuree()` are overridden
- Single array (`Media[]`) holds all types
- Correct method version called at runtime

### 2. Dynamic Array Management
- Library starts with capacity 4
- Automatically doubles when full
- No size limit for library

### 3. Special Case Handling
- LiveStream returns -1 for duration
- `totalDuration()` intelligently handles this
- Demonstrates polymorphism with varying behaviors

### 4. Uniform Interface
```java
for (int i = 0; i < count; i++) {
    items[i].lire();  // Works for all media types
}
```
- Same code works for Audio, Video, LiveStream
- Java determines correct method at runtime

## Polymorphism Explanation

### Single Array, Multiple Types
```java
Media[] items = new Media[4];
items[0] = new Audio(...);
items[1] = new Video(...);
items[2] = new LiveStream(...);
```

All stored in one array of type `Media[]`!

### Dynamic Method Resolution
When calling `items[i].lire()`:
1. Java checks actual object type at runtime
2. Invokes the specific override (Audio.lire(), Video.lire(), etc.)
3. This is **dynamic binding** or **late binding**

### getDuree() Polymorphism
```java
int d = items[i].getDuree();
```
- Returns actual duration for Audio/Video
- Returns -1 for LiveStream
- Caller can handle different return values

## Verification Checklist
- [ ] Library accepts all media types
- [ ] Dynamic array expands when needed (test with >4 items)
- [ ] playAll() calls correct lire() for each type
- [ ] totalDuration() correctly sums durations
- [ ] Live streams excluded from duration calculation
- [ ] Output matches expected format
- [ ] No ClassCastException errors

## Possible Extensions

### 1. New Media Type: Podcast
```java
public class Podcast extends Audio {
    private String hote;  // Host name

    public Podcast(String titre, int duree, String hote) {
        super(titre, duree);
        this.hote = hote;
    }

    @Override
    public void lire() {
        System.out.println("Lecture podcast : " + titre
            + " avec " + hote);
    }
}
```

### 2. Add stop() Method
```java
public abstract class Media {
    public void lire() { ... }
    public void stop() {
        System.out.println("Arrêt de : " + titre);
    }
}
```

### 3. Playlist Management
- Create playlists with subsets of media
- Shuffle and repeat functionality
- Play queue system

### 4. Search and Filter
```java
public Media[] findByTitle(String keyword) { ... }
public Media[] findByType(Class<? extends Media> type) { ... }
public Audio[] getAllAudio() { ... }
```

### 5. Persistence
- Save library to JSON file
- Load library from file
- Export playlist to M3U format

### 6. Statistics
```java
public int countByType(Class<? extends Media> type) { ... }
public double getAverageDuration() { ... }
public Media getLongest() { ... }
```

### 7. GUI Integration
- JavaFX media player interface
- Playlist visualization
- Progress bars for playback

### 8. Metadata Enhancement
- Add artist/author field
- Add genre/category
- Add release date
- Add ratings system

## Advanced Features

### Type-Specific Operations
```java
public void enhanceVideo(int index) {
    if (items[index] instanceof Video) {
        Video v = (Video) items[index];
        // Access Video-specific features
        System.out.println("Resolution: " + v.getResolution());
    }
}
```

### Filtering by Duration
```java
public Media[] getMediaLongerThan(int seconds) {
    List<Media> result = new ArrayList<>();
    for (int i = 0; i < count; i++) {
        if (items[i].getDuree() > seconds) {
            result.add(items[i]);
        }
    }
    return result.toArray(new Media[0]);
}
```

## Common Mistakes to Avoid

### 1. Forgetting to Handle -1
```java
// Bad - includes -1 in sum
int sum = 0;
for (int i = 0; i < count; i++) {
    sum += items[i].getDuree();
}

// Good - excludes live streams
int sum = 0;
for (int i = 0; i < count; i++) {
    int d = items[i].getDuree();
    if (d > 0) sum += d;
}
```

### 2. Not Expanding Array
```java
// Bad - silently fails
public void add(Media m) {
    if (count < items.length) {
        items[count++] = m;
    }
}

// Good - expands when needed
public void add(Media m) {
    if (count == items.length) {
        expand();
    }
    items[count++] = m;
}
```

### 3. Unsafe Casting
```java
// Dangerous
Video v = (Video) items[0];  // May fail if not Video

// Safe
if (items[0] instanceof Video) {
    Video v = (Video) items[0];
}
```

## Files
- `Media.java`: Base media class
- `Audio.java`: Audio file with duration
- `Video.java`: Video file with resolution
- `LiveStream.java`: Live stream with URL
- `MediaLibrary.java`: Library manager with dynamic array
- `Main.java`: Test program demonstrating polymorphism
- `subject.txt`: Complete exercise specifications

## Learning Outcomes
- Understanding polymorphism with multiple methods
- Managing heterogeneous collections
- Handling special cases (live streams)
- Dynamic array management techniques
- Designing extensible media systems
