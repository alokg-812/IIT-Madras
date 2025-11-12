# Week 5

## 🎓 Lecture 1: Polymorphism Revisited (Detailed Notes)

### 1. Object-Oriented Polymorphism: Dynamic Dispatch ⚙️

**Polymorphism** literally means "many forms". In Object-Oriented Programming (OOP), it primarily refers to the effect of **dynamic dispatch**.

#### **A. Dynamic Dispatch Explained**
Dynamic dispatch is the process of deciding *which* implementation of a method to call at **runtime** (when the program is executing), based on the actual type of the object, not the type of the variable holding the object.

| Key Concept | Definition |
| :--- | :--- |
| **Subclass (S)** | A class that inherits features from another class (the superclass T). |
| **Superclass (T)** | The parent class from which a subclass (S) inherits. |
| **Method Overriding** | The subclass (S) provides a specific implementation for a method ($f()$) that is already defined in its superclass (T). |

**Scenario & Outcome:**
[cite_start]If a variable $v$ is declared as the Superclass type ($T$), but is assigned an object of the Subclass type ($S$) [cite: 11][cite_start], the call to $v.f()$ uses the method definition from **S**[cite: 12].

> [cite_start]**Core Principle:** **Every object "knows" what it needs to do**[cite: 23]. This means the specific method implementation is bound to the actual object at the time of the call (dynamically).

***

### 2. General Polymorphism: Structural Polymorphism 🏗️

More generally, polymorphism can refer to behavior that depends only on an object's **specific capabilities**—what methods it can perform—regardless of its exact class. [cite_start]This is called **structural polymorphism**[cite: 35, 50].

#### **A. Capabilities Required for General Functions**
Structural polymorphism allows you to write generic functions that work on many types, provided those types support the necessary operations:

* [cite_start]**Reverse an array/list:** This function is simple; it should work for **any type** as long as you can swap positions[cite: 51].
* [cite_start]**Search for an element:** This requires a way to check if two elements are the same, meaning it **needs an equality check**[cite: 67].
* [cite_start]**Sort an array/list:** This requires a way to determine the order, meaning it **needs to compare values**[cite: 83].

#### **B. Simulating Structural Polymorphism in Java**

Java uses the built-in class hierarchy and interfaces to achieve this:

1.  **Using the `Object` Class for Generality:**
    * [cite_start]Since every class in Java inherits from **`Object`**, a function that accepts an array of **`Object[]`** can technically hold elements of **any type** (e.g., `public void reverse (Object[] objarr)`)[cite: 98, 97].
2.  **Using `Object.equals()` for Comparison:**
    * [cite_start]For a polymorphic **`find`** function (`public int find (Object[] objarr, Object o)`), the comparison $objarr[i] == o$ is actually intended to translate to a call to **`Object.equals()`**[cite: 118, 120, 123].
3.  **Using Interfaces for Capabilities (Sorting):**
    * [cite_start]To enforce a capability like **comparison** (required for sorting), Java uses **Interfaces**[cite: 140]. An interface captures the required methods.
    * [cite_start]Any class that implements the `Comparable` interface guarantees it has a method to compare itself with another object (e.g., `public abstract int cmp (Comparable s)`)[cite: 135, 137].
    * [cite_start]A generic sort function (like `quicksort`) can then accept an array of `Comparable[] a`, and use $a[i].cmp(a[j])$ for comparisons[cite: 139, 144].

***

### 3. Type Consistency and Array Copying ⚖️

When designing polymorphic functions, especially those that copy data, **type consistency** is critical.

#### **A. The `arraycopy` Problem**
[cite_start]A polymorphic `arraycopy` function (e.g., `public static void arraycopy (Object[] src, Object[] tgt)`) must ensure that the **target array (`tgt`) is type compatible with the source array (`src`)**[cite: 162]. [cite_start]The goal is that **Type errors should be flagged at compile time**[cite: 168].

#### **B. Array Subtyping Rules**

