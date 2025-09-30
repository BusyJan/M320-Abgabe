# JavaDoc Tool - Complete Guide

## What is JavaDoc?

**JavaDoc** is a documentation generator tool in Java that creates standard API documentation in HTML format. It parses JavaDoc comments (`/** ... */`) from source files and generates comprehensive documentation for classes, methods, constructors, and fields.

### Key Features:
- Generates HTML documentation automatically
- Parses special JavaDoc comments with `/**` syntax
- Creates cross-references between classes and methods
- Supports HTML tags within comments
- Comments are removed at compile time (no performance impact)

## JavaDoc Comment Types

```java
// Single-Line Comment (not JavaDoc)
/* 
 * Multiple-Line comment (not JavaDoc)
 */
/** 
 * JavaDoc comment - THIS is what JavaDoc tool reads
 */
```

## JavaDoc Format

JavaDoc comments have two parts:
1. **Description** - Free-form text describing the element
2. **Block Tags** - Structured information using @ symbols

## Essential JavaDoc Tags

| Tag | Parameter | Description |
|-----|-----------|-------------|
| `@author` | author_name | Describes the author |
| `@param` | description | Information about method parameters |
| `@return` | description | Describes the return value |
| `@throws` | exception description | Documents exceptions that can be thrown |
| `@see` | reference | Creates links to other documentation elements |
| `@version` | version_name | Provides version information |
| `@since` | version | Indicates when the feature was introduced |
| `@deprecated` | description | Marks elements as deprecated |

## How to Generate JavaDoc

### Command Line
```bash
# Generate for single file
javadoc ClassName.java

# Generate for package
javadoc package_name

# Generate with options
javadoc -d docs -sourcepath src -subpackages com.example
```

### Eclipse IDE
1. Right-click project → `Generate Javadoc...`
2. Select destination folder
3. Choose packages/classes to document
4. Configure visibility (public, protected, package, private)
5. Click `Finish`

### IntelliJ IDEA
1. `Tools` → `Generate JavaDoc...`
2. Set output directory
3. Configure scope and options
4. Click `OK`

## Practical Example: V2 Vehicle Project

Here's how JavaDoc is applied to our vehicle inheritance project:

### Base Class Documentation
```java
/**
 * Repräsentiert ein allgemeines Fahrzeug mit Marke und Kilometerstand.
 * Diese Klasse dient als Basisklasse für spezifische Fahrzeugtypen wie Auto, 
 * Motorrad und LKW. Sie demonstriert Vererbung und Polymorphismus.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class Fahrzeug {
    /** Die Marke des Fahrzeugs */
    private String marke;
    
    /** Der aktuelle Kilometerstand des Fahrzeugs */
    private int kilometerstand;

    /**
     * Konstruktor für ein neues Fahrzeug.
     * 
     * @param marke Die Marke des Fahrzeugs (z.B. "BMW", "Mercedes")
     * @param kilometerstand Der aktuelle Kilometerstand
     * @throws IllegalArgumentException wenn marke null oder leer ist
     */
    public Fahrzeug(String marke, int kilometerstand) {
        this.marke = marke;
        this.kilometerstand = kilometerstand;
    }

    /**
     * Berechnet die Reparaturkosten basierend auf dem Fahrzeugtyp.
     * Diese Methode sollte in Unterklassen überschrieben werden, um 
     * spezifische Kostenberechnungen zu implementieren.
     * 
     * @return Die geschätzten Reparaturkosten in Euro
     */
    public double berechneReparaturKosten() {
        return 100.0;
    }
}
```

### Subclass Documentation
```java
/**
 * Repräsentiert ein Auto als spezifischen Fahrzeugtyp.
 * Erbt von Fahrzeug und überschreibt die Kostenberechnung für Autos.
 * Autos haben moderate Reparaturkosten basierend auf dem Kilometerstand.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class Auto extends Fahrzeug {
    
    /**
     * Berechnet die Reparaturkosten für ein Auto.
     * Autos haben moderate Kosten: 0.2€ pro Kilometer plus 200€ Grundkosten.
     * 
     * @return Die berechneten Reparaturkosten in Euro
     */
    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.2 + 200;
    }
}
```

## Best Practices for JavaDoc

### ✅ DO Comment:
- **Classes**: Purpose, inheritance relationships, usage examples
- **Public methods**: Parameters, return values, exceptions, behavior
- **Constructors**: Parameter validation, initialization details
- **Complex algorithms**: Business logic, calculation formulas
- **Overridden methods**: Differences from parent class

### ❌ DON'T Comment:
- Obvious code (getters/setters without logic)
- Self-documenting variable names
- Code that's already clear
- Implementation details in public API docs

### Documentation Standards:
```java
/**
 * Brief description (first sentence becomes summary).
 * 
 * Longer description explaining the method's purpose,
 * behavior, and any important details. Can include
 * multiple paragraphs and HTML formatting.
 * 
 * @param paramName Description of parameter
 * @return Description of return value
 * @throws ExceptionType When this exception occurs
 * @see RelatedClass#method()
 * @since 1.0
 * @author Author Name
 */
```

## Generating Documentation for V2 Project

To generate JavaDoc for our vehicle project:

```bash
# Navigate to Tasks/V2 directory
cd Tasks/V2

# Generate JavaDoc
javadoc -d javadoc -sourcepath . *.java

# Open the generated documentation
# Look for index.html in the javadoc folder
```

## What You Get

After running JavaDoc, you'll have:
- **index.html** - Main documentation page
- **Class pages** - Individual documentation for each class
- **Cross-references** - Links between related classes
- **Inheritance diagrams** - Visual class hierarchies
- **Method signatures** - Complete API documentation

## Project Structure After JavaDoc Generation

```
Tasks/V2/
├── Fahrzeug.java          (source with JavaDoc comments)
├── Auto.java              (source with JavaDoc comments)
├── Motorrad.java          (source with JavaDoc comments)
├── LKW.java               (source with JavaDoc comments)
├── GarageSimulation.java  (source with JavaDoc comments)
└── javadoc/               (generated documentation)
    ├── index.html         (main documentation)
    ├── Fahrzeug.html      (class documentation)
    ├── Auto.html          (class documentation)
    └── ...                (other generated files)
```

## Conclusion

JavaDoc is essential for:
- **Professional documentation** - Clear API descriptions
- **Team collaboration** - Shared understanding of code
- **Maintenance** - Easier to modify and extend code
- **Learning** - Self-documenting codebase

The V2 vehicle project demonstrates proper JavaDoc usage with inheritance, polymorphism, and method overriding - all clearly documented for future developers and maintainers.
