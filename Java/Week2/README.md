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

Java has numerous versions; the materials reference **Java 11**]. Extensive online documentation is available and should be consulted in case of doubt.