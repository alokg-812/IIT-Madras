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


#### The ed Editor: Basics and Commands
- The `ed editor` is a basic line editor found on all Unix-like systems.
- It operates without a visual interface, using commands to navigate and edit files. Addresses (like line numbers, '.', '$', '%', '+', '-') specify which lines commands act upon. Common commands include printing, editing, saving, and quitting.
