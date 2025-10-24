# Week 3 

## Lecture 1: OO Design

### The Shift to Object-Oriented Design
The philosophy of OO programming by contrasting it with traditional structured programming, referencing Niklaus Wirth's statement: 
`Algorithms + Data Structures = Programs`


### A. Structured Programming (Traditional Approach)

* **Focus:** _**Algorithms come first**_.
* **Process:**
    * Design a set of **procedures** for specific tasks.
    * Combine procedures to build complex systems.
    * **Data representation comes later**. Data structures are designed to suit the procedural manipulations.

### B. Object-Oriented (OO) Design

* **Focus:** _**Reverses the focus**_.
* **Process:**
    * **First, identify the data** we want to maintain and manipulate.
    * **Then, identify algorithms (methods)** to operate on that data.

### C. Benefits of OO Design for Large Systems

OO design **works better for large systems**:

* **Easier to Grasp Design:** A system built with 100 classes (each having about 20 methods) is much easier to grasp than one built with 2000 procedures manipulating global data.
* **Easier Debugging:** If an object is in an incorrect state, the problem is likely localized. One only needs to **search among 20 methods** rather than 2000 procedures to find what caused the change.


### Components of Object Design

The design of individual objects centers around three interacting features: `Behaviour`, `State`, and `Identity`.

| Feature | Definition | Relationship & Example |
| :--- | :--- | :--- |
| **Behaviour** | The **methods** needed to operate on the object . | An "Order" object might have a method called `addItem()`. |
| **State** | The information held in the **instance variables**. It determines how the object reacts when methods are invoked. | **Encapsulation** dictates that the state should not change unless a method operates on it. |
| **Identity** | The characteristic that **distinguishes between different objects** of the same class. |  Two objects can have the same state (e.g., two orders containing the same item) but are still distinct. |

* **Interaction:** _**State**_ affects _**behaviour**_.<br> For example, we **cannot add an item to an order that has already been shipped**, nor can we ship an empty order.


### Relationships Between Classes

Classes in an OO system interact in several ways, often referred to as coupling.

* **Dependence:** When one class needs another class to perform a task.
    * **Example:** An `Order` class might need an `Account` class to check the customer's credit status.
    * **Robust Design:** A robust design aims to **minimize dependencies**, or **coupling**, between classes.
* **Aggregation:** A `"has-a"` relationship, where one class contains objects of another class.
    * **Example:** An `Order` class **contains** multiple `Item` objects.
* **Inheritance:** An `"is-a"` relationship, where one object is a specialized version of another.
    * **Example:** `ExpressOrder` **inherits** from `Order`.
    * The specialized class might have extra methods, such as those to compute shipping charges or priority handling.


## Lecture 2: Java Subclasses and Inheritence

**Subclasses and Inheritance** is a fundamental concept of Object-Oriented Programming (OOP).

Like an `Employee` class and a `Manager` subclass are examples of Subclasses.

### The Base Class: `Employee`

Let's take a basic `Employee` class, which serves as the **parent class** or **superclass**.

<img width="344" height="495" alt="image" src="https://github.com/user-attachments/assets/c5f659aa-9651-4caf-a77b-968c3a8fc47b" />

| Component | Description |
| :--- | :--- |
| **Instance Variables** | Two private instance variables to store the employee's name and salary. |
| **Constructors** | Methods to set up (initialize) the object. |
| **Mutator Methods** | Methods (like `setName`, `setSalary`) to set the values of the private instance variables. |
| **Accessor Methods** | Methods (like `getName`, `getSalary`) to read and report the values of the private instance variables. |
| **Other Methods** | Public methods, such as one to compute a bonus. |

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

### Defining a Subclass and Inheritance

A **subclass** is created to represent a specialized version of the parent class. In the example, a `Manager` is a special type of `Employee` with extra features.

| Term | Definition & Context |
| :--- | :--- |
| **Subclass / Child Class** | A class that **extends** another class. `Manager` is a subclass of `Employee` |
| **Superclass / Parent Class** | The class that is being extended (e.g., `Employee`). |
| **Inheritance** | The mechanism by which the subclass **inherits** (receives) the instance variables and methods from the parent class |

