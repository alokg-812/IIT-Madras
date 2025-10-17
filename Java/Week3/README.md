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


## Lecture 5: Subtyping vs Inheritence


## Lecture 6: Modifiers