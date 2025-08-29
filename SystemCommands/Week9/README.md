# Week 9

## Lecture 1

### Introduction to AWK
* **What is AWK?**
  * AWK is a **programming language** for **processing structured text**.
  * Unlike `sed` (which edits text streams line by line), AWK is designed for **fields and records**.
  * Developed by **Aho, Weinberger, and Kernighan** → that’s why it’s named **AWK**.
  * It is part of the **POSIX standard** (IEEE 1003.1-2008), meaning it is widely available in UNIX/Linux systems.
  * There are different variants:
    * **nawk** (new awk)
    * **gawk** (GNU awk – most commonly used, with extra features beyond POSIX)
    * **mawk** (lightweight, fast version)

👉We can think of **AWK** as a language where **each line (record)** of input is broken into **fields** automatically, and we can process them directly.

### Execution Model of AWK
* **Input stream** is considered as **a set of records**.
  * By default, a **record** = one line (separated by `\n`).
* Each **record** is split into **fields**.
  * Default **field separator** = whitespace (` ` or tab).
  * Example:

    ```
    Input:   Alice 24 Student
    Record:  entire line "Alice 24 Student"
    Fields:  $1="Alice", $2="24", $3="Student", $0="Alice 24 Student"
    ```
* AWK automatically splits records into fields based on **FS (field separator)**.
  * FS can be **changed** (space, tab, colon, regex).
  * Example: `/etc/passwd` uses `:` as separator.
* AWK executes **code blocks per record**:
  * We define a **pattern** (what record to match).
  * If the record matches, the **procedure (action)** runs.

👉 **Big difference from sed**: sed processes **lines**, AWK processes **fields inside lines**.

### Usage

Two ways to run AWK:
#### (a) One-liner on command line

```bash
cat /etc/passwd | awk -F":" '{print $1}'
```

* `-F":"` → sets **field separator** as `:`.
* `{print $1}` → prints the first field of each record (username).
* Example Output:

  ```
  root
  daemon
  bin
  alok
  ```

#### (b) Using a script file

`myscript.awk`:

```awk
#!/usr/bin/gawk -f

BEGIN {
  FS=":"        # set field separator
}
{
  print $1      # print first field
}
```

Run:

```bash
./myscript.awk /etc/passwd
```

👉 Useful for **large programs** with many statements.

### Built-in Variables (Part 1)

AWK provides many **built-in variables** for handling input/output:
| Variable Name | Explaination                                    |
| ------------- | ----------------------------------------------- |
| **ARGC**      | Number of arguments on command line (excluding `-f`/`-v`). | 
| **ARGV**      | Array of command-line arguments.                |
| **ENVIRON**   | Associative array of environment variables.     |
| **FILENAME**  | Current file being processed.                   |
| **FNR**       | Record number within the current file.          |
| **FS**        | Field separator (default: whitespace).          |
| **NF**        | Number of fields in current record.             |
| **NR**        | Total record number (across all files).         |
| **OFMT**      | Output format for numbers.                      |
| **OFS**       | Output field separator (default: space).        |
| **ORS**       | Output record separator (default: newline).     |
| **RS**        | Record separator (default: newline).            | 
| **RLENGTH**   | Length of string matched by `match()` function. |
| **RSTART**    | Starting position of match().                   |
| **SUBSEP**    | Separator for array subscripts.                 |
| **\$0**       | Entire input record.                            |
| **\$n**       | nth field of current record.                    |

👉 Example:

```bash
echo "A B C" | awk '{print NF, $0}'
```

Output:

```
3 A B C
```

(`NF=3`, `$0=whole line`)

```bash
echo "A:B:C" | awk -F":" '{print $2, $3}'
```

Output:

```
B C
```

### AWK Script Structure

General format:

```
pattern { procedure }
```

Types of patterns:
* **BEGIN** → executed once before input is read.
* **END** → executed once after all input is processed.
* **General expressions** → run if true.
* **Regex** → `/pattern/ { action }`
* **Relational expressions** → `($3 > 100) {print $1}`
* **Pattern-matching** → `($1 ~ /abc/) {print $1}`

