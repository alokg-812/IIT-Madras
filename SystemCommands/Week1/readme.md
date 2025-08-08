## Getting Started with the Terminal

When you open a terminal in Linux, you're entering a **command line interface** (CLI). It allows you to interact with your computer using text-based commands.

##### ✨ Common Terminal Emulators
- `GNOME Terminal` – Default terminal in Ubuntu
- `Konsole` – Popular with KDE desktop environments.
- `xterm` – A very lightweight, minimal terminal.
- `Guake` – A drop-down terminal you can open with a single key press, great for quick commands.

No matter which terminal emulator you use, the commands you type will be the same — so once you learn them, you can work in any Linux environment.

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

- 🗂️ Man Page Sections
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

### File System Overview: traversing the tree:
Linux uses a tree-like file structure, starting at the root `/`.

Key Symbols:
 
- `/` is the `root` of the file system.
- `/` is also the `delimiter` for sub-directories
- `.` is `current directory`
- `..` is `parent directory`

**eg#:**
```yaml
alok@AlokThinkbook:/mnt/c/Users/alokg/mnt/c/Users/alokg/Desktop/madras/SC cd .. // this will take us to the parent directory
```
> Path for traversal can be absolute or relative

#### Absolute vs Relative Paths
- **Absolute path**: Full path from root
```yaml
cd /home/alok/Documents
```

- **Relative path**: Path from the current location
```yaml
cd Documents
```
---

### 📁 Important Linux Directories

| Directory | Purpose |
|-----------|---------|
| `/bin`    | Essential command binaries (like `ls`, `cp`) |
| `/boot`   | Boot loader files |
| `/dev`    | Device files (e.g., USB, disk) |
| `/etc`    | System configuration files |
| `/home`   | User home directories (like `C:\Users\`) |
| `/lib`    | Essential shared libraries |
| `/media`  | Mount point for removable devices |
| `/mnt`    | Mount point (usually for manually mounted devices) |
| `/opt`    | Optional/add-on software packages |
| `/sbin`   | System binaries (mostly for root/admin) |
| `/srv`    | Service data |
| `/tmp`    | Temporary files |
| `/usr`    | Secondary hierarchy (user-level programs) |
| `/var`    | Variable files like logs, caches |

---

### The `/usr` Hierarchy

| Folder       | Contents |
|--------------|----------|
| `/usr/bin`   | Regular user commands |
| `/usr/lib`   | Shared libraries |
| `/usr/local` | Locally installed software |
| `/usr/sbin`  | Admin system commands (non-essential) |
| `/usr/share` | Architecture-independent data |
| `/usr/include` | Header files for C programs |
| `/usr/src`   | Source code |

---

### The `/var` Hierarchy

| Folder        | Purpose |
|---------------|---------|
| `/var/cache`  | Cached data for applications |
| `/var/lib`    | State information (e.g., databases) |
| `/var/local`  | Variable data for `/usr/local` apps |
| `/var/lock`   | Lock files (to avoid concurrent use) |
| `/var/log`    | Log files (e.g., boot logs, system logs) |
| `/var/run`    | Information about running processes |
| `/var/tmp`    | Temporary files that survive reboots |

---

### Shareable vs Unshareable Directories

| Property     | Static (doesn’t change) | Variable (changes often) |
|--------------|--------------------------|----------------------------|
| **Sharable**   | `/usr`, `/opt`             | `/var/mail`                 |
| **Unsharable** | `/etc`, `/boot`            | `/var/run`, `/var/lock`     |

---

## 🧮 Simple Commands in Linux – Part 1

#### 1. 🕓 `date` – Displays the current system date and time.

**eg#:**
```yaml
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/madras/SC/week1$ date
Sat May 18 10:45:32 IST 2025
```

#### 2. 📅 `cal` – Displays a simple calendar of the current or specified month/year.
**eg#:**
```yaml
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/madras/SC/week1$ cal
      May 2025
Su Mo Tu We Th Fr Sa
             1  2  3
 4  5  6  7  8  9 10
11 12 13 14 15 16 17
18 19 20 21 22 23 24
25 26 27 28 29 30 31
```

> for specific month:
``alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/madras/SC/week1$ cal 12 2023``

#### 3. 🧠 `free` – Shows system memory usage, including RAM and swap space.
![image](https://github.com/user-attachments/assets/a8e13900-47cd-460f-b524-d1ddf8e6f198)

#### 4. 👥 `groups` – Lists the groups the current user belongs to.
![image](https://github.com/user-attachments/assets/0bfab86f-883f-4af3-97ad-17b946bc682f)

#### 5. 🗃️ `file` – Displays the type of a file, not just by extension, but by checking actual content.
![image](https://github.com/user-attachments/assets/970eba2e-f4f7-4e13-8c25-332c854f719f)

#### 6. 🧱 `File Types & Permissions` - When you run:
![image](https://github.com/user-attachments/assets/120e18f5-b4e8-4c27-8027-82e4a21c4256)

- Breakdown of Fields:
| Field          | Meaning                            |
| -------------- | ---------------------------------- |
| `d`            | File type (`d` = directory)        |
| `drwxrwxrwx`    | Permissions (owner, group, others) |
| `1`            | Number of hard links               |
| `alok`         | Owner                              |
| `alok`         | Group                              |
| `4096`         | File size in bytes                 |
| `May 16 22:40` | Last modified date/time            |
| `level1`       | File or folder name                |








