# Week 5


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



### Flags set in bash

* `h` : locate and hash commands
* `B` : brace expansion enabled
* `i` : interactive mode
* `m` : job control enabled
* `H` : ! style history substitution enabled
* `s` : commands are read from stdin
* `c` : commands are read from arguments
* echo $-

<img width="1502" height="269" alt="image" src="https://github.com/user-attachments/assets/0df43631-fb6b-4485-a65e-cc64f4460a7a" />
* 












