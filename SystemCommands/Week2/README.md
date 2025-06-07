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


## emacs editor
- The `Emacs` is faster, powerful, and simple in terms of usage because of its simple user interface.
- Unlike the vi editor, the Emacs editor does `not` use an insert mode, and it is by default in editing mode
   - i.e., whatever you type will directly be written to the buffer, unless you manually enter command mode by using keyboard shortcuts

![image](https://github.com/user-attachments/assets/352cbb72-d598-4d85-81cf-24b16d8c8abf)

## Networks and IP Addressing

**Public vs Private Networks**

* A `public network` is accessible from the internet (like your college website).
* A `private network` is restricted to local machines (like your Wi-Fi at home).

### IP Address Ranges:

These IP ranges are `reserved` for private use:

| Class | CIDR Notation  | Range Example | Use Case                      |
| ----- | -------------- | ------------- | ----------------------------- |
| A     | 10.0.0.0/8     | 10.23.45.67   | Large companies' internal LAN |
| B     | 172.16.0.0/12  | 172.20.5.1    | Medium-sized orgs             |
| C     | 192.168.0.0/16 | 192.168.1.10  | Home/Office Wi-Fi             |
| —     | 127.0.0.0/8    | 127.0.0.1     | Loopback / localhost          |

* `Loopback address (127.0.0.1)` refers to your own machine. Try `ping 127.0.0.1` — it will always respond if your network is working properly.

### Gateways and Routing

Imagine three private networks in a university:

* CS Lab (Private Net #1)
* Mechanical Lab (Private Net #2)
* Admin Department (Private Net #3)

- To send a file from CS Lab to Admin Department, data has to `hop` through **gateways** (devices that connect networks):

```
CS Lab --> Gateway 1-2 --> Gateway 2-3 --> Admin Dept
```
- This process is called `routing`. A router/gateway decides the best path to reach the destination.


## 3. IP and Port Numbers

**Ques:** What is a Port?

An IP address alone only points to a device. A **port number** tells which service on that device you want to communicate with.

#### Example:

You want to visit a website hosted on IP `123.45.67.89`.

* Browser requests go to:
  `123.45.67.89:80` → for HTTP
  `123.45.67.89:443` → for HTTPS

So:

* `IP Address` = House Address
* `Port Number` = Room Number

### 💡 Important Port-Protocol Pairs:

| Port | Protocol | Service                    |
| ---- | -------- | -------------------------- |
| 22   | TCP      | SSH – Remote login         |
| 21   | TCP      | FTP – File transfers       |
| 25   | TCP      | SMTP – Send Emails         |
| 80   | TCP      | HTTP – Websites (unsecure) |
| 443  | TCP      | HTTPS – Websites (secure)  |
| 3306 | TCP      | MySQL – Database server    |


## How to Access Remote Systems

### Tools for Remote Access:

| Method             | Example Tools             | Notes                                 |
| ------------------ | ------------------------- | ------------------------------------- |
| VPN                | OpenVPN, WireGuard        | Extends your private network remotely |
| SSH                | `ssh user@IP`             | CLI remote control                    |
| Remote Desktop     | RDP, X2Go, PCoIP          | GUI-based remote access               |
| Web-based Desktops | Apache Guacamole          | Access desktops through browser       |
| Commercial Tools   | TeamViewer, AnyDesk, Zoho | Cross-platform GUI remote access      |


## Firewall – The Security Guard

A `firewall` is like a bouncer who decides which connections are allowed.

### Firewall Checks:

* Is this `port` allowed?
* Is this `IP address` allowed?
* Is the `protocol (TCP/UDP)` safe?

Your firewall must *allow port 22* if you want to connect via SSH to your machine from outside.

## Server Protection Layers

To protect a public server, **multiple layers of security** are used:

1. `Web Application Filter` – Blocks SQL injection, XSS attacks, etc.
2. `Network Firewall` – Prevents unauthorized network access.
3. `SELinux` – Internal OS-level control (explained next).


## SELinux (Security-Enhanced Linux)

A powerful Linux security module.

### What it Does:

* Prevents unauthorized access by services like Apache or MySQL.
* Enforces **least privilege**: processes get **only** what they need.

#### Components:

* **RBAC (Role-Based Access Control)**:
  * `user:role:type:level`
  * Example: `unconfined_u:object_r:user_home_t:s0`

* **Modes**:
  * `disabled` – turned off
  * `permissive` – logs only
  * `enforcing` – actively blocks

#### Useful Commands:

* `ls -lZ file.txt` → show security context of a file.
* `ps -eZ` → view running processes' contexts.
* `semanage port -l` → list SELinux port rules.

**Recommended** for any internet-facing Linux server!

## Networking Tools Explained

| Tool         | Purpose                                  | Example                 |
| ------------ | ---------------------------------------- | ----------------------- |
| `ping`       | Is machine alive?                        | `ping google.com`       |
| `traceroute` | Show path to a host                      | `traceroute iitm.ac.in` |
| `nslookup`   | Resolve DNS to IP                        | `nslookup iitm.ac.in`   |
| `dig`        | Advanced DNS info                        | `dig +short iitm.ac.in` |
| `netstat`    | Show current network connections         | `netstat -tulnp`        |
| `whois`      | Get domain ownership                     | `whois example.com`     |
| `nmap`       | Scan open ports (🔴use responsibly!)     | `nmap 192.168.0.1`      |
| `wireshark`  | Deep packet analysis (use in lab setups) | GUI tool                |
| `mxtoolbox`  | Online tool to check domain mail configs | visit mxtoolbox.com     |

## High Performance Computing (HPC)

When working with `supercomputers or clusters`, like the ones used in IITs or research centers:

### Basics:

* You don’t run programs directly—you submit them as **jobs**.
* Jobs are queued and managed by **schedulers** (like SLURM, PBS).
* Data processing is **done remotely** (don’t download 50GB raw data!).
* Most access is via **SSH**, so comfort with Linux command line is essential.

