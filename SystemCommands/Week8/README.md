# Week 8 

## What “Automating scripts” means (the big picture)

* *Idea:* running our shell scripts `without typing them`, on a `schedule` (daily, weekly, on boot, etc.). 
* This is how backups, clean-ups, and reports run by themselves.

#### **Where this shows up in real life**
* Nightly database/file backups
* Log clean-ups (delete/zip old logs)
* Health checks & email/slack alerts
* Rotate or roll up data at fixed times

#### **Your mental model**

1. You write a normal script (same commands you’d run manually).
2. You tell the system *when/how often* to run it.
3. The system runs it in the background and (optionally) logs the output.&#x20;

**Practice (2 mins)**

```bash
# 1) Make a sample script
mkdir -p ~/wk8 && cd ~/wk8
cat > health.sh <<'SH'
#!/usr/bin/env bash
echo "=== $(date) ==="
echo "Disk:"
df -h /
echo "Memory:"
free -h
SH
chmod +x health.sh

# 2) Try it manually once
./health.sh
```

### The cron ecosystem & “drop-in” locations

`cron` is the long-running service that executes commands at scheduled times. We interact with it using: `at`, `crontab`, `anacron`, and `logrotate` (each solves a different need). It also watches special folders for hourly/daily/weekly/monthly jobs.

#### Tools we’ll hear about
* *crontab*: edit your personal schedule (per-user cron).
* *at*: run **once** at a specific time (“tomorrow 9:30”).
* *anacron*: like cron, but **catches up** missed daily/weekly jobs (great for laptops powered off at night).
* *logrotate*: not a scheduler; it **rotates/compresses** logs on a schedule, usually triggered by cron.&#x20;

### System locations cron scans automatically
* `/etc/crontab`, `/etc/cron.d/` (system crontab files)
* `/etc/cron.hourly/`, `/etc/cron.daily/`, `/etc/cron.weekly/`, `/etc/cron.monthly/` (drop a script here to run at that frequency)

**Best-practice tips (super important)**

* Use **full paths** in cron (e.g., `/usr/bin/python`, `/bin/tar`) because PATH is minimal.
* Add a shebang to scripts: `#!/usr/bin/env bash`
* Make scripts executable: `chmod +x script.sh`
* Redirect output so you can see what happened:

  ```bash
  ./myscript.sh >> /var/log/myscript.log 2>&1
  ```

**Practice**

```bash
# A) Run something once in the near future (with `at`)
echo "$HOME/wk8/health.sh >> $HOME/wk8/health.log 2>&1" | at now + 2 minutes

# B) Make a daily job by dropping into cron.daily (requires sudo)
#    (If you can't use sudo, skip to Page 3 to use per-user crontab.)
printf '%s\n' '#!/usr/bin/env bash' "$HOME/wk8/health.sh >> $HOME/wk8/health.log 2>&1" \
  | sudo tee /etc/cron.daily/wk8-health >/dev/null
sudo chmod +x /etc/cron.daily/wk8-health
```

(Your distro decides **exact times** daily/weekly/monthly jobs run.)&#x20;

### Cron job line format (how to read/write schedules)

A single cron “job line” has **time fields** + (sometimes) a **user** + the **command**. In system files like `/etc/crontab`, there *is* a `user` field; in your personal `crontab -e`, there is **no** `user` field.&#x20;

**Fields (left → right)**
`minute hour day_of_month month day_of_week [user] command`

* minute: `0–59`
* hour: `0–23`
* day of month: `1–31`
* month: `1–12` or `jan,feb,...`
* day of week: `0–6` or `sun,mon,...` (0 = Sunday)&#x20;

**Nice syntax you can use**

* `*` → “every value”
* `*/15` → every 15 units (e.g., minutes)
* `1-5` → a range (Mon–Fri for DOW)
* `1,15` → a list (1st and 15th of the month)

**Example from the slide (system crontab style)**

```
5 2 * * 1-5 root cd /home/scripts/backup && ./mkbackup.sh
# → Run at 02:05 every weekday, as user 'root'
```