| Scenario | Example (Classes $T$ and Subclass $S$ where $S$ extends $T$) | Validity | Explanation |
| :--- | :--- | :--- | :--- |
| **Illegal Copy** | [cite_start]`arraycopy(datearr, emparr)` [cite: 174] | [cite_start]**Run-time Error** [cite: 174] | The types `Date` and `Employee` are unrelated. [cite_start]The `emparr` cannot safely hold a `Date` object[cite: 172, 173]. |
| **Allowed Copy (Subtype to Supertype)** | [cite_start]`arraycopy (etktarr, tktarr)` [cite: 196] | [cite_start]**Allowed** [cite: 196] | [cite_start]The source array (`ETicket[]`, a subclass) can be copied to the target array (`Ticket[]`, a superclass)[cite: 193, 194, 195]. [cite_start]This is because a Subtype object can always be treated as its Supertype object[cite: 185]. |
| **Illegal Copy (Supertype to Subtype)** | [cite_start]`arraycopy (tktarr, ektarr)` [cite: 219] | [cite_start]**Illegal** [cite: 219] | [cite_start]The **converse is illegal**[cite: 214]. [cite_start]You cannot copy a general `Ticket[]` array into a specific `ETicket[]` array because the `Ticket` array might contain objects that are *not* `ETicket` objects[cite: 217, 218]. |

***

### 4. Polymorphic Data Structures & Generics 🔗

[cite_start]**Polymorphic data structures** (like arrays and lists) should allow arbitrary elements[cite: 225]. [cite_start]In early Java, this was achieved by having the data structure store values of type **`Object`**[cite: 232].

#### **A. Problems with `Object`-based Polymorphic Lists**
[cite_start]Using the base `Object` class for data storage leads to two major problems[cite: 250]:

1.  **Type Information is Lost, requiring Casts:**
    * [cite_start]The `head()` method returns a generic `Object`[cite: 271, 280].
    * [cite_start]The user must **manually cast** the result back to the specific type (e.g., `Ticket`) to use its specific methods: `t2 = (Ticket) (list.head());`[cite: 269, 279].
2.  **List Need Not Be Homogenous:**
    * [cite_start]Since the list accepts any `Object`, it can unknowingly mix unrelated types, like a `Ticket` and a `Date`, leading to type confusion and potential runtime errors when elements are retrieved[cite: 296, 298, 299, 301].

#### **B. Generic Programming in Java (The Solution) ✨**

[cite_start]Java added **generic programming** to solve the limitations of `Object`-based data structures[cite: 307].

* [cite_start]**Type Parameters:** Classes and functions can now have **type parameters** (represented by angle brackets `< >`)[cite: 308].
    * [cite_start]*Example:* `class LinearList<T>`: This list holds values of a specified type `T`[cite: 309].
* [cite_start]**Type Safety:** Methods now return the specified type: `public T head() { ... }` returns a value of the same type `T` as the enclosing class, eliminating the need for manual casting and preventing accidental mixing of types[cite: 310].
* **Subclass Relationships in Generics:** Generics allow describing subclass relationships between the type variables themselves using the `extends` keyword.
    * *Example:* `public static <S extends T, T> void arraycopy (S[] src, T[] tgt){...}`. [cite_start]This ensures the source type `S` must be a subclass of the target type `T`, enforcing type safety at compile time[cite: 311, 312].

## 🎓 Lecture 2: Java Generics (Detailed Notes)

### 1\. Motivation for Java Generics (Recap) 💡

Generics were introduced to Java to properly address the two key aspects of polymorphism:

#### **A. Structural Polymorphism in Functions** [cite: 321, 322]

This is about writing functions that work across multiple types, provided they support the necessary **capabilities**[cite: 322]:

  * **Reverse an array/list:** Works for **any type**[cite: 323].
  * **Search for an element:** Needs an **equality check**[cite: 323].
  * **Sort an array/list:** Needs the ability to **compare values**[cite: 323].
  * **Constraints:** May need to impose constraints on argument types, such as when copying an array where the source type must *extend* the target type[cite: 324].

