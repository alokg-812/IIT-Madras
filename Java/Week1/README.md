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




