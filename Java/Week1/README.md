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
  ```py
  def sumlist(l):
      if l==[]:
          return(0)
      else:
          return l[0] + sumlist(l[1:])
  ```

### 
* Internally, everything is stored a sequence of bits.
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