#### **B. Polymorphic Data Structures** [cite: 325]

Data structures (like lists and arrays) should be able to hold values of an **arbitrary type**[cite: 326]. They must also satisfy two requirements that the old `Object`-based approach failed to meet:

  * They must be **homogenous** (contain only one specified type)[cite: 327].
  * The user **should not have to cast** return values[cite: 328].

Generics solve these problems by introducing **type variables**[cite: 334, 340].


### 2\. Generics in Functions (Generic Methods) 🛠️

In Java, methods can be made generic by using a **type quantifier** (the type variable in angle brackets) *before* the return type[cite: 342, 359, 376].

#### **A. Generic Reverse**

This function works for any type `T`[cite: 342]:

```java
// "For every type T..."
public <T> void reverse (T[] objarr) {
    T tempobj;
    // usual array reversal code using T objects
}
```

  * **Explanation:** The `<T>` acts as a declaration: "For every type T, this method is valid"[cite: 342]. This replaces the need for the less-safe `Object[]` array.

#### **B. Generic Find**

This function ensures that the array and the search object have compatible types[cite: 362].

```java
public <T> int find (T[] objarr, T o){
    // ... search logic ...
}
```

  * **Benefit:** Searching for a value of an incompatible type (e.g., searching a `Ticket[]` array for a `Date` object) is now caught as a **compile-time error**, enhancing type safety[cite: 361, 382].

#### **C. Generic Array Copy (Type Consistency)**

1.  **Strict Array Copy (Identical Types)** [cite: 383]
    This simple form requires the source and target arrays to have an **identical type** `T`[cite: 384].

    ```java
    public static <T> void arraycopy (T[] src, T[] tgt){ 
        // ... copy logic ...
    }
    ```

2.  **More Generous Array Copy (Subtyping)** [cite: 408]
    To allow copying a subclass array into a superclass array, generics use the `extends` keyword to define constraints between type variables[cite: 409, 410].

    ```java
    public static <S extends T, T> // S must be a Subtype of T
    void arraycopy (S[] src, T[] tgt){
        // ... copy logic ...
    }
    ```

      * **Explanation:** This definition allows the source type `S` to be a **subtype** of the target type `T`[cite: 410]. For example, `ETicket[]` (`S`) can be copied into a `Ticket[]` (`T`).


### 3\. Generics in Classes (Polymorphic Data Structures) 🧱

Generics allow a class to be defined with a **type parameter** that applies to the class as a whole[cite: 438, 501].

#### **A. Generic `LinkedList<T>` Structure**

```java
public class LinkedList<T> { // T is the class's type parameter
    private Node first;

    public T head() { // Returns the specific type T
        // ... returns an element
    }

    public void insert(T newdata) { // Accepts the specific type T
        // ... inserts data
    }

    private class Node {
        private T data; // The node data is also of type T
        private Node next;
    }
}
```

  * **Scope of T:** The type parameter `T` applies throughout the class [cite: 438], including the fields of internal classes like `Node` [cite: 459, 480], the return value of `head()` [cite: 481], and the argument of `insert()`[cite: 481].

#### **B. Instantiating Generic Classes**

Generic classes are instantiated using a **concrete type** in the angle brackets `< >`[cite: 503].

```java
// The list is now guaranteed to hold only Ticket objects (Homogenous)
[cite_start]LinkedList<Ticket> ticketlist = new LinkedList<Ticket>(); [cite: 506]

// The list is now guaranteed to hold only Date objects
[cite_start]LinkedList<Date> datelist = new LinkedList<Date>(); [cite: 508]
```

  * **Benefit:** When using `ticketlist`, the `head()` method will return a `Ticket` object directly, eliminating the need for manual casting and enforcing type safety at compile time.


### 4\. Important Caveat: Hiding Type Variables ⚠️

It is critical to be careful not to **accidentally hide (or mask)** a class's type variable[cite: 516, 536].

  * **The Error:** If you define a generic method *inside* a generic class, the method's type quantifier will create a **new, local type variable** that hides the class's variable.

