# Week 3 

## Lecture 1: OO Design

### The Shift to Object-Oriented Design
The philosophy of OO programming by contrasting it with traditional structured programming, referencing Niklaus Wirth's statement: 
`Algorithms + Data Structures = Programs`


### A. Structured Programming (Traditional Approach)

* **Focus:** **Algorithms come first**.
* **Process:**
    * Design a set of **procedures** for specific tasks.
    * Combine procedures to build complex systems.
    * **Data representation comes later**. Data structures are designed to suit the procedural manipulations.

### B. Object-Oriented (OO) Design

* **Focus:** **Reverses the focus**.
* **Process:**
    * **First, identify the data** we want to maintain and manipulate.
    * **Then, identify algorithms (methods)** to operate on that data.

### C. Benefits of OO Design for Large Systems

The slides claim that OO design **works better for large systems** 166, 177, 189, 202, 216]:

* **Easier to Grasp Design:** A system built with 100 classes (each having about 20 methods) is much easier to grasp than one built with 2000 procedures manipulating global data 179, 180, 191, 192, 193, 204, 205, 206, 218, 219, 220].
* **Easier Debugging:** If an object is in an incorrect state, the problem is likely localized. One only needs to **search among 20 methods** rather than 2000 procedures to find what caused the change 221, 222].

---

## 2. Components of Object Design

The design of individual objects centers around three interacting features: **Behaviour, State, and Identity** 282, 293].

| Feature | Definition | Relationship & Example |
| :--- | :--- | :--- |
| **Behaviour** | The **methods** needed to operate on the object 268, 274, 275, 283, 285, 294, 296]. | An "Order" object might have a method called `addItem()`. |
| **State** | The information held in the **instance variables** 286, 297]. It determines how the object reacts when methods are invoked 286, 297]. | **Encapsulation** dictates that the state should not change unless a method operates on it 277, 286, 287, 297, 298]. |
| **Identity** | The characteristic that **distinguishes between different objects** of the same class 299]. Two objects can have the same state (e.g., two orders containing the same item) but are still distinct 289, 299]. |

* **Interaction:** State affects behaviour. For example, you **cannot add an item to an order that has already been shipped**, nor can you ship an empty order 302, 303].

---

## 3. Relationships Between Classes

Classes in an OO system interact in several ways, often referred to as coupling.

* **Dependence:** When one class needs another class to perform a task.
    * **Example:** An `Order` class might need an `Account` class to check the customer's credit status 310, 318, 319, 330].
    * **Robust Design:** A robust design aims to **minimize dependencies**, or **coupling**, between classes 321, 332].
* **Aggregation:** A "has-a" relationship, where one class contains objects of another class.
    * **Example:** An `Order` class **contains** multiple `Item` objects 323, 333, 334].
* **Inheritance:** An "is-a" relationship, where one object is a specialized version of another.
    * **Example:** `ExpressOrder` **inherits** from `Order` 336, 337].
    * The specialized class might have extra methods, such as those to compute shipping charges or priority handling.


## Lecture 2: Java Subclasses

This document explains **Subclasses and Inheritance** in Java, a fundamental concept of Object-Oriented Programming (OOP). It uses an `Employee` class and a `Manager` subclass as examples.

-----

## 1\. The Base Class: `Employee`

The slides start by defining a basic `Employee` class, which serves as the **parent class** or **superclass**.

| Component | Description | Java Code Example (Implied) |
| :--- | :--- | :--- |
| **Instance Variables** | [cite\_start]Two private instance variables to store the employee's name and salary[cite: 672, 693]. | `private String name;` `private double salary;` |
| **Constructors** | [cite\_start]Methods to set up (initialize) the object[cite: 673, 716]. | `public Employee(String n, double s) { ... }` |
| **Mutator Methods** | [cite\_start]Methods (like `setName`, `setSalary`) to set the values of the private instance variables[cite: 675, 741]. | `public boolean setSalary(double x) { ... }` |
| **Accessor Methods** | [cite\_start]Methods (like `getName`, `getSalary`) to read and report the values of the private instance variables[cite: 677, 683, 741]. | `public double getSalary() { ... }` |
| **Other Methods** | [cite\_start]Public methods, such as one to compute a bonus[cite: 685, 767]. | `public double bonus(float percent) { return (percent/100.0) * salary; [cite_start]}` [cite: 685] |

