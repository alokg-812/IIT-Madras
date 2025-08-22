# Week 6

## Lect-1

### Some Command line utilities
1. `find`
2. file packaging tools(`tar`, `gzip`, etc...)
3. `make`


### Find - _Locating Files and Processing Them_
- Purpose: Search for files or directories based on conditions, and optionally perform actions on them.
<img width="976" height="384" alt="image" src="https://github.com/user-attachments/assets/18c9607f-22de-4a81-a512-0a77d6e31f54" />


#### Syntax:
```bash
	find [pathnames] [conditions]
```

#### Common Options:
* `-name pattern` → match filename (supports wildcards, e.g., `*.txt`)
* `-type [f/d/l]` → filter by file type
  * `f` = regular file
  * `d` = directory
  * `l` = symbolic link
* `-atime +n / -n` → last accessed more than n days ago (`+n`) or less than n days ago (`-n`)
* `-ctime +n / -n` → last *metadata change* (permissions, owner, etc.)
* `-regex` → match filenames with regex (use `-regextype` to set regex flavor)
* `-exec` → run another command on each file (`{}` is placeholder for filename)
* `-print` → print full file path (default action if not specified)

**🫴eg#01:**
```bash
find . -name "*.txt" -type f
```
Finds all `.txt` files in current directory.
<img width="1659" height="252" alt="image" src="https://github.com/user-attachments/assets/173dc2b6-e680-43ca-a034-03a348df0997" />

### File Packaging Commands (`tar`, `gzip`, etc.)
These are used for:
* `Combining` files into one archive
* `Compressing` them to save space

**`tar` – Create Archive**
```bash
tar -cvf archive.tar file1 file2 dir/
```
* `c` = create
* `v` = verbose (show files)
* `f` = file name for archive

**`gzip` – Compress**
```bash
gzip archive.tar
```
* Creates `archive.tar.gz`

**Extracting:**
```bash
tar -xvf archive.tar
tar -xvzf archive.tar.gz
```
* `x` = extract
* `z` = decompress gzip while extracting
<img width="1909" height="305" alt="image" src="https://github.com/user-attachments/assets/bf284ecf-6979-49e9-bc52-95090b55fdef" />

**Other compressors:**

* `bzip2`, `xz`, `7z` – different algorithms for better compression ratios.


### `make` – Automating Conditional Actions

* `make` is usually for compiling programs, but it can automate **any repetitive tasks**.
* Uses a `Makefile` (or `make.file`).
* **🫴eg#02:**
```makefile
# Variables
TMP_FILES = *.bak
.PHONY: backup clean
backup:
        tar -cvf backups/mybackup.tar data
clean:
        rm -f $(TEMP_FILES)
```

**Run:**

```bash
make build
make clean
```

* `.PHONY` means it's not a real file, just a command label.
* `make` only runs a target if its prerequisites have changed (saves time).
<img width="1683" height="517" alt="image" src="https://github.com/user-attachments/assets/13b5f136-cea9-41cc-816e-02d9f57a3a52" />


## Lec 2
*__Shell Scripts__* - creating our own commands.

* **Definition of Shell Script:** 
	* A **shell script** is just a file containing a series of shell commands (the same ones you type in the terminal).
	* Instead of typing commands one by one, you put them in a file and execute the file.
	* This saves time, reduces mistakes, and allows automation.

* **Why Needed?**
	* To **automate repetitive tasks** (like backups, monitoring logs, system setup).
	* To ensure consistency (same steps every time).
	* To combine multiple commands into one program.

### Software Tools Principles
1. Do one thing well.
2. Process lines of text, not binary.
3. Use regular expressions.
4. Default to standard I/O.
5. Don't be chatty.
6. Generate same output format accepted as input.
7. Let someone else do the hard part.
8. Detour to build specialized tools. 
   * _Ref: Classic Shell Scripting - Arnold Robbins & Nelson H.F. Beebe_

<img width="444" height="233" alt="image" src="https://github.com/user-attachments/assets/6271ccef-c547-4af2-b399-78f37b1c6aca" /> <br>
* _In this course till now, we have used `shell script` but in upcoming time, we'll also use `awk script` & `sed script`._
* The first link of any script tells the interpretetion style.
  * **🫴eg#03:** The bash written tells us that this is a bash scripting
  * ```
    #!/bin/bash
    ```


### How to Run a Script – Sourced vs Executed

<img width="1211" height="569" alt="image" src="https://github.com/user-attachments/assets/df565702-4a6c-46da-9e7c-ec9089e43355" /> <br>
* **Sourced (`source script.sh` or `. script.sh`)**:
  	* Runs in your current shell.
  	* Variables persist.
* **Executed (`./script.sh`)**:
  	* Runs in a new shell process.
  	* Changes vanish after execution.

*  **Why Needed?**
	* **Sourcing** is useful when you want to prepare your environment (e.g., set PATH or environment variables).
	* **Execution** is useful when you just want to run a program/task independently.

> **🫴eg#04:** <br>
> <img width="1397" height="260" alt="image" src="https://github.com/user-attachments/assets/5ff3d432-bf41-4fb2-9d6f-812cd68e443e" />
> <img width="1716" height="947" alt="image" src="https://github.com/user-attachments/assets/83876eb5-23be-49a1-bc79-6e5833703ea2" />


### Variables in Shell Scripts
* Variables store values (like numbers, strings, paths).
* Use `=` to assign (no spaces around `=`).
* Access with `$variablename`.

* **Why Needed?**
	* To store **temporary values** (like filenames, paths, counters).
	* To avoid rewriting values multiple times.
	* To make scripts **dynamic and reusable**.

* **🫴eg#05:** 

```bash
#!/bin/bash
name="Alok"
age=21
echo "My name is $name and I am $age years old."
```

### Positional Parameters (\$0, \$1, \$2, …, \$@, $\*)
* `$0` → script name.
* `$1, $2, …` → arguments passed to script.
* `$@` → all arguments as separate words.
* `$*` → all arguments as one string.

* **Why Needed?**
	* To write scripts that accept input when run.
	* Useful for **dynamic input** (filenames, user data, etc.).

* **🫴eg#06:** 
```bash
#!/bin/bash
echo "Script name: $0"
echo "First arg: $1"
echo "Second arg: $2"
echo "All args separate: $@"
echo "All args as one string: $*"
```

Run it:

```bash
./myscript.sh apple banana mango
```

✅ Output shows script name + arguments.


### Conditional Statements (if, else, elif)
* Allow decision-making in scripts.
* Syntax:

```bash
if [ condition ]; then
   commands
elif [ condition ]; then
   commands
else
   commands
fi
```

* **Why Needed?**
	* To run commands **only if conditions are true**.
	* Example: Check if a file exists, if user input is valid, etc.

**🫴eg#07:** 
```bash
#!/bin/bash
if [ -f "$1" ]; then
   echo "File $1 exists."
else
   echo "File $1 does not exist."
fi
```

Run:

```bash
./checkfile.sh myfile.txt
```

### Loops (for, while, until)
* **for loop** → repeat over list of items.
* **while loop** → run while condition true.
* **until loop** → run until condition true.

* **Why Needed?**
	* To automate repetition.
	* Example: Processing 100 files, counting numbers, monitoring logs.

* **🫴eg#08:** 

```bash
# For loop
for i in 1 2 3 4 5; do
   echo "Number: $i"
done

# While loop
count=1
while [ $count -le 5 ]; do
   echo "Count: $count"
   count=$((count+1))
done
```

### Functions
* Functions group commands together for reuse.
* Syntax:

```bash
myfunc() {
   echo "Hello from function"
}
```

* **Why Needed?**
	* Avoid repeating code.
	* Organize scripts like real programs.

**🫴eg#09:** 
```bash
#!/bin/bash
sayhi() {
   echo "Hello, $1!"
}
sayhi Alok
sayhi World
```

### Practical Examples (Backup, User Input, File Checks, etc.)**

📖 **Explanation**

* Real-world scripts: backups, monitoring, installing, etc.
* Demonstrates combining variables, loops, conditions, functions.

* **Why Needed?**

* This is where **shell scripting power** shows up: automation of system tasks.

💻 **Example Practice:**

```bash
#!/bin/bash
read -p "Enter directory to backup: " dir
tar -czf backup.tar.gz "$dir"
echo "Backup of $dir created as backup.tar.gz"
```

### Script Location
* You can run a script by:
  * **Absolute path** → `/home/alok/myscript.sh`
  * **Relative path** → `./myscript.sh` (from current folder)
* If you want to run scripts **without typing path**, add their folder to `$PATH`.
* `$PATH` is a list of directories the shell searches for commands.

* **Why Needed?**
	* To run your scripts easily like normal commands (e.g., `ls`, `pwd`).

* **🫴eg#10:** 
```bash
# Check your PATH
echo $PATH

# Add current folder to PATH (temporary)
export PATH=$PATH:.

# Now you can run your script directly
myscript.sh
```

### Bash Environment
* **Login shell** → runs startup files like `/etc/profile`, `~/.bash_profile`, `~/.bash_login`, `~/.profile`.
* **Non-login shell** → runs `/etc/bash.bashrc` and `~/.bashrc`.

* **Why Needed?**
	* Lets you **customize environment** (set aliases, PATH, prompt, etc.).
	* Different files for login vs interactive sessions.

* **🫴eg#11:** 
```bash
# Add alias in your ~/.bashrc
echo "alias ll='ls -la'" >> ~/.bashrc

# Reload the file
source ~/.bashrc

# Now test
ll
```

### Output from Shell Scripts
* `echo` → prints text, ends with newline (unless `-n`).
* `printf` → works like C’s `printf`, allows formatted output.

* **Why Needed?**
	* To display messages and results clearly.
	* `printf` is better for formatting tables.

* **🫴eg#12:** 
```bash
echo "My home is $HOME"
printf "User: %s\nHome: %s\n" $USER $HOME
```

### Input to Shell Scripts
* Use `read var` to take user input from the terminal.

* **Why Needed?**
	* For **interactive scripts** where user provides info.

* **🫴eg#13:**  
```bash
#!/bin/bash
read -p "Enter your name: " name
echo "Hello $name, welcome to Linux!"
```

### Shell Script Arguments

* `$0` → script name
* `$#` → number of arguments
* `$1, $2 …` → arguments
* `$@` → all arguments separately
* `$*` → all arguments as single string

* **Why Needed?**
	* To pass input dynamically without hardcoding values.

* **🫴eg#14:** 
```bash
#!/bin/bash
echo "Script: $0"
echo "Args count: $#"
echo "First: $1"
echo "Second: $2"
echo "All separate: $@"
echo "All together: $*"
```

Run:
```bash
./args.sh apple banana cherry
```

### Command Substitution
* Run a command inside another.
* Syntax:
  * Old: `` var=`command` ``
  * New: `var=$(command)`

* **Why Needed?**
	* To use **command outputs as variables**.

* **🫴eg#15:** 
```bash
today=$(date)
echo "Today is $today"

files=$(ls | wc -l)
echo "Files in this folder: $files"
```

### Loops – For, Case, If

📖 **For loop**

* Syntax:

```bash
for var in list;
do
   commands
done
```

* Needed to repeat over lists (files, numbers, users).

* **🫴eg#16:** 
```bash
for i in 1 2 3;
do
	echo "Number $i";
done
```

📖 **Case statement**

* Syntax:
```bash
case var in
   pattern1) commands ;;
   pattern2) commands ;;
esac
```

* Needed to match options (like menus).

* **🫴eg#17:** 
```bash
read -p "Enter y/n: " ans
case $ans in
   y|Y) echo "Yes";;
   n|N) echo "No";;
   *) echo "Invalid";;
esac
```

### Conditions & Expressions

📖 **Ways to test conditions**

* `command` return code (success = true).
* `test expression` or `[ expr ]`.
* `[[ expr ]]` for advanced conditions.
* Arithmetic: `(( expr ))`.

* **Why Needed?**
	* To control script flow based on checks.

* **🫴eg#18:** 
```bash
# Check file exists
if [ -e myfile.txt ]; then
   echo "File exists"
fi

# Check numeric comparison
n=5
if [ $n -gt 3 ]; then
   echo "$n is greater than 3"
fi

# String comparison
str="hello"
if [ "$str" = "hello" ]; then
   echo "String matched"
fi
```

### Numeric Comparisons (test)
Shell doesn’t use `>` `<` for numbers directly — it uses **flags** with `test` or `[ ]`.

* `-eq` → equal
* `-ne` → not equal
* `-gt` → greater than
* `-ge` → greater or equal
* `-lt` → less than
* `-le` → less or equal

* **Why Needed?**
	* For comparing **numbers** in scripts (loops, counters, input validation).

* **🫴eg#19:** 
```bash
#!/bin/bash
n1=10
n2=20

if [ $n1 -lt $n2 ]; then
   echo "$n1 is less than $n2"
fi
```

### String Comparisons
* `=` → equal
* `!=` → not equal
* `<` and `>` → lexicographic comparison
* `-n str` → length > 0
* `-z str` → length = 0

* **Why Needed?**
	* To validate user input, check filenames, compare text.

* **🫴eg#19:** 
```bash
#!/bin/bash
str="hello"
if [ "$str" = "hello" ]; then
   echo "Matched"
fi

if [ -z "$str" ]; then
   echo "Empty string"
else
   echo "Not empty"
fi
```

### Unary File Comparisons
* `-e file` → exists
* `-d file` → is directory
* `-f file` → is regular file
* `-r file` → readable
* `-w file` → writable
* `-x file` → executable
* `-s file` → not empty
* `-O file` → owned by current user
* `-G file` → same group as user

* **Why Needed?**
	* To make scripts **file-aware** (backup, monitoring, automation).

* **🫴eg#20:** 
```bash
#!/bin/bash
file="myfile.txt"
if [ -f "$file" ]; then
   echo "$file is a regular file"
else
   echo "$file not found"
fi
```

### Binary File Comparisons
* `file1 -nt file2` → file1 newer
* `file1 -ot file2` → file1 older

* **Why Needed?**
	* Useful for **backup or sync scripts** (check if a file needs updating).

* **🫴eg#21:** 
```bash
if [ file1.txt -nt file2.txt ]; then
   echo "file1.txt is newer"
else
   echo "file2.txt is newer or same"
fi
```

### While Loop
Runs while condition is **true**. It is used:
	1. For reading logs, 
 	2. waiting for events, or 
  	3. looping until a task finishes.

* **🫴eg#22:** 
```bash
count=1
while [ $count -le 5 ]; do
   echo "Count = $count"
   count=$((count+1))
done
```

### Until Loop
Runs until condition becomes **true** (opposite of `while`).

* **Why Needed?**
For retry logic (keep running until success).

* **🫴eg#23:** 
```bash
num=1
until [ $num -gt 5 ]; do
   echo "Number = $num"
   num=$((num+1))
done
```

### Functions in Shell
* Define once, call multiple times.
* Syntax:

```bash
myfunc() {
   commands
}
```

* Must be **defined before calling**.

* **Why Needed?**
	* To reuse logic (like mini-programs inside your script).
	* Keeps code organized.
* **🫴eg#24:** 
```bash
#!/bin/bash
greet() {
   echo "Hello $1"
}

greet Alok
greet World
```

### Wrap-Up / Explore More
* The slides encourage you to **explore with the commands learned so far**.
* Features like **arrays, advanced string handling, trap, subshells, etc.** exist but were not covered.

* **Why Needed?**
	* Shell scripting is very deep — these basics let you start **automation & real-world scripting**.
	* Next steps: learn `sed`, `awk`, `grep`, and integrate with scripts.