| Code Example (Incorrect) | Explanation |
| :--- | :--- |
| ` java public class LinkedList<T>{ // Class T public <T> void insert (T newdata) { // Method T... **NEW T** } }  ` | The **quantifier `<T>` masks** the type parameter `T` of `LinkedList`[cite: 561, 583]. The `T` in the argument of `insert()` is a **new, independent T**[cite: 539, 560, 582]. |
| **Contrast (Correct)** | This is different from a `static` generic method like `arraycopy`, where the method **must** declare its own type variable `<T>` because static methods are not tied to the class's generic type[cite: 612]. |


## 🎓 Lecture 3: Java Generics and Subtyping (Detailed Notes)

### 1\. The Peculiar Case of Array Subtyping (Covariance)

In Java, array subtyping exhibits a property called **covariance**.

| Key Concept | Definition |
| :--- | :--- |
| **Covariance** | A principle where if type $S$ is a subtype of type $T$, then the array type $S[]$ is also considered a subtype of the array type $T[]$[cite: 7, 51, 52]. |

**Example of Covariance:**
If `ETicket` is a subclass of `Ticket`, the following assignment is **allowed** by the compiler:

```java
ETicket[] elecarr = new ETicket[10];
Ticket[] ticketarr = elecarr; // OK. [cite_start]ETicket[] is a subtype of Ticket[][cite: 10, 18].
```

#### **The Runtime Danger** ⚠️

Covariance, while simple, breaks type safety and can lead to runtime errors:

```java
// ticketarr is declared as Ticket[], but it actually holds an ETicket[] array.
ticketarr[5] = new Ticket(); [cite_start]// Not OK[cite: 22].
[cite_start]// This attempts to put a general Ticket object into a slot that can only hold ETicket objects[cite: 23, 49].
[cite_start]// Result: A Type error at run time! [cite: 36, 50] (Specifically, an ArrayStoreException).
```


### 2\. Generic Subtyping (Invariance)

Unlike arrays, **generic classes are not covariant**[cite: 58, 301]. This is a crucial difference designed to preserve type safety at compile time.

| Key Concept | Definition |
| :--- | :--- |
| **Invariance** | A principle where if type $S$ is a subtype of $T$, the generic type `List<S>` is **not** a subtype of `List<T>`. |

**Example of Invariance:**

```java
// This will result in a compile-time error.
LinkedList<String> stringList;
LinkedList<Object> objectList = stringList; [cite_start]// Not compatible[cite: 59, 66].
```

  * **Why?** If this were allowed, you could add a non-String object (like an Integer) to `objectList`, which is secretly a `LinkedList<String>`, leading to the same runtime error issue seen with arrays. **Generics prevent this error at compile time.**

#### **The Limitation and Solution**

This strong type safety creates a limitation: A function designed for a general `LinkedList<Object>` cannot operate on a specific `LinkedList<String>`[cite: 67, 80].

**Solution 1: Generic Methods (`<T>`)**
We can use a **type quantifier** (`<T>`) to make the method generic:

```java
[cite_start]// <T> is a type quantifier: "For every type T, this function is valid." [cite: 115, 127]
public static <T> void printlist (LinkedList<T> l) {
    [cite_start]// Note: T is often not actually used inside the function[cite: 128].
    [cite_start]// We often use Object o as a generic variable to cycle through the list[cite: 129].
    Object o;
    // ... iteration and printing code ...
}
```

**Solution 2: Wildcards (`?`)**
Since the type variable $T$ is often not needed inside the function, we can simplify the syntax using a **wildcard**[cite: 179].

| Key Concept | Definition |
| :--- | :--- |
| **Wildcard (`?`)** | A generic argument that stands for an **arbitrary unknown type**[cite: 161, 178]. It is used when the specific type $T$ is not required for the return type or to declare local variables[cite: 304, 305]. |

```java
[cite_start]public static void printlist (LinkedList<?> l) { // ? means any list type is accepted [cite: 137, 152]
    Object o;
    // ... iteration and printing code ...
}
```