**Class Definition:**

```java
public class Employee {
    private String name;
    private double salary;
    
    // Constructor Example
    public Employee(String n, double s) {
        this.name = n;
        this.salary = s;
    }
    
    // Accessor Example
    public String getName() {
        return name;
    }
    
    // Other Method Example
    public double bonus(float percent) {
        return (percent / 100.0) * salary; // uses private 'salary'
    }
}
```

-----

## 2\. Defining a Subclass and Inheritance

A **subclass** is created to represent a specialized version of the parent class. [cite\_start]In the example, a `Manager` is a special type of `Employee` with extra features[cite: 785].

| Term | Definition & Context |
| :--- | :--- |
| **Subclass / Child Class** | [cite\_start]A class that **extends** another class[cite: 797, 823, 972]. [cite\_start](`Manager` is a subclass of `Employee` [cite: 823]). |
| **Superclass / Parent Class** | The class that is being extended (e.g., `Employee`). |
| **Inheritance** | [cite\_start]The mechanism by which the subclass **inherits** (receives) the instance variables and methods from the parent class[cite: 806, 908, 934, 973]. |

**Subclass Definition:**
[cite\_start]The `extends` keyword is used to define a subclass[cite: 786]. [cite\_start]The subclass can add its own new instance variables and methods[cite: 787, 974].

```java
public class Manager extends Employee {
    private String secretary; // New feature for Manager
    
    // New mutator for the new feature
    public boolean setSecretary(String s) { 
        this.secretary = s;
        // ...
    }
    // New accessor
    public String getSecretary() {
        return secretary;
    }
}
```

  * [cite\_start]**Effect of Inheritance:** Every `Manager` object automatically inherits the `name`, `salary`, `getName()`, `getSalary()`, and `bonus()` methods from `Employee`[cite: 807].

-----

## 3\. The `private` Restriction and `super`

A key point in the slides is the access restriction regarding private members:

  * [cite\_start]**Private Access:** `Manager` objects **do not automatically have access to the private data of the parent class** (`Employee`)[cite: 830, 846, 977]. [cite\_start]This is true even when extending a parent class written by someone else[cite: 831, 839, 847].
  * [cite\_start]**The Problem:** How can a constructor for `Manager` set the `name` and `salary` instance variables that are private to `Employee`[cite: 840, 848]?

### The `super` Keyword

[cite\_start]The solution is to use the **parent class's constructor** via the **`super`** keyword[cite: 868, 978].

  * [cite\_start]**`super` (Keyword):** Calls a constructor of the superclass (parent class)[cite: 898, 978]. This allows the parent class to initialize its own private variables using its own logic.

**Manager Constructor Example:**

```java
public class Manager extends Employee {
    private String secretary;

    public Manager(String n, double s, String sn) {
        super(n, s); // ⬅️ super calls the Employee(String, double) constructor
        this.secretary = sn; // Initializes the Manager's own variable
    }
}
```

-----

## 4\. Subclass Substitution (Polymorphism)

[cite\_start]Inheritance establishes an "is-a" relationship: **Every `Manager` is an `Employee`, but not vice versa**[cite: 916, 935]. This leads to the principle of substitution:

  * [cite\_start]**Valid Substitution:** A subclass can be used in place of its superclass[cite: 925, 960].
    ```java
    Employee e = new Manager("Alice", 120000.00, "Bob"); // OK: A Manager is an Employee
    ```
  * [cite\_start]**Invalid Substitution:** The reverse is **not valid**[cite: 938, 962].
    ```java
    Manager m = new Employee("Charlie", 80000.00); // ERROR: An Employee is NOT necessarily a Manager
    ```

## Lecture 3: Polymorphism

This document explains **Dynamic Dispatch** and **Polymorphism** in Java, focusing on how method calls are resolved when using inheritance and method overriding.

-----

## 1\. Method Overriding

