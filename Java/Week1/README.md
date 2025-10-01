# Week 1

## Programming Languages
- A lang. is a medium of communication.
- A programming lang. is a medium of communication to instruct computers.

- Originally (in low level programming), the direct architecture was:
    - Memory locations store values, registers allow arithmetics.
    - Load a value from memory location `M` to register `R`.
    - Add the contents of register `R1` and `R2` and store the result back in `R1`.
    - Write the value in `R1` to memory location `M'`.

> This is very tedious and error-prone process.

### Abstraction 
* Abstractions used in `computational thinking`
  - Assigning values to named variables
  - Conditional execution
  - Iteration
  - Functions/ procedures, recursion
  - Aggregate data structures - arrays, lists , dictionaries.
* Expressing these ideas in programming language produces what we call `compilers` or `interpreters`.

### Styles of programming
* Imperative
  * How to computer
  * Step by step instructions on what is to be done
* Declarative
  * What the computatuin should produce
  * Often exploit inductive structure, express in terms of smaller computations
  * Typically avoid using intermediate variables
  * Combination of small transformations - `functional programming`

* _**eg:#01** Imperative vs Declarative_:
  ```py
  def sumlist(l):
      mysum=0
      for x in l:
          mysum=mysum+x
      return mysum
  ```
  * Intermediate values `mysum`, `x`
  * Explicit iteration to examine each element in the list
  ```py
  def sumlist(l):
      if l==[]:
          return(0)
      else:
          return l[0] + sumlist(l[1:])
  ```
  * Describe the desired output by induction
    * `Base case` → Empty list has sum
    * `Inductive step` → Add the first element to the sum of the rest of the list
  * No intermediate variables

### Names, types, values
* Internally, everything is stored a `sequence of bits`.
* No difference between data and instructions, let alone numbers, characters, booleans.
  * For a compiler or interpreter, our `code` is its `data`.
* We impose a notion of `type` to create some discipline.
  * Interpret bit strings as "_high level_" concepts.
  * Nature and range of allowed values.
  * Operations that are permitted on these values.
* Strict type-checking helps catch bugs early.
  * Incorrect expression evaluation - like dimension mismatch in science.
  * Incorrect assignment - expression value does not match variable type.

### Abstract datatypes, object-oriented programming
* Collections are important:
  * Arrays, lists, dictionaries.
* Abstract datatypes:
  * Structured collection with fixed interface.
  * Stack is a sequence, but only allows `push` and `pop`.
  * Seperate implementation from interface.
    * Priority queue allows `insert` and `delete-max`.
    * Can implement a priority queue using sorted or unsorted lists, or using a heap.
* Object-oriented programming:
  * Focus on datatypes.
  * Functions are invoked through the object rather than passing data to the functions.
  * In Python, `myList.sort()` vs `sorted(myList)`

### What is yet to come ...
- Explore concepts in programming languages
   - Object-oriented programming
   - Exception handling, concurrency, event-driven programming
- Using `Java` as the illustrative language
  - `Imperative`, `object-oriented` by design.
  - Incorporates almost all the features
- Discuss design decisions where relavant
  - Every language makes some compromises
- Understand and appreciate why there is a zoo of programming languages out there
- ...And why new ones are still being created

## Lecture 2 Types

### The role of types

- Interpreting data stored in binary in a consistent manner
    - View sequence of bits as integers, floats, characters, ...
    - Nature and range of allowed values
    - Operations that are permitted on these values
- Naming concepts and structuring our computation
    - Especially at a higher level
    - `Point` vs `(Float, Float)`
    - Banking applications → accounts of different types, customers, ...
- Catching bugs early
    - Incorrect expression evaluation
    - Incorrect assignment

### Dynamic vs Static Typing

- Every variable we use has a `type`.
- How is the type of a variable determined?
  - In Python, it determines the type based on the current value assigned to it:
    - ***Dynamic typing →*** derive type from the current value
      - `x = 10` — `x` is of type `int`
      - `x = 7.5` — now `x` is of type `float`
      - An uninitialized name has no type
    - ***Static typing →*** associate a type in advance with a name
      - Need to declare names and their types in advance
      - `int x`, `float a`, ...
      - Cannot assign an incompatible value — `x = 7.5` is illegal
    - It is difficult to catch errors, such as typos
        ```py
        def factors(n):
	        factorlist = []
	        for i in range(1, n + 1):
		        if n % i == 0:
			        factorlst = factorlist + [i]  # Typo here! will show no error
	        return factorlist
        ```
    - Empty user defined objects
        - Linked list is sequence of objects of type `Node`
        - Convenient to represent empty linked list by `None`
        - Without declaring type of `l`, Python cannot associate type after `l = None`

