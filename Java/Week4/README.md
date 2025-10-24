# Week 4
## 1\. Grouping Classes and the Need for Abstraction

[cite\_start]The class hierarchy is used to **group together related classes**65]. [cite\_start]For example, the classes **`Circle`**, **`Square`**, and **`Rectangle`** are all types of **`Shape`**4].

A common practice is to:

1.  [cite\_start]**Create a parent class** (e.g., `Shape`) that the specific classes (`Circle`, `Square`, `Rectangle`) **extend**2, 40].
2.  [cite\_start]**Force every subclass to define a specific function** (e.g., `public double perimeter()`)1, 52].

### The Problem with a Concrete Parent Method

[cite\_start]If you define a concrete `perimeter()` function in the `Shape` class, it would return an "absurd value" (e.g., `-1.0`) because a generic `Shape` doesn't have a meaningful perimeter3, 55]. [cite\_start]This approach relies on the subclass to **redefine (override)** this function6].

[cite\_start]**The Risk**: If a programmer forgets to redefine the method in a subclass, the class will incorrectly inherit the absurd default value, which is a logic error that **should not depend on programmer discipline**0, 71].

-----

## 2\. Abstract Methods and Classes

### Abstract Method

[cite\_start]The **better solution** is to provide an **abstract definition** for the required function in the parent class7, 78].

  * [cite\_start]An **abstract method** has a signature but **no body** (no implementation)9].
  * [cite\_start]**Syntax Example**: `public abstract double perimeter();`9].
  * [cite\_start]It **forces subclasses** to provide a concrete implementation8, 266].

### Abstract Class

[cite\_start]Any class that contains one or more **abstract methods** must itself be declared as **`abstract`**08, 109, 267].

  * **Syntax Example**:
    ````java
    public abstract class Shape {
        // ...
        public abstract double perimeter();
    }
    ``` 10, 112, 113]

    ````

### Key Properties of Abstract Classes

| Property | Description |
| :--- | :--- |
| **Instantiation** | [cite\_start]You **cannot create objects** from an abstract class8, 268]. You cannot do `new Shape()`. |
| **Variables** | [cite\_start]You **can still declare variables** whose type is an abstract class20, 269]. This is useful for polymorphism (see example below). |

### Example of Abstract Class Usage (Polymorphism)

[cite\_start]You can create an array of `Shape` objects, and each element can hold a concrete subclass object (like `Circle`, `Square`, or `Rectangle`)27, 131, 133, 135].

````java
Shape shapeArr[] = new Shape[3]; // Variable declaration is allowed
shapeArr[0] = new Circle(...);    // Stores a concrete object
shapeArr[1] = new Square(...);    // Stores a concrete object

// Calling the method on the array calls the appropriate subclass method (Polymorphism)
shapeArr[i].perimeter(); // Calls Circle's perimeter, then Square's, etc.
``` 27, 131, 133, 142, 143]

---

## 3. Generic Functions and Abstract Classes

Abstract classes can be used to describe **capabilities**, allowing for the creation of **generic functions** that operate on various object types49, 270].

### Example: The `Comparable` Abstract Class

An abstract class called `Comparable` can be defined to force any class that extends it to have a comparison function:

```java
public abstract class Comparable {
    // Forces the subclass to implement a comparison logic
    public abstract int cmp(Comparable s); 
    // Returns: -1 if this < s, 0 if this == s, +1 if this > s
}
``` 50, 151, 152, 156, 157]

A **generic sort function** can then be written in a class like `SortFunctions`. This function can sort **any array of objects** that extend `Comparable`, using `a[i].cmp(a[j])` for comparisons72, 173, 177].

```java
public class SortFunctions {
    // Can sort any array 'a' whose elements extend Comparable
    public static void quicksort(Comparable[] a) {
        // ... uses a[i].cmp(a[j]) to compare elements
    }
}
``` 73, 174, 177]

---

## 4. Interfaces and Multiple Inheritance

### The Problem of Multiple Inheritance

Java classes can only **extend one parent class** (single inheritance)16, 259, 272].

**Example**: A `Circle` class *already* extends `Shape`. If you want to use the generic sort function, `Circle` would also need to extend `Comparable`, but Java does not allow this second extension14, 215, 216].

### The Solution: Interfaces

An **interface** is essentially an **abstract class with no concrete components**25, 251, 271]. It defines a contract of required methods.

* **Syntax**: Use the keyword `interface`25].

```java
public interface Comparable {
    public abstract int cmp(Comparable s); 
}
```


### Implementing an Interface

A class that "extends" an interface is said to **implement** it39, 255].

* A class can **extend only one parent class**, but it can **implement multiple interfaces**59, 272].

**Example**: `Circle` can extend `Shape` (the single parent class) and implement `Comparable` (one of many possible interfaces):

```java
public class Circle extends Shape implements Comparable {
    public double perimeter() { /* ... implementation ... */ } // From Shape
    public int cmp(Comparable s) { /* ... implementation ... */ }  // From Comparable
}
``` 40, 241, 242]

````

## Lecture 2: Interfaces

Here are detailed notes on Java **Interfaces**, including their purpose, methods, and conflict resolution, with Java examples.

# Java Interfaces Notes 📝

## 1\. Core Concept of Interfaces

[cite\_start]An **Interface** is a purely **abstract class**[cite: 8]. [cite\_start]Its primary purpose is to express **abstract capabilities** [cite: 200] [cite\_start]or a specific "slice" of capabilities for a class[cite: 15].