[cite\_start]A key capability of inheritance is **method overriding**, where a subclass can provide its own implementation for a method already defined in its superclass[cite: 579, 601].

  * [cite\_start]**Overriding:** Multiple methods exist with the **same name and same signature** (list of argument types) in a superclass and its subclass[cite: 800].
  * [cite\_start]**The `super` Keyword:** The overriding method in the subclass can use the `super` keyword to call the original method implementation in the parent class[cite: 622, 624].

### Example: Overriding `bonus()`

[cite\_start]The `Manager` subclass can redefine the `bonus()` method to offer a higher bonus calculation, for instance, a 1.5x multiplier on the standard employee bonus[cite: 620].

```java
// Employee Class (Superclass)
public class Employee {
    // ... instance variables and other methods
    public double bonus(float percent) {
        return (percent / 100.0) * salary; // Employee's original bonus calculation
    }
}

// Manager Class (Subclass)
public class Manager extends Employee {
    // ... instance variables and other methods
    // Overrides the Employee's bonus method
    public double bonus(float percent) {
        // Calls the parent class's bonus() via super and applies a 1.5x multiplier
        return 1.5 * super.bonus(percent); 
    }
}
```

-----

## 2\. Dynamic Dispatch

[cite\_start]**Dynamic dispatch** (also known as **dynamic binding** or **late method binding**) is the mechanism that determines which version of an overridden method to execute at **run-time**[cite: 688, 824, 943]. [cite\_start]This is the default behavior in Java, unlike in languages like C++ where it can be optional[cite: 689].

### Static vs. Dynamic Type

Consider the assignment of a subclass object to a superclass reference:

```java
Employee e = new Manager(...);
```

  * [cite\_start]**Static Type:** The type declared on the left side, **`Employee`**[cite: 653]. This is checked at compile-time.
  * **Dynamic Type (Run-time Identity):** The actual type of the object created on the right side, **`Manager`**.

### The Dispatch Problem

When calling a method on the reference `e`, which method is used?

  * [cite\_start]**Method Check (Static):** When attempting to call `e.setSecretary()` (a method unique to `Manager`), **static type-checking** fails because the reference `e` is declared as an `Employee`, which does not contain that method[cite: 652, 654].
  * **Method Call (Dynamic):** When calling an overridden method like `e.bonus(p)`:
      * [cite\_start]**Static choice** (compile-time) would use `Employee.bonus()`[cite: 671].
      * [cite\_start]**Dynamic choice** (run-time) uses `Manager.bonus()`[cite: 671, 687].

[cite\_start]**Dynamic Dispatch** ensures that the correct method, based on the object's **actual run-time identity (`Manager`)**, is called[cite: 943].

-----

## 3\. Polymorphism (Runtime/Inheritance)

[cite\_start]**Polymorphism** (specifically **runtime polymorphism** or **inheritance polymorphism**) is the core concept enabled by dynamic dispatch[cite: 721, 944]. It means a variable of a superclass type can hold objects of its subclasses, and method calls on that variable will execute the correct subclass method.

### Polymorphic Array Example

Polymorphism allows an array declared as `Employee[]` to hold a mixture of `Employee` and `Manager` objects. [cite\_start]When iterating and calling the `bonus()` method, each object executes its own specialized version[cite: 694, 696].

```java
Employee[] emparray = new Employee[2];
emparray[0] = new Employee(...);
emparray[1] = new Manager(...);

for (int i = 0; i < emparray.length; i++){
    // When i=0, calls Employee.bonus()
    // When i=1, calls Manager.bonus() due to dynamic dispatch
    System.out.println(emparray[i].bonus(5.0));
}
[cite_start]// Every Employee in emparray "knows" how to calculate its bonus correctly! [cite: 695]
```

-----

## 4\. Overloading vs. Overriding

The document clearly distinguishes two different forms of polymorphism related to method signatures:

