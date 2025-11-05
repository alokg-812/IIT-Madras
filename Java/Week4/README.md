# Week 4
## Lecture 1: Abstract Classes
### Grouping Classes and the Need for Abstraction

The class hierarchy is used to **group together related classes**. For example, the classes **`Circle`**, **`Square`**, and **`Rectangle`** are all types of **`Shape`**.

A common practice is to:

1.  **Create a parent class** (e.g., `Shape`) that the specific classes (`Circle`, `Square`, `Rectangle`) **extend**.
2.  **Force every subclass to define a specific function** (e.g., `public double perimeter()`).

### The Problem with a Concrete Parent Method

If we define a concrete `perimeter()` function in the `Shape` class, it would return an "absurd value" (e.g., `-1.0`) because a generic `Shape` doesn't have a meaningful perimeter. This approach relies on the subclass to **redefine (override)** this function.

**The Risk**: If a programmer forgets to redefine the method in a subclass, the class will incorrectly inherit the absurd default value, which is a logic error that **should not depend on programmer discipline**.


### Abstract Methods and Classes

### Abstract Method

The **better solution** is to provide an **abstract definition** for the required function in the parent class.
  * An **abstract method** has a signature but **no body** (no implementation).
  * **Syntax Example**: `public abstract double perimeter();`.
  * It **forces subclasses** to provide a concrete implementation.

### Abstract Class
Any class that contains one or more **abstract methods** must itself be declared as **`abstract`**.

  * **Syntax Example**:
    ````java
    public abstract class Shape {
        // ...
        public abstract double perimeter();
    }
    ````

### Key Properties of Abstract Classes

| Property | Description |
| :--- | :--- |
| **Instantiation** | We **cannot create objects** from an abstract class. We cannot do `new Shape()`. |
| **Variables** | We **can still declare variables** whose type is an abstract class. This is useful for polymorphism (see example below). |

#### Example of Abstract Class Usage (Polymorphism)

We can create an array of `Shape` objects, and each element can hold a concrete subclass object (like `Circle`, `Square`, or `Rectangle`).

````java
Shape shapeArr[] = new Shape[3]; // Variable declaration is allowed
shapeArr[0] = new Circle(...);    // Stores a concrete object
shapeArr[1] = new Square(...);    // Stores a concrete object

// Calling the method on the array calls the appropriate subclass method (Polymorphism)
shapeArr[i].perimeter(); // Calls Circle's perimeter, then Square's, etc.
````

### Generic Functions and Abstract Classes

Abstract classes can be used to describe **capabilities**, allowing for the creation of **generic functions** that operate on various object types.

#### Example: The `Comparable` Abstract Class

An abstract class called `Comparable` can be defined to force any class that extends it to have a comparison function:

```java
public abstract class Comparable {
    // Forces the subclass to implement a comparison logic
    public abstract int cmp(Comparable s); 
    // Returns: -1 if this < s, 0 if this == s, +1 if this > s
}
```

A **generic sort function** can then be written in a class like `SortFunctions`. This function can sort **any array of objects** that extend `Comparable`, using `a[i].cmp(a[j])` for comparisons.

```java
public class SortFunctions {
    // Can sort any array 'a' whose elements extend Comparable
    public static void quicksort(Comparable[] a) {
        // ... uses a[i].cmp(a[j]) to compare elements
    }
}
```

### Interfaces and Multiple Inheritance

### The Problem of Multiple Inheritance

Java classes can only **extend one parent class** (single inheritance).

**Example**: A `Circle` class *already* extends `Shape`. If you want to use the generic sort function, `Circle` would also need to extend `Comparable`, but Java does not allow this second extension.

### The Solution: Interfaces

An **interface** is essentially an **abstract class with no concrete components**. It defines a contract of required methods.

* **Syntax**: Use the keyword `interface`.

```java
public interface Comparable {
    public abstract int cmp(Comparable s); 
}
```


### Implementing an Interface

A class that "extends" an interface is said to **implement** it.

* A class can **extend only one parent class**, but it can        **implement multiple interfaces**.

**Example**: `Circle` can extend `Shape` (the single parent class) and implement `Comparable` (one of many possible interfaces):