#### **Wildcard Assignment Restriction**

You can declare a variable using a wildcard and assign a concrete generic type to it[cite: 194]:

```java
LinkedList<?> l = new LinkedList<String>();
```

However, you cannot add any arbitrary object to $l$:

```java
l.add(new Object()); [cite_start]// Compile time error[cite: 197].
```

The **compiler cannot guarantee the types match** because it doesn't know what `?` represents (it could be `String`, `Date`, etc.)[cite: 208].


### 3\. Bounded Wildcards (Restricting the Unknown Type) 🔏

**Bounded wildcards** allow you to restrict the arbitrary unknown type (`?`) to classes that either extend a certain class or are a superclass of a certain class[cite: 306].

#### **A. Upper Bounded Wildcard (`? extends T`)**

This is used when you only need to **read** values from the list (like a source).

| Syntax | `LinkedList<? extends T>` [cite: 307] |
| :--- | :--- |
| **Meaning** | The list can hold objects of type $T$ or **any subclass** of $T$. |
| **Use Case** | **Reading** data (e.g., retrieving elements and calling methods defined in $T$). |
| **Example** | `public static void drawAll (LinkedList<? extends Shape> l)`[cite: 239]. |

If `Circle`, `Square`, and `Rectangle` all extend `Shape`, and `Shape` has a method `draw()` [cite: 214, 221, 235], this method can accept `LinkedList<Circle>`, `LinkedList<Square>`, or `LinkedList<Shape>` because all elements are guaranteed to be a subtype of `Shape`, meaning they all have a `draw()` method[cite: 238].

#### **B. Lower Bounded Wildcard (`? super T`)**

This is used when you only need to **write** values into the list (like a target).

| Syntax | `LinkedList<? super T>` [cite: 308] |
| :--- | :--- |
| **Meaning** | The list can hold objects of type $T$ or **any superclass** of $T$. |
| **Use Case** | **Writing** data (e.g., adding elements of type $T$). |
| **Example** | `public static void listcopy (LinkedList<T> src, LinkedList<? super T> tgt)`[cite: 287, 289]. |

  * **Explanation:** By using `? super T` for the target list, we guarantee that the target list is capable of safely holding objects of type $T$ (or a supertype of $T$) copied from the source list[cite: 285].


## 🎓 Lecture 4: Reflection (Detailed Notes)

### 1\. What is Reflection? 🤔

**Reflection** is the ability of a program or process to **examine, introspect, and modify its own structure and behavior** at runtime[cite: 624].

It consists of two main components:

