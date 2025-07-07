# Week 3

## Lecture 1 Combining commands & files

## 1. Networks and IP Addressing

### Public vs Private Networks

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

🔁 **Loopback address (`127.0.0.1`)** refers to your own machine. Try `ping 127.0.0.1` — it will always respond if your network is working properly.


## 2. Gateways and Routing

Imagine three private networks in a university:

* CS Lab (Private Net #1)
* Mechanical Lab (Private Net #2)
* Admin Department (Private Net #3)

To send a file from CS Lab to Admin Department, data has to "hop" through **gateways** (devices that connect networks):

```
CS Lab --> Gateway 1-2 --> Gateway 2-3 --> Admin Dept
```

This process is called `routing`. A router/gateway decides the best path to reach the destination.


## 3. IP and Port Numbers

### What is a Port?

An IP address alone only points to a device. A `port number` tells which service on that device you want to communicate with.

#### Example:

You want to visit a website hosted on IP `123.45.67.89`.

* Browser requests go to:
  **123.45.67.89:80** → for HTTP
  **123.45.67.89:443** → for HTTPS

So:

* `IP Address` = House Address
* `Port Number` = Room Number

### Important Port-Protocol Pairs:

| Port | Protocol | Service                    |
| ---- | -------- | -------------------------- |
| 22   | TCP      | SSH – Remote login         |
| 21   | TCP      | FTP – File transfers       |
| 25   | TCP      | SMTP – Send Emails         |
| 80   | TCP      | HTTP – Websites (unsecure) |
| 443  | TCP      | HTTPS – Websites (secure)  |
| 3306 | TCP      | MySQL – Database server    |


## 4. How to Access Remote Systems

### Tools for Remote Access:

| Method             | Example Tools             | Notes                                 |
| ------------------ | ------------------------- | ------------------------------------- |
| VPN                | OpenVPN, WireGuard        | Extends your private network remotely |
| SSH                | `ssh user@IP`             | CLI remote control                    |
| Remote Desktop     | RDP, X2Go, PCoIP          | GUI-based remote access               |
| Web-based Desktops | Apache Guacamole          | Access desktops through browser       |
| Commercial Tools   | TeamViewer, AnyDesk, Zoho | Cross-platform GUI remote access      |


## 5. Firewall – The Security Guard

A `firewall` is like a bouncer who decides which connections are allowed.

### Firewall Checks:

* Is this *port* allowed?
* Is this *IP address* allowed?
* Is the *protocol (TCP/UDP)* safe?

Your firewall must **allow port 22** if you want to connect via SSH to your machine from outside.


## 6. Server Protection Layers
To protect a public server, `multiple layers of security` are used:

1. `Web Application Filter` – Blocks SQL injection, XSS attacks, etc.
2. `Network Firewall` – Prevents unauthorized network access.
3. `SELinux` – Internal OS-level control (explained next).


## 7. SELinux (Security-Enhanced Linux)

A powerful Linux security module.

### What it Does:

* Prevents unauthorized access by services like Apache or MySQL.
* Enforces `least privilege`: processes get **only** what they need.

#### Components:

* **RBAC (Role-Based Access Control)**:
  * `user:role:type:level`
  * Example: `unconfined_u:object_r:user_home_t:s0`

* **Modes**:
  * `disabled` – turned off
  * `permissive` – logs only
  * `enforcing` – actively blocks

#### Useful Commands:

* `ls -lZ file.txt` → shows security context of a file.
* `ps -eZ` → view running processes' contexts.
* `semanage port -l` → list SELinux port rules.

**Recommended** for any internet-facing Linux server!

## 8. Networking Tools Explained

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


## 9. High Performance Computing (HPC)
When working with **supercomputers or clusters**, like the ones used in IITs or research centers:

### Basics:
* You don’t run programs directly—you submit them as **jobs**.
* Jobs are queued and managed by **schedulers** (like SLURM, PBS).
* Data processing is **done remotely** (don’t download 50GB raw data!).
* Most access is via **SSH**, so comfort with Linux command line is essential.

💡 Check [www.top500.org](https://www.top500.org) to see the world’s fastest computers.


## Part 2: Software Management
### 1.Using package management systems:

#### 👉 Need for a package manager:
* Tools for installing, updating, removing, managing software
* Install new / updated software across network
* Package – File look up, both ways
* Database of packages on the system including versions
* Dependency checking
* Signature verification tools
* Tools for building packages

### Package types:

![image](https://github.com/user-attachments/assets/c38b1ef5-3065-4f34-8c14-e80ed6e44eec)

* `Ubuntu` is the most widely used system and hence `debian` is the most known package.
* ```bash
    alok@AlokThinkbook:/mnt/c/Users/alokg/desktop/iit-madras/systemcommands/week3/practice$ lsb_release -a
    No LSB modules are available.
    Distributor ID: Ubuntu
    Description:    Ubuntu 22.04.5 LTS
    Release:        22.04
    Codename:       jammy 
  ```
* This system also have the `Ubuntu System`.
<br>

### Architectures
* Linux OPerating System is available for a wide variety of architectures:
![image](https://github.com/user-attachments/assets/87a9173c-0f51-48c6-a8ae-f07aba15019a)

* This system has `x86_64` type of architecture:
```bash
alok@AlokThinkbook:/mnt/c/Users/alokg/desktop/iit-madras/systemcommands/week3/practice$ uname -a
Linux AlokThinkbook 5.15.167.4-microsoft-standard-WSL2 #1 SMP Tue Nov 5 00:21:55 UTC 2024 x86_64 x86_64 x86_64 GNU/Linux
```
 

### Tools
![image](https://github.com/user-attachments/assets/3c573f0d-2c72-4a03-b28f-37fe0f3f2ddd)


#### Package Management using Linux|Ubuntu using apt

![image](https://github.com/user-attachments/assets/c4f87c02-229e-48c6-a409-f01d669011be)

```
  alok@AlokThinkbook:/mnt/c/Users/alokg/desktop/iit-madras/systemcommands/week3/practice$ apt-cache show nmap
  Filename: pool/universe/n/nmap/nmap_7.91+dfsg1+really7.80+dfsg1-2build1_amd64.deb
```
* In this filename in the above line, its shown:
  * type - `DEB`
  * package - `nmap`
  * version - `7.91+dfsg1+really7.80+dfsg1`
  * revision - `2build1`
  * architecture - `amd64`

#### Package priorities(Level wise)
● Each package also lists what kind of priorities it has: <br>
● `required` : essential to proper functioning of the system (_topmost priority_)<br>
● `important` : provides functionality that enables the system to run well <br>
● `standard` : included in a standard system installation <br>
● `optional(skip is not needed)` : can omit if you do not have enough storage <br>
● `extra` : could conflict with packages with higher priority, has specialized requirements, install only if needed <br>

**eg01#:**
```
  alok@AlokThinkbook:/mnt/c/Users/alokg/desktop/iit-madras/systemcommands/week3/practice$ apt-cache show nmap
  Priority: extra
```
**eg02#:**
```
  alok@AlokThinkbook:/mnt/c/Users/alokg/desktop/iit-madras/systemcommands/week3/practice$ apt-cache show wget
  Priority: standard
```
**eg03#:**
```
  alok@AlokThinkbook:/mnt/c/Users/alokg/desktop/iit-madras/systemcommands/week3/practice$ apt-cache show fortunes
  Priority: optional
```

### Package Authentication
**Ques:** How do we know that the package we are installing in our OS is Authentic? <br>
**Ans:** The way to verify is looking at `Checksums`.
* Checksums:
  * `md5sum`: 128 bits.
  * `sha1`: 160 bits.
  * `sha256`: 256 bits.
* _eg#01:_
* Even a minor change in a file can change the whole 128/160/256 of bits unit.
![image](https://github.com/user-attachments/assets/9ae90edc-22f1-4c9f-b9ec-fbbc7e02772b)

---

## Software Management 2

- Only `administrators(sudoers)` can install| upgrade| remove packages
- To install all the software/ software updates in the system, one must be a sudoer.
- `sudo cat /etc/sudoers` is used to get the privilages provided to users.
*Ex:*

![image](https://github.com/user-attachments/assets/6d8aadbe-f97f-43cc-9c17-6db476d97331)
![image](https://github.com/user-attachments/assets/51941567-88bd-41d5-a871-98001f96d0d3)