```java
public class Circle extends Shape implements Comparable {
    public double perimeter() { /* ... implementation ... */ } // From Shape
    public int cmp(Comparable s) { /* ... implementation ... */ }  // From Comparable
}
```

## Lecture 2: Interfaces

### Core Concept of Interfaces

An **Interface** is a purely **abstract class**. Its primary purpose is to express **abstract capabilities** or a specific "slice" of capabilities for a class.

| Feature | Description |
| :--- | :--- |
| **Methods** | All methods in a classic interface are implicitly **abstract** (no method body). |
| **Inheritance** | A class **implements** an interface. |
| **Multiple Inheritance** | Classes can **implement multiple interfaces**. This works because abstract functions have no body, so there's **no contradictory inheritance** of implementation. |
| **Implementation** | The implementing class must **provide concrete code** for every abstract function defined in the interface. |

#### Example: The `Comparable` Interface

An interface can be used to describe the capability of an object being **comparable**.

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


### Using Interfaces for Limited Capabilities

Interfaces are crucial for enabling **generic functions** that operate on various data types. They ensure that another class only needs to know about the **relevant capabilities** exposed by the interface. All other aspects of the underlying type are **irrelevant** to the generic function.

#### Example: Generic Quicksort Function

A generic `quicksort` function needs only one capability from the elements it sorts: the ability to be compared.

```java
public class SortFunctions {
    // The argument type is Comparable[], meaning it accepts 
    // an array of *any* object type that implements Comparable.
    public static void quicksort(Comparable[] a) {
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

### Concrete Methods in Interfaces (Java 8+)

Java interfaces were later **extended** to allow concrete functions to be added, resolving the difficulty in expressing the intended behavior of abstract functions explicitly.

### A. Static Functions

**Static functions** can be added to an interface.
  * They **cannot access instance variables**.
  * They are invoked directly using the **interface name**.

<!-- end list -->

```java
public interface Comparable {
    public abstract int cmp(Comparable s); 

    // Static function definition
    public static String cmpdoc() {
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

**Default functions** provide a **default implementation** for an interface method.

  * A class **can override** the default implementation.
  * They are invoked like a **normal method**, using the object name.

<!-- end list -->

```java
public interface Comparable {
    // ... abstract and static methods ...

    // Default function definition
    public default int cmp(Comparable s) {
        return(0); // Default behavior: treat all objects as equal
    }
}

// Invocation example (if not overridden):
// int result = obj.cmp(anotherObj); // returns 0
```


### Conflict Resolution in Multiple Inheritance

Adding concrete methods (**static/default**) to interfaces **reintroduces conflicts** in multiple inheritance scenarios.

### A. Conflict Between Multiple Interfaces

If a class implements two interfaces that both provide a default method with the **same signature**, the subclass must resolve the conflict.

  * The **subclass must provide a fresh implementation** of the conflicting method.

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

If a class inherits a method from a **superclass** and also implements an interface with a conflicting default method, a **special "class wins" rule** applies.

  * The **method inherited from the class "wins"** (is used). This is motivated by **reverse compatibility**.

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

## Lecture 3: Private Classes

The concept of nested objects, encapsulation, and their specific use cases.

### Nested Objects

An object can have **nested objects** as its **instance variables**. This is a common form of composition, where one class uses another class to define its state.

### Example: `Employee` using `Date`

In this standard scenario, `Employee` uses `Date` for its `joindate`.

```java
// Date is a public class, available to other classes
public class Date {
    private int day, month, year; // Instance variables for Date
}

public class Employee {
    private String name;
    private double salary;
    private Date joindate; // Instance variable is a user-defined type
}
```

In this case, `Date` is a **public class** and is accessible to any other class in the application.

### The Need for Private Classes

In some situations, the **structure of nested objects need not be exposed** to the outside world.

### Use Case: The `LinkedList` and `Node` Structure

A `LinkedList` is built using a **`Node`** class. The `Node` class defines the internal structure of the list.

If `Node` is a **public class**, it exposes its internal details (like `data` and `next`) to anyone using the `LinkedList`.

```java
// Node is public, exposing its structure
public class Node {
    public Object data;
    public Node next;
}

public class LinkedList {
    private int size;
    private Node first; // Uses Node internally

    public Object head() { /* ... implementation ... */ } // Interface of LinkedList
    // ...
}
```

The internal structure of the list (whether it has a `next` pointer or is a doubly linked list with a `prev` field) **does not affect the public interface** of the `LinkedList` (like `head()` or `insert()`). Therefore, the internal `Node` class should be hidden.

### The Solution: Inner Classes

Instead of making `Node` a public class, we can make it a **private class**. A private class that is defined inside another class is also called an **inner class**.

  * **Definition:** The inner class is **nested within** the enclosing class (`LinkedList`).

<!-- end list -->

```java
public class LinkedList {
    private int size;
    private Node first; // The inner class Node is used internally

    // This is the public interface for the users of LinkedList
    public Object head() { 
        // ... uses 'first.data' and 'first.next'
    }

    // The Node class is hidden from external classes
    private class Node { // Declared as private: only accessible within LinkedList
        public Object data;
        public Node next;
    }
}
```

### Benefits of Private Classes

Private classes provide an **additional degree of data encapsulation**.

| Concept | Description |
| :--- | :--- |
| **Encapsulation** | Hiding the `Node` class prevents external code from manipulating the list's internal structure directly, ensuring the `LinkedList` remains consistent. |
| **Access Control** | Objects of the private class (`Node` objects) can **see the private components of the enclosing class** (`LinkedList`). |
| **Controlled Access** | Private classes can be combined with interfaces to provide **controlled access** to an object's state. |

In the `LinkedList` example, by making `Node` private, the details of how the list is constructed are entirely internal to the `LinkedList` class, making the overall structure more secure and easier to modify later (e.g., changing it to a doubly linked list).

## Lecture 4: Interaction with States

### Encapsulation and Data Integrity

**Encapsulation** is a core principle of object-oriented programming.

  * **Internal data** is kept **private**.
  * **Access to the data** is regulated through **public methods**. These methods are often called **accessor** (getter) and **mutator** (setter) methods.
  * The primary benefit is that you can **ensure data integrity by regulating access**.

#### Example: Regulating `Date` Access

Instead of allowing separate, uncontrolled updates to individual components, a single mutator method can ensure the combination is valid.

```java
public class Date {
    private int day, month, year; // Internal data is private

    // Accessor (Getter) methods
    public int getDay() { /* ... returns day ... */ } 
    // ... getMonth(), getYear() ...

    // Mutator (Setter) method that regulates access
    public void setDate(int d, int m, int y) {
        // Validate d-m-y combination here
        // ... Logic to check if the date is valid ...
        this.day = d;
        this.month = m;
        this.year = y;
        // Update date as a whole, rather than individual components
    }
}
```

### Controlled Interaction with State

Sometimes, simply validating the input data isn't sufficient; the validity of an action depends on the **current state** of the system or the user's interaction history (an "**Interaction with state**").

### Scenario: Regulating Database Queries

Consider a `RailwayBooking` system where seat availability can be queried. To control spamming by bots, a user should be required to **log in before querying**.

The ability to query (`getStatus`) must be connected to the **logged in status of the user**.

### Solution: Combining Interfaces and Private Classes

The solution uses **objects** to control the interaction:

1.  On successful login, the user receives a special **Query Object**.
2.  This Query Object is created from a **private class** (an inner class) that has access to the main `RailwayBooking`'s private data (like `railwaydb`).
3.  An **Interface** is used to tell the external user what the capabilities of the returned object are, without exposing the private class definition.

#### Implementation Breakdown

| Component | Explanation | Example Code Snippet |
| :--- | :--- | :--- |
| **Interface** | Describes the capability (`getStatus`) of the object returned on login. External code only knows about this type, not the private class. | `public interface QIF{ public abstract int getStatus(...); }` |
| **Outer Class** | Handles the login and returns an object of the **Interface** type. | `public QIF login(String u, String p){ // ... return new QueryObject(); }` |
| **Private Class** | The **inner class** that actually implements the capability and can look up the `railwaydb`. It implements the public interface. | `private class QueryObject implements QIF { ... }` |


## Tracking State with the Query Object

The Query Object can **remember the state of the interaction** by maintaining its own **instance variables**.

### Example: Limiting Queries per Login

To limit the number of queries per login, the private `QueryObject` can maintain a counter.

```java
public class RailwayBooking {
    private BookingDB railwaydb;
    public QIF login(String u, String p){
        // ... (login logic)
        return new QueryObject(); // returns an object of the private class
    }

    private class QueryObject implements QIF {
        private int numqueries = 0; // Tracks the state of the interaction
        private static final int QLIM = 10; // Query limit

        @Override
        public int getStatus(int trainno, Date d) {
            if (numqueries < QLIM) {
                // Look up railwaydb (has access to outer class's private members)
                // respond, increment numqueries
                numqueries++;
                // ...
            }
            // else: return an error or unavailable status
            return 0;
        }
    }
}
```

### Summary of Controlled Access

This pattern provides **controlled access to an object** by combining three key elements:

1.  **Private Class**: Provides the implementation and maintains the **state of the interaction** using instance variables.
2.  **Interface**: Defines the **publicly known capabilities** of the returned object.
3.  **Outer Class Method**: Serves as the **gatekeeper** that creates and returns the object only upon a valid pre-condition (e.g., login).

## Lecture 5: Callbacks

### Concept of Callbacks

A **callback facility** is used when one object (**Owner**) initiates an action by another object (**Worker**) that runs in parallel, and the Worker needs to notify the Owner when the action is complete.

  * **Scenario Example**: A class **`Myclass` (m)** creates a **`Timer` (t)** object.
  * `Myclass` calls `t.start()` to start the timer running in parallel.
  * `Myclass` continues its execution.
  * When the time limit expires, the `Timer` **notifies** `Myclass` by calling a function like `timerdone()`.

### Basic Implementation (Specific to `Myclass`)

To allow the `Timer` to notify its creator, the creator must pass its **identity** (`this`) to the `Timer` when creating it.

| Component | Code | Explanation |
| :--- | :--- | :--- |
| **Owner** (`Myclass`) | `Timer t = new Timer(this);` | Passes itself (`this`) to the Timer constructor. |
| **Worker** (`Timer`) | `private Myclass owner;` `public Timer(Myclass o){ owner = o; }` | Stores the creator object (`Myclass`) as its `owner`. |
| **Callback** | `public void start(){ owner.timerdone(); }` | When the Worker is done, it calls the `timerdone()` method on the stored `owner`. |

> **Note**: The `Timer` class often implements the `Runnable` interface, which indicates it can run in parallel.


### Challenge: Creating a Generic Worker

The basic implementation is **specific to `Myclass`**. To make a generic `Timer` that can notify *any* class, the design must be generalized.

### Attempt 1: Using `Object`

We could make the `Timer` constructor accept the generic type **`Object`**.

```java
// Timer constructor accepts the most generic type
public class Timer implements Runnable {
    private Object owner;
    public Timer(Object o) { owner = o; } 

    public void start() {
        // Must cast the owner back to the expected type!
        ((Myclass) owner).timerdone();
    }
}
```

  * **Problem**: We **need to cast** the `owner` back to the specific `Myclass` type. If the `Timer` is used by a different class, the cast will fail, making the `Timer` still not truly generic.


### Solution: Using Interfaces for Callbacks

**Interfaces** provide the elegant solution to making callbacks generic. They define the **capability** the owner must possess to be notified.

### Implementation with Interfaces

1.  **Define a Callback Interface**: This interface specifies the methods the Worker will call.

    ```java
    public interface TimerOwner {
        public abstract void timerdone();
    }
    ```

2.  **Owner Implements the Interface**: `Myclass` must implement the `TimerOwner` interface, guaranteeing it has the required `timerdone()` method.

    ```java
    public class Myclass implements TimerOwner {
        // ...
        public void timerdone() { ... }
    }
    ```

3.  **Worker Stores the Interface Type**: The `Timer` stores the owner as the **interface type** (`TimerOwner`). This eliminates the need for casting.

    ```java
    public class Timer implements Runnable {
        private TimerOwner owner; // Stores the object as the interface type
        
        public Timer(TimerOwner o) { owner = o; }
        
        public void start() {
            // No cast needed! Any object passed must have implemented TimerOwner
            owner.timerdone();
        }
    }
    ```

This structure ensures the `owner` object has the required capability, allowing the callback to be **generic**. The object being notified (`owner`) doesn't even need to be the object that created the `Timer`.

### Summary

  * Callbacks are useful when a class is **spawned in parallel** and needs to notify an object when it's done.
  * The **interface** defines the necessary capability (`timerdone()`).
  * The use of an interface allows the Worker object to be **generic** and decouple itself from the specific type of the Owner class.

## Lecture 6: Iterators

### The Problem of Generic Iteration

A **linear list** is a generic collection of objects whose internal implementation can vary.

  * **Internal Implementations** can be based on an **array** or a **linked list** (using a private `Node` inner class).

The goal is to create a loop that can run through all values in the list (**iteration**).

### The Encapsulation Conflict

Direct iteration requires exposing the list's private data structure, which violates **encapsulation**:

  * If the list were a **public array**, the iteration loop would expose the array's size and index access:
    ```java
    // Requires public access to the internal 'data' array and 'length'
    for (int i = 0; i < data.length; i++) { /* ... do something with data[i] ... */ }
    ```
  * If the list were a **public linked list**, the iteration loop would expose the private `Node` structure:
    ```java
    // Requires public access to the internal 'head' Node
    for (Node m = head; m != null; m = m.next) { /* ... do something with m.data ... */ }
    ```

Furthermore, external code often **doesn't know which implementation is in use** (array or linked list).

### The Iterator Abstraction

The **Iterator** pattern abstracts the traversal process, allowing external code to iterate over a data structure without knowing its underlying implementation.

### The `Iterator` Interface

The functionality is encapsulated in a public interface called `Iterator`.

| Method | Purpose | Abstraction |
| :--- | :--- | :--- |
| `has_next()` | Checks if there is another element available. | `while (there is a next element)` |
| `get_next()` | Returns the next element and advances the position. | `get the next element; do something with it` |

```java
public interface Iterator {
    public abstract boolean has_next();
    public abstract Object get_next();
}
```
### Implementing the Iterator

To support iteration, the main data structure (`Linearlist`) must be able to **export an object** that implements the `Iterator` interface.

### Solving the State Problem (Nested Loops)

Since iteration is a type of **interaction with state**, and you need to remember the current position in the list, a dedicated object is required. This also allows for **nested loops**.

The solution is to use an **inner private class** that implements `Iterator` and creates a **fresh object** for each iteration process.

| Component | Code | Explanation |
| :--- | :--- | :--- |
| **Inner Class** | `private class Iter implements Iterator { private Node position; ... }` | A private class whose definition depends on the list's implementation. It holds the necessary **position pointer**. |
| **Export Method**| `public Iterator get_iterator(){ Iter it = new Iter(); return(it); }` | The public method in `Linearlist` that creates and returns a **new**, fresh `Iterator` object. |

### External List Traversal

The external user can now traverse the list using the standard Iterator abstraction, regardless of the list's internal implementation:

```java
Linearlist l = new Linearlist();
// ... (add objects to l) ...

Iterator i = l.get_iterator(); // Get a new iterator object
while (i.has_next()) {
    Object o = i.get_next();
    // do something with o
}
```

### Handling Nested Loops

For nested loops, simply **acquire multiple iterators**, one for each loop. Each iterator object maintains its own internal state (`position`) independently:

```java
Iterator i = l.get_iterator(); // Outer iterator
while (i.has_next()) {
    Object oi = i.get_next();

    Iterator j = l.get_iterator(); // Inner iterator (fresh state)
    while (j.has_next()) {
        Object oj = j.get_next();
        // do something with oi, oj
    }
}
```

> **Note**: The Java enhanced `for` loop (`for (type x : a)`) implicitly constructs and uses an iterator behind the scenes.