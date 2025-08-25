# Week 7

* In this week, we'll deep dive into more features in bash script
  * Debugging (`set -x`)
  * Combining conditions (`&&`, `||`)
  * Arithmetic (`let`, `expr`, `$(( ))`)
  * `expr` operators (math + string ops)
  * Heredoc input
  * If / Elif / Else control flow
  * Case statement (menu-driven)
  * C-style loops (1 var, 2 vars)
  * Redirect loop output
  * Break & break with nested loops
  * Continue statement
  * Shift command
  * `exec` (replace shell, redirect logs)
  * `eval` (build/run dynamic commands)
  * `getopts` (parse flags/options)
  * `select` (interactive menus)
  * Break inside menus
  * Putting it all together


## Lecture 1 - Bash Script 2A

### Debugging Shell Scripts
👉 Debugging shows us what commands our script is executing.
* Options:
  * `set -x` inside script → prints each command before executing.
  * `bash -x script.sh` → run with debugging enabled.

🤔 **Why Needed?**
  * Helps **trace errors**.
  * See how variables expand.
  * Understand script execution flow.

* **🫴eg#01:** 
```bash
#!/bin/bash
set -x
name="Alok"
echo "Hello $name"
```

Run:
```bash
./debug.sh
```

✅ We’ll see each line executed with its expanded form.

### Combining Conditions
* Use logical operators:
  * `&&` → AND (both must be true)
  * `||` → OR (either one true)

🤔 **Why Needed?**
  * To check multiple conditions in one line.

* **🫴eg#02:**
```bash
a=5
if [ $a -gt 3 ] && [ $a -lt 10 ]; then
   echo "a is between 3 and 10"
fi
```

### Arithmetic in Shell
Ways to do math in bash:
* `let var=expr`
* `expr expr1 + expr2`
* `$(( expression ))` → preferred
* `$[ expression ]` → older style
* `(( expr ))` → shorthand

🤔 **Why Needed?**
  * For counters, calculations, automation tasks.

* **🫴eg#03:**
```bash
a=10
b=20
let sum=$a+$b
echo "Sum: $sum"

expr $a + 5

c=$((a * b))
echo "Product: $c"
```

### `expr` Command Operators – Part 1
* `a + b` → sum
* `a - b` → subtraction
* `a * b` → multiplication
* `a / b` → division
* `a % b` → remainder
* `a > b` → comparisons (returns 1 or 0)
* `a >= b` → comparisons (returns 1 or 0)
* `a < b` → comparisons (returns 1 or 0)
* `a <= b` → comparisons (returns 1 or 0)
* `a = b` → equality (returns 1 or 0)

🤔 **Why Needed?**
  * `expr` is an older but still used tool for arithmetic in shell.
* **🫴eg#04:**
```bash
a=8
b=4
expr $a + $b
expr $a \* $b
expr $a \> $b
```

(Note: Escape `*` with `\*` in shell.)

### `expr` Command Operators – Part 2
More operators:

* `a | b` → return a if non-zero, else b
* `a & b` → return a if both non-zero
* `a != b` → not equal
* String ops:
  * `str : reg` → regex match
  * `match str reg` → regex extract
  * `substr str n m` → substring
  * `index str chars` → position of chars in string
  * `length str` → string length

🤔 **Why Needed?**
  * To manipulate strings without external tools.

* **🫴eg#05:**
```bash
#!/bin/bash

a=256
b=4
c=3

ans=$( expr $a + $b )
echo $ans

ans=$( expr $a - $b )
echo $ans

ans=$( expr $a \* $b )
echo $ans

ans=$( expr $a / $b )
echo $ans

ans=$( expr $a % $b )
echo $ans

ans=$( expr $a \> $b )
echo $ans

ans=$( expr $a \>= $b )
echo $ans

ans=$( expr $a \< $b )
echo $ans

ans=$( expr $a \<= $b )
echo $ans

ans=$( expr $a = $b )
echo $ans

ans=$( expr $a != $b )
echo $ans

ans=$( expr $a \| $b )
echo $ans

ans=$( expr $a \& $b )
echo $ans

str="octavio version as in Jan 2022 is 6.4.0"
reg="[oO]ctav[aeiou]"
ans=$( expr "$str" : $reg )
echo $ans

ans=$( expr index "$str" "Js" )
echo $ans

ans=$( expr substr "$str" 8 8)
echo $ans

ans=$( expr length "$str")
echo $ans
```
**Output:** <br>
<img width="1849" height="505" alt="image" src="https://github.com/user-attachments/assets/02d2cbfb-f0ba-432d-87a1-e8c577c9eb41" /> <br>


