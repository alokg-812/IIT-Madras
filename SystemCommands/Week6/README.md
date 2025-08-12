# Week 6

Lect-1
## Some Command line utilities
1. `find`
2. file packaging tools(`tar`, `gzip`, etc...)
3. `make`


### Find - _Locating and Processing Files_
- Purpose: Search for files or directories based on conditions, and optionally perform actions on them.
<img width="976" height="384" alt="image" src="https://github.com/user-attachments/assets/18c9607f-22de-4a81-a512-0a77d6e31f54" />


* `-name <pattern>` → Match filenames
  Example: `-name "*.txt"`
* `-type <code>` → Filter by file type
  (`f` = regular file, `d` = directory, `l` = symlink, etc.)
* `-atime +/-n` → Access time filter (days)
  `+n` = more than n days ago, `-n` = less than n days ago
* `-ctime` → Change time filter (file metadata changed)
* `-regex` → Match with regex patterns
* `-regextype` → Set regex type (`posix-basic`, `posix-egrep`)
* `-exec <command> {} \;` → Run a command on each found file
* `-print` → Output the matching file paths (default in many cases)

**Practice Commands:**

```bash
# Find all .txt files in your home directory
find ~ -name "*.txt"

# Find all directories modified in the last 2 days
find . -type d -mtime -2

# Find and delete all .log files older than 7 days
find /var/log -name "*.log" -type f -mtime +7 -exec rm {} \;

# Find files matching regex pattern (example: start with "data" and end with a number)
find . -regextype posix-egrep -regex ".*/data[0-9]+$"
```

## **2. File Packaging — `tar`, `gzip`, etc.**

**Purpose:** Bundle files into one archive and optionally compress them.

**Key tools:**

* `tar` → Collect files into a single archive (`.tar`)
* `gzip`, `bzip2`, `xz`, `7z` → Compress files
* Combined: `.tar.gz` or `.tgz` = tarball + gzip compression

**Practice Commands:**

```bash
# Create a tar archive of 'project' folder
tar -cvf project.tar project/

# Extract the tar archive
tar -xvf project.tar

# Create and compress with gzip
tar -czvf project.tar.gz project/

# Extract a gzipped tarball
tar -xzvf project.tar.gz

# Check the contents of a tar file without extracting
tar -tvf project.tar
```


## **3. `make` — Automating Tasks**

**Purpose:** Automate builds or repetitive tasks by checking file dependencies.

**Key points from PDF:**

* `make -f make.file` → Run using a specific makefile
* **Variables** → Store reusable values
* **.PHONY** → Targets that aren’t files
* **target : prerequisites** → Run recipe if prerequisites are newer
* `recipe` → Command(s) to execute
* Common example: `clean` target to remove temporary files

**Example Makefile:**

```make
# Variables
TMP_FILES = *.o *.aux

# This target is always run
.PHONY : clean

# Default target
all: main.o utils.o
	gcc -o myprogram main.o utils.o

# Clean target
clean:
	rm -f $(TMP_FILES) myprogram
```

**Practice Commands:**

```bash
# Run make with default 'Makefile'
make

# Run a specific target
make clean

# Use a custom makefile
make -f custom.makefile
```
