### Types of organizing concepts

- Even simple type “synonyms” can help clarify code
    - 2D point is a pair `(float, float)`, 3D point is triple `(float, float, float)`
    - Create new type names `point2d`and `point3d`
    - These are synonyms for `(float, float)` and `(float, float, float)`
    - Makes the intent more transparent when writing, reading and maintaining code
- More elaborate types — abstract datatypes and object-oriented programming
    - Consider a banking application
    - Data and operations related to accounts, customers, deposits, withdrawals, transfers
    - Denote accounts and customers as separate types
    - Deposits, withdrawals, transfers can be applied to accounts, not to customers
    - Updating personal details applies to customers, not accounts

### Static analysis

- Identify errors as early as possible — saves cost & effort
- In general, compilers cannot check that a program will work correctly
    - Halting problem — Alan Turing
- With variable declarations, compilers can detect type errors at compile time - ***static analysis***
    - Dynamic typing would catch these errors only when the code runs
    - Executing code also shows down due to simultaneous monitoring for type correctness
- Compilers can also perform optimizations based on static analysis
    - Re-order statements to optimize reads and writes
    - Store previously computed expressions to re-use later
 


## Lecture 3

## Keeping Track of Variables

### Variables store intermediate values during computation

* `Local Variables`: Typically local to a function.
* `Global Variables`: Can also refer to global variables outside the function.
* `Dynamic Data`: Includes dynamically created data like nodes in a list.

### Scope of a Variable

* **Definition**: Determines when the variable is available for use.
* _**eg:#02**_

  ```python
  def f(l):
      ...
      for x in l:
          y = y + g(x)
      ...

  def g(m):
      ...
      for x in range(m):
          ...
  ```
* The variable `x` in `f()` is **not in scope** within the call to `g()`.
* Each function maintains its own variable scope.

### Lifetime of a Variable
* **Definition**: Duration for which the storage for the variable remains allocated.
* In the above example, the **lifetime of `x` in `f()` ends when `f()` exits**.
* **Hole in Scope**: A situation where a variable is alive (memory allocated) but **not accessible** in the current scope.

### Memory Stack

### Storage for Local Variables

* Each function requires storage for its local variables.
* **Activation record** is created whenever a function is called.

### Activation Records

* **Stacked on Function Calls**:
  * New records are added to the stack when a function is called.
  * **Popped** when the function exits.
* **Control Link**: Points to the start of the previous activation record.
* **Return Value Link**: Indicates where the result of the function should be stored.

### Scope of a Variable

* **Local Variables**: Exist in the activation record at the top of the stack.
* **Global Variables**: Accessed by following control links.

### Lifetime of a Variable
* Storage remains allocated **while it is on the stack**, even if the function exits.

#### Example: Factorial

* **Call**: `factorial(3)`
* **Recursive Call**: `factorial(3)` calls `factorial(2)`
* Stack holds:

  * Value of `n`
  * Recursive calls (`factorial(n-1)`)
  * Control and return value links

### Passing Arguments to functions
When a function is called, the values passed to it (the `arguments`) are used to initialize the variables defined in the function's signature (the `formal parameters`).

* This process is essentially an **implicit assignment**. For example, in a function call `f(x, myl)`, where the function is defined as `def f(a,l):`, the parameters `a` and `l` are implicitly assigned the values of `x` and `myl` as if you had written `a = x` and `l = myl` at the beginning of the function.
* The parameters are stored as part of the function's **activation record**, a data structure created when a function is called. This record contains all the information needed to manage the function's execution, including local variables, parameters, and the return address.

### **2. Two Methods for Initializing Parameters**

There are two primary ways to pass arguments to a function, which determine how the parameters are initialized and how changes within the function affect the original arguments.
1. `call by value`
2. `call by reference`
 
#### Call by Value
This method copies the **value** of the argument into the formal parameter.