**Per-user crontab (most common for you)**

```bash
crontab -e        # edit your user’s cron
crontab -l        # list
crontab -r        # remove (careful!)
```

**Your first reliable cron job (with logging)**

```bash
# Open your crontab
crontab -e

# Add this line (runs every 2 minutes; change path as needed)
*/2 * * * * /bin/bash -c '$HOME/wk8/health.sh >> $HOME/wk8/health.log 2>&1'
```

Check results after a few minutes:

```bash
tail -n 50 ~/wk8/health.log
```

**More patterns you’ll use often**

```cron
# Every day at 02:05
5 2 * * *  /path/to/backup.sh >> /path/to/backup.log 2>&1

# Every 15 minutes on weekdays
*/15 * * * 1-5  /path/to/report.sh >> /path/to/report.log 2>&1

# First day of each month at midnight
0 0 1 * *  /path/to/invoice-rollup.sh >> /path/to/invoice.log 2>&1
```

### Startup scripts & runlevels (legacy boot flow)

Older (SysV-style) systems use **init** with **runlevels**. At boot or when changing runlevel, the system runs scripts from specific directories:

* **Startup script dirs:** `/etc/init/`, `/etc/init.d/`
* **Runlevel dirs:** `/etc/rc0.d/` … `/etc/rc6.d/`

  * `0`: halt/power off
  * `1`: single-user (maintenance)
  * `2`: multi-user, no GUI, no networking (varies by distro)
  * `3`: multi-user with networking (no GUI)
  * `4`: special/custom
  * `5`: multi-user with GUI
  * `6`: reboot&#x20;

**How to think about it**

* The system decides *which* runlevel to enter; the corresponding `/etc/rcX.d/` folder contains symlinks that start/stop services in order.
* Modern Linux often uses **systemd** (units/timers) instead; but these directories still help you understand how boot automation historically worked. (Use `@reboot` in cron or systemd user timers if you don’t have root.)

**Zero-risk “run on startup” (user space)**

```bash
# Add this to your user crontab to run once at boot:
@reboot /bin/bash -c '$HOME/wk8/health.sh >> $HOME/wk8/boot.log 2>&1'
```

This mimics a “startup script” without touching system folders.

**Observe current runlevel (if available)**

```bash
who -r     # shows current runlevel on SysV-like systems
```

(If your distro is purely systemd, prefer `systemctl`/`journalctl` tools.)

## Quick checklist for reliable automation

* [ ] Script has `#!/usr/bin/env bash` and `chmod +x`
* [ ] Uses **absolute paths** to commands/files
* [ ] Redirects output to a **log file** (`>> file 2>&1`)
* [ ] Tested manually once before scheduling
* [ ] Scheduled via **crontab** (or `at`/`anacron` as needed)

## Lecture 2 sed

### Introduction
* **What is sed?**
  * `sed` stands for `Stream EDitor`.
  * It is a __*programming language*__ designed specifically for **processing and transforming text streams**.
  * It is part of the **POSIX standard** (so it’s available on almost all UNIX/Linux systems).
  * sed was developed **before awk**, so it’s one of the earliest text-processing utilities.

👉 Think of sed as a **robotic editor** that edits text line by line **without opening it in an editor** (like `vim` or `nano`). Instead, it works on a **stream of text** (file content, stdin, or piped input).

### Execution Model

* **Input stream**: Sed processes text **line by line**.
* **Each line** is treated as a **sequence of characters**.
* sed maintains **two main buffers**:
  1. **Pattern Space** → The active buffer that holds the current line being processed.
  2. **Hold Space** → An auxiliary buffer for temporary storage (used for advanced operations).
* **Execution cycle**:

  1. sed reads a line from input into the **pattern space**.
  2. It applies the **sed script/commands** sequentially.
  3. After processing, it outputs (unless suppressed with `-n`) and clears the pattern space.
  4. Moves to the **next line** and repeats until the file/stream ends.

