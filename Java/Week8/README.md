# Week 8

## Lecture 1: Cloning

### 1. The Problem: Normal Assignment ≠ Copy

**Layman Explanation**  
When we do `e2 = e1`, we are not copying the employee — we are just giving a second remote control to the same person. If one remote changes the name, both see the change.

**Code Proof**
```java
Employee e1 = new Employee("Dhruv", 21500);
Employee e2 = e1;           // Only reference copy
e2.setName("Eknath");
System.out.println(e1.getName());   // Prints "Eknath" → Oops!
```

We want a real photocopy → two independent employees with same initial data.

### 2. Two Ways to Make a Real Copy in Java

| Method                    | How it works                                   | Pros                              | Cons / Warnings                          |
|---------------------------|------------------------------------------------|-----------------------------------|------------------------------------------|
| **Copy Constructor**      | Write a constructor that takes same-type object| Simple, clear, safe               | You have to write it manually            |
| **clone() method**        | Java’s built-in mechanism (Object.clone())    | Standard, works with collections  | Tricky, easy to get wrong → shallow copy |

### 3. Way 1: Copy Constructor (Recommended & Safest)

```java
public class Employee {
    private String name;
    private double salary;
    private Date joiningDate;        // reference type!

    // Normal constructor
    public Employee(String n, double s, Date d) {
        this.name = n;
        this.salary = s;
        this.joiningDate = d;
    }

    // COPY CONSTRUCTOR ← Best way
    public Employee(Employee other) {
        this.name = other.name;
        this.salary = other.salary;
        this.joiningDate = new Date(other.joiningDate.getTime()); // deep copy!
    }
}

// Usage
Employee e1 = new Employee("Dhruv", 21500, new Date());
Employee e2 = new Employee(e1);     // Real independent copy
```

### 4. Way 2: clone() + Cloneable (Java’s Official Way – But Tricky)

**Step-by-step (must remember exactly)**

1. Implement **Cloneable** interface (it’s a marker interface – empty)
2. Override **clone()** and make it **public**
3. Call **super.clone()** first
4. Handle **CloneNotSupportedException**

**Shallow Clone (Default – Dangerous!)**
```java
public class Employee implements Cloneable {
    private String name;
    private Date joiningDate;

    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();   // shallow copy!
    }
}
```
Problem: Both objects share the same `joiningDate` object → changing one affects the other!

**Correct Deep Clone**
```java
@Override
public Employee clone() throws CloneNotSupportedException {
    Employee cloned = (Employee) super.clone();           // shallow copy first
    cloned.joiningDate = new Date(this.joiningDate.getTime()); // deep copy reference fields
    return cloned;
}
```

### 5. Shallow Copy vs Deep Copy (Golden Table – Draw in Exam!)

| Type          | What gets copied                          | Reference fields (like Date, List) | Safe?          |
|---------------|-------------------------------------------|------------------------------------|----------------|
| Shallow Copy  | Only primitive fields + references        | Shared (same object)               | Usually NO     |
| Deep Copy     | Everything including nested objects       | New independent objects            | YES            |

**Example of shallow copy bug**
```java
Employee e1 = new Employee("A", new Date());
Employee e2 = e1.clone();           // shallow
e2.getJoiningDate().setYear(2020);  // changes e1 too! → BUG
```

### 6. Rules for Safe Cloning (Write These in Exam)

1. If our class has only primitive fields → simple `super.clone()` is enough  
2. If our class has reference fields → you must clone them manually (deep copy)  
3. Always make `clone()` public  
4. Always throw or declare `CloneNotSupportedException`  
5. Prefer copy constructor in new code (many experts say clone() is broken by design)

### 7. One-Page Decision Table (Stick on Your Wall)

| Situation                                    | Recommended Way                   |
|----------------------------------------------|-----------------------------------|
| Simple class (only int, double, String)      | clone() with super.clone()        |
| Class contains Date, List, Array, objects    | Deep clone() OR copy constructor  |
| You want maximum clarity and safety          | Copy constructor (modern choice)  |
| Working with old Java collections (pre-2010)| clone() (they expect Cloneable)   |

### 8. Final Golden Summary (Memorize This!)

| Concept                  | Code Snippet / Rule                                           |
|--------------------------|---------------------------------------------------------------|
| Normal assignment        | `e2 = e1` → same object                                       |
| Real copy needed         | Use copy constructor OR proper deep clone()                   |
| Cloneable                | Marker interface – must implement                             |
| clone() visibility       | Must be public                                                |
| super.clone()            | Always call first – gives you a shallow copy                 |
| Shallow vs Deep          | Shallow = references shared; Deep = everything independent   |
| Best modern practice     | Prefer copy constructor over clone()                          |

## Lecture 2: Type Inference
### 1. What is Type Inference?

