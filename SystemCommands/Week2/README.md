## Week 2:

### Cmmand Line Editor:
Command line editors allow users to edit text files directly on remote or supercomputers, which is useful when file transfers are slow or multiple network hops are required. Editing within the terminal is often more practical in such cases.

![image](https://github.com/user-attachments/assets/35df32e5-f3c4-4f4b-bf14-5feb44f53ffe)

| Category         | Examples                                     | Key Features                          |
| ---------------- | -------------------------------------------- | ------------------------------------- |
| Line Editors     | ed, ex                                       | Edit one line at a time, very minimal |
| Terminal Editors | pico, nano, vi, emacs                        | Terminal-based, more interactive      |
| GUI Editors      | kate, kwrite, gedit, sublime, atom, brackets | Easy to use with mouse and menus      |
| IDE              | eclipse, Bluefish, NetBeans                  | Full development environment          |

> We are gonna study more about `vi` and `nano` editor because they come bundled with every linux operating system.

**Features while choosing an editor:**
- Scrolling, view modes, current position in file
- Navigation (char, word, line, pattern)
- Insert, Replace, Delete
- Cut-Copy-Paste
- Search-Replace
- Language-aware syntax highlighting
- Key-maps, init scripts, macros
- Plugins
_Editors like vi and emacs support most of these features._


### The ed Editor: Basics and Commands
- The `ed editor` is a basic line editor found on all Unix-like systems.
- It operates without a visual interface, using commands to navigate and edit files. Addresses (like line numbers, '.', '$', '%', '+', '-') specify which lines commands act upon. Common commands include printing, editing, saving, and quitting.

### `ed` / `ex` Commands Reference

| Command | Description                           |
|---------|---------------------------------------|
| `f`     | Show name of file being edited        |
| `p`     | Print the current line                |
| `a`     | Append at the current line            |
| `c`     | Change the line                       |
| `d`     | Delete the current line               |
| `i`     | Insert line at the current position   |
| `j`     | Join lines                            |
| `s`     | Search for regex pattern              |
| `m`     | Move current line to position         |
| `u`     | Undo latest change                    |

**For more shortcuts:**
- [nano editor shortcuts](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week2/nanoEdShortcuts.md)

## vi editor
- considered to be the most `complex terminal` in linux world.
- **Reason:** It has acquired so many features in the recent times that it has some commands in even multiple pages.
- _If known this editor completely, no other editor needed to be learnt._
- `vim`, the latest vi editor is available in all the Operating Systems.
- 3 major modes of vi editor:
  - `command mode`
  - `insert mode`
  - `ex mode`

#### 1. **Command Mode**

* **Default mode when you open `vi`**.
* In this mode, **you can navigate, delete, copy, paste, and manipulate text**, but **cannot type text directly**.
* To **return to Command Mode** from any other mode, press:

  * ✅ `Esc` key

#### 2. **Insert Mode**

* In this mode, you can **type and insert text into the file**.
* You enter insert mode using any of the following keys:

  | Key | Function                               |
  | --- | -------------------------------------- |
  | `i` | Insert before the cursor               |
  | `I` | Insert at the beginning of the line    |
  | `a` | Append after the cursor                |
  | `A` | Append at the end of the line          |
  | `o` | Open a new line below the current line |
  | `O` | Open a new line above the current line |

#### 3. **Ex Mode (Colon Mode)**

* Used for **saving, quitting, and executing advanced commands**.
* You enter it by pressing:

  * ✅ `:` (colon)
* Once in this mode, you can type commands like:

  * `:w` to save
  * `:q` to quit
  * `:wq` to save and quit
  * `:x`, `:q!`, etc.

#### Table:

| Mode         | Enter With                   | Used For                             |
| ------------ | ---------------------------- | ------------------------------------ |
| Command Mode | `Esc`                        | Navigation, copy, delete, etc.       |
| Insert Mode  | `i`, `I`, `a`, `A`, `o`, `O` | Typing and editing text              |
| Ex Mode      | `:`                          | Saving, quitting, executing commands |









- 
