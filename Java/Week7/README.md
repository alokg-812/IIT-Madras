# Week 7

## Lecture 1: Dealing with Errors

### 1. What is an “Error Situation”?
**Layman Explanation**  
Something went wrong that the programmer didn’t expect in the normal happy flow:  
- User typed a wrong file name  
- Network cable got unplugged  
- You tried to read index 99 of a 10-element array  
- You divided 100 by 0  
- Your variable is null but you called .length() on it

**Technical Name**  
These are called **Exceptions** in Java.

### 2. Two Old & Bad Ways to Report Errors
1. Return a special “invalid” value  
   ```java
   int value = readNumber();
   if (value == -1) { /* means error */ }
   ```
   Problem: What if -1 is a valid number?

2. Use a global error variable (like C’s errno)  
   Very messy and not thread-safe.

Java said NO to both → introduced **Exceptions**.

### 3. What is an Exception in Java?
**Layman Explanation**  
An exception is a red flag object that jumps up the call stack saying “Something went wrong here!” and forces the program to stop unless someone catches it.

**Technical Explanation**  
- Every exception is an object of class Throwable (or its subclasses)  
- Throwable has two big children:  
  ├─ **Error** → Very serious (OutOfMemoryError, StackOverflowError) → usually you cannot recover  
  └─ **Exception** → Recoverable problems → this is what we handle

### 4. Exception Hierarchy You Must Draw in Exams

```
                java.lang.Object
                       ↑
                java.lang.Throwable
               /                  \
      java.lang.Error         java.lang.Exception
                                 /               \
                   IOException         RuntimeException
                                          /         |          \
                           NullPointerException   ArrayIndexOutOfBoundsException
                                          ArithmeticException  etc.
```

### 5. Two Categories of Exceptions (Most Important for Exams)

| Category              | Examples                               | Must you write try-catch or throws? | Can you ignore? |
|-----------------------|----------------------------------------|--------------------------------------|-----------------|
| **Checked Exceptions**| IOException, FileNotFoundException, SQLException | YES – compiler forces you          | No              |
| **Unchecked Exceptions** (RuntimeException and Error) | NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException | NO – optional                     | Yes (but bad practice) |

Rule of thumb:  
Checked = external problems (files, network)  
Unchecked = your coding mistakes

### 6. How to Throw an Exception
```java
throw new ArithmeticException("Cannot divide by zero!");
// or shorter (Java auto-wraps the string)
throw new RuntimeException("Something bad happened");
```

### 7. try-catch-finally – The Safety Net

**Layman Explanation**  
try → “Let me try this risky code”  
catch → “If anything bad happens, I will handle it here”  
finally → “Do this no matter what (even if return or exception)”

**Code Example**
```java
try {
    int x = 10 / 0;                     // throws ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Don't divide by zero!");
    e.printStackTrace();                // prints full error
} finally {
    System.out.println("I always run – cleanup code goes here");
}
```

### 8. Multi-catch (Java 7+) – Clean Code
```java
try {
    // some code
} catch (IOException | SQLException e) {   // one block for many types
    e.printStackTrace();
}
```

### 9. try-with-resources (Java 7+) – Auto close files, sockets etc.
**Old painful way**
```java
FileReader fr = null;
try {
    fr = new FileReader("data.txt");
    // use fr
} finally {
    if (fr != null) fr.close();
}
```

**Beautiful new way (auto-closes)**
```java
try (FileReader fr = new FileReader("data.txt")) {
    // use fr → automatically closed at the end
} catch (IOException e) {
    e.printStackTrace();
}
```

### 10. throws clause – “I don’t want to handle it, caller must”
```java
public void readFile() throws IOException {   // compiler forces caller to handle
    FileReader fr = new FileReader("missing.txt");
}
```

### 11. Common Built-in Exceptions You Must Know

| Exception                        | When it happens                            |
|----------------------------------|--------------------------------------------|
| NullPointerException             | obj.method() when obj is null              |
| ArrayIndexOutOfBoundsException   | arr[10] when size is 5                     |
| ArithmeticException              | 10 / 0                                     |
| ClassCastException               | (Dog) animal when animal is actually Cat   |
| IllegalArgumentException         | You pass bad value (negative age etc.)    |
| NoSuchElementException           | next() on empty Iterator                   |
| UnsupportedOperationException    | add() on unmodifiable list                 |

### One-Page Golden Summary (Stick on Wall)

