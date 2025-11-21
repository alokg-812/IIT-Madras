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

## Lecture 3: Higher Order Functions

## Lecture 4: Streams