### Heredoc Feature
* Allows passing multiple lines of input to a command.
* Syntax:

```bash
var=$(command <<EOF
... content ...
EOF
)
```

* `<<-` ignores leading tabs.
* Marker name doesn’t have to be `EOF`.

🤔 **Why Needed?**
  * To send multi-line input (like formulas to `bc`, SQL queries, configs).

* **🫴eg#06:**
```bash
a=2.5
b=3.2
c=4
d=$(bc -l << EOF
scale=5
($a+$b)^$c
EOF
)
echo $d
```

### If-Else & If-Elif-Else

* **If-else**:

```bash
if condition; then
   commands
else
   commands
fi
```

* **If-elif-else**: multiple conditions

```bash
#!/bin/bash
read -p "Enter a number: " n
if [ $n -gt 10 ]; then
   echo "Greater than 10"
elif [ $n -eq 10 ]; then
   echo "Equal to 10"
else
   echo "Less than 10"
fi
```

### Case Statement Options
* `case` is like a multi-choice `if-elif`.
* Matches a variable against patterns.
* `*` is the default case.

* **Why Needed?**
  * Menu-driven programs, input validation, handling options.

* **🫴eg#07:**
```bash
#!/bin/bash
echo "Choose an option:"
echo "1. Show date"
echo "2. Show current user"
echo "3. List files"
echo "4. Exit"

read -p "Enter choice [1-4]: " choice

case $choice in
  1) date ;;
  2) whoami ;;
  3) ls -lh ;;
  4) echo "Exiting..."; exit ;;
  *) echo "Invalid choice" ;;
esac
```

### C-Style For Loop (One Variable)
* Similar to C programming loops.
* Syntax:
  ```bash
  for (( init; condition; increment ))
  do
    ...
  done
  ```

* **Why Needed?**
  * Easier numeric iteration (compared to `for in list`).

* **🫴eg#08: Print Even Numbers**
```bash
#!/bin/bash
begin=1
finish=20

echo "Even numbers from $begin to $finish:"
for (( i=$begin; i<=$finish; i++ ))
do
  if (( i % 2 == 0 )); then
    echo "$i"
  fi
done
```

### C-Style For Loop (Two Variables)
* Allows multiple variables in a loop.

* **Why Needed?**
  * Useful when you want two counters moving in opposite directions.

* **🫴eg#09:**
```bash
#!/bin/bash
for (( a=1, b=10; a<=10; a++, b-- ))
do
  echo "a=$a, b=$b"
done
```

### Processing Output of a Loop
* Loop output can be redirected to a file.

* **Why Needed?**
  * To save results for later processing.

* **🫴eg#10:**
```bash
#!/bin/bash
read -p "Enter a number for multiplication table: " n
outfile="table_${n}.txt"

for (( i=1; i<=10; i++ ))
do
  echo "$n x $i = $((n*i))"
done > "$outfile"

echo "Table saved in $outfile"
cat "$outfile"
```

### Break Statement
* `break` exits the loop early.
* `break 2` → exits two nested loops.

* **Why Needed?**
  * To stop looping once condition is met.

* **🫴eg#11:**
```bash
#!/bin/bash
for (( i=1; i<=10; i++ ))
do
  if [ $i -eq 6 ]; then
    echo "Stopping at $i"
    break
  fi
  echo "Number: $i"
done
```

### Break in Nested Loops

* Can break out of **outer loop** using `break N`.

* **Why Needed?**
  * Useful in search problems: stop once found.

* **🫴eg#12:**
```bash
#!/bin/bash
target=15
for (( i=1; i<=10; i++ ))
do
  for (( j=1; j<=10; j++ ))
  do
    sum=$((i+j))
    echo "Checking $i + $j = $sum"
    if [ $sum -eq $target ]; then
      echo "Found pair: $i + $j = $target"
      break 2   # exit both loops
    fi
  done
done
```

### Continue Statement
* Skips rest of loop body, moves to next iteration.

* **Why Needed?**
  * To **skip unwanted cases** without stopping loop.