| Concept                     | Checked? | Must Handle? | Example                        |
|-----------------------------|----------|--------------|--------------------------------|
| IOException                 | Yes      | Yes          | File, Network                  |
| RuntimeException & children | No       | Optional     | NullPointer, IndexOutOfBounds  |
| Error                       | No       | Usually no   | OutOfMemoryError               |

**Best Practices**
1. Always use try-with-resources for files/DB/network  
2. Never catch Exception blindly → catch specific ones  
3. Never do this:
   ```java
   catch (Exception e) {}   // silent death – very bad
   ```
4. Throw early, catch late


## Exceptions in Java

### 1. The Complete Throwable Hierarchy (Draw This Diagram in Every Exam!)

```
                     java.lang.Object
                            ↑
                  java.lang.Throwable
                   /                 \
         java.lang.Error         java.lang.Exception
                                       /              \
                             Checked Exceptions     RuntimeException (and its children)
                             (e.g., IOException)   (e.g., NullPointerException,
                                                    ArrayIndexOutOfBoundsException,
                                                    ArithmeticException, etc.)
```

### 2. Three Main Categories You Must Remember

| Category                | Meaning                                      | Can you recover? | Must write try-catch or throws? | Examples                                      |
|-------------------------|----------------------------------------------|------------------|----------------------------------|-----------------------------------------------|
| **Error**               | Serious JVM-level problems                   | Almost never     | No                               | OutOfMemoryError, StackOverflowError         |
| **Checked Exception**   | External problems / violated assumptions     | Yes              | YES – compiler forces you        | IOException, FileNotFoundException, SQLException |
| **RuntimeException**    | Programming bugs (your mistake)              | Sometimes        | No – optional                    | NullPointerException, IndexOutOfBounds, ArithmeticException |

Golden Rule:  
Checked → Think “I/O, network, files”  
Unchecked (RuntimeException + Error) → Think “My coding mistake or unrecoverable”

### 3. Why Checked vs Unchecked?

| Checked (e.g., IOException)           | Unchecked (e.g., NullPointerException)     |
|---------------------------------------|---------------------------------------------|
| Forces you to think about recovery    | Would be annoying to write try-catch everywhere for silly bugs |
| Example: opening a file that may not exist | Example: calling .length() on null string  |

### 4. How Exceptions Actually Work (Call Stack Magic)

```java
main() → methodA() → methodB() → methodC()
                                   ↑
                             throws new IOException()
```

The exception travels backwards (up the call stack) until:
- Someone catches it with try-catch → handled
- No one catches it → program crashes with stack trace

### 5. Creating Your Own Exception Classes (Very Common in Exams)

**1. Custom checked exception**
```java
public class NegativeQuantityException extends Exception {     // checked
    public NegativeQuantityException(String msg) {
        super(msg);
    }
}
```

**2. Custom unchecked exception**
```java
public class InvalidAgeException extends RuntimeException {    // unchecked
    public InvalidAgeException(String msg) {
        super(msg);
    }
}
```

**Usage**
```java
if (quantity < 0) {
    throw new NegativeQuantityException("Quantity cannot be negative");
}
```

### 6. The Four Keywords You Must Know Cold

| Keyword     | Where it goes                               | Meaning                                      |
|-------------|---------------------------------------------|----------------------------------------------|
| throw       | Inside method body                          | Manually throw an exception object           |
| throws      | In method declaration                       | “This method might throw these exceptions”  |
| try         | Block around risky code                     | “Try this, but be ready for exceptions”     |
| catch       | Immediately after try                       | “If this exception occurs, do this”          |
| finally     | After last catch (optional)                 | “Run this no matter what”                    |

### 7. Complete Example (Most Asked Pattern)

```java
public static void processOrder(int qty) throws NegativeQuantityException {
    if (qty < 0) {
        throw new NegativeQuantityException("Quantity = " + qty);
    }
    // normal processing
}

public static void main(String[] args) {
    try {
        processOrder(-5);
    } catch (NegativeQuantityException e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    } finally {
        System.out.println("Order processing finished");
    }
}
```

### 8. Best Practices

1. Never do empty catch blocks
   ```java
   catch (Exception e) {}   // EVIL – silent failure
   ```

2. Catch specific exceptions first, then general
   ```java
   catch (FileNotFoundException e) { ... }
   catch (IOException e) { ... }           // broader
   ```

3. Use try-with-resources whenever possible
   ```java
   try (FileReader fr = new FileReader("data.txt")) {
       // auto-closed
   }
   ```

4. Prefer unchecked exceptions for programming errors  
   Checked exceptions only when caller can realistically recover

### 9. One-Page Cheat Sheet (Stick on Your Desk)