**Layman Explanation**  
The compiler is smart enough to figure out the type by itself, so you don’t have to write it again and again.

**Technical Definition**  
The Java compiler uses context (right-hand side, generic method calls, lambda parameters, etc.) to automatically infer the correct type instead of forcing you to write it explicitly.

### 2. Evolution of Type Inference in Java (Timeline – Must Know for Exams)

| Java Version | Feature Introduced                 | Before                                 | After (with inference)                             |
|--------------|------------------------------------|----------------------------------------|----------------------------------------------------|
| Java 5       | Generics                           | `List<String> list = new ArrayList<String>();` | Still had to write `<String>` twice                |
| Java 7       | Diamond Operator `<>`              | `List<String> list = new ArrayList<String>();` | `List<String> list = new ArrayList<>();`           |
| Java 8       | Lambda + Target Typing             | Very verbose anonymous classes         | `Runnable r = () -> System.out.println("Hi");`     |
| Java 10      | `var` (local variable type inference) | `ArrayList<String> list = new ArrayList<>();` | `var list = new ArrayList<String>();`              |
| Java 11+     | `var` in lambda parameters (preview, then permanent) | `(String a, String b) -> a + b`       | `var a, var b) -> a + b` is wrong → `(var a, var b) -> a + b` |

### 3. The Diamond Operator `<>` (Java 7+)

**Before Java 7**
```java
Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
```

**With Diamond (clean & safe)**
```java
Map<String, List<Integer>> map = new HashMap<>();
// Compiler infers: HashMap<String, List<Integer>>
```

Works with all generic classes (ArrayList, HashSet, custom classes, etc.)

### 4. `var` – Local Variable Type Inference (Java 10+)

**Layman Rule**  
You can write `var` instead of the type when declaring local variables — but only if the compiler can clearly see the type from the right-hand side.

**Correct Usage**
```java
var name = "Rohan";                  // infers String
var list = new ArrayList<String>();  // infers ArrayList<String>
var map = Map.of("A", 1, "B", 2);    // infers Map<String,Integer>
var stream = list.stream();          // infers Stream<String>
```

**Wrong / Not Allowed**
```java
var x;                     // ERROR – no initializer
var arr = null;            // ERROR – cannot infer
var[] nums = {1,2,3};      // ERROR – not allowed
public var field = 10;     // ERROR – only local variables
var method() { ... }       // ERROR – not for method return types
```

### 5. `var` in Lambda Parameters (Java 11+)

```java
// Old way
BiFunction<String, String, Integer> f = (String a, String b) -> a.length() + b.length();

// With var (cleaner)
BiFunction<String, String, Integer> f = (var a, var b) -> a.length() + b.length();
```

we can even add annotations:
```java
(var x, @NonNull var y) -> x + y
```

### 6. Golden Rules & Limitations of `var`

| Allowed                                       | Not Allowed                                              |
|-----------------------------------------------|----------------------------------------------------------|
| Local variables with initializer              | Method parameters, return types, fields                 |
| Enhanced for-loop: `for (var s : strings)`    | `var` without initializer                               |
| try-with-resources: `try (var in = new FileInputStream(...))` | `var` in array: `var[] arr = new int[5];`           |
| Lambda parameters (with parentheses)          | `var x = null;`                                          |

### 7. One-Page Cheat Sheet

| Feature                | Syntax Example                                    | Java Version | What the compiler infers                     |
|------------------------|---------------------------------------------------|--------------|----------------------------------------------|
| Diamond Operator       | `List<String> list = new ArrayList<>();`          | 7+           | Full generic type from left side             |
| var (local)            | `var count = 10;`                                 | 10+          | int                                          |
| var with generics      | `var map = new HashMap<String, Integer>();`       | 10+          | HashMap<String,Integer>                      |
| var in loops           | `for (var i = 0; i < 10; i++)`                    | 10+          | int                                          |
| var in lambda          | `(var x, var y) -> x + y`                         | 11+          | Type from target functional interface       |

### 8. Final Summary (One-Liner for Exams)

“Java gradually added type inference to reduce boilerplate:  
Diamond `<>` (Java 7) → lambdas (Java 8) → `var` for local variables (Java 10) → `var` in lambdas (Java 11).  
It makes code cleaner without losing type safety.”

## Lecture 3: Higher Order Functions
### 1. What is a Higher-Order Function?

**Layman Explanation**  
A function that does at least one of these two things:  
1. Accepts another function as parameter  
2. Returns a function  

Just like you can pass a String or int to a method, higher-order functions let you pass behavior (code) itself.

**Old Java way (before Java 8)** → Use interfaces  
**New Java 8+ way** → Use lambda expressions & functional interfaces

### 2. The Old Painful Way (Pre-Java 8) – Anonymous Inner Classes