<img width="584" height="279" alt="image" src="https://github.com/user-attachments/assets/f7cefc61-32e9-480d-8294-b74c2ea69c88" />

**Subclass Definition:**
The `extends` keyword is used to define a subclass. The subclass can add its own new instance variables and methods.

```java
public class Manager extends Employee {
    private String name; //extended from the Employee Class
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

 * **Effect of Inheritance:** Every `Manager` object automatically inherits the `name`, `salary`, `getName()`, `getSalary()`, and `bonus()` methods from `Employee`.

### The `private` Restriction and `super`

Access restriction regarding private members:

 * **Private Access:** `Manager` objects **do not automatically have access to the private data of the parent class** (`Employee`). This is true even when extending a parent class written by someone else.
 * **The Problem:** How can a constructor for `Manager` set the `name` and `salary` instance variables that are private to `Employee`.

### The `super` Keyword

The solution is to use the **parent class's constructor** via the **`super`** keyword.

  * **`super` (Keyword):** Calls a constructor of the superclass (parent class). This allows the parent class to initialize its own private variables using its own logic.

**Manager Constructor Example:**

```java
public class Employee{
    public Employee(String n, double s){
        name = n; salary = s;
    }
    public Employee(String n){
        this(n, 500);
    }
}

public class Manager extends Employee {
    private String secretary;

    public Manager(String n, double s, String sn) {
        super(n, s); // ⬅️ super calls the Employee(String n, double s) constructor
        this.secretary = sn; // Initializes the Manager's own variable
    }
}
```

### Subclass Substitution (Polymorphism)

Inheritance establishes an `"is-a"` relationship: **Every `Manager` is an `Employee`, but not vice versa**. This leads to the principle of substitution:

  * **Valid Substitution:** A subclass can be used in place of its superclass.
    ```java
    Employee e = new Manager("Alice", 120000.00, "Bob"); // OK: A Manager is an Employee
    ```
  * **Invalid Substitution:** The reverse is **not valid**.
    ```java
    Manager m = new Employee("Charlie", 80000.00); // ERROR: An Employee is NOT necessarily a Manager
    ```

## Lecture 3: Polymorphism
 
**Dynamic Dispatch** and **Polymorphism** in Java focuses on how method calls are resolved when using inheritance and method overriding.

### Method Overriding

A key capability of inheritance is **method overriding**, where a subclass can provide its own implementation for a method already defined in its superclass.

  * **Overriding:** Multiple methods exist with the **same name and same signature** (list of argument types) in a superclass and its subclass.
  * **The `super` Keyword:** The overriding method in the subclass can use the `super` keyword to call the original method implementation in the parent class.

### Example: Overriding `bonus()`

The `Manager` subclass can redefine the `bonus()` method to offer a higher bonus calculation, for instance, a 1.5x multiplier on the standard employee bonus.

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

### Dynamic Dispatch

**Dynamic dispatch** (also known as **dynamic binding** or **late method binding**) is the mechanism that determines which version of an overridden method to execute at **run-time**. This is the default behavior in Java, unlike in languages like C++ where it can be optional.

### Static vs. Dynamic Type

Consider the assignment of a subclass object to a superclass reference:

```java
Employee e = new Manager(...);
```

  * **Static Type:** The type declared on the left side, **`Employee`**. This is checked at compile-time.
  * **Dynamic Type (Run-time Identity):** The actual type of the object created on the right side, **`Manager`**.

### The Dispatch Problem

When calling a method on the reference `e`, which method is used?

  * **Method Check (Static):** When attempting to call `e.setSecretary()` (a method unique to `Manager`), **static type-checking** fails because the reference `e` is declared as an `Employee`, which does not contain that method.
  * **Method Call (Dynamic):** When calling an overridden method like `e.bonus(p)`:
      * **Static choice** (compile-time) would use `Employee.bonus()`.
      * **Dynamic choice** (run-time) uses `Manager.bonus()`.

**Dynamic Dispatch** ensures that the correct method, based on the object's **actual run-time identity (`Manager`)**, is called.


### Polymorphism (Runtime/Inheritance)

**Polymorphism** (specifically **runtime polymorphism** or **inheritance polymorphism**) is the core concept enabled by dynamic dispatch. It means a variable of a superclass type can hold objects of its subclasses, and method calls on that variable will execute the correct subclass method.

### Polymorphic Array Example

Polymorphism allows an array declared as `Employee[]` to hold a mixture of `Employee` and `Manager` objects. When iterating and calling the `bonus()` method, each object executes its own specialized version.

```java
Employee[] emparray = new Employee[2];
emparray[0] = new Employee(...);
emparray[1] = new Manager(...);