| Feature | Overloading | Overriding | Dynamic Dispatch (Run-time Polymorphism) |
| :--- | :--- | :--- | :--- |
| **Methods** | [cite\_start]Multiple methods [cite: 778] | [cite\_start]Multiple methods [cite: 800] | [cite\_start]Multiple methods [cite: 824] |
| **Signature** | [cite\_start]**Different** signatures [cite: 778] | [cite\_start]**Same** signature [cite: 800] | [cite\_start]**Same** signature [cite: 824] |
| **Choice Time** | [cite\_start]**Static** (compile-time) [cite: 778] | [cite\_start]**Static** (compile-time) [cite: 800] | [cite\_start]**Run-time** [cite: 824] |
| **Context** | [cite\_start]In the same class (e.g., multiple constructors, `Arrays.sort()` methods) [cite: 747] | [cite\_start]Across a superclass and subclass [cite: 801] | [cite\_start]Across a superclass and subclass [cite: 824] |

-----

## 5\. Type Casting and Reflection

To overcome the restrictions imposed by static type-checking, **type casting** can be used.

  * [cite\_start]**Type Casting:** The process of converting a reference from one type to another[cite: 862]. This is used to access subclass-specific methods (like `setSecretary()`) when the reference is held by the superclass type (`Employee e`).
  * **Casting Syntax:** To call the `setSecretary()` method on the `Employee` reference `e`, it must be explicitly cast back to `Manager`:
    ```java
    ((Manager) e).setSecretary(s); [cite_start]// [cite: 863]
    ```
  * [cite\_start]**Run-time Error:** The cast fails (results in an error at run-time) if the object `e` is **not actually a `Manager`**[cite: 877].
  * **Checking Type (`instanceof`):** To avoid run-time errors, the `instanceof` keyword can be used to check the object's type before casting:
    ```java
    [cite_start]if (e instanceof Manager) { // [cite: 892, 910]
        ((Manager) e).setSecretary(s); 
    }
    ```
  * [cite\_start]**Reflection:** Using `instanceof` to test the object's type at run-time is a simple example of **reflection**, which is when a program can "think about oneself" or examine its own structure[cite: 914, 915].
  * [cite\_start]**Basic Type Casting:** Casting is also used for basic types (e.g., converting a `double` to an `int`)[cite: 935, 936].

## Lecture 4: Class Hierarchy

This document explains the structure of the **Java Class Hierarchy**, focusing on why Java disallows multiple inheritance and the significance of the universal superclass, `Object`.

-----

## 1\. Multiple Inheritance and The Diamond Problem

The slides begin by addressing **multiple inheritance**, where a class attempts to inherit from two or more parent classes.

  * [cite\_start]**Definition:** Multiple inheritance is when a subclass (`C3`) attempts to extend multiple parent classes (`C1` and `C2`)[cite: 7, 9].
  * [cite\_start]**The Conflict (Diamond Problem):** A conflict arises if both parent classes (`C1` and `C2`) define a method with the same signature, such as `public int f()`[cite: 18, 23]. [cite\_start]If the subclass (`C3`) doesn't override `f()`, it's ambiguous which version of `f()` should be used in `C3`[cite: 21, 33].
  * [cite\_start]**Java's Stance:** **Java does not allow multiple inheritance** to avoid this ambiguity and the resulting complexity[cite: 34, 47, 266].
      * [cite\_start](In contrast, C++ allows multiple inheritance if the parent classes have no conflict[cite: 48].)

-----

## 2\. The Java Class Hierarchy Structure

[cite\_start]Because Java prohibits multiple inheritance, the class structure is **tree-like**[cite: 57, 65, 268].

### The Universal Superclass: `Object`

  * [cite\_start]**Root of the Tree:** The entire Java class hierarchy has a **universal superclass** called **`Object`** at its root[cite: 66, 74, 101, 269].
  * [cite\_start]**Implicit Inheritance:** Every class you write implicitly inherits from `Object`[cite: 271].
  * [cite\_start]**Default Methods:** The `Object` class defines several useful methods that are inherited by all classes[cite: 75, 87, 102, 270]:
      * [cite\_start]**`public boolean equals(Object o)`:** Defaults to **pointer equality** (`==`), meaning it checks if the two object references point to the exact same location in memory[cite: 76, 88, 103, 134].
      * [cite\_start]**`public String toString()`:** Converts the values of the instance variables to a `String`[cite: 77, 90, 104, 105, 270]. [cite\_start]This method is implicitly invoked when printing an object (e.g., `System.out.println(o + "")` implicitly calls `o.toString()` [cite: 107, 108]).