| Type                    | Extends               | Compiler Forces Handling? | Typical Use Case                      |
|-------------------------|-----------------------|----------------------------|---------------------------------------|
| Error                   | Throwable             | No                         | OutOfMemory, StackOverflow            |
| Checked Exception       | Exception (not Runtime)| Yes                        | IOException, SQLException             |
| RuntimeException        | Exception             | No                         | NullPointer, IndexOutOfBounds, etc.   |
| Your Custom Checked     | Exception             | Yes                        | Business rule violations             |
| Your Custom Unchecked   | RuntimeException      | No                         | Programming/validation errors         |


## Packages

### 1. What is a Package?
**Layman Explanation**  
A folder system for your Java classes — just like you keep different subjects in different folders on your laptop.

**Technical Explanation**  
- A package is a namespace that organizes classes and interfaces  
- Prevents naming conflicts  
- Provides access control (package-private)  
- Enables code reuse and versioning

**Real-Life Example**  
```java
java.util.ArrayList
java.awt.Button
javax.swing.JFrame
```
All live in different “folders” so they don’t clash.

### 2. How to Create Your Own Package

**Step-by-step (Must remember for lab exams)**

1. **Put this as the very first line of your .java file**
   ```java
   package com.cmi.student;        // your folder structure
   // or
   package iitb.cs101.bank;
   ```

2. **Folder structure MUST match the package name**
   ```
   src/
     └── com/
         └── cmi/
             └── student/
                 └── Student.java    ← package com.cmi.student;
   ```

3. **Compile from parent directory**
   ```bash
   javac com/cmi/student/Student.java
   ```

4. **Run with full class name**
   ```bash
   java com.cmi.student.Student
   ```

### 3. The 4 Access Modifiers + Package Scope (Golden Table)

| Modifier     | Same Class | Same Package | Subclass (different package) | Everywhere Else |
|--------------|------------|--------------|------------------------------|-----------------|
| private      | Yes        | No           | No                           | No              |
| (default/no modifier) → package-private | Yes | Yes          | No                           | No              |
| protected    | Yes        | Yes          | Yes                          | No              |
| public       | Yes        | Yes          | Yes                          | Yes             |

**Most important for exams:**  
If no modifier → only classes in the **exact same package** can access it.

### 4. import Statement – How to Use Other Packages

| Statement                            | Meaning                                                      | Example                              |
|--------------------------------------|--------------------------------------------------------------|--------------------------------------|
| import java.util.ArrayList;          | Import only one class                                        | ArrayList<String> list = new ArrayList<>(); |
| import java.util.*;                  | Import ALL classes in java.util (NOT sub-packages)          | Scanner, List, Set, Map all available |
| import java.awt.*;                   | Good                                                         |                                      |
| import java.*;                       | Invalid – java is a package, not a class                     |                                      |

**Important:** * is NOT recursive  
```java
import java.util.*;       // does NOT import java.util.concurrent
import java.awt.*;        // does NOT import java.awt.event
```

### 5. Static Import (Java 5+) – For Constants & Static Methods

**Normal way**
```java
Math.sqrt(16);
System.out.println(Math.PI);
```

**With static import (cleaner)**
```java
import static java.lang.Math.*;     // or specific ones

// Now you can write:
sqrt(16);
PI
pow(2, 10);
```

**Real exam favorite example**
```java
import static java.lang.System.out;

out.println("Hello");    // instead of System.out.println
```

### 6. Standard Java Package Naming Convention (Reverse Domain)

Correct way (used by everyone):
```java
package com.google.maps;
package org.apache.commons.lang;
package edu.iitb.cs101.assignment;
```

Wrong ways:
```java
package mypackage
package assignments
package week7
```

### 7. Common Built-in Packages You Must Know

| Package                  | What’s Inside                                      |
|--------------------------|----------------------------------------------------|
| java.lang                | Automatically imported (String, Math, System)     |
| java.util               | Collections, Scanner, Date, Random                 |
| java.io                  | File, InputStream, Reader, Writer                  |
| java.nio.file           | Modern file handling (Java 7+)                     |
| java.time               | New Date-Time API (LocalDate, LocalTime)           |
| java.net                | URL, Socket, HttpURLConnection                    |
| java.awt / javax.swing  | GUI (don’t worry much for basic course)            |

### 8. One-Page Golden Summary (Stick on Your Wall)

