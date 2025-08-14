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

### File Packaging Commands (`tar`, `gzip`, etc.)
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
<img width="1909" height="305" alt="image" src="https://github.com/user-attachments/assets/bf284ecf-6979-49e9-bc52-95090b55fdef" />

**Other compressors:**

* `bzip2`, `xz`, `7z` – different algorithms for better compression ratios.


### `make` – Automating Conditional Actions

* `make` is usually for compiling programs, but it can automate **any repetitive tasks**.
* Uses a `Makefile` (or `make.file`).
**eg#:**
```makefile
# Variables
TMP_FILES = *.bak
.PHONY: backup clean
backup:
        tar -cvf backups/mybackup.tar data
clean:
        rm -f $(TEMP_FILES)
```

**Run:**

```bash
make build
make clean
```

* `.PHONY` means it's not a real file, just a command label.
* `make` only runs a target if its prerequisites have changed (saves time).
<img width="1683" height="517" alt="image" src="https://github.com/user-attachments/assets/13b5f136-cea9-41cc-816e-02d9f57a3a52" />


## Lec 2
*__Shell Scripts__* - creating our own commands.