* A completely new variable is created for the parameter within the function's scope.
* Changes made to this parameter inside the function **do not affect** the original argument outside the function. This is because the function is working on a separate, independent copy of the value.
* This approach is safer, as it prevents a function from accidentally modifying the original data.

#### Call by Reference
This method passes the **memory address** of the argument to the formal parameter.

* The parameter doesn't get a new copy of the value; instead, it points to the same memory location as the original argument.
* Changes made to the parameter **can have side effects** on the original argument. Since both the parameter and the argument point to the same location in memory, any modification to the data at that location will be reflected in the original variable.
* It's crucial to be careful with this method. You **can update the contents** of the data at the shared memory location, but you **cannot change the reference itself** to point to a different location. If you try to reassign the parameter, you will not affect what the original argument points to.


### Heap area and Stack area in Memory
- Used for **dynamic memory allocation**, outlives function execution.
- Needed when storing data beyond a function's scope (e.g., linked list nodes).
- Heap vs Stack:
   - `Heap` → Persistent storage, manually managed.
   - `Stack` → Temporary storage for function calls.
- Not the same as the heap data structure (priority queues).


### Managing Heap Storage
- On the stack, variables are deallocated when the function exits
- How do we `return` unused storage on the heap?
  - After deleting a node in a linked list, deleted node is now a dead storage, unreachable
- **Manual memory management:**
  - Programmers expilicitly requests and returns heap storage
    - `p=malloc(...)` and `free(p)` in C
  - Error-prone -- memory leaks, invalid assignments

**_Automatic Garbage Collection:_(Java, Python, ...)**
- Run-time environment cheks and cleans up dead storage -- e.g., _mark-and-sweep_.
  - Mark all storage that is reachable form program variables
  - Return all unmarked memory cells to free space
- Convenience for programmer vs performance penalty

## Lecture 4

Writing software is a complex process, we have to typically build it at large scale, hence we build them in parts i.e., `modular fashion`.

### Modular sfotware development
- We use refinement to divide the solution into `components`
- We then build a `prototype` of each component to validate design.
- Components are described in terms of
  - `Interfaces`-(_Structural Requirements_) what is visible to other components, typically function calls.
  - `Speccification`-(_Behavioural Requirements_) behaviour of the component, as visible through interface.

- Simplest example of a component: **a function**
	- _Interfaces_ -> function header, arguments and return type
	- _Specification_ -> intended input-output behaviour

### Abstraction and OOP

Abstraction is a way of hiding unnecessary details so you can focus on the bigger picture. A simple overview of how programming languages use abstraction to manage complexity is written below: 

#### 1. Control Abstraction

This is about managing the flow of a program.
* **Functions and Procedures:** Group a set of instructions into a single, reusable unit.
* **Encapsulation:** "Wrap up" a block of code so you can reuse it in different parts of your program without rewriting it.

#### 2. Data Abstraction

