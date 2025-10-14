# Week 2 

## Lecture 1: A First Taste of Java

### The "Hello, world" Program Comparison

The complexity of the "Hello, world" program varies significantly by language:

| Language | Code Example | Note |
| :--- | :--- | :--- |
| **Python** | `print("hello, world")`  | Very concise. |
| **C** | `\#include <stdio.h>` `main()` `{ printf("hello, world\n"); }` | Requires including a standard library and defining a `main` function. |
| **Java** | `public class helloworld {` `public static void main(String [] args)` `{ System.out.println("hello, world"); }` `}` | Requires class definition and a specific main method signature. |


## 💻 Unpacking the Java Syntax

Java's complexity stems from its strict **object-oriented design**. **All code in Java lives within a class**; there are no free-floating functions like in Python or C.

The structure of the main method is:
`public static void main(String [] args)` 

### 1. `public class helloworld`

* **`class`**: Defines the code as a **class**.
* **`public`**: A **modifier** specifying **visibility**. This means the class is available to run from outside the program package.

### 2. `main(String [] args)`

This is the fixed function name that is **called by default** to start the program, a convention adopted from C.

* **Input Parameter (`String [] args`):** The input parameter is an **array of strings**. These represent the **command line arguments** passed to the program.
* **Return Type (`void`):** The function has **no output**, so its return type is `void`.

### 3. Modifiers

Modifiers specify how and where the class or method can be used, contributing to the "unavoidable overhead of object-oriented design".

| Modifier | Concept | Explanation |
| :--- | :--- | :--- |
| **`public`**  | **Visibility**  | Specifies that the function must be **available to run from outside the class** (e.g., by the *operating system* or the *JVM*) . |
| **`static`**  | **Availability**  | Since functions defined inside classes are normally **attached to objects**, `static` is needed for the starting function. It declares a function that **exists independent of the dynamic creation of objects** . This bypasses the question of how to create an object *before* the program starts. |

### 4. The Operation: `System.out.println("hello, world")`

This line performs the actual printing operation.

* **`System`**: A **public class** defined within Java .
* **`out`**: A **stream object** defined within the `System` class. It acts **like a file handle** and must also be **`static`**.
* **`println()`**: A **method** associated with streams. It prints its argument followed by a **newline**, similar to Python's `print()`.
* **Punctuation (`{`, `}`, `;`):** Java uses **punctuation** to delimit blocks and statements , unlike the layout and indentation used in Python.


## ⚙️ Compiling and Running Java

Java programs are a **collection of classes**.

### 1. Source Code and Compilation

* **File Structure:** Each class must be defined in a separate file with the **same name** as the class and the extension **`.java`**. For example, the class `helloworld` is in `helloworld.java`.
* **Compilation:** The `javac` compiler is used to compile the `.java` source code into **JVM bytecode** .
    * **Command:** `javac helloworld.java`.
    * **Output:** Creates a bytecode file named `helloworld.class`.
    * **Note:** `javac` requires the file extension `.java`. It automatically follows dependencies and compiles all required classes.

### 2. Execution

* **Java Virtual Machine (JVM):** Java programs are usually **interpreted on the JVM**.
    * **Portability:** The JVM provides a **uniform execution environment** across different operating systems. The semantics of Java are defined in terms of the JVM, making the code guaranteed to be portable ("Write once, run anywhere") and OS-independent.
* **Running:** The `java` command is used to interpret and run the bytecode.
    * **Command:** `java helloworld`.
    * **Note:** The file extension `.class` should **not** be provided to the `java` command.

### Summary of Java's Design
Java requires function and variable types to be **declared in advance**. The heavy syntax, involving many modifiers, is an unavoidable result of its object-oriented design, ensuring structure, type safety, visibility, and availability (allowing static definitions).


## Lecture 2: Basic DataTypes

### Scalar (Primitive) Data Types

Although Java is an object-oriented language where, ideally, all data should be encapsulated as objects, using objects for simple numeric values is often cumbersome. Therefore, Java retains eight **primitive scalar types** to manipulate numeric values like conventional languages.

The **size of each type is fixed by the JVM** (Java Virtual Machine) and does **not depend on the native architecture**.

