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

### Tracks of variables
- Local Variables
- Global Variables
- Dynamic created(Node in a list)