Procedures can include:
* Variable assignments
* Array operations
* Input/output (`print`, `printf`)
* Built-in/user functions
* Loops and conditions

👉 Example:

```awk
BEGIN { print "Start processing..." }
{ print NR, $1 }
END { print "Done!" }
```

### Execution Flow

* **BEGIN {…}**:
  * Runs once before processing input.
  * Useful for initialization (set FS, headers, etc.).
* **{…}** (no pattern):
  * Runs for every record by default.
* **pattern {…}**:
  * Runs only if pattern matches.
* **END {…}**:
  * Runs once after all input is done.
* Multiple BEGIN/END blocks allowed.
* Multiple patterns and procedures allowed.
* Patterns can use `&&`, `||`, `!` operators.
* Record ranges can be matched:

  ```awk
  /start/,/end/ { print $0 }
  ```

👉 Example AWK script:

```awk
BEGIN { FS=":"; print "Usernames:" }
{ print $1 }
END { print "Done." }
```

Output (from `/etc/passwd`):

```
Usernames:
root
daemon
bin
alok
Done.
```

### Operators in AWK
AWK supports many categories of operators.

#### (a) Assignment Operators
* `=`  simple assignment
* `+=, -=, *=, /=, %=, ^=, **=`  → update variables using arithmetic

```awk
{ x=5; x+=3; print x }   # prints 8
```

#### (b) Logical Operators
* `||` → OR
* `&&` → AND
* `!`  → NOT

```awk
$3 > 50 && $4 < 100 { print $1 }   # print if 3rd field >50 and 4th field <100
```

#### (c) Arithmetic Operators
* `+ - * / % ^ **`
* `^` and `**` → exponentiation

```awk
BEGIN { print 2^3, 2**4 }   # Output: 8 16
```

#### (d) Relational Operators
* `>, <, >=, <=, ==, !=`
```awk
$2 == "Admin" { print $1 }   # print first field if second field is "Admin"
```

### More Operators

#### (a) Conditional Operator (ternary)
```awk
{ max = ($1 > $2) ? $1 : $2; print max }
```
→ Prints the larger of field 1 or field 2.

#### (b) Array Membership
```awk
if ("apple" in fruits) print fruits["apple"]
```

#### (c) Regex Matching
* `a ~ /regex/` → true if matches
* `a !~ /regex/` → true if does not match
```awk
$1 ~ /^[A-Z]/ { print $1 }   # print if first field starts with capital letter
```

#### (d) Increment/Decrement
* `++`, `--` (pre and post forms supported)
```awk
{ count++; print count, $0 }
```

#### (e) Field Reference
* `$n` → field reference
* Blank space = string concatenation

```awk
{ print $1 $2 }   # concatenates field1 and field2 without space
```

### Functions and Commands in AWK

#### (a) Arithmetic Functions
* `atan2(y,x), cos(), sin(), exp(), int(), log(), sqrt(), rand(), srand()`
```awk
BEGIN { print sqrt(25), int(4.7), rand() }
```

#### (b) String Functions
* `length(str)` → string length
* `tolower(str)` / `toupper(str)`
* `substr(str, start, len)`
* `index(str, sub)` → find substring
* `gsub(regex, repl, target)` → replace all
* `sub(regex, repl, target)` → replace first
* `split(str, arr, sep)` → split into array
* `sprintf(fmt, args…)` → format like C’s printf
* `match(str, regex)` → find match, sets RSTART & RLENGTH
* `strtonum(str)` → convert to number
* `asort(), asorti()` → sort arrays (gawk extensions)

#### (c) Control Flow
* `if, else, while, do…while, for, break, continue, return, exit`

#### (d) Input/Output
* `print`, `printf`
* `getline` → read next record manually
* `close(filename)`, `fflush()`

#### (e) System & Extensions
* `system("ls")` → run external command
* `delete array[index]`
* Bitwise ops: `and`, `or`, `xor`, `compl`, `lshift`, `rshift`

### Arrays in AWK
* AWK supports **associative arrays**.
* Keys need not be integers → can be **strings**.
* Sparse storage → only allocated when used.