| Concept                     | Rule / Syntax                                          |
|-----------------------------|--------------------------------------------------------|
| Create package              | package your.name.here; → first line                   |
| Folder structure            | Must match package name exactly                        |
| Default access              | No modifier = package-private                          |
| import single class         | import java.util.ArrayList;                            |
| import whole package        | import java.util.*;                                    |
| Static import               | import static java.lang.Math.*;                        |
| java.lang                   | Auto-imported — no need to write import                |
| * is not recursive          | java.util.* does not include sub-packages              |
| Naming convention           | Reverse domain: com.company.project                    |


## Lecture 4:  Assertions

### 1. What is an Assertion?
**Layman Explanation**  
A gentle way of saying: “I am 100% sure this condition is true. If it’s not, something is badly wrong in my code — please stop and tell me during testing!”

**Technical Definition**  
Assertions are **debug-time checks** that verify assumptions you made while writing the code. They are meant to catch programmer mistakes early, not user errors.

### 2. Assertion vs Exception — The Big Difference (Most Important Table)

| Feature                        | Assertion (assert)                          | Exception (throw)                              |
|--------------------------------|---------------------------------------------|------------------------------------------------|
| Purpose                        | Catch **your own programming bugs**        | Handle **runtime/user/external** errors        |
| Enabled by default?            | NO                                          | Always active                                  |
| Performance impact in production| Zero (disabled)                           | Always there                                   |
| Should be used for             | Internal invariants, preconditions         | File not found, invalid input, network down   |
| Example                        | assert x >= 0;                              | if (x < 0) throw new IllegalArgumentException(); |

**Golden Rule:**  
Use **assert** for things that **should never happen** if your code is correct.  
Use **exceptions** for things that can legitimately go wrong at runtime.

### 3. Syntax of assert (Two Forms)

```java
// Form 1: Simple
assert condition;

// Form 2: With message (very useful in exams)
assert condition : "Detailed error message";
```

**Examples**
```java
public double sqrt(double x) {
    assert x >= 0 : "sqrt called with negative number: " + x;
    // ... actual code
    return Math.sqrt(x);
}

public void removeFirst(List<String> list) {
    assert !list.isEmpty() : "Trying to remove from empty list!";
    list.remove(0);
}

void process(int[] arr, int index) {
    assert arr != null : "Array is null";
    assert index >= 0 && index < arr.length : "Index " + index + " out of bounds";
    // safe to use arr[index]
}
```

### 4. How to Enable/Disable Assertions

| Command                              | Meaning                                      |
|--------------------------------------|----------------------------------------------|
| java MyProgram                       | Assertions disabled (default)                |
| java -ea MyProgram                   | Enable assertions (-ea = enable assertions)  |
| java -enableassertions MyProgram     | Same as above (long form)                    |
| java -da MyProgram                   | Explicitly disable (-da = disable assertions)|
| java -ea:com.cmi... MyProgram        | Enable only for specific package             |

**In real life & companies:**  
Assertions are **enabled during development and testing**, **disabled in production** → zero performance cost.

### 5. Where to Use Assertions (Exam Favorites)

| Correct Use (Good)                                 | Wrong Use (Bad)                                         |
|----------------------------------------------------|----------------------------------------------------------|
| Checking internal invariants                       | Validating user input                                    |
| Pre-conditions in private methods                  | Validating public method parameters (use exception)     |
| Post-conditions after complex logic                | Replacing proper error handling                          |
| Verifying that a null will never happen here       | File I/O, network, database errors                       |

**Correct Example (Private method)**
```java
private void helper(Node node) {
    assert node != null : "helper called with null";  // OK — internal
    // ...
}
```

**Wrong Example**
```java
public void setAge(int age) {
    assert age > 0 : "Age negative";   // WRONG! Use exception instead
}
```

### 6. What Happens When Assertion Fails?
Program terminates immediately with **AssertionError** (which is a subclass of Error, not Exception — so you don’t catch it normally).

**Output**
```
Exception in thread "main" java.lang.AssertionError: sqrt called with negative number: -5
    at MyClass.sqrt(MyClass.java:10)
```

### 7. One-Page Golden Summary (Memorize This!)

| Concept                       | Answer You Write in Exam                                      |
|-------------------------------|----------------------------------------------------------------|
| Purpose of assert             | Catch programmer errors during development                     |
| Enabled by default?           | No                                                             |
| How to enable                 | java -ea or java -enableassertions                             |
| Can you catch AssertionError? | Technically yes, but never do — it means your code is broken  |
| Best for                      | Private method assumptions, loop invariants, never-null checks|
| Never use for                 | Public API validation, user input, I/O errors                 |
| Throws                        | AssertionError (extends Error, not Exception)                  |


## Lecture 5: Logging

