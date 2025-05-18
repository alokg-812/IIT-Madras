## Getting Started with the Terminal

When you open a terminal in Linux, you're entering a **command line interface** (CLI). It allows you to interact with your computer using text-based commands.

##### ✨ Common Terminal Emulators
- **GNOME Terminal** – Default in Ubuntu
- **Konsole** – KDE
- **xterm**
- **guake** – Drop-down terminal

---

## 📂 Basic Commands to Begin

| Command | Description |
|--------|-------------|
| `pwd` | Prints the **current directory** you're in. (Where am I?) |
| `ls` | Lists the **files and folders** in your current directory. |
| `ps` | Shows **currently running processes**. |
| `uname` | Displays **system information** (like kernel name, OS type). |
| `clear` or `Ctrl + L` | Clears the terminal screen. |
| `exit` or `Ctrl + D` | Closes the terminal session. |

**eg#:**
```bash
pwd
# Output: /home/alok
ls
# Output: Documents  Downloads  Pictures
uname -a
# Output: Linux AlokThinkbook 5.15.90.1-microsoft-standard-WSL2 ...
```

![image](https://github.com/user-attachments/assets/342c718c-c72f-4fc3-a257-d258af33ac61)


## 🖥️ Understanding the Terminal Prompt
When you're in the terminal, you typically see a prompt like:
```bash
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/madras/SC/week1 ~$ ls -a
```

- Parts of the Prompt

| Part                                           | Meaning                                  |
|------------------------------------------------|------------------------------------------|
| `alok`                                         | Username                                 |
| `AlokThinkbook`                                | Hostname                                 |
| `/mnt/c/Users/alokg/Desktop/madras/SC/week1`   | Path                                     |
| `~`                                            | Symbol for the home directory            |
| `$`                                            | Indicates a normal user (`#` means root) |
| `ls`                                           | Command                                  |
| `-a`                                           | Option (shows all files, including hidden ones) |

---

## The `man` Command – Manual Pages
Use the `man` (manual) command to view help pages for any Linux command.
**eg#:**
```bash
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/madras/SC/week1$ man ls
```
> Use arrow keys to scroll, `h` for help, `q` to quit.  <br>

![image](https://github.com/user-attachments/assets/97fbab4a-ed59-46c3-9b45-2447f09e1598)

🗂️ Man Page Sections
The Linux manual is organized into sections. You can specify the section when needed:

| Section | Description                   |
|---------|-------------------------------|
| 1       | User commands (e.g., ls, pwd) |
| 2       | System calls                  |
| 3       | Library functions             |
| 4       | Special files (e.g., devices) |
| 5       | File formats                  |
| 6       | Games                         |
| 7       | Miscellaneous                 |
| 8       | System admin commands         |
| 9       | Kernel routines               |

ex#:
```yml
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/madras/SC/week1$ man 1 ls
# Shows the 'ls' command from section 1 (user commands)
```