for (int i = 0; i < emparray.length; i++){
    // When i=0, calls Employee.bonus()
    // When i=1, calls Manager.bonus() due to dynamic dispatch
    System.out.println(emparray[i].bonus(5.0));
}
// Every Employee in emparray "knows" how to calculate its bonus correctly!
```


### Overloading vs. Overriding

<img width="558" height="427" alt="image" src="https://github.com/user-attachments/assets/ab890eba-b3ca-481c-95ef-beefe90a7ba1" />

Different forms of polymorphism related to method signatures:

| Feature | Overloading | Overriding | Dynamic Dispatch (Run-time Polymorphism) |
| :--- | :--- | :--- | :--- |
| **Methods** | Multiple methods | Multiple methods | Multiple methods |
| **Signature** | **Different** signatures | **Same** signature | **Same** signature |
| **Choice Time** | **Static** (compile-time) | **Static** (compile-time) | **Run-time** |
| **Context** | In the same class (e.g., multiple constructors, `Arrays.sort()` methods) | Across a superclass and subclass | Across a superclass and subclass |

### Type Casting and Reflection

To overcome the restrictions imposed by static type-checking, **type casting** can be used.

  * **Type Casting:** The process of converting a reference from one type to another862]. This is used to access subclass-specific methods (like `setSecretary()`) when the reference is held by the superclass type (`Employee e`).
  * **Casting Syntax:** To call the `setSecretary()` method on the `Employee` reference `e`, it must be explicitly cast back to `Manager`:
  * [Code Example](https://github.com/alokg-812/IIT-Madras/blob/main/Java/Week3/TypeCasting.java)
    ```java
    ((Manager) e).setSecretary(s);
    ```
  * **Run-time Error:** The cast fails (results in an error at run-time) if the object `e` is **not actually a `Manager`**877].
  * **Checking Type (`instanceof`):** To avoid run-time errors, the `instanceof` keyword can be used to check the object's type before casting:
    ```java
    if (e instanceof Manager) {
        ((Manager) e).setSecretary(s); 
    }
    ```
  * **Reflection:** Using `instanceof` to test the object's type at run-time is a simple example of **reflection**, which is when a program can "think about oneself" or examine its own structure.
  * **Basic Type Casting:** Casting is also used for basic types (e.g., converting a `double` to an `int`).
<img width="1378" height="274" alt="image" src="https://github.com/user-attachments/assets/b08b1e03-2bde-40c7-963d-3742b2baff70" />


## Lecture 4: Class Hierarchy

Why Java disallows multiple inheritance and the significance of the universal superclass, `Object`.

### Multiple Inheritance and The Diamond Problem

Addressing **multiple inheritance**, where a class attempts to inherit from two or more parent classes.
<img width="374" height="210" alt="image" src="https://github.com/user-attachments/assets/affe54d4-fce3-4a7d-961d-1e8075f71830" />

  * **Definition:** Multiple inheritance is when a subclass (`C3`) attempts to extend multiple parent classes (`C1` and `C2`).
  * **The Conflict (Diamond Problem):** A conflict arises if both parent classes (`C1` and `C2`) define a method with the same signature, such as `public int f()`. If the subclass (`C3`) doesn't override `f()`, it's ambiguous which version of `f()` should be used in `C3`.
  * **Java's Stance:** **Java does not allow multiple inheritance** to avoid this ambiguity and the resulting complexity.
      * (In contrast, C++ allows multiple inheritance if the parent classes have no conflict)

### The Java Class Hierarchy Structure

Because Java prohibits multiple inheritance, the class structure is **tree-like**.

### The Universal Superclass: `Object`
  * **Root of the Tree:** The entire Java class hierarchy has a **universal superclass** called **`Object`** at its root.
  * **Implicit Inheritance:** Every class we write implicitly inherits from `Object`.
  * **Default Methods:** The `Object` class defines several useful methods that are inherited by all classes:
      * **`public boolean equals(Object o)`:** Defaults to **pointer equality** (`==`), meaning it checks if the two object references point to the exact same location in memory.
      * **`public String toString()`:** Converts the values of the instance variables to a `String`. This method is implicitly invoked when printing an object (e.g., `System.out.println(o + "")` implicitly calls `o.toString()`).

<img width="879" height="91" alt="image" src="https://github.com/user-attachments/assets/6c92a5dd-3b2b-44b6-be1b-5609844da20f" />

### Writing Generic Functions with `Object`

The universal `Object` class allows developers to write **generic functions** that can operate on any Java object.
#### Example: Generic `find` Function

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

If a class overrides the `equals()` method, the generic function can be modified to use it. If the function is changed to use `objarr[i].equals(o)`, **dynamic dispatch** will ensure the correct (overridden) `equals()` method is called instead of `Object.equals()`.


### Careful Overriding of `equals()`

When overriding methods inherited from `Object`, one must be careful to match the signature exactly.

### The Correct Signature

For a class like `Date`, an attempt to override `equals()` to compare the object's state (day, month, year) might look like this:

```java
// THIS IS INCORRECT OVERRIDING
public boolean equals(Date d) { // Signature is (Date)
    return (this.day == d.day) && (this.month == d.month) && (this.year == d.year);
}
// This method does NOT override Object's equals(Object o)! 175, 176, 198, 199]
```

Since the inherited method is `public boolean equals(Object o)`, the overriding method must match this signature precisely. The correct implementation requires a **run-time type check** and a **cast**:

```java
// THIS IS CORRECT OVERRIDING
public boolean equals(Object d) { // Signature is (Object)
    if (d instanceof Date) { // Run-time type check 187, 201]
        Date myd = (Date) d; // Cast 188, 201]
        return ((this.day == myd.day) && (this.month == myd.month) && (this.year == myd.year));
    }
    return false; // Return false if the object is not a Date
}
```

  * **Overriding Rules:** When a method is invoked, the Java runtime looks for the "**closest**" match in the hierarchy206, 212, 219, 249]. If a class has a method compatible with multiple inherited methods (e.g., `boolean equals(Manager m)` is compatible with both `boolean equals(Employee e)` and `boolean equals(Object o)`), the closest, most specific compatible method is used242, 243, 258, 259, 260].


## Lecture 5: Subtyping vs Inheritence

**Subtyping** and **Inheritance** explaining how Java's class hierarchy typically provides both, which can sometimes blur the conceptual difference.


### Subtyping vs. Inheritance: The Core Difference

The Java class hierarchy often represents both subtyping and inheritance simultaneously. However, they represent two distinct concepts:

| Concept | Focus | Definition | Example (Employee/Manager) |
| :--- | :--- | :--- | :--- |
| **Subtyping** | **Compatibility of Interfaces**]. | If B is a subtype of A, the capabilities of B are a **superset** of A. This means wherever an object of type A is required, an object of type B can be used. | `Employee e = new Manager(...)` is legal, because a `Manager` object can perform every function defined for an `Employee`. |
| **Inheritance** | **Reuse of Implementations**. | B inherits from A if some functions for B are **written in terms of functions of A**. The subtype reuses the code of the main type. | `Manager.bonus()` uses `super.Employee.bonus()`. |

The core distinction is: **Subtyping** relates to *what* the object can do (its interface/behavior), and **Inheritance** relates to *how* the object is built (its code structure/implementation).

### Decoupling the Concepts (The Data Structure Example)

Let's use a classic data structure example to show that subtyping and inheritance relationships do not always align in the same direction.

### Data Structure Definitions
  * **Queue:** Methods include `insert-rear`, `delete-front`.
  * **Stack:** Methods include `insert-front`, `delete-front`.
  * **Deque (Double-Ended Queue):** Methods include `insert-front`, `delete-front`, `insert-rear`, `delete-rear`.

### Subtyping Relationship
  * **Logic:** Since a Deque has all the functionality (methods) of both a Queue and a Stack, it can be substituted for either one.
  * **Conclusion (Based on Capability):**
    $$\text{Deque} \text{ is a subtype of } \text{Queue}$$
    $$\text{Deque} \text{ is a subtype of } \text{Stack}$$

### Inheritance Relationship
  * **Logic:** For code reuse, it is often easier to implement the simpler structures (Queue and Stack) by limiting the functionality of the more complex structure (Deque). For instance, a Queue implementation might *reuse* the implementation of Deque by only exposing `insert-rear` and `delete-front`.
  * **Conclusion (Based on Code Reuse):**
    $$\text{Queue} \text{ inherits from } \text{Deque}$$
    $$\text{Stack} \text{ inherits from } \text{Deque}$$

#### Example Code (Conceptual)

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

### The Blurring of Concepts
The use of a single mechanism—the **class hierarchy** (`extends` keyword)—to implement both **subtyping** and **inheritance** can **blur the distinction between the two**. In many practical Java scenarios, a subclass (`B`) is *both* a subtype *and* an inheritor of its superclass (`A`).

However, understanding the difference is key to good object-oriented design:

  * **Subtyping is about safety:** It guarantees that you can safely substitute a subtype object wherever a supertype object is expected (Liskov Substitution Principle).
  * **Inheritance is about efficiency:** It allows developers to avoid rewriting code by reusing the implementation from a parent class.

## Lecture 6: Modifiers

Java Modifiers are keywords used in declarations to enforce various features of Object-Oriented Programming (OOP) such as encapsulation, class membership, and immutability. These modifiers can be applied to **classes, instance variables, and methods**.

### Access Control Modifiers (`public` vs. `private`)
These modifiers are essential for enforcing **encapsulation of data**.

| Modifier | Application | Purpose & Standard Use |
| :--- | :--- | :--- |
| **`private`** | Instance Variables, Methods | Limits visibility to **within the declared class only**. **Typically, instance variables are `private`** to protect the object's state. |
| **`public`** | Classes, Methods, Variables | Grants **universal access**. **Methods to query (accessor) and update (mutator) the state are typically `public`**. |

### The Role of `private` Methods
While most methods are public, **private methods also make sense**. They are used for internal, helper functionality that should not be exposed to the outside world.

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

Using individual `public` mutator methods (like `setDay`, `setMonth`, `setYear`) can lead to **inconsistent updates** by allowing invalid combinations of values to be set separately. It is better to allow only a combined update that can validate the entire state at once646].

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

### Membership Modifier (`static`)

The `static` modifier is used for entities defined inside classes that **exist without creating objects of the class**.

| Component | Purpose & Examples | Java Code Example |
| :--- | :--- | :--- |
| **Static Methods** | Used for utility/library functions (like `main()`) that do not rely on object state661]. | `public static void main(String[] args)` |
| **Static Variables** | Used for **useful constants** (often also `public` and `final`), which are common to all objects in the class. | `public static final double PI = 3.14159;` |

### `private static` Components

**Private static components make sense** for internal bookkeeping information across all objects of a class690.

**Example: Generating Unique IDs**

A private static variable is used as a counter, shared by all instances, to assign a unique serial number (`orderid`) to each new object upon creation.

```java
public class Order {
    // Shared state: 'lastorderid' is common to all objects in the class
    private static int lastorderid = 0; // Private static field
    
    // Instance state: Unique for each object
    private int orderid;
    
    public Order(/* ... */) {
        lastorderid++;        // Increments the shared counter
        this.orderid = lastorderid; // Assigns the unique ID to the new object
    }
}
// Note: Care must be taken about concurrent updates to static variables.
```

### Immutability Modifier (`final`)

The `final` modifier denotes that a value **cannot be updated**.

| Component | Purpose | Context |
| :--- | :--- | :--- |
| **Final Variables** | Creates a constant. Usually used with **public and static instance variables** (e.g., `Math.PI`). | `public static final int MAX_VALUE = 2147483647;` |
| **Final Methods** | Prevents a method from being **overridden by a subclass**. | Used to ensure a critical base class implementation is always used. |
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