### 1. Why We Need Proper Logging (Instead of System.out.println)

| System.out.println                          | Proper Logging (java.util.logging)                              |
|---------------------------------------------|-----------------------------------------------------------------|
| Always prints (no control)                  | Can turn on/off without changing code                           |
| No levels (everything mixed)                | Has 7 standard levels (SEVERE, WARNING, INFO, FINE, etc.)      |
| Goes only to console                        | Can go to file, network, email, database, etc.                  |
| No timestamp/class name/line number         | Automatically adds all details                                  |
| Hard to filter in production                | Controlled by external config file or command-line flags       |

### 2. The 7 Standard Logging Levels (Memorize the Order!)

| Level      | Meaning                                    | Typical Use                                      |
|------------|--------------------------------------------|--------------------------------------------------|
| SEVERE     | Critical error → app may stop              | Database down, OutOfMemory                       |
| WARNING    | Serious but recoverable                    | File not found, deprecated API used              |
| INFO       | Important milestones                       | Server started, user logged in                   |
| CONFIG     | Configuration details                      | Loaded config file X                             |
| FINE       | General debugging                          | Method entered, loop iteration                   |
| FINER      | More detailed tracing                      | SQL query executed, parameters                   |
| FINEST     | Very verbose (everything)                  | Every tiny step                                  |

Plus two special levels:  
OFF → no logging at all  
ALL → enable every single message

### 3. Core Classes You Must Know

| Class           | What it is                                          |
|-----------------|-----------------------------------------------------|
| Logger          | The actual logger object you use in code            |
| Level           | The 7+ levels above                                 |
| Handler         | Where logs go (ConsoleHandler, FileHandler, etc.)   |
| Formatter       | How logs look (SimpleFormatter, XMLFormatter)       |

### 4. Basic Usage – 4 Lines That Do Everything

```java
import java.util.logging.Logger;
import java.util.logging.Level;

public class BankAccount {
    // Step 1: Get a logger (name usually = class name)
    private static final Logger log = Logger.getLogger(BankAccount.class.getName());

    public void withdraw(double amount) {
        log.entering("BankAccount", "withdraw", amount);   // method entry

        if (amount <= 0) {
            log.log(Level.WARNING, "Attempt to withdraw non-positive: {0}", amount);
            return;
        }

        log.fine("Withdrawal successful");   // shortcut methods
        log.info("New balance: " + balance);
    }
}
```

### 5. Most Common Logging Methods (You Will Use These Daily)

```java
log.severe("message");
log.warning("message");
log.info("message");
log.config("message");
log.fine("message");
log.finer("message");
log.finest("message");

// General form (best for parameters)
log.log(Level.INFO, "User {0} logged in from IP {1}", new Object[]{name, ip});
```

### 6. Controlling Logging – Two Ways (Exam Question!)

**Way 1: From Command Line (quick testing)**
```bash
java -Djava.util.logging.config.file=myapp.properties MyApp
# OR simply change level for all loggers
java MyApp                    → default = INFO
java -Djava.util.logging.level=FINE MyApp
```

**Way 2: Configuration File (real projects) – logging.properties**
```properties
# Set root level
.level = INFO

# Your package more verbose
com.cmi.bank.level = FINE

# Send logs to file
handlers = java.util.logging.FileHandler, java.util.logging.ConsoleHandler

# File handler settings
java.util.logging.FileHandler.pattern = app.log
java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
```

### 7. Quick One-Page Cheat Sheet (Stick on Your Desk)

| Need                                 | Code You Write                                      |
|--------------------------------------|-----------------------------------------------------|
| Get logger                           | Logger log = Logger.getLogger(MyClass.class.getName()); |
| Normal info message                  | log.info("Server started on port 8080");            |
| Warning                              | log.warning("Low disk space");                      |
| Debug (only in dev)                  | log.fine("Processing user " + userId);              |
| With parameters (avoid string concat)| log.log(Level.FINE, "User {0} age {1}", new Object[]{name, age}); |
| Method entry/exit                    | log.entering("MyClass", "myMethod", params);<br>log.exiting("MyClass", "myMethod", result); |
| Turn on detailed logging             | java -Djava.util.logging.level=FINE MyApp           |

### 8. Best Practices

1. Never do string concatenation in log calls when level is disabled  
   Wrong: `log.fine("User: " + user.toString());` → toString() runs anyway  
   Correct: `log.log(Level.FINE, "User: {0}", user);`

2. Always use class-based logger name  
   `Logger.getLogger(MyClass.class.getName())`

3. Never use System.out.println in production code