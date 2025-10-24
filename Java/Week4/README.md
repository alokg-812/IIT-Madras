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
