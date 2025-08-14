# Week 6

Lect-1
## Some Command line utilities
1. `find`
2. file packaging tools(`tar`, `gzip`, etc...)
3. `make`


### Find - _Locating Files and Processing Them_
- Purpose: Search for files or directories based on conditions, and optionally perform actions on them.
<img width="976" height="384" alt="image" src="https://github.com/user-attachments/assets/18c9607f-22de-4a81-a512-0a77d6e31f54" />


#### Syntax:
```bash
	find [pathnames] [conditions]
```

#### Common Options:
* `-name pattern` → match filename (supports wildcards, e.g., `*.txt`)
* `-type [f/d/l]` → filter by file type
  * `f` = regular file
  * `d` = directory
  * `l` = symbolic link
* `-atime +n / -n` → last accessed more than n days ago (`+n`) or less than n days ago (`-n`)
* `-ctime +n / -n` → last *metadata change* (permissions, owner, etc.)
* `-regex` → match filenames with regex (use `-regextype` to set regex flavor)
* `-exec` → run another command on each file (`{}` is placeholder for filename)
* `-print` → print full file path (default action if not specified)

**eg#01:**
```bash
find . -name "*.txt" -type f
```
Finds all `.txt` files in current directory.
<img width="1659" height="252" alt="image" src="https://github.com/user-attachments/assets/173dc2b6-e680-43ca-a034-03a348df0997" />

## 2. File Packaging Commands (`tar`, `gzip`, etc.)
These are used for:
* `Combining` files into one archive
* `Compressing` them to save space

**`tar` – Create Archive**
```bash
tar -cvf archive.tar file1 file2 dir/
```
* `c` = create
* `v` = verbose (show files)
* `f` = file name for archive

**`gzip` – Compress**
```bash
gzip archive.tar
```
* Creates `archive.tar.gz`

**Extracting:**
```bash
tar -xvf archive.tar
tar -xvzf archive.tar.gz
```

* `x` = extract
* `z` = decompress gzip while extracting

**Other compressors:**

* `bzip2`, `xz`, `7z` – different algorithms for better compression ratios.


## **3. `make` – Automating Conditional Actions**

* `make` is usually for compiling programs, but it can automate **any repetitive tasks**.
* Uses a `Makefile` (or `make.file`).

**Example Makefile:**

```makefile
# Variables
TMP_FILES = *.o *.aux

.PHONY : clean

# A target
build:
	echo "Building project..."
	touch output.txt

# Clean target
clean:
	rm -f $(TMP_FILES)
```

**Run:**

```bash
make build
make clean
```

* `.PHONY` means it's not a real file, just a command label.
* `make` only runs a target if its prerequisites have changed (saves time).

---

## **💻 Practice Setup**

Let’s make a **practice folder** with dummy files so you can try each command.

**Step 1: Create Practice Folder & Files**

```bash
mkdir ~/practice_cmds
cd ~/practice_cmds

# Create files
touch report1.txt report2.txt image1.png script.sh notes.md
mkdir data backups
touch data/data1.csv data/data2.csv
```

**Step 2: Try `find` Commands**

```bash
# Find all txt files
find . -name "*.txt"

# Find only in 'data' folder
find data -type f

# Find files accessed in last 2 days
find . -atime -2

# Find and delete all CSV files (careful!)
find . -name "*.csv" -exec rm {} \;
```

**Step 3: Try Packaging**

```bash
# Create tar archive of data folder
tar -cvf data_backup.tar data/

# Compress it
gzip data_backup.tar

# Extract it
tar -xvzf data_backup.tar.gz
```

**Step 4: Try `make`**

```bash
nano Makefile
```

Paste:

```makefile
TMP_FILES = *.bak

.PHONY: backup clean

backup:
	tar -cvf backups/mybackup.tar data/

clean:
	rm -f $(TMP_FILES)
```

Run:

```bash
make backup
make clean
```