| Component | Definition |
| :--- | :--- |
| **Introspection** | The ability of a program to **observe** (examine) and therefore reason about its own state and structure (e.g., checking what class an object belongs to)[cite: 641]. |
| **Intercession** | The ability of a program to **modify** its execution state or alter its own interpretation or meaning (e.g., dynamically calling a method or setting a field's value)[cite: 652]. |

#### **Simple Introspection Example**

A basic form of introspection you've used is the `instanceof` operator[cite: 658]:

```java
Employee e = new Manager(...);
if (e instanceof Manager) {
    // Code to execute only if 'e' is actually a Manager object
}
```


### 2\. The Need for Full Reflection (The `Class` Object) 💻

The `instanceof` operator is limited because it requires the type to be known **in advance**[cite: 674].

#### **The Problem Scenario: `classequal`**

You cannot use `instanceof` to solve a problem where you need to check if two objects, `o1` and `o2`, are instances of the *same*, but *unknown*, class at runtime[cite: 686, 688]:

```java
public static boolean classequal(Object o1, Object o2){...}
```

  * You **cannot** check against all defined classes, as this set is not fixed[cite: 703, 704].
  * You **cannot** use generic type variables with `instanceof` (e.g., `if (o1 instanceof T)` is syntactically disallowed)[cite: 715, 716, 717].

#### **Solution: The `getClass()` Method**

Java provides the `getClass()` method (inherited from `Object`) to solve this[cite: 723].

| Method | Syntax | Return Type |
| :--- | :--- | :--- |
| **`getClass()`** | `o1.getClass()` | An object of type **`Class`** [cite: 765] |

**Implementation of `classequal` using Reflection:**

```java
// Requires: import java.lang.reflect.*;
public static boolean classequal(Object o1, Object o2){
    return (o1.getClass() == o2.getClass()); // Compares the Class objects
}
```

#### **The `Class` Class and Reification**

For every class $C$ that is currently loaded in the Java Virtual Machine (JVM), Java creates an instance of the special class **`java.lang.Class`**[cite: 765, 796]. This object encapsulates all the information about class $C$.

  * **Reification:** This process of **encoding the execution state (abstract idea) as concrete data (an object)** is known as **reification**[cite: 813, 814, 815]. The `Class` object is the reification of a Java class's structure.


### 3\. Using the `Class` Object (Introspection & Intercession) 🚀

The `Class` object is the entry point for all advanced reflective capabilities, allowing both inspection (Introspection) and modification (Intercession).

#### **A. Creating New Instances at Runtime**

You can create a new object of a class that was previously unknown at compile time:

1.  **From an Existing Object:**
    ```java
    Class c = obj.getClass(); // Get class of an existing object
    Object o = c.newInstance(); [cite_start]// Create a new object of the same type as obj [cite: 824, 825]
    ```
2.  **From a Class Name (String):**
    ```java
    // Get the Class object using its name (String)
    [cite_start]Class c = Class.forName("Manager"); [cite: 838]
    Object o = c.newInstance(); [cite_start]// Create a new Manager object [cite: 839]
    ```

#### **B. Extracting Class Structure Details**

From a `Class` object, you can extract details about a class's **constructors, methods, and fields**[cite: 864]. These components are themselves represented by reflection classes: **`Constructor`**, **`Method`**, and **`Field`** (found in `java.lang.reflect`)[cite: 885].
| Information Category | Method on `Class` Object | Returns |
| :--- | :--- | :--- |
| **Constructors** | `c.getConstructors()` [cite: 905] | `Constructor[]` |
| **Methods** | `c.getMethods()` [cite: 904] | `Method[]` |
| **Fields** | `c.getFields()` [cite: 904] | `Field[]` |

These returned objects, like `Constructor` and `Method`, have their own functions to get further details, such as parameter types[cite: 916].

**Example: Getting Constructor Parameters**

```java
Constructor[] constructors = c.getConstructors();
for (int i = 0; i < constructors.length; i++) {
    // Returns an array of type Class[]
    [cite_start]Class[] params = constructors[i].getParameterTypes(); [cite: 928, 945]
}
```

#### **C. Invoking Methods and Accessing Fields (Intercession)**

Reflection allows you to interact with a class's members dynamically at runtime:

| Action | Example Code |
| :--- | :--- |
| **Invoke a Method** | `methods[3].invoke(obj, args);` // Invoke method at index 3 on `obj` with arguments `args` [cite: 957, 958] |
| **Get Field Value** | `Object o = fields[2].get(obj);` // Get the value of field at index 2 from `obj` [cite: 973, 974] |
| **Set Field Value** | `fields[3].set(obj, value);` // Set the value of field at index 3 in `obj` to `value` [cite: 975, 976] |


### 4\. Reflection and Security 🔐

By default, the methods like `getConstructors()` and `getMethods()` only return **publicly defined** members[cite: 991].

To access **private** components, you must use special "declared" methods:

| Public Access Only | Private/Declared Access |
| :--- | :--- |
| `getConstructors()` [cite: 991] | **`getDeclaredConstructors()`** [cite: 1001] |
| `getMethods()` | **`getDeclaredMethods()`** [cite: 1002] |
| `getFields()` | **`getDeclaredFields()`** [cite: 1003] |

  * **Security Concern:** Allowing access to private members is a **security issue**[cite: 1030]. Access to these private components is often restricted through **external security policies** (e.g., Security Managers)[cite: 1045].


### 5\. Uses and Limitations of Java Reflection 🚧

#### **A. Key Use Case (BlueJ)**

Programming environments like **BlueJ** (used to learn Java) utilize reflection[cite: 1051, 1075].

  * When a class is compiled, BlueJ uses Java's reflective capabilities to **create objects**, **invoke methods**, and **examine the state** of the compiled code[cite: 1068, 1078].
  * This means BlueJ does not have to maintain internal "debugging" information about each class; it relies on the information provided by the JVM's `Class` objects[cite: 1078].

#### **B. Limitations**

Java's reflection capabilities are limited compared to some other languages:
  * You **cannot create or modify classes at runtime**[cite: 1097, 1106].
      * `Class c = new Class(...);` is not possible[cite: 1099, 1118].
  * An environment like BlueJ must still **invoke the Java compiler** before a new class can be used[cite: 1100, 1120].
  * Languages like **Python** allow a class definition to be executed at runtime, and languages like **Smalltalk** allow methods to be redefined at runtime, capabilities not present in Java Reflection[cite: 1111, 1122, 1123].


## 🎓 Lecture 5: Java Generics at Run Time

This lecture focuses on what happens to Java's generic types (like `LinkedList<T>`) when the program is compiled and executed. The central concept is **Type Erasure**.

### 1\. What is Type Erasure? 🗑️

Type erasure is the process where the Java compiler removes generic type information after ensuring type safety at compile-time. [cite: 1448]

At runtime, Java **does not keep a record** of different versions of a generic class (like `LinkedList<String>` or `LinkedList<Date>`) as separate types. [cite: 1450]

#### **How Erasure Works:**

When the code is compiled, all type variables are replaced:

1.  **Unbounded Type Variables:** Type variables without any `extends` clause (like $T$ in `LinkedList<T>`) are promoted to **`Object`**. [cite: 1465]
      * **At Runtime:** `LinkedList<T>` becomes `LinkedList<Object>`. [cite: 1466, 1477]
2.  **Bounded Type Variables:** Type variables with a bound (like `? extends Shape`) are promoted to their **upper bound** (`Shape`). [cite: 1478]
      * **At Runtime:** `LinkedList<? extends Shape>` becomes `LinkedList<Shape>`. [cite: 1479, 1729]

#### **Immediate Consequence: `instanceof` is Illegal**

Because all generic type information is erased at runtime, you **cannot** use `instanceof` to check for a specific generic type.

  * `if (s instanceof LinkedList<String>)` is **illegal** because the JVM only sees `LinkedList<Object>` and cannot know it was a `String` list. [cite: 1451, 1462, 1731]
  * `if (o instanceof T)` is also **illegal** because the type $T$ is not preserved at runtime. [cite: 1493, 1732]


### 2\. Consequences of Type Erasure

Type erasure has several important side effects that you must be aware of.

#### **A. `getClass()` Comparison**

At runtime, all instantiations of a generic class share the **same `Class` object**.

```java
o1 = new LinkedList<Employee>();
o2 = new LinkedList<Date>();

if (o1.getClass() == o2.getClass()) {
    [cite_start]// This block IS executed, because the comparison returns True. [cite: 1499, 1502]
}
```

  * **Explanation:** Because of type erasure, both `o1` and `o2` are just `LinkedList` objects at runtime. Their `getClass()` method returns the same `Class` object (`LinkedList.class`).

#### **B. Illegal Method Overloading**

You **cannot** overload a method based on different instantiations of a generic type.

| Key Concept | Definition |
| :--- | :--- |
| **Method Overloading** | Defining two or more methods within the same class that share the same name but have different parameter lists (e.g., `print(int x)` and `print(String s)`). |

**Illegal Example:**

```java
public class Example {
    // This is illegal and will not compile
    [cite_start]public void printlist (LinkedList<String> strList) { ... } [cite: 1519]
    [cite_start]public void printlist (LinkedList<Date> dateList) { ... } [cite: 1519]
}
```

  * **Explanation:** After type erasure, both methods have the **exact same signature**: `printlist(LinkedList<Object>)`. [cite: 1535] This creates a duplicate method, which is not allowed. [cite: 1516, 1733]


### 3\. Type Erasure and Arrays (A Special Case) 📦

This is a critical restriction in Java Generics.

1.  **Recall Array Covariance:** Java arrays are "covariant." If `ETicket` extends `Ticket`, then `ETicket[]` is a subtype of `Ticket[]`. [cite: 1542, 1549] This leads to **runtime type errors** (`ArrayStoreException`) if you try to put a `Ticket` into an `ETicket[]` array. [cite: 1550, 1554]
2.  **Generics are Invariant:** Generics were made "invariant" (`LinkedList<ETicket>` is *not* a subtype of `LinkedList<Ticket>`) specifically to *avoid* this runtime error.
3.  **The Restriction:** To avoid these problems entirely, Java **forbids the instantiation of generic arrays**. [cite: 1568, 1734]

**The Rule:**

```java
T[] newarray; [cite_start]// OK to declare a generic array [cite: 1569, 1571]
newarray = new T[100]; // ILLEGAL. [cite_start]Cannot create a new generic array. [cite: 1573]
```

  * **Ugly Workaround:** You may see this workaround, which casts an `Object` array. It **generates a compiler warning** but functions. [cite: 1592, 1618]
    ```java
    [cite_start]newarray = (T[]) new Object[100]; [cite: 1595]
    ```


### 4\. Type Erasure and Primitive Types (Wrapper Classes) 🎁

This section explains *why* you must use `Integer` instead of `int` in generic classes.

#### **A. The Problem**

1.  As we know, type erasure promotes all type variables ($T$) to **`Object`** at runtime. [cite: 1625, 1631]
2.  Java's **basic types** (also called primitive types) like `int`, `float`, `double`, `boolean`, etc., are **not compatible with `Object`**. [cite: 1632] They are not classes.
3.  **Conclusion:** You **cannot** use a basic type as a generic type variable. [cite: 1640]
      * `LinkedList<int>` is **illegal**. [cite: 1641]
      * `LinkedList<double>` is **illegal**. [cite: 1641]

#### **B. The Solution: Wrapper Classes**

Java provides a "wrapper class" for each basic type. These are standard classes that "wrap" the primitive value in an `Object`. [cite: 1651]

| Basic (Primitive) Type | Wrapper Class (is an `Object`) |
| :--- | :--- |
| `byte` | **`Byte`** [cite: 1652] |
| `short` | **`Short`** [cite: 1652] |
| `int` | **`Integer`** [cite: 1652] |
| `long` | **`Long`** [cite: 1652] |
| `float` | **`Float`** [cite: 1653] |
| `double` | **`Double`** [cite: 1653] |
| `boolean` | **`Boolean`** [cite: 1653] |
| `char` | **`Character`** [cite: 1653] |
*(Note: All wrapper classes other than `Boolean` and `Character` extend the class `Number`.)* [cite: 1665]

#### **C. Autoboxing**

In modern Java, you rarely have to convert between basic types and wrapper types manually. [cite: 1682, 1693] This conversion is done implicitly (automatically) through a process called **autoboxing**. [cite: 1699]

| Action | Manual Conversion (Old Way) | **Autoboxing (Modern Way)** |
| :--- | :--- | :--- |
| **"Boxing"** (primitive to object) | `int x = 5;` [cite: 1672] <br> `Integer myx = new Integer(x);` [cite: 1674] | `int x = 5;` [cite: 1700] <br> `Integer myx = x;` [cite: 1701] |
| **"Unboxing"** (object to primitive) | `int y = myx.intValue();` [cite: 1676] | `int y = myx;` [cite: 1702] |

**Final Rule:** You must always use wrapper types when defining generic data structures (e.g., `LinkedList<Integer>`, `LinkedList<Double>`). [cite: 1720, 1735]
