# Week 4 starting

## 1. Regex 
- `regex(regular expression)` is a **pattern template** to search, filter, or match text.
- It is used in:
  * Programming Languages: *Java, Python, Perl, Ruby*
  * Tools: *grep, sed, awk*
  * Databases: *MySQL, PostgreSQL*

- Two types of regex engines(as per POSIX):
 
| Type | Name                         | Used With          |
| ---- | ---------------------------- | ------------------ |
| BRE  | Basic Regular Expressions    | `grep` (default)   |
| ERE  | Extended Regular Expressions | `egrep`, `grep -E` |

- `POSIX`, which stands for `Portable Operating System Interface`, is a family of standards specified by the IEEE that define a common interface for operating systems.


## 2. grep Command Syntax

- Syntax: ``grep 'pattern' filename``
```bash
grep 'pattern' filename
```
- **By Default-** `BRE` engine is used
- To use ERE engine, use:
```bash
grep -E 'pattern' filename   # ERE version
        <or>
egrep 'pattern' filename     # same as grep -E
```

```bash
command | grep 'pattern'     # search output of a command
```

## 3. Special Characters (Common in BRE & ERE)

| Symbol | Meaning                                                   |
| ------ | --------------------------------------------------------- |
| `.`    | Any single character (except newline)                     |
| `*`    | Zero or more of the preceding character/expression        |
| `[]`   | Match any one of the enclosed characters (e.g. `[aeiou]`) |
| `[^]`  | Match any character NOT enclosed                          |
| `^`    | Anchor: Start of line                                     |
| `$`    | Anchor: End of line                                       |
| `\`    | Escape special characters                                 |

**Examples:**

```bash
grep '^hello' file.txt     # lines starting with 'hello'
grep 'end$' file.txt       # lines ending with 'end'
grep 'gr.y' file.txt       # matches 'gray', 'grey'
grep '[aeiou]' file.txt    # lines with at least one vowel
```

---

## 🔍 5. Special Characters in BRE

| Syntax       | Meaning                                                   |
| ------------ | --------------------------------------------------------- |
| `\{n,m\}`    | Match previous element at least `n` and at most `m` times |
| `\(` `\)`    | Group expressions                                         |
| `\1` to `\9` | Backreferences (refer to captured groups)                 |

### 🔁 Example of backreference:

```bash
grep '\(hello\).*\1' file.txt
# matches lines with two occurrences of 'hello'
```

---

## 🔀 6. Special Characters in ERE

| Syntax  | Meaning                                    |                           |
| ------- | ------------------------------------------ | ------------------------- |
| `{n,m}` | Match previous expression `n` to `m` times |                           |
| `()`    | Grouping                                   |                           |
| `+`     | One or more of previous item               |                           |
| `?`     | Zero or one of previous item               |                           |
| \`      | \`                                         | OR operator (alternation) |

### 🧪 ERE Examples:

```bash
egrep 'go(d|t)' file.txt     # matches 'god' or 'got'
egrep 'colou?r' file.txt     # matches 'color' or 'colour'
egrep '[0-9]{3,5}' file.txt  # matches numbers with 3 to 5 digits
```

---

## 🧱 7. Character Classes

POSIX character classes for `[]`:

| Class          | Meaning                    |
| -------------- | -------------------------- |
| `[[:alnum:]]`  | Alphanumeric               |
| `[[:alpha:]]`  | Alphabetic                 |
| `[[:digit:]]`  | Digits                     |
| `[[:lower:]]`  | Lowercase letters          |
| `[[:upper:]]`  | Uppercase letters          |
| `[[:space:]]`  | Whitespace                 |
| `[[:punct:]]`  | Punctuation                |
| `[[:print:]]`  | Printable characters       |
| `[[:xdigit:]]` | Hex digits (0-9, a-f, A-F) |
| `[[:cntrl:]]`  | Control characters         |

**Example:**

```bash
grep '[[:digit:]]' file.txt     # lines with digits
grep '[[:upper:]][[:lower:]]+' file.txt  # lines starting with a capital letter followed by lowercase
```

---

## 🔁 8. Backreferences

Used to **match repeated patterns** using captured groups.

* `\1`, `\2`, ..., `\9`: match the **same text** matched by earlier groups.

**Example:**

```bash
grep '\([a-z]\)\1' file.txt
# Matches repeated lowercase letters like 'tt', 'ee'
```

---

## 🧮 9. Operator Precedence (Order of Execution)

### ➤ BRE Precedence (Highest to Lowest)

1. Character classes: `[::]`
2. Escaped metacharacters: `\`
3. Bracket expressions: `[ ]`
4. Grouping: `\( \)` and backreferences: `\1` to `\9`
5. Repetition: `*`, `\{ \}`
6. Concatenation
7. Anchors: `^`, `$`

### ➤ ERE Precedence

1. Character classes: `[::]`
2. Escaped metacharacters: `\`
3. Bracket expressions: `[ ]`
4. Grouping: `( )`
5. Repetition: `*`, `+`, `?`, `{ }`
6. Concatenation
7. Anchors: `^`, `$`
8. Alternation: `|`

---

## 🧰 Real-world Use Case

Suppose a file `students.csv` contains names and marks:

```
Alok,95
Akanksha,89
Raj,91
Raja,82
raj,77
```

### Example Queries:

```bash
grep '\b[Rr]aj\b' students.csv
# matches only 'Raj' and 'raj' as full words, not 'Raja'

egrep '^[A-Z][a-z]+,[9][0-9]$' students.csv
# matches names starting with capital, followed by lowercase, and marks in 90s
```