| Type | Description | Size in bytes |
| :--- | :--- | :--- |
| **Integers** | `byte`, `short`, `int`, `long` | **`byte`**: 1, |
| | | **`short`**: 2, |
| | | **`int`**: 4, |
| | | **`long`**: 8, |
| **Floating Point** | `float`, `double` | **`float`**: 4, |
| | | **`double`**: 8, |
| **Character** | `char`, | **`char`**: 2 (used for Unicode) |
| **Boolean** | `boolean` | **`boolean`**: 1 , |

***

### Variable Declaration and Initialization

In Java, **variables must be declared before they are used**.

* **Declaration:** `int x, y;`
* **Statement End:** Note the **semicolon (`;`)** after each statement.
* **Declaration Location:** Declarations can technically come **anywhere** in the code, but should be used judiciously to retain readability.
* **Initialization:** Variables can be **initialized at the time of declaration**.
    * *Example:* `int x = 10;`

#### Assigning Values

* **Numbers:** The assignment statement works as usual (e.g., `x = 5;`).
* **Characters (`char`):** Characters are written with **single-quotes (`'x'`) only**.
    * Unicode characters can be represented using an escape sequence: `d = '\u03C0';` (Greek pi).
    * **Double quotes** denote **strings** (which are objects, not characters).
* **Booleans (`boolean`):** Boolean constants are **`true`** and **`false`**.

### Constants

To declare a value as a **constant** (a value that cannot be changed after initialization), the modifier **`final`** is used.

* *Example:* `final float pi = 3.1415927f;`
    * Attempting to re-assign a `final` variable (e.g., `pi = 22/7;`) is **flagged as an error**.
* **Float Note:** You must **append `f`** after a number literal for it to be interpreted as a `float`; otherwise, it is interpreted as a `double` by default.

***

### Operators and Shortcuts

