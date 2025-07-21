# Week 5


### Shell Variables
* In scripting (whether Bash, Zsh, etc.), _shell variables_ are used to `store data temporarily` during the execution of scripts or commands. 
* These variables can store `strings`, `numbers`, `file paths`, `command outputs`, etc.

### What Are Shell Variables?
Shell variables are like `placeholders` in our shell session or script. <br>
We can `assign a value` to a name and use it later.

### Frequently used shell variables:
1. `$username`
2. `$hostname`
3. `$home`
4. `$pwd`
5. `$path`

### Special shell variables
- `$0`-> name of the shell
- `$$`-> process ID of the shell
- `$?`-> return code of previously run program
- `$-`-> flags set in the bash shell


### Program exit codes
* `0`: Success
* `1`: Failure
* `2`: Misused
* `126`: Command cannot be executed
* `127`: Command not found
* `130`: Processes killed using _**Ctrl+C**_.
* `137`: Processes killed using _**kill -9<pid>**_

* Always in the range of `0-255`.
* Any code above 255 is taken % and then considered the modulo value as exit code.


