* **🫴eg#13:**
```bash
#!/bin/bash
for (( i=1; i<=15; i++ ))
do
  if (( i % 3 == 0 )); then
    continue
  fi
  echo "Number: $i"
done
```

### Shift Command

* `shift` moves command-line arguments left.
* `$1` becomes `$2`, `$2` becomes `$3`, etc.

* **Why Needed?**
  * To process arguments one by one in a loop.

* **🫴eg#14:**
```bash
#!/bin/bash
i=1
while [ -n "$1" ]
do
  echo "Argument $i = $1"
  shift
  ((i++))
done
```

Run:

```bash
./args.sh apple banana cherry mango
```

### exec
* `exec` replaces the current shell with another program.
* If the program runs successfully → shell is replaced (doesn’t return).
* If it fails → shell continues.
* Also used to **redirect input/output permanently** in a script.

* **Why Needed?**
  * To launch another program *without* keeping the parent shell.
  * To redirect logs for the *entire script*.

* **🫴eg#15:**

```bash
#!/bin/bash
# Redirect all script output to a log file
exec > script.log 2>&1

echo "This will go to script.log"
date
ls -lh
```

Run it, then check `cat script.log`. ✅ Everything is logged.

### eval
* `eval` takes a string and executes it as a command.
* Combines variables into commands dynamically.

* **Why Needed?**
  * When you need to **build commands at runtime**.
  * Example: constructing `ls -l <directory>` dynamically.

* **🫴eg#16:**
```bash
#!/bin/bash
cmd="ls -lh"
echo "About to run: $cmd"
eval $cmd
```

✅ `eval` converts the string into a real command and runs it.

### getopts (Option Parsing)
* `getopts` is used to handle **flags/options** like `-a`, `-b value`, etc.
* `OPTARG` stores option values.

* **Why Needed?**
  * To make **professional scripts** like real Linux commands (`ls -l`, `grep -i`).
  
* **🫴eg#17:**
```bash
#!/bin/bash
while getopts "ab:c:" opt; do
  case $opt in
    a) echo "Option -a selected" ;;
    b) echo "Option -b with value: $OPTARG" ;;
    c) echo "Option -c with value: $OPTARG" ;;
    *) echo "Usage: $0 [-a] [-b value] [-c value]" ;;
  esac
done
```

Run:

```bash
./options.sh -a -b hello -c world
```

### select Loop (Menus)
* `select` builds interactive text menus.
* User chooses from numbered list.
* Usually combined with `case`.

* **Why Needed?**
  * For interactive scripts (system admin menus, installers).

* **🫴eg#18:**
```bash
#!/bin/bash
echo "Choose a fruit:"
select fruit in Apple Banana Mango Exit
do
  case $fruit in
    Apple) echo "You chose Apple" ;;
    Banana) echo "Banana is yellow" ;;
    Mango) echo "Mango is sweet" ;;
    Exit) echo "Goodbye!"; break ;;
    *) echo "Invalid choice" ;;
  esac
done
```

### Break with Select
* Like loops, `break` ends a `select` loop.
* Useful when user picks “Exit”.

* **Why Needed?**
   * Lets you build **user-friendly menus** that can quit gracefully.
* **🫴eg#19:**
```bash
#!/bin/bash
echo "Select an action:"
select opt in Date Time Exit
do
  case $opt in
    Date) date ;;
    Time) date +"%H:%M:%S" ;;
    Exit) echo "Exiting menu..."; break ;;
    *) echo "Invalid choice" ;;
  esac
done
```

### Wrap-Up / Professional Scripts
By now, we’ve seen:
* Debugging, conditions, arithmetic, loops, case/select, break/continue, shift, exec, eval, getopts.
* These are **core tools** to make production-ready bash scripts.

* **Why Needed?**
  * With these, we can:
     * Build command-line utilities
     * Automate system tasks
     * Create menus and interactive tools
     * Parse arguments like real Linux commands

* **🫴eg#20:**
```bash
#!/bin/bash
while true; do
  echo "==== System Menu ===="
  echo "1. Show Date"
  echo "2. Show Disk Usage"
  echo "3. Show Logged-in Users"
  echo "4. Exit"
  read -p "Enter choice [1-4]: " choice

  case $choice in
    1) date ;;
    2) df -h ;;
    3) who ;;
    4) echo "Bye!"; break ;;
    *) echo "Invalid option" ;;
  esac
done
```



