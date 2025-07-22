# Week 5

## Lec 1

### Shell Variables
* In scripting (whether Bash, Zsh, etc.), _shell variables_ are used to `store data temporarily` during the execution of scripts or commands. 
* These variables can store `strings`, `numbers`, `file paths`, `command outputs`, etc.

### What Are Shell Variables?
Shell variables are like `placeholders` in our shell session or script. <br>
We can `assign a value` to a name and use it later.

<img width="1299" height="136" alt="image" src="https://github.com/user-attachments/assets/46815b16-b752-47c4-951d-b6156c6450d0" />

* `name="Alok"` → Assigns **"Alok"** to variable `name` (no spaces around `=`).
* `echo name` → Prints the **literal word** `name` (no `$` used).
* `echo $name` → Prints the **value stored in** variable (`Alok`).
* `$variable_name` is used to **access** the variable's value.

### Frequently used shell variables:
1. `$username`
2. `$hostname`
3. `$home`
4. `$pwd`
5. `$path`

<img width="1705" height="586" alt="image" src="https://github.com/user-attachments/assets/789cac34-d986-43f7-aa1b-b3d94b9fa831" />

* `$USER` → Shows **logged-in username** (e.g., `alok`).
* `$HOSTNAME` → Shows **machine name** (e.g., `AlokThinkbook`).
* `$HOME` → Shows **user's home directory** path (e.g., `/home/alok`).
* `$PWD` → Shows **present working directory** (your current folder).
* `$PATH` → Shows all **directories searched** when running commands (colon-separated list of paths).

> These are **default environment variables**, always available in our shell.

### Special shell variables
- `$0`-> name of the shell
- `$$`-> process ID of the shell
- `$?`-> return code of previously run program
- `$-`-> flags set in the bash shell

<img width="1280" height="559" alt="image" src="https://github.com/user-attachments/assets/5521d17a-4356-4e5b-884c-9119c00f0725" />

* `echo $0` → Shows the **name of the current shell** (here: `-bash`).
* `echo $$` → Prints the **Process ID (PID)** of your **current shell** (e.g., `373`).
* `ps` → Lists **current running processes** (you saw `bash` and `ps` itself).
* `echo $?` → Shows **exit status** of the last command (`0` = success).
* `echo $-` → Displays **active shell flags** (e.g., `himBHs` showing interactive mode, history enabled, job control, etc.).


### Process control
* Use of & to run a job in the background
* fg
* coproc
* jobs
* top
* kill
* echo $$
  
### Program exit codes
* `0`: Success
* `1`: Failure
* `2`: Misused
* `126`: Command cannot be executed
* `127`: Command not found
* `130`: Processes killed using _**Ctrl+C**_.
* `137`: Processes killed using _**kill -9<pid>**_
* echo $?

* Always in the range of `0-255`.
* Any code above 255 is taken % and then considered the modulo value as exit code.

<img width="1270" height="337" alt="image" src="https://github.com/user-attachments/assets/de73c7bb-84d1-4441-ac95-822d27407586" />

* `ls` → Command ran successfully (exit code `$?` was `0`).
* Exit code `0` means **success** (no errors).
* Running `hello` (an invalid command) failed.
* Exit code `$?` became **127**, meaning **“command not found”**.
* `$?` always holds the **exit status** of the **last executed command** (range: 0–255).


### Flags set in bash

* `h` : locate and hash commands
* `B` : brace expansion enabled
* `i` : interactive mode
* `m` : job control enabled
* `H` : ! style history substitution enabled
* `s` : commands are read from stdin
* `c` : commands are read from arguments
* echo $-

## Lec 2

### Creating a variable

<img width="671" height="364" alt="image" src="https://github.com/user-attachments/assets/7b32b119-9dba-47ef-9f42-f5c5acfb2f41" />

_#eg:_
```bash
greeting="Hello World"
echo $greeting        //Hello World
```

### Exporting a variable
1. `export myvar = "value string"` <br>
_OR_
2.
   ```
   myvar = "value string"
   export myvar
   ``` 
### Using variable values
1. `echo $myvar`
2. `echo ${myvar}`
3. `echo "${myvar}_something"`
<img width="954" height="202" alt="image" src="https://github.com/user-attachments/assets/aa276516-1c35-4920-8b36-836015fd88cb" />
* When variables are used in a statement, its always a good practice to use the braces around them

