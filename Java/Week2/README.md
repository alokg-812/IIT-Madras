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