#### Syntax:
```awk
arr[index] = value
```

#### Iterating:
```awk
for (i in arr) {
  print i, arr[i]
}
```

**Example:**
```awk
BEGIN {
  marks["Alok"] = 90
  marks["Akanksha"] = 95
  for (name in marks)
    print name, marks[name]
}
```

#### Delete element:
```awk
delete marks["Alok"]
```

### Loops in AWK

#### (a) For-in loop (for arrays)
```awk
for (a in array) {
  print a, array[a]
}
```

#### (b) C-style for loop
```awk
for (i=1; i<=5; i++) {
  print i
}
```

#### (c) If condition
```awk
if ($3 > 50) {
  print $1, "passed"
} else {
  print $1, "failed"
}
```

#### (d) While loop
```awk
while (i < 5) {
  print i; i++
}
```

#### (e) Do-while loop
```awk
do {
  print i; i++
} while (i < 5)
```

### User-defined Functions
We can define functions in AWK for reusability.

**Example:**
`mylib` (library file):
```awk
function myfunc1() {
  printf "%s\n", $1
}

function myfunc2(a) {
  return a * rand()
}
```
`myscript.awk`:
```awk
BEGIN { a=1 }
{
  myfunc1()
  b = myfunc2(a)
  print b
}
```

Running with:
```bash
cat infile | awk -f mylib -f myscript.awk
```

### Pretty Printing with printf
`printf` in AWK works like C’s printf.

#### Syntax:
```
printf "format", var1, var2, ...
```

### Format specifiers:
* `%c` → character
* `%d` or `%i` → integer
* `%f` → floating-point
* `%e` → scientific notation
* `%g` → shorter of `%f` or `%e`
* `%o` → octal
* `%x` / `%X` → hex (lower/upper)
* `%s` → string

We can also set:
* **width** → minimum field width
* **prec** → precision
* `-` → left justify

**Example:**
```awk
BEGIN {
  x=123.456
  printf "Integer: %d\nFloat: %.2f\nHex: %x\n", x, x, x
}
```

Output:

```
Integer: 123
Float: 123.46
Hex: 7b
```

### Bash + AWK
- AWK is often combined with **bash scripting** for real-world automation.
- Ways to use AWK in shell scripts:

#### (a) Directly inside shell script
```bash
#!/bin/bash
awk -F":" '{print $1, $3}' /etc/passwd
```
* Prints username and UID from `/etc/passwd`.

#### (b) Using **heredoc**
Embed an AWK program directly:

```bash
#!/bin/bash
awk -F":" '
BEGIN { print "User   UID" }
{ print $1, $3 }
END { print "Done!" }
' /etc/passwd
```

#### (c) With **pipes**
AWK is often chained with other tools like `grep`, `sed`, `sort`, etc.

```bash
ps aux | awk '{print $1}' | sort | uniq -c
```
* Lists number of processes per user.
* 👉 This is where AWK shines — as part of the **UNIX pipeline philosophy**.

### Portability & Availability
* **AWK is everywhere** — it’s part of POSIX, so every Linux/UNIX system has it.
* **Variants**:
  * `awk` → traditional.
  * `nawk` → new awk.
  * `gawk` → GNU awk, has extra features (like `asort`, `gensub`).
  * `mawk` → smaller, faster version.
* For **maximum portability**, stick to **POSIX AWK features**.
* For advanced scripting, use `gawk`.
* 👉 Similar to `sed`, AWK is often the **first choice** for text and log processing, because it’s fast and built-in.

### Final Takeaway
* **AWK = a programming language** (not just a command).
* **Quick to code, fast in execution**.
* Works great for:
  * Log analysis
  * CSV/TSV data processing
  * Generating quick reports
  * Preprocessing data for other tools
* Combine AWK with **shell scripts and pipelines** for powerful one-liners.

  ```bash
  awk '{sum+=$2} END {print "Total:", sum}' sales.txt
  ```

  → Reads numbers from column 2 and prints the total.

👉 Rule of thumb:

* Use **`grep`** for filtering lines.
* Use **`sed`** for editing streams.
* Use **`awk`** for **field-based processing and reporting**.