```java
button.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        System.out.println("Button clicked!");
    }
});

list.sort(new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});
```

→ Extremely verbose for one-line logic!

### 3. The Magic of Java 8: Lambda Expressions

**Same examples with lambdas (100% standard now)**

```java
// 1. Passing behavior to a button
button.setOnClickListener(v -> System.out.println("Clicked!"));

// 2. Sorting with custom logic
list.sort((a, b) -> a.length() - b.length());

// 3. Timer callback (your lecture example)
timer.setCallback(() -> System.out.println("Time's up!"));
```

### 4. Lambda Syntax – Memorize This Table

| Number of params | Syntax                          | Example                                      |
|------------------|---------------------------------|----------------------------------------------|
| 0 params         | `() -> expression`              | `() -> Math.random()`                        |
| 1 param          | `x -> expression`               | `s -> s.length()`                            |
| 1 param (with type) | `(String s) -> s.length()`   | `(int x) -> x * x`                           |
| 2+ params        | `(x, y) -> expression`          | `(a, b) -> a + b`                            |
| Multi-statement  | `(x, y) -> { stmt1; stmt2; return value; }` | `(x, y) -> { log.info("Adding"); return x+y; }` |

### 5. Functional Interfaces – The Key to Lambdas

**Definition**  
An interface with exactly ONE abstract method → can be used with lambda.

**Built-in Functional Interfaces You Must Know (java.util.function)**

| Interface          | Abstract Method                  | Common Use Case                          | Example Lambda                          |
|-------------------|----------------------------------|------------------------------------------|-----------------------------------------|
| Predicate<T>       | boolean test(T t)                | Filter collection                        | `s -> s.startsWith("A")`                |
| Function<T,R>      | R apply(T t)                     | Transform/map                            | `s -> s.toUpperCase()`                  |
| Consumer<T>        | void accept(T t)                 | ForEach, logging                         | `s -> System.out.println(s)`            |
| Supplier<T>        | T get()                          | Factory, lazy generation                 | `() -> new ArrayList<>()`               |
| BiFunction<T,U,R>  | R apply(T t, U u)                | Reduce, combine two values               | `(a,b) -> a + b`                        |
| Comparator<T>      | int compare(T o1, T o2)          | Sorting                                  | `(a,b) -> a.length() - b.length()`      |
| Runnable           | void run()                       | Threads, timers                          | `() -> System.out.println("Hi")`        |

### 6. Real Examples (Write These in Exams)

```java
// 1. Filter only long names
List<String> longNames = names.stream()
                              .filter(s -> s.length() > 5)
                              .collect(Collectors.toList());

// 2. Transform to uppercase
List<String> upper = names.stream()
                          .map(String::toUpperCase)   // method reference!
                          .collect(Collectors.toList());

// 3. Print each
names.forEach(System.out::println);   // Consumer

// 4. Timer callback (your lecture example)
timer.setTimeout(() -> showGameOver(), 30000);
```

### 7. Method References – Even Cleaner Than Lambdas (Java 8)

| Type                        | Syntax                     | Equivalent Lambda                      |
|-----------------------------|----------------------------|----------------------------------------|
| Static method               | `Class::staticMethod`      | `x -> Class.staticMethod(x)`           |
| Instance method (specific)  | `obj::instanceMethod`      | `x -> obj.instanceMethod(x)`           |
| Instance method (generic)   | `Class::instanceMethod`    | `(obj,x) -> obj.instanceMethod(x)`     |
| Constructor                 | `Class::new`               | `() -> new Class()`                    |

**Examples**
```java
list.forEach(System.out::println);           // Consumer
list.sort(String::compareToIgnoreCase);      // Comparator
list.stream().map(String::toUpperCase);
Supplier<ArrayList<String>> supplier = ArrayList::new;
```

### 8. One-Page Golden Summary

| Concept                    | Old Way (Pre-8)               | Modern Way (Java 8+)                     |
|----------------------------|-------------------------------|------------------------------------------|
| Pass behavior              | Anonymous inner class         | Lambda `x -> ...`                        |
| One-method interface       | Write full interface          | Use @FunctionalInterface + lambda        |
| Print list                 | for-loop                      | `list.forEach(System.out::println)`      |
| Sort with custom logic     | Comparator anonymous class   | `list.sort((a,b) -> ...)`                |
| Timer/callback             | Interface + inner class       | `timer.setCallback(() -> doSomething())` |

### Final Summary

“Higher-order functions = passing behavior as data.  
Java 8 introduced lambda expressions and functional interfaces (Predicate, Function, Consumer, Supplier) to make it clean and concise.  
Method references (::) are syntactic sugar for common lambdas.”


We’ve completed the three core topics of Week 8:
1. Cloning
2. Type Inference (var, diamond)
3. Higher-Order Functions & Lambdas

## Lecture 4: Streams