-----

## 3\. Writing Generic Functions with `Object`

[cite\_start]The universal `Object` class allows developers to write **generic functions** that can operate on any Java object[cite: 114, 127, 140].

### Example: Generic `find` Function

A function designed to search for an element in an array can use `Object` as the type for both the array and the search target:

```java
public int find(Object[] objarr, Object o) {
    int i;
    for (i = 0; i < objarr.length; i++) {
        // By default, this checks for pointer equality
        if (objarr[i] == o) { 
            return i;
        }
    }
    return -1;
}
```

If a class overrides the `equals()` method, the generic function can be modified to use it. [cite\_start]If the function is changed to use `objarr[i].equals(o)`, **dynamic dispatch** will ensure the correct (overridden) `equals()` method is called instead of `Object.equals()`[cite: 148, 149].

-----

## 4\. Careful Overriding of `equals()`

[cite\_start]When overriding methods inherited from `Object`, one must be careful to match the signature exactly[cite: 272].

### The Correct Signature

For a class like `Date`, an attempt to override `equals()` to compare the object's state (day, month, year) might look like this:

```java
// THIS IS INCORRECT OVERRIDING
public boolean equals(Date d) { // Signature is (Date)
    return (this.day == d.day) && (this.month == d.month) && (this.year == d.year);
}
[cite_start]// This method does NOT override Object's equals(Object o)! [cite: 175, 176, 198, 199]
```

Since the inherited method is `public boolean equals(Object o)`, the overriding method must match this signature precisely. The correct implementation requires a **run-time type check** and a **cast**:

```java
// THIS IS CORRECT OVERRIDING
public boolean equals(Object d) { // Signature is (Object)
    [cite_start]if (d instanceof Date) { // Run-time type check [cite: 187, 201]
        Date myd = (Date) d; [cite_start]// Cast [cite: 188, 201]
        return ((this.day == myd.day) && (this.month == myd.month) && (this.year == myd.year));
    }
    return false; // Return false if the object is not a Date
}
```

  * [cite\_start]**Overriding Rules:** When a method is invoked, the Java runtime looks for the "**closest**" match in the hierarchy[cite: 206, 212, 219, 249]. [cite\_start]If a class has a method compatible with multiple inherited methods (e.g., `boolean equals(Manager m)` is compatible with both `boolean equals(Employee e)` and `boolean equals(Object o)`), the closest, most specific compatible method is used[cite: 242, 243, 258, 259, 260].


## Lecture 5: Subtyping vs Inheritence

This document clarifies the crucial distinction between **Subtyping** and **Inheritance**, explaining how Java's class hierarchy typically provides both, which can sometimes blur the conceptual difference.

-----

## 1\. Subtyping vs. Inheritance: The Core Difference

[cite\_start]The Java class hierarchy often represents both subtyping and inheritance simultaneously[cite: 1647, 1663]. However, they represent two distinct concepts:

| Concept | Focus | Definition | Example (Employee/Manager) |
| :--- | :--- | :--- | :--- |
| **Subtyping** | [cite\_start]**Compatibility of Interfaces**[cite: 1727, 1736]. | [cite\_start]If B is a subtype of A, the capabilities of B are a **superset** of A[cite: 1655]. [cite\_start]This means wherever an object of type A is required, an object of type B can be used[cite: 1656, 1728]. | [cite\_start]`Employee e = new Manager(...)` is legal, because a `Manager` object can perform every function defined for an `Employee`[cite: 1657]. |
| **Inheritance** | [cite\_start]**Reuse of Implementations**[cite: 1739]. | [cite\_start]B inherits from A if some functions for B are **written in terms of functions of A**[cite: 1670, 1740]. [cite\_start]The subtype reuses the code of the main type[cite: 1669]. | [cite\_start]`Manager.bonus()` uses `super.Employee.bonus()`[cite: 1670]. |