* **Arithmetic Operators:** The usual operators are supported: `+`, `-`, `*`, `/`, `%` (modulo).
* **Integer Division:** Java has **no separate integer division operator** (like Python's `//`). When **both arguments** to the `/` operator are integers, it performs **integer division**.
    * *Example:* `float f = 22/7;` results in the value `3.0` because the division `22/7` (integer arguments) is performed first, yielding the integer `3`, which is then implicitly converted to the float `3.0`.
* **Exponentiation:** There is **no exponentiation operator** (like `**` in python). You must use the **`Math.pow(a, n)`** method, which returns a `double`.
* **Increment/Decrement Operators:** Special operators exist for modifying integers by one.
    * `a++;` is the same as `a = a + 1;`.
    * `b--;` is the same as `b = b - 1;`.
* **Assignment Shortcuts:** Shortcuts are available for updating a variable.
    * `a += 7;` is the same as `a = a + 7;`.
    * `b *= 12;` is the same as `b = b * 12;`.

***

### Strings and Arrays (Object Types)

Unlike the primitive types, **Strings and Arrays are objects** in Java.

### Strings

* **String Class:** `String` is a **built-in class**.
* **Declaration:** `String s, t;`
* **Constants:** String constants are enclosed in **double quotes** (e.g., `"Hello"`).
* **Concatenation:** The `+` operator is **overloaded for string concatenation**.
    * *Example:* `String u = s + " " + t;` results in `"Hello world"`.
* **Not Character Arrays:** Strings are **not arrays of characters**.
    * We **cannot** use array-style assignment to change characters (e.g., `s[3] = 'p';`).
    * Instead, we must **invoke methods** like `substring()` from the `String` class to modify a string.
* **Immutability:** When we "change" a String (e.g., `s = s.substring(0,3) + "p!";`), we are actually creating a **new `String` object**, and the variable `s` then points to this new object. Java uses **automatic garbage collection** to manage old, unreferenced objects.

#### Arrays

* **Objects:** Arrays are also **objects**.
* **Declaration:** The typical declaration involves two steps:
    1.  Declare the reference: `int[] a;` (or `int a[]`).
    2.  Instantiate the array object: `a = new int [100];`.
    * This can be combined: `int[] a = new int [100];`.
    * The **size of the array can vary** at runtime (e.g., `a = new int [n];`).
* **Array Size:** The size of the array is given by the **`.length`** property: `a.length`.
    * *Note:* This is an **attribute (`.length`)** for arrays, but a **method (`.length()`)** for String objects.
* **Indices:** Array indices run from **0 to `a.length - 1`**, .
* **Array Constants:** Arrays can be initialized using array constants with curly braces: `{v1, v2, v3}`.
    * *Example:* `a = {2, 3, 5, 7, 11};`.

***

### Resources

Java has numerous versions; the materials reference **Java 11**. Extensive online documentation is available and should be consulted in case of doubt.

## Lecture 3: Java Control Flow

### Program Layout

In Java, control flow depends on strict syntax rules:

  * **Statements:** Every individual statement must end with a **semicolon (`;`)**.
  * **Blocks:** Blocks of statements (such as the body of a loop, conditional block, or method) are delimited by **braces (`{...}`)**.
  * **Indentation:** Indentation is **not forced** (unlike Python)


### Conditional Execution

Conditional statements allow code to execute based on a boolean condition.

  * **`if-else` Structure:**
    ```java
    if (condition) {
        // statements for true condition
    } else {
        // statements for false condition (optional)     
    }
    ```
  * **Syntax Rules:**
      * The **condition must be in parentheses** (`(condition)`)
      * If the body contains only a **single statement**, the surrounding braces `{}` are **not needed**.
  * **Multiple Conditions:** Java uses the standard `else if` structure, as there is **no `elif`** keyword like in Python. Nested `if` statements are treated as a single statement, not requiring separate braces

*Example:*

```java
public static int sign(int v) {
    if (v < 0) {
        return(-1); // Single statement, braces optional
    } 
    else if (v > 0) {
        return(1);
    } 
    else {
        return(0);
    }
}
```

### Conditional Loops

Conditional loops execute a block of code repeatedly as long as a condition is met.

#### 1. `while` Loop
> The `while` loop checks the condition **before** executing the loop body.

```java
while (condition) {
    // code of block
}
```
* The condition must be in parentheses.
* If the loop body is a single statement, braces are not required.

#### 2. `do-while` Loop
> The `do-while` loop executes the body **at least once** before checking the condition.

```java
do {
    // statements
} while (condition); // Condition checked at the end
```
* This structure is particularly **useful for interactive user input** where you need to execute an action (like reading input) before testing its validity.

### Iteration

Java provides two main types of `for` loops for iteration.

#### 1. C-style `for` Loop
> This loop is designed for controlled iteration, typically using a counter.
```java
for (init; cond; upd){
    // block of code ...
}
```
* **`init`**: Initialization of the loop variable
* **`cond`**: Terminating condition
* **`upd`**: Update statement (usually increment/decrement)

- **Equivalence:** The `for` loop is **completely equivalent** to a `while` loop structure:
    ```java
    i = 0; // init     
    while (i < n){// cond
        // loop body
        i++; // upd
    }
    ``` 

* **Local Scope:** The loop variable can be defined **within the loop's initialization** (e.g., `for (int i = 0; i < n; i++)`), making the variable **`i` local to the loop**. This is an example of Java's local scoping

* **Style Note:** It is considered **poor style** to write a `for` loop in place of a `while` loop when the C-style structure (init; cond; upd) is not naturally used.

#### 2. Enhanced `for` Loop (For-Each)
Introduced later in Java in the style of Python's `for x in list`, this loop iterates directly over the elements of an array or collection.

```java
for (type x: a) { 
    // 'x' is the element, 'a' is the array/collection
    // do something with x;
}
```

* The loop variable (`v` in the example) must be **declared in the local scope** within the loop header.

*Example:*

```java
public static int sumarray(int[] arr) {
    int sum = 0;
    for (int v : arr) { 
        // v takes on the value of each element in array arr       
        sum += v;
    }
    return(sum);
}
```

### Multiway Branching

The `switch` statement selects among different options based on the value of an expression.

```java
switch (v) {
    case -1: {
        System.out.println("Negative");
        break; // Crucial to prevent fall-through
    }
    // ... other cases
}
``` 

* **Options:** The `case` options must be **constants** You cannot use conditional expressions (e.g., `< 0`)
* **Fall-Through:** By default, Java **"falls through"** from one `case` to the next if a `break` is not included.
* **`break`:** You **need to explicitly break out of the `switch`** to prevent fall-through. The `break` keyword is also available for use within loops.
* **Return Type:** If the method has a non-void return type, every execution path must return an appropriate value.

## Lecture 4: Classes

### Core Concepts: Class and Object
- _**Class:**_ A template for an encapsulated type. It is a blueprint that defines the variables (attributes) and methods (behavior) common to all objects of a certain kind.
- _**Object:**_ An instance of a class. An object is a concrete entity created from the class template.


### Defining a Class

The process of defining a class is illustrated using a `Date` class example.

* **Class Definition Block:** Uses the `class` keyword followed by the class name, enclosed in curly braces (`{}`).
* **Visibility Modifier (`public`):** The `public` modifier is used to indicate the visibility of the class.
    * Java allows `public` to be omitted.
    * The **default visibility** is **public to the package**.
* **Package:** Administrative units of code. All classes defined in the same directory form part of the same package.

| Term | Definition & Context |
| :--- | :--- |
| **`public class Date { ... }`** | The definition of a class named `Date` |
| **`private int day, month, year;`** | These are **instance variables** |
| **Instance Variables:** | Variables defined within a class; each concrete object of that class (e.g., `Date`) will have **local copies** of these variables |
| **`private` (Modifier):** | These variables are marked **`private`**, which enforces **encapsulation** They are accessible only within the class itself. |
| **Encapsulation:** | The principle of bundling data (instance variables) and the methods that operate on the data into a single unit (the class). Keeping instance variables `private` helps uphold this principle. |

### Creating and Managing Objects

### A. Creating an Object

The process involves two main steps:

1.  **Declaration:** Declaring the variable type using the class name. Example: `Date d;`.
2.  **Instantiation:** Using the **`new`** keyword, which creates a new object in memory. Example: `d = new Date();`.

### B. Accessor and Mutator Methods

After creating an object, methods are needed to set and retrieve the values of the private instance variables, which are often called **Accessor and Mutator methods**.

| Method Type | Purpose | Example Method |
| :--- | :--- | :--- |
| **Mutator (Setter)** | Methods added to **update (set) the values** of instance variables. Example: `setDate(int d, int m, int y)`. |
| **Accessor (Getter)** | Methods added to **read and report the values** of instance variables. Examples: `getDay()`, `getMonth()`, `getYear()`. |

* **`this`:** A special keyword that is a **reference to the current object**. It is used to disambiguate between an instance variable and a local method parameter if they have the same name. It can be omitted if the reference is unambiguous.


### Object Initialization with Constructors

It would be more convenient to initialize an object immediately upon creation rather than calling a separate `setDate()` method afterwards.

* **Constructor:** A **special function** that is implicitly **called when an object is created** (when `new` is used).
    * It must have the **same name as the class**.
    * Example use: `d = new Date(13, 8, 2015);`.

### A. Constructor Overloading and Chaining

* **Multiple Constructors (Overloading):** Java allows **function overloading**, where multiple functions (including constructors) can have the **same name** but **different signatures** (i.e., different number or types of arguments). This allows for objects to be created with different sets of initial parameters.
* **Constructor Chaining (`this()`):** A later constructor can **call an earlier one using `this`**. This avoids code duplication. Example: `this(d, m, 2021);` calls the three-argument constructor from the two-argument constructor.

### B. Default Constructor

* **Implicit Default Constructor:** If **no constructor is defined** in a class, Java provides a **default constructor with empty arguments**. This allows `new Date()` to be called implicitly4].
    * This default constructor sets instance variables to sensible defaults (e.g., `int` variables are set to 0).
* **Explicit No-Argument Constructor:** If *any* constructor is defined, the implicit default constructor is **not valid**. In this case, an **explicit constructor without arguments** must be defined if the user wants to call `new Date()`.

### Copy Constructors and Cloning

* **Copy Constructor:** A special type of constructor used to **create a new object from an existing one**.
    * It takes an object of the **same type as an argument**.
    * It copies the instance variables from the source object to the new object.
    * **Visibility Note:** The `private` instance variables of the argument object *are visible* within the copy constructor of the same class.

* **Copy Mechanism:** The copy constructor copies the values of the source object's instance variables (e.g., `this.day = d.day;`).
    * **Shallow Copy vs. Deep Copy:** The document notes a distinction when instance variables are themselves objects:
        * **Shallow Copy:** May result in **aliasing** (two objects referencing the same underlying object) rather than truly copying.
        * **Deep Copy:** Required to ensure the new object is **disjoint from the old one** (meaning changes to one don't affect the other). This topic is mentioned for later discussion.


## Lecture 5: Java Input Output

### Generating Output