| Feature | Description |
| :--- | :--- |
| **Methods** | [cite\_start]All methods in a classic interface are implicitly **abstract** (no method body)[cite: 9]. |
| **Inheritance** | [cite\_start]A class **implements** an interface[cite: 10]. |
| **Multiple Inheritance** | [cite\_start]Classes can **implement multiple interfaces**[cite: 12]. [cite\_start]This works because abstract functions have no body, so there's **no contradictory inheritance** of implementation[cite: 13]. |
| **Implementation** | [cite\_start]The implementing class must **provide concrete code** for every abstract function defined in the interface[cite: 11]. |

### Example: The `Comparable` Interface

[cite\_start]An interface can be used to describe the capability of an object being **comparable**[cite: 51].

```java
// Interface definition
public interface Comparable {
    // All methods are implicitly public and abstract
    public abstract int cmp(Comparable s); 
    /* * return -1 if this < s
     * return 0 if this == s 
     * return +1 if this > s 
     */
}

// Class implementing the interface
public class MyInteger implements Comparable {
    private int value;

    public MyInteger(int v) {
        this.value = v;
    }

    // Must provide a concrete implementation for cmp()
    @Override
    public int cmp(Comparable s) {
        if (s instanceof MyInteger) {
            MyInteger other = (MyInteger) s;
            if (this.value < other.value) return -1;
            if (this.value > other.value) return 1;
            return 0;
        }
        // Handle comparison with other types or throw an error
        return 0; 
    }
}
```

-----

## 2\. Using Interfaces for Limited Capabilities

[cite\_start]Interfaces are crucial for enabling **generic functions** that operate on various data types[cite: 21, 65]. [cite\_start]They ensure that another class only needs to know about the **relevant capabilities** exposed by the interface[cite: 15]. [cite\_start]All other aspects of the underlying type are **irrelevant** to the generic function[cite: 30, 73].

### Example: Generic Quicksort Function

A generic `quicksort` function needs only one capability from the elements it sorts: the ability to be compared.

```java
public class SortFunctions {
    // The argument type is Comparable[], meaning it accepts 
    // an array of *any* object type that implements Comparable.
    [cite_start]public static void quicksort(Comparable[] a) { [cite: 33, 69]
        // ... Usual code for quicksort ...
        
        // To compare two elements a[i] and a[j], 
        // it uses the method defined by the interface.
        if (a[i].cmp(a[j]) < 0) {
            // a[i] is smaller than a[j]
        }
        // ...
    }
}
```

-----

## 3\. Concrete Methods in Interfaces (Java 8+)

[cite\_start]Java interfaces were later **extended** to allow concrete functions to be added, resolving the difficulty in expressing the intended behavior of abstract functions explicitly[cite: 90, 202].

### A. Static Functions

[cite\_start]**Static functions** can be added to an interface[cite: 97, 203].

  * [cite\_start]They **cannot access instance variables**[cite: 98, 205].
  * [cite\_start]They are invoked directly using the **interface name**[cite: 98].

<!-- end list -->

```java
public interface Comparable {
    public abstract int cmp(Comparable s); 

    // Static function definition
    [cite_start]public static String cmpdoc() { [cite: 100]
        String s = "Return -1 if this < s, ";
        s = s + "0 if this == s, ";
        s = s + "+1 if this > s.";
        return(s);
    }
}

// Invocation example:
// String doc = Comparable.cmpdoc(); 
```

### B. Default Functions

[cite\_start]**Default functions** provide a **default implementation** for an interface method[cite: 114, 204].

  * [cite\_start]A class **can override** the default implementation[cite: 115, 206].
  * [cite\_start]They are invoked like a **normal method**, using the object name[cite: 116, 117].

<!-- end list -->

```java
public interface Comparable {
    // ... abstract and static methods ...

    // Default function definition
    [cite_start]public default int cmp(Comparable s) { [cite: 123]
        return(0); // Default behavior: treat all objects as equal
    }
}

// Invocation example (if not overridden):
// int result = obj.cmp(anotherObj); // returns 0
```

-----

## 4\. Conflict Resolution in Multiple Inheritance

[cite\_start]Adding concrete methods (**static/default**) to interfaces **reintroduces conflicts** in multiple inheritance scenarios[cite: 133, 207].

### A. Conflict Between Multiple Interfaces

If a class implements two interfaces that both provide a default method with the **same signature**, the subclass must resolve the conflict.

  * [cite\_start]The **subclass must provide a fresh implementation** of the conflicting method[cite: 153, 208].

<!-- end list -->

```java
public interface Person {
    public default String getName() { return("No name"); } // Default 1
}

public interface Designation {
    public default String getName() { return("No designation"); } // Default 2
}

public class Employee implements Person, Designation {
    // ERROR without this method: must resolve the conflict!
    @Override
    public String getName() { 
        // Developer chooses the actual logic here
        return "Employee's name"; 
    }
}
```

### B. Conflict Between Class and Interface

[cite\_start]If a class inherits a method from a **superclass** and also implements an interface with a conflicting default method, a **special "class wins" rule** applies[cite: 187, 190, 208].

  * [cite\_start]The **method inherited from the class "wins"** (is used)[cite: 190]. [cite\_start]This is motivated by **reverse compatibility**[cite: 191].

<!-- end list -->

```java
public class Person {
    public String getName() { return("No name"); } // Concrete method
}

public interface Designation {
    public default String getName() { return("No designation"); } // Default method
}

public class Employee extends Person implements Designation {
    // No implementation required here. The getName() from Person is used.
}
```

