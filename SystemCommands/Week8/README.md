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

---

## Quick checklist for reliable automation

* [ ] Script has `#!/usr/bin/env bash` and `chmod +x`
* [ ] Uses **absolute paths** to commands/files
* [ ] Redirects output to a **log file** (`>> file 2>&1`)
* [ ] Tested manually once before scheduling
* [ ] Scheduled via **crontab** (or `at`/`anacron` as needed)

> All slide content summarized from *Automating Scripts (Week 8, Part 1)*.&#x20;