This is about simplifying how we handle data.
* **Abstract Data Types (ADTs):** Define data based on what it is and what we can do with it, not how it's built.
* **Key Principles:**
    * An ADT is a set of values with specific operations.
    * The internal details (how it's stored) are hidden.
    * We can only interact with it through a public interface (the allowed operations).
* **Example:** A **stack** is an ADT. We can `push` (add) and `pop` (remove) items. We don't need to know if it's implemented using a list or another data structure.

#### 3. Object-Oriented Programming (OOP)

OOP combines control and data abstraction into a powerful model.
* **Organize ADTs in a Hierarchy:** Create a family of related data types (e.g., a `Vehicle` class with a `Car` and `Motorcycle` as subclasses).
* **Implicit Reuse:** Through **subtyping** and **inheritance**, a new object type automatically gets the characteristics and operations of its parent type. This means less code to write and better organization.


## Lecture 5 Object Oriented Programming

### **_OBJECT:_**
We can think of an **Object** as a smart container. It's a fundamental unit that perfectly wraps up two things:
1. **data** (information) and
2. the **operations** (actions/functions) that work on that data.
This is similar to a concept called an **Abstract Datatype (ADT)**.

| Feature | Description | Analogy |
| ---- | ---- | ---- |
| **Data** | The information the object holds. This is kept **hidden** (private) inside. | The internal mechanics of a car (engine, fuel tank) . |
| **Operations** | The public actions you can use to interact with the object's data. These are called **methods**, **messages**, or **member-functions**. | The public controls of a car (steering wheel, pedals, door handle) . |

* **Abstraction:** This is the principle of hiding the complex internal details and showing only the necessary public interface. If the object's internal implementation changes (e.g., swapping a diesel engine for an electric one), we don't need to change how we interact with it (we still use the steering wheel and pedals).
* **Encapsulation:** Objects provide a uniform way of wrapping (encapsulating) different combinations of data and functionality. An object can be as simple as a counter (holding a single integer) or as complex as an entire database or filesystem.
* **Data-Centric View:** OOP encourages a focus on what data is needed and how to maintain and manipulate it, making it easier to coordinate changes to both the data structure and the methods that operate on it.

### Distinguishing Features of OOP
The following concepts are what truly define an object-oriented language:
  - Abstrsaction
  - Subtyping
  - Dynamic lookup
  - Inheritance

### Subtyping (Interface Compatibility)

Subtyping arranges object types in a **hierarchy**.
* **Specialization:** A **subtype** is a specialization of a more general type.
* **The Rule:** If Type **A** is a subtype of Type **B**, it means that any object of Type A can be used in any place where an object of Type B is required. Every object of Type A is considered an object of Type B.
* **The Interface:** Subtyping is fundamentally a relationship about **interfaces**—the set of public methods an object supports. If a method $f()$ exists in Type B, then any subtype A must also support $f()$.
* **Analogy (Subset):** Think of it like a mathematical subset. If set $X$ is a subset of set $Y$ ($X \subseteq Y$), then every element in $X$ is also in $Y$.

Subtyping allows a well-typed list (like the Simula event queue) to hold different types of objects, as long as they all share a common parent type and are compatible.

### Dynamic Lookup (Run-time Method Choice)

Dynamic lookup is how the program chooses the correct method to execute when that method's name appears multiple times across different types.
* **Static vs. Dynamic:**
    * **Static Property:** Whether a method *can be called* on an object is checked by the compiler (type-checking).
    * **Dynamic Property:** *How* the method actually behaves (its implementation) is determined by the object's **actual type** at run-time.
* **Mechanism:** A variable $v$ might be declared with a general type, say **B**, but at run-time, it holds an object of a subtype, say **A**. When you call a method on $v$, the system ignores the variable's static type **B** and uses the object's run-time type **A** to find the correct implementation.
* **Example (Simulation):** In the Simula event queue, all events support a `simulate` method. When the program executes `e.simulate()`, the *action* that is triggered depends entirely on the specific type of event $e$ is at that moment (e.g., a "CarArrival" event or a "ServiceComplete" event). The object "knows" how to perform the operation itself.
* **Not Overloading:** This is different from method overloading (like the `+` operation), where the choice of implementation is determined by the static type of the arguments.

### Inheritance (Implementation Reuse)

Inheritance is primarily focused on **reusing implementations** 
* **The Goal:** It allows a new object type to start with all the data fields and methods of an existing type, saving development time.
* **Example (Employees):** You can define an **Employee** object with basic personal data. A **Manager** object can then **inherit** from Employee, retaining all the basic data and functionality, and simply **adding** new fields (like date of promotion) and new functions.
* **Key Distinction:** While many languages combine them (A can inherit from B *if* A is a subtype of B), they are conceptually different:
    * **Subtyping** is about **interfaces** (compatibility).
    * **Inheritance** is about **implementations** (code reuse).


### Subtyping vs. Inheritance Example (Deque)

This example clarifies the difference between the two concepts:

1. A **Deque** (Double-Ended Queue) supports four operations: `insert-front()`, `delete-front()`, `insert-rear()`, and `delete-rear()`.
2. We can build a **Stack** or a **Queue** by using the Deque's internal implementation:
    * **Stack:** Uses only `insert-front()` and `delete-front()` (LIFO).
    * **Queue:** Uses only `insert-rear()` and `delete-front()` (FIFO).
3. **Inheritance:** Stack and Queue can **inherit** from Deque to **reuse the underlying implementation** (the code for storing the data).
4. **Subtyping:** Stack and Queue are **not subtypes** of Deque.
    * Why? Because a Stack object does *not* support the `insert-rear()` operation. If you treated a Stack as a Deque, you could break the program by calling a method the Stack doesn't have.
    * In this specific case, the relationship is inverted: a **Deque is actually a subtype of both Stack and Queue** because it supports *all* of their operations and more.