### Removing a variable
`unset myvar`

### Removing a variable
`myvar= `(nothing after the = sign)

### Checking if a Variable is Set

* **If variable is set:**
```bash
[[ -v myvar ]]; echo $?
```
  * `0` means *yes*
  * `1` means *no*.

* **If variable is not set:**

```bash
[[ -z ${myvar+x} ]]; echo $?
```
  * `0` means **not set**
  * `1` means **set**.
<img width="948" height="150" alt="image" src="https://github.com/user-attachments/assets/8375b801-3dff-4126-98ab-979486204b46" />


### Default Values

* **Use default if not set (but don’t assign):**

```bash
echo ${greeting:-"Hi There"}
```

* **Set default if not set (and assign):**

```bash
echo ${greeting:="Hi There"}
```

* **Set a value only if variable exists:**

```bash
echo ${greeting:+"Welcome Back"}
```

---

# **8. String Manipulation Examples**

* **Length of value:**

```bash
greeting="Hello World"
echo ${#greeting}    # Output: 11
```

* **Slice (Substring):**

```bash
echo ${greeting:6:5}   # Output: World
```

* **Remove Pattern:**

```bash
greeting="Hello-World-World"
echo ${greeting#*-}     # removes first occurrence → World-World
echo ${greeting##*-}    # removes max possible → World
```

* **Keep Pattern (Reverse of Remove):**

```bash
echo ${greeting%*-}     # removes after last dash → Hello-World
echo ${greeting%%*-}    # removes after first dash → Hello
```

* **Replace Patterns:**

```bash
echo ${greeting/World/Earth}   # Replace first World → Hello-Earth-World
echo ${greeting//World/Earth}  # Replace all World → Hello-Earth-Earth
```

* **Replace at Start or End:**

```bash
echo ${greeting/#Hello/Hi}    # Replace start
echo ${greeting/%World/Planet}  # Replace end
```

---

# **9. Changing Case**

* **To lowercase:**

```bash
var="HELLO"
echo ${var,,}     # Output: hello
```

* **To uppercase:**

```bash
var="hello"
echo ${var^^}     # Output: HELLO
```

* **First character change only:**

```bash
echo ${var^}      # Capitalize first letter
```

---

# **10. Value Restrictions Using `declare`**

* **Integers only:**

```bash
declare -i number
number=10+20
echo $number      # Output: 30
```

* **Force lowercase:**

```bash
declare -l lower
lower="HeLLo"
echo $lower       # Output: hello
```

* **Force uppercase:**

```bash
declare -u upper
upper="HeLLo"
echo $upper       # Output: HELLO
```

* **Make variable read-only:**

```bash
declare -r readonlyvar="fixed"
readonlyvar="change"   # This will throw an error
```

---

# **11. Arrays**

### Indexed Arrays:

```bash
declare -a fruits
fruits[0]="Apple"
fruits[1]="Banana"
fruits+=("Cherry")        # Appends

echo ${fruits[0]}         # Apple
echo ${#fruits[@]}        # 3 (total items)
echo ${!fruits[@]}        # 0 1 2 (indices)
echo ${fruits[@]}         # Apple Banana Cherry
unset 'fruits[1]'         # Deletes Banana
```

### Associative Arrays (like dictionaries):

```bash
declare -A capitals
capitals["India"]="New Delhi"
capitals["France"]="Paris"

echo ${capitals["India"]}   # New Delhi
echo ${!capitals[@]}        # India France
echo ${capitals[@]}         # New Delhi Paris
echo ${#capitals[@]}        # 2
unset 'capitals["India"]'   # Deletes entry for India
```

---

# **Example Practical Scenario**

Suppose you’re writing a script to greet users in their preferred language.

```bash
user_name="Alok"
language=${1:-"English"}

if [[ $language == "English" ]]; then
    greeting="Hello"
elif [[ $language == "Hindi" ]]; then
    greeting="Namaste"
else
    greeting="Hi"
fi

echo "${greeting}, $user_name!"
```

If you run:

```bash
./script.sh Hindi
```

Output:

```
Namaste, Alok!
```

If no language is passed:

```
Hello, Alok!
```





