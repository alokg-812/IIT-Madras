# Week 2 

## Lecture 1: A First Taste of Java

### The "Hello, world" Program Comparison

The complexity of the "Hello, world" program varies significantly by language:

| Language | Code Example | Note |
| :--- | :--- | :--- |
| **Python** | `print("hello, world")` [cite: 545] | Very concise[cite: 544]. |
| **C** | `\#include <stdio.h>` `main()` `{ printf("hello, world\n"); }` [cite: 560-563] | Requires including a standard library and defining a `main` function[cite: 559]. |
| **Java** | `public class helloworld {` `public static void main(String [] args)` `{ System.out.println("hello, world"); }` `}` [cite: 585-590] | Requires class definition and a specific main method signature. |

---

## 💻 Unpacking the Java Syntax

Java's complexity stems from its strict **object-oriented design**[cite: 851]. **All code in Java lives within a class**; there are no free-floating functions like in Python or C[cite: 612, 615].

The structure of the main method is:
`public static void main(String [] args)` [cite: 586]

### 1. `public class helloworld`

* **`class`**: Defines the code as a **class**[cite: 611].
* **`public`**: A **modifier** specifying **visibility**[cite: 616]. This means the class is available to run from outside the program package.

### 2. `main(String [] args)`

This is the fixed function name that is **called by default** to start the program, a convention adopted from C[cite: 634, 635].

* **Input Parameter (`String [] args`):** The input parameter is an **array of strings**[cite: 643]. These represent the **command line arguments** passed to the program[cite: 644, 659].
* **Return Type (`void`):** The function has **no output**, so its return type is `void`[cite: 644, 660].

### 3. Modifiers

Modifiers specify how and where the class or method can be used, contributing to the "unavoidable overhead of object-oriented design"[cite: 851].

| Modifier | Concept | Explanation |
| :--- | :--- | :--- |
| **`public`** [cite: 663] | **Visibility** [cite: 852] | Specifies that the function must be **available to run from outside the class** (e.g., by the operating system/JVM)[cite: 662, 663]. |
| **`static`** [cite: 683] | **Availability** [cite: 853] | Since functions defined inside classes are normally **attached to objects**[cite: 676], `static` is needed for the starting function. It declares a function that **exists independent of the dynamic creation of objects**[cite: 686, 687]. This bypasses the question of how to create an object *before* the program starts[cite: 680]. |

### 4. The Operation: `System.out.println("hello, world")`

This line performs the actual printing operation[cite: 694].

* **`System`**: A **public class** defined within Java[cite: 695, 709, 726].
* **`out`**: A **stream object** defined within the `System` class[cite: 710, 727]. It acts **like a file handle** [cite: 716, 728] and must also be **`static`**[cite: 718, 729].
* **`println()`**: A **method** associated with streams[cite: 730]. It prints its argument followed by a **newline**, similar to Python's `print()`[cite: 731, 750].
* **Punctuation (`{`, `}`, `;`):** Java uses **punctuation** to delimit blocks and statements [cite: 758], unlike the layout and indentation used in Python[cite: 759].

---

## ⚙️ Compiling and Running Java

Java programs are a **collection of classes**[cite: 766, 778].

### 1. Source Code and Compilation

* **File Structure:** Each class must be defined in a separate file with the **same name** as the class and the extension **`.java`**[cite: 779]. For example, the class `helloworld` is in `helloworld.java`[cite: 780].
* **Compilation:** The `javac` compiler is used to compile the `.java` source code into **JVM bytecode**[cite: 808, 833].
    * **Command:** `javac helloworld.java`[cite: 808].
    * **Output:** Creates a bytecode file named `helloworld.class`[cite: 808, 820, 833].
    * **Note:** `javac` requires the file extension `.java`[cite: 836]. It automatically follows dependencies and compiles all required classes[cite: 843].

### 2. Execution

* **Java Virtual Machine (JVM):** Java programs are usually **interpreted on the JVM**[cite: 801].
    * **Portability:** The JVM provides a **uniform execution environment** across different operating systems[cite: 802, 857]. The semantics of Java are defined in terms of the JVM, making the code guaranteed to be portable ("Write once, run anywhere") and OS-independent[cite: 802, 857].
* **Running:** The `java` command is used to interpret and run the bytecode[cite: 821, 834].
    * **Command:** `java helloworld`[cite: 821].
    * **Note:** The file extension `.class` should **not** be provided to the `java` command[cite: 842].

### Summary of Java's Design
Java requires function and variable types to be **declared in advance**[cite: 855]. The heavy syntax, involving many modifiers, is an unavoidable result of its object-oriented design, ensuring structure, type safety, visibility, and availability (allowing static definitions) [cite: 850-853, 855].