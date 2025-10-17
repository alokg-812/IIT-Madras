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

