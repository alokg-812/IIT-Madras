# Week 7

* In this week, we'll deep dive into more features in bash script
  * Debugging (`set -x`)
  * Combining conditions (`&&`, `||`)
  * Arithmetic (`let`, `expr`, `$(( ))`)
  * `expr` operators (math + string ops)
  * Heredoc input
  * If / Elif / Else control flow

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