[cite\_start]The core distinction is: **Subtyping** relates to *what* the object can do (its interface/behavior), and **Inheritance** relates to *how* the object is built (its code structure/implementation)[cite: 1748, 1751].

-----

## 2\. Decoupling the Concepts (The Data Structure Example)

The slides use a classic data structure example to show that subtyping and inheritance relationships do not always align in the same direction.

### [cite\_start]Data Structure Definitions [cite: 1677, 1678]

  * **Queue:** Methods include `insert-rear`, `delete-front`.
  * **Stack:** Methods include `insert-front`, `delete-front`.
  * **Deque (Double-Ended Queue):** Methods include `insert-front`, `delete-front`, `insert-rear`, `delete-rear`.

### [cite\_start]Subtyping Relationship [cite: 1709, 1710, 1711]

  * **Logic:** Since a Deque has all the functionality (methods) of both a Queue and a Stack, it can be substituted for either one.
  * **Conclusion (Based on Capability):**
    $$\text{Deque} \text{ is a subtype of } \text{Queue}$$
    $$\text{Deque} \text{ is a subtype of } \text{Stack}$$

### [cite\_start]Inheritance Relationship [cite: 1712, 1713]

  * **Logic:** For code reuse, it is often easier to implement the simpler structures (Queue and Stack) by limiting the functionality of the more complex structure (Deque). For instance, a Queue implementation might *reuse* the implementation of Deque by only exposing `insert-rear` and `delete-front`.
  * **Conclusion (Based on Code Reuse):**
    $$\text{Queue} \text{ inherits from } \text{Deque}$$
    $$\text{Stack} \text{ inherits from } \text{Deque}$$

### Example Code (Conceptual)

This example shows how `Queue` reuses the implementation of `Deque` by *limiting access* (inheritance for code reuse), even though `Deque` is the logical *subtype*.

```java
// Deque.java (Superclass for Implementation Reuse)
public class Deque {
    // Methods for a full Deque
    public void insertFront(Object o) { ... }
    public void deleteFront() { ... }
    public void insertRear(Object o) { ... }
    public void deleteRear() { ... }
}

// Queue.java (Subclass, but the Supertype in Capability)
public class Queue extends Deque {
    // Queue only exposes two methods by calling super's implementation
    
    // insert-rear is reused from Deque
    public void insertRear(Object o) {
        super.insertRear(o); 
    }
    
    // delete-front is reused from Deque
    public void deleteFront() {
        super.deleteFront();
    }
    
    // Note: insertFront and deleteRear are inherited but should be hidden 
    // or overridden to throw an error to enforce the Queue interface.
}
```

## 3\. The Blurring of Concepts

[cite\_start]The use of a single mechanism—the **class hierarchy** (`extends` keyword)—to implement both **subtyping** and **inheritance** can **blur the distinction between the two**[cite: 1719, 1725, 1753]. In many practical Java scenarios, a subclass (`B`) is *both* a subtype *and* an inheritor of its superclass (`A`).

However, understanding the difference is key to good object-oriented design:

  * **Subtyping is about safety:** It guarantees that you can safely substitute a subtype object wherever a supertype object is expected (Liskov Substitution Principle).
  * **Inheritance is about efficiency:** It allows developers to avoid rewriting code by reusing the implementation from a parent class.

## Lecture 6: Modifiers

This document provides a detailed explanation of **Java Modifiers**, which are keywords used in declarations to enforce various features of Object-Oriented Programming (OOP) such as encapsulation, class membership, and immutability. [cite\_start]These modifiers can be applied to **classes, instance variables, and methods**[cite: 480].

-----

## 1\. Access Control Modifiers (`public` vs. `private`)

[cite\_start]These modifiers are essential for enforcing **encapsulation of data**[cite: 453].

| Modifier | Application | Purpose & Standard Use |
| :--- | :--- | :--- |
| **`private`** | Instance Variables, Methods | [cite\_start]Limits visibility to **within the declared class only**[cite: 511]. [cite\_start]**Typically, instance variables are `private`** to protect the object's state[cite: 500]. |
| **`public`** | Classes, Methods, Variables | [cite\_start]Grants **universal access**[cite: 512]. [cite\_start]**Methods to query (accessor) and update (mutator) the state are typically `public`**[cite: 502]. |