👉 This makes sed **non-interactive** (unlike editors like `vi`), it just streams through text and edits as per instructions.

### Usage
There are two ways to run sed:

1. **Single line command (inline execution)**

   ```bash
   sed -e 's/hello/world/g' input.txt
   ```

   * `s/hello/world/g` → Substitute `hello` with `world` **globally** on every line.
   * `-e` allows writing the script inline.
   * `input.txt` is the file being processed.

2. **Script file execution**
   We can put multiple sed commands in a file and run them:

   ```bash
   sed -f ./myscript.sed input.txt
   ```

   Example `myscript.sed`:

   ```sed
   2,8s/hello/world/g
   ```
   * This replaces `hello` with `world` only between lines 2 and 8.
   * Also, we can make the script itself executable:

   ```bash
   #!/usr/bin/sed -f
   2,8s/hello/world/g
   ```

### Sed Statements

Sed commands follow this structure:

```
[address] action
```

* **Address** → specifies where the command applies (line number, regex, or range).
* **Action** → what sed should do (substitute, delete, print, etc.).

Examples:

* `5p` → print only line 5.
* `/error/d` → delete lines containing `error`.
* `2,8s/hello/world/g` → substitute `hello` with `world` between lines 2–8.

👉 Similar to the old `ed`/`ex` line editors.

### Grouping Commands
* Multiple commands can be grouped using `{}`:
  ```bash
  sed '/start/,/end/ { s/foo/bar/g; p }' input.txt
  ```

  * Between lines matching "start" and "end":
    * Replace `foo` with `bar`
    * Print those lines.

### Addressing (Targeting Lines)

You can specify **which lines** sed should act on:

* By **line numbers**:

  * `5` → line 5
  * `1,10` → lines 1 to 10
  * `$` → last line
  * `1~3` → every 3rd line starting from line 1
  * `%` → the whole file

* By **patterns (regex)**:

  * `/error/` → lines containing `error`
  * `/start/,/end/` → from line matching "start" to "end"
  * `/error/, +4` → line with "error" and next 4 lines

👉 This gives us **fine-grained control**.

### Actions (Editing Commands)

Here are the most commonly used actions:

| key       | action                    |
| --------- | ------------------------- |
| `p`       | print the pattern space   |
| `d`       | delete the pattern space  |
| `s`       | substitute using regex pattern (_s/pattern/replacement/g_) |
| `=`       | print current input line number, **\n** |
| `#`       | comment                   |
| `i`       | insert above current line |
| `a`       | append below current line |
| `c`       | change current line       |


  ```bash
  sed -n '5p' file.txt   # print line 5 only
  sed '2d' file.txt      # delete line 2
  sed 's/foo/bar/g' file.txt   # replace foo with bar globally
  sed '=' file.txt
  sed '3iThis is inserted above line 3' file.txt
  sed '3aThis is appended below line 3' file.txt
  sed '3cThis replaces line 3 completely' file.txt
  ```

### Programming Features

sed also supports **control flow**:

| key label   | control flow                             |
| ----------- | ---------------------------------------- |
| **b label** | Branch unconditionally to `:label`       |
| **:label**  | Marks a location for branch              |
| **N**       | Append next line to pattern space        |
| **q**       | Quit sed (stop processing further lines) |
| **t label** | Branch if substitution succeeded         |
| **T label** | Branch if substitution failed            |
| **w filename** | Write output to a file                |
| **x**       | Exchange contents of pattern space and hold space |

👉 These make sed **programmable** (like loops/conditions).

### Bash + Sed

* Sed can be used **inside shell scripts**.
* Commonly used with:

  * **heredoc** (`<<EOF`) for multi-line input.
  * **pipes** to chain with other commands.

    ```bash
    cat file.txt | sed 's/foo/bar/g'
    ```

### Why sed?
* Available **everywhere** (part of POSIX).
* **Fast** – processes text streams quickly.
* Excellent for **pre-processing data** before further analysis with tools like `awk`, `grep`, or programming scripts.

