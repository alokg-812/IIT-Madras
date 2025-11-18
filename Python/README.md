# WEEK 1:
## Why Programming???
*Ans:* Language needed to be learnt to communicate with computer.


## Why Python only???
*Ans:* Beacause python is very widely spoken, easy to understand and very powerful language.

### Ex-
```
print("Hello")
```
**Output:** 
```
Hello
```
### Ex-
```
print("*")
print("**")
print("***")
print("****")
print("*****")
```
**Output:** 
```
*
**
***
****
*****
```

#### Is it possible to print multiple statements in a same print statement???
**Ans:**
100% __Yes__ we can do that as the following way:
```
print("Hello India", "Hello Madrasians")
```
**Output:**
```
Hello India, Hello Madrasians
```
>[!NOTE]
>* We can use multiple statements while printing them.
>* Only **()** are allowed to print statements.
>* We can print anything we want to inside the "" thing.
>* If want to print any type of number, be it whole number or any decimal number, they are not written inside these " ".

## Variables:
Variables are containers on RAM used to store data values.
Ex:
```
x = 10
y = "Hello India"
```
**Explanation:**
- x is storing the integer value 10.
- y is storing the string value "Hello India".
Ex:
```
age = 10
Age = 12
name = "Alok"
print(age)
print(Age)
print(name)
```
**Ouput:**
```
10
12
Alok
```
**Explanation:**
- age, Age & name are storing the values
  
>[!TIP]
>* Variables should have _meaningful_ names.
>* Python is case-sensitive, so Age and age are different variables.

## Input Taking:
_Ans:_ We can take input from the user using the **input()** function.

Ex:
```
name = input("Enter your name: ")
print("Hello", name)
```
**Output:**
```
Enter your name: Alok
Hello Alok
```
>[!Note]
>If taking input, the input's default type would be **string.**

```
print("Hello, type your name here:")
n=input()
print("Which place are you from?")
p = input()
print("Hello!", n, "How is the weather in", p,"?")
print("What is your age?")
age = int(input())
print("Good to know you are", age, " years old")
```
**Output:**
```
Hello, type your name here:
Alok
Which place are you from?
Bengaluru
Hello! Alok How is the weather in Bengaluru ?
What is your age?
19
Good to know you are 19  years old
```

### Variables v/s Literals:
**Literals** are the constants that appear directly in the program and can be assigned to the variables.
```
print("Enter the radius of the circle:")
rad = int(input())
area = 3.14 * rad * rad
print("The area of the circle with",rad,"is:",area)
```
>[!Note]
>* area, rad are the variables here
>* while 3.14 is the constant value used hence it is a literal

## Datatypes:
```
n=10
print(n)
p=6.3
print(p)
s = 'Python'
print(s)
print("n is of type:", type(n))
print("p is of type:", type(p))
print("s is of type:", type(s))
```
**Output:**
```
10
6.3
Python
n is of type: <class 'int'>
p is of type: <class 'float'>
s is of type: <class 'str'>
```
>[!Note]
>* these 'int', 'float', 'str' are different data types.
>* python is a **_Dynalmically Typed Programming Lang_** and hence it automatically fetch what type of data it has been provided.

👉 We will discuss about different datatypes later but for now, we need to know only that python has different datatypes(like int, float, string, bool, list).

### To explicitly convert a certain datatype into another suitable datatype can be done by following method:
This is also known as _**Type conversion**_
```
n = 10.71
p = int(n)
print(type(n)) #float
print(type(p)) #int
a = 10
b = float(a) 
print(type(a)) # int
print(type(b)) #float
c = 10
d = str(c)
print(type(c)) #int
print(type(d)) #str
```
**Output:**
```
<class 'float'>
<class 'int'>
<class 'int'>
<class 'float'>
<class 'int'>
<class 'str'>
```
>[!Note]
>* 0 represent _true_ rest any value represent _false_ part except the null value.

## Operators & Expressions:

Let's take an example:
```
n = 10+13*2
```
* Here, we all know that by default nature is left to right and hence we will evaluate this as first _10+13_ as _23_ and then _23*2_ is _46_ but ans is _**36**_. But question is **why** so???
**Ans:** It is because we have some mathematical rules in the python language. Here while doing some operations, we term them as **_Operator Precedence._**
* Here, the correct answer would be _36_.
* If you want to completely go in the left to right direction only, you can use brackets.
* Ex:
  ```
  n = ((10+13)*2)
  ```

  ### Types of Operators:
  In python, we have 3 major types of operators named as:
  * Arithmetic Operators
    - (+, -, *, /, //, % , **)
  * Relational Operators
    - (<, >, <=, >=, ==, !=)
  * Logical Operators
    - (and, or, not)

⭐Arithmatic Operator
```
print("Addition: ", 7+3)
print("Subtraction:", 7-3)
print("Multiplication:", 7*3)
print("Division:", 7/3) #leaves quotient as a floating-point number
print("Floor Division:", 7//3) #leaves quotient
print("Modulo:", 7%3) # leaves remainder
print("Power:", 7**3) # leaves 7 raise to the power of 3
```
**Output:**
```
Addition:  10
Subtraction: 4
Multiplication: 21
Division: 2.3333333333333335
Floor Division: 2
Modulo: 1
Power: 343
```
⭐Relational Operator
```
print("Equal to (7 == 3):", 7 == 3)            # Checks if 7 is equal to 3
print("Not equal to (7 != 3):", 7 != 3)        # Checks if 7 is not equal to 3
print("Greater than (7 > 3):", 7 > 3)          # Checks if 7 is greater than 3
print("Less than (7 < 3):", 7 < 3)             # Checks if 7 is less than 3
print("Greater than or equal to (7 >= 3):", 7 >= 3)  # Checks if 7 is greater than or equal to 3
print("Less than or equal to (7 <= 3):", 7 <= 3)     # Checks if 7 is less than or equal to 3
```
**Output:**
```
Equal to (7 == 3): False
Not equal to (7 != 3): True
Greater than (7 > 3): True
Less than (7 < 3): False
Greater than or equal to (7 >= 3): True
Less than or equal to (7 <= 3): False
```
⭐Logical Operator
```
print(True and True)
print(False or True)
print(not True)
```
**Output:**
```
True
True
False
```
## Introduction To String:
**String** is datatype that can store any type of existing data within itself.
Ex:
```
a = '10'
e = '10.0'
c = 'True'
p = 'a'
s = 'Python is a very powerful language'
```
🌟 Here, the whole sentence written are strings only.

>[!Note]
>* **Only Operation allowed on string:** _Concatenation_
Ex:
```
s = '123456789'
a = s[0]
b = s[6]
print(a+b)
```
👉 When a non-programmer looks into this, they will say that a is storing 1 and b is storing 7 and hence the print statement would give the result as _1+7_ that is _8_.
But, in reality both a and b are of string datatypes and hence they will concatenate and res would be _17_. 


### Slicing and Indexing in String(same would be applicable when learn about lists)
```
s = 'Python is a very powerful language'
print(s[1:7]) # _'yhton '_
print(s[-1:-4]) # _'egau'_
```

👉 **s.length()** gives us the number of string characters.

#### String Comparision
```python
s= 'aab'>'aaa' # _false_
```


_________________________________________________________________________________________________________________________
# WEEK 2 

### three words to keep remember:
if, for & while

> in a line, anything written after '#' is ignored and not used in the program, that is what we call _comments._

##### Variables Revisited Dynamic Typing: 
integer ko float type bana deta jab bhi division/ floor division use ho.

##### Keywords:
Some reserved words can't be used as variables, they are known as _keywords._
like for, while, and, or, not and so on...

**del(variable_name)**: is used to delete a certain variable.
```python
x = 5
print(x) # 5
del(x)
print(x) # variable does not exist
```
_________________________________________________________

### Swapping numbers in Python:
```python
x, y = 1,3
print(x,y) # 1 3
x,y = y,x
print(x,y) # 3 1
```

#### ShortHand Operator:
``x = x+y <=> x+=y``
___________________________________________________________________
##### ``in`` keyword:
👉used in search engine types things
```python
print('study' **in** 'I study in IIT Madras') # True
print('India' **in** 'I study in IIT Madras') # False
```
______________________________________________________________
#### Chaining Operator:
* Using Multiple Relational operators in a single line
```python
x =5
print(2<x<=10) # True
```
________________________________________________________________
### Escape Characters:
* Using ``'\x'`` where x represents different characters,
```python
print('It's beautiful day') # error
print('It\'s beautiful day')   # It's beautiful day
```
|character|usage|
|-|-|
|\'|to differentiate between strings|
|\"|to differentiate between strings|
|\t|to add multiple spaces|
|\n|to move cursor to the new line|
_________________________________________________________________
#### Types of Quotes in Python:
|Quote|Usage|
|-|-|
|'...'| for string identification|
|"..."| for string identification|
|'''...'''| for multiple line strings or to define multiline comments|
_____________________________________________________________________________

### String Methods:
|Method|Description|Code[x = 'pytHoN sTrIng mEthOdS']|Output|
|-|-|-|-|
| **islower** | Checks if all characters in the string are lowercase. | ```print(x.islower())``` | True/False |
| **isupper** | Checks if all characters in the string are uppercase. | ```print(x.isupper())``` | True/False |
| **istitle** | Checks if the string follows the title case. | ```print(x.istitle())``` | True/False |
| **isdigit** | Checks if all characters in the string are digits. | ```print(x.isdigit())``` | True/False |
| **isalpha** | Checks if all characters in the string are alphabetic. | ```print(x.isalpha())``` | True/False |
| **isalnum** | Checks if all characters in the string are alphanumeric. | ```print(x.isalnum())``` | True/False |
| **strip** | Removes leading and trailing whitespaces. | ```print(x.strip())``` | String with no leading/trailing spaces |
| **lstrip** | Removes leading whitespaces. | ```print(x.lstrip())``` | String with no leading spaces |
| **rstrip** | Removes trailing whitespaces. | ```print(x.rstrip())``` | String with no trailing spaces |
| **startswith** | Checks if the string starts with a specified prefix. | ```print(x.startswith('prefix'))``` | True/False |
| **endswith** | Checks if the string ends with a specified suffix. | ```print(x.endswith('suffix'))``` | True/False |
| **count** | Counts occurrences of a substring in the string. | ```print(x.count('substring'))``` | Number of occurrences |
| **index** | Finds the index of the first occurrence of a substring. | ```print(x.index('substring'))``` | Index position |
| **replace** | Replaces a substring with another substring. | ```print(x.replace('old', 'new'))``` | Modified string |

# caeser cipher in cryptography
![image](https://github.com/user-attachments/assets/c47f909e-c82d-439b-b72d-214bf6d26cfe)

____________________________
## If Condition:
The `if` statement is used to execute a block of code only if a specified condition is `True`. If the condition is `False`, the code within the `if` block will be skipped.

Code Example:
```python
# Input age and current year
age = int(input("Enter your age: "))
current_year = 2024

# Movie restriction based on age
if age > 13:
    print("Allowed to watch the movie.")
else:
    print("Not allowed to watch the movie.")
```
## if, else, else if(elif):

Example:

```python
num = int(input("Enter a number: "))
# Checking if the number is odd or even and ends with 0 or 5
if num % 2 == 0:
    print("The number is even.")
else:
    print("The number is odd.")
if num % 10 == 0:
    print("The number ends with 0.")
elif num % 10 == 5:
    print("The number ends with 5.")
else:
    print("The number does not end with 0 or 5.")
```

### Problem:
![image](https://github.com/user-attachments/assets/a57cbc29-f56b-4cd8-8f6c-0f2e99cc036a)

Solution:
```python
# Start of the process
time = input("Enter travel time (longer/shorter): ").lower()
price = input("Enter price (lower/higher): ").lower()

# Determine travel time
if time == "longer":
    # Check price for longer travel
    if price == "lower":
        print("Travel by Coach")
    elif price == "higher":
        print("Travel by Train")
elif time == "shorter":
    # Check price for shorter travel
    if price == "lower":
        print("Travel by Red Eye Flight")
    elif price == "higher":
        print("Travel by Daytime Flight")

# End of the process
print("Arrive at City B")
```

## Importing Libraries

* Using math functions:
```python
import math
print(math.sqrt(100))        # Prints the square root of 100
print(math.log(7))           # Prints the natural logarithm of 7
print(math.factorial(10))    # Prints the factorial of 10
print(math.pow(10, 3))       # Prints 10 raised to the power of 3 (10^3)
```
# Importing random library
```python
import random
# Using random function to print a random number between 0 and 1
print(random.random())  # Prints a random float number between 0 and 1

# Simulating a coin toss game
a = random.random()  # Generates a random float between 0 and 1
if a > 0.5:
    print("Heads")
else:
    print("Tails")

# Simulating a dice roll
# Generates a random integer between 1 and 6
dice_roll = random.randint(1, 6)
print("Dice rolled: {dice_roll}")
```

## importing calendar library

```python
import calendar
print(calendar.month(2021,3))  #calendar of march month of 2021
print(calendar.calendar(2021))  # calendar of entire 2021
```

* Other way of importing inbuilt libraries:
a. 
```python
from calendar import * 
print(calendar(2024))  ## calendar of entire 2024
```

- if we are using only few eatures of a library, it is preferable to import only required files such as:
```python
from calendar import month
print(month(2024,9)) # will give month of sept 2024 as the output
print(calendar(2024)) # error as only month library has been imported
```

b.
```python
import calendar as c
print(c.month(2024,10))

from calendar import month as m
print(m(2024,10))
```
_____________________________________
# WEEK 3

```python
print("When did India get its independence (year)?")
year = int(input())

if year == 1947:
    print("Correct! India got its independence in 1947.")
else:
    print("Incorrect. Please try again.")
    print("When did India get its independence (year)?")
    year = int(input())

    if year == 1947:
        print("Correct! India got its independence in 1947.")
    else:
        print("Incorrect. Last chance!")
        print("When did India get its independence (year)?")
        year = int(input())

        if year == 1947:
            print("Correct! India got its independence in 1947.")
        else:
            print("Incorrect. The correct answer is 1947.")
```

* We can shorten this repeatedness of the code using `while` loop
```python
print("When did India get its independence (year)?")
year = int(input())

# I would like to write a piece of code, which lets the 
# user attempt as many times as he wants.
# The code will end, only when it gets the right answer.

while(year != 1947):
    print("You got this wrong. Enter once again.")
    year = int(input())
```

* Computing factorial using while loop-
```python
# Input from the user
n = int(input("Enter a number to compute its factorial: "))

# Initialize factorial variable
factorial = 1
i = 1

# Use a while loop to compute the factorial
while i <= n:
    factorial *= i
    i += 1

# Output the result
print("The factorial of " + str(n) + " is " +factorial)
```

**TUTORIAL QUESTIONS:**
1. WAPC to compute the factorial of a given input
2. WAPC to count the number of digits in a given input
3. WAPC to reverse the number in the given input
4. WAPC to validate a given number is palindrome or not
```python
# Input from the user
n = int(input("Enter a number for all operations: "))

# Factorial computation
factorial = 1
i = 1
while i <= n:
    factorial *= i
    i += 1
print("The factorial of " + str(n) + " is " + str(factorial))

# Digit count
count = 0
temp = n
while temp != 0:
    temp //= 10
    count += 1
print("The number " + str(n) + " has " + str(count) + " digits.")

# Reverse the number
reversed_num = 0
temp = n
while temp != 0:
    digit = temp % 10
    reversed_num = reversed_num * 10 + digit
    temp //= 10
print("The reverse of " + str(n) + " is " + str(reversed_num) + ".")

# Palindrome check
if n == reversed_num:
    print("The number " + str(n) + " is a palindrome.")
else:
    print("The number " + str(n) + " is not a palindrome.")
```
______________
## for loop
```python
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")
print("Hello World")

# This can also be written as

for i in range(10):
  print("Hello World")
```

* Example: Manually Adding Numbers from 0 to 9
* Manually, 0 + 1 + 2 + ... + 9 = 45
```python
# Using a For Loop to Add Numbers
sum = 0
for i in range(10):
    sum += i
print("Sum of numbers from 0 to 9 is:", sum)
```
- Explanation:
- The loop iterates through numbers 0 to 9.
- During each iteration, the current number 'i' is added to 'sum'.

👉 Understanding range(x, y):
> The range(x, y) function generates a sequence of numbers from x (inclusive) to y (exclusive).

```python
# Example: Multiplication Table of 2
for i in range(1, 11):
    print("2 *", i, "=", 2 * i)
```
* Output:
* ![image](https://github.com/user-attachments/assets/147193de-c13c-49d4-8f87-4a81e0ef045b)


### For Loop Structure:

A for loop typically has 3 parts:
1. Initialization (e.g., i = 0)
2. Condition (e.g., i < 10)
3. Update (e.g., i += 1)

👉 Formatted Printing
```python
# Example 1: Using 'end' parameter
for i in range(1, 6):
    print(i, end=' ')  # prints: 1 2 3 4 5
```
```python
# Example 2: Using 'sep' parameter
d, m, y = 16, 10, 2024
print("Today's date is", end=' ')
print(d, m, y, sep='-')  # prints: Today's date is 16-10-2024
```
___________
# Week 4: Lists, Sets, Tuples, and Functions  

## **1. Lists**  

### **What are Lists?**  
A list is a collection of ordered elements in Python. Unlike arrays in many programming languages, lists can contain elements of different types (e.g., integers, strings, or even other lists).  

### **Why are Lists Needed?**  
In programming, we often deal with collections of data. For example, you may want to store the names of students in a class or temperatures recorded over a week. Lists provide a flexible way to store and manipulate such data.  

### **Small Code Example**  
```python
# Creating a list
fruits = ["apple", "banana", "cherry"]

# Accessing elements
print(fruits[0])  # Output: apple

# Adding an element
fruits.append("date")
print(fruits)  # Output: ['apple', 'banana', 'cherry', 'date']

# Using lists for multiple operations
numbers = [10, 20, 30, 40]

# Insert at specific position
numbers.insert(2, 25)  # Adds 25 at index 2
print(numbers)  # Output: [10, 20, 25, 30, 40]

# Removing elements
numbers.remove(25)
print(numbers)  # Output: [10, 20, 30, 40]

# List slicing
sublist = numbers[1:3]
print(sublist)  # Output: [20, 30]
```
## More on Lists

**Qs:** What is "More on Lists"?
**Ans:** After understanding the basics, we dive deeper into advanced list operations like slicing, comprehensions, and using built-in methods.

**Qs:** Why is it Important?
**Ans:** Advanced list operations are crucial for handling and transforming large datasets efficiently. Tasks like filtering, mapping, or combining lists become easier and more intuitive.
_Ex:_
```python
# Slicing lists
numbers = [10, 20, 30, 40, 50]
print(numbers[1:4])  # Output: [20, 30, 40]

# Reversing a list
reversed_numbers = numbers[::-1]
print(reversed_numbers)  # Output: [50, 40, 30, 20, 10]
```

_Ex:_
```python
# Combining two lists
list1 = [1, 2, 3]
list2 = [4, 5, 6]
combined = list1 + list2
print(combined)  # Output: [1, 2, 3, 4, 5, 6]

# Filtering using list comprehensions
filtered = [x for x in combined if x % 2 == 0]
print(filtered)  # Output: [2, 4, 6]

# Nested lists
matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
print(matrix[1][2])  # Output: 6
```
<br>
## Sets
**Qs:** What are `sets`?
**Ans:** A set is an unordered collection of unique elements in Python. Sets do not allow duplicate values, making them useful for filtering out redundant data.

**Qs:** Why are sets even needed?
**Ans:** In many real-world scenarios, sometimes we need to ensure that data contains only unique elements. For example, you might want to store a list of unique email IDs or eliminate duplicates from a dataset. Sets make such tasks straightforward.
_Ex:_
```python# Creating a set
unique_numbers = {1, 2, 3, 3, 4}

# Notice duplicates are removed
print(unique_numbers)  # Output: {1, 2, 3, 4}

# Adding an element
unique_numbers.add(5)
print(unique_numbers)  # Output: {1, 2, 3, 4, 5}
```
_Ex:_
```python
unique_numbers = {1, 2, 3, 3, 4}

# Notice duplicates are removed
print(unique_numbers)  # Output: {1, 2, 3, 4}

# Adding an element
unique_numbers.add(5)
print(unique_numbers)  # Output: {1, 2, 3, 4, 5}
```
<br>
# Week 5: Dictionaries, Collections, and Functions  

## **1. Dictionaries**  

### **What are Dictionaries?**  
A dictionary in Python is an unordered collection of key-value pairs. Keys are unique, and values can be of any data type.  

### **Why are Dictionaries Needed?**  
Dictionaries are useful when you need to associate data (values) with unique identifiers (keys). For example, you can store student records with roll numbers as keys and names or scores as values.  

### **Small Code Example**  
```python
# Creating a dictionary
student_scores = {"Alice": 85, "Bob": 90, "Charlie": 78}

# Accessing values
print(student_scores["Alice"])  # Output: 85

# Adding a new key-value pair
student_scores["David"] = 92
print(student_scores)  # Output: {'Alice': 85, 'Bob': 90, 'Charlie': 78, 'David': 92}
```
# Collections Summary:
![image](https://github.com/user-attachments/assets/48591a79-fe5d-4a88-9ab6-a297a59b5963)

![image](https://github.com/user-attachments/assets/078cbca2-6553-40eb-a272-3c85a27c7bc9)