### The Role of `private` Methods

[cite\_start]While most methods are public, **private methods also make sense**[cite: 817]. They are used for internal, helper functionality that should not be exposed to the outside world.

**Example: Private Methods in a `Stack` Class**

A `Stack` needs to check if it's full (`stack_full()`) and possibly expand its storage (`extend_stack()`) internally before a `push()` operation. These helper methods should not be called directly by a user, thus making them `private`.

```java
public class Stack {
    private int[] values; 
    private int tos;    // top of stack
    private int size;   // values.length

    // ... Constructor ...

    public void push(int i) {
        if (stack_full()) {
            extend_stack(); // Invokes private method
        }
        // ... Usual push operations ...
    }

    // Private helper method - only callable from within the Stack class
    private boolean stack_full() {
        return (tos == size);
    }
    
    // Private utility method - details hidden from user
    private void extend_stack() {
        /* Allocate additional space, reset size, etc. */
    }
    
    public int pop() { ... }
}
```

### The Problem with Separate Mutators

[cite\_start]Using individual `public` mutator methods (like `setDay`, `setMonth`, `setYear`) can lead to **inconsistent updates** by allowing invalid combinations of values to be set separately[cite: 629, 630]. [cite\_start]It is better to allow only a combined update that can validate the entire state at once[cite: 646].

```java
public class Date {
    private int day, month, year;
    // ... Accessors/Getters ...

    // Combined Mutator (Setter) is preferred for validation
    public void setDate(int d, int m, int y) {
        // Validate d-m-y combination before setting
        // ...
    }
}
```

-----

## 2\. Membership Modifier (`static`)

[cite\_start]The `static` modifier is used for entities defined inside classes that **exist without creating objects of the class**[cite: 461, 660].

| Component | Purpose & Examples | Java Code Example |
| :--- | :--- | :--- |
| **Static Methods** | [cite\_start]Used for utility/library functions (like `main()`) that do not rely on object state[cite: 661]. | `public static void main(String[] args)` |
| **Static Variables** | [cite\_start]Used for **useful constants** (often also `public` and `final`), which are common to all objects in the class[cite: 662, 742]. | `public static final double PI = 3.14159;` |

### `private static` Components

[cite\_start]**Private static components make sense** for internal bookkeeping information across all objects of a class[cite: 690, 691, 818].

**Example: Generating Unique IDs**

A private static variable is used as a counter, shared by all instances, to assign a unique serial number (`orderid`) to each new object upon creation.

```java
public class Order {
    [cite_start]// Shared state: 'lastorderid' is common to all objects in the class [cite: 742]
    private static int lastorderid = 0; [cite_start]// Private static field [cite: 721]
    
    // Instance state: Unique for each object
    private int orderid;
    
    public Order(/* ... */) {
        lastorderid++;        // Increments the shared counter
        this.orderid = lastorderid; // Assigns the unique ID to the new object
    }
}
[cite_start]// Note: Care must be taken about concurrent updates to static variables[cite: 763].
```

-----

## 3\. Immutability Modifier (`final`)

[cite\_start]The `final` modifier denotes that a value **cannot be updated**[cite: 767, 774].

| Component | Purpose | Context |
| :--- | :--- | :--- |
| **Final Variables** | Creates a constant. [cite\_start]Usually used with **public and static instance variables** (e.g., `Math.PI`)[cite: 775, 782]. | `public static final int MAX_VALUE = 2147483647;` |
| **Final Methods** | [cite\_start]Prevents a method from being **overridden by a subclass**[cite: 810, 821]. | Used to ensure a critical base class implementation is always used. |
| **Final Classes** | Prevents a class from being subclassed (not explicitly detailed but implied by the method rule). | E.g., Java's `String` class is final. |

**Example: Final Method**

If the `Employee` class has a crucial method that no subclass (like `Manager`) should ever change, it's declared `final`.

```java
public class Employee {
    // ...
    // A final method cannot be overridden by any subclass
    public final void checkCompliance() {
        // ...
    }
}
```