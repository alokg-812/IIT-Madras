# Week 1: Introduction to DataBase

# Week 2: Module Outline PPD

## Topics Covered:
- Attribute Types
- Relation Schema and Instance
- Keys
- Relational Query Languages
- Relational Operators
- Aggregate Operators
- Data Definition Language (DDL)
- Data Manipulation Language (DML)
- SQL Clauses: SELECT, FROM, WHERE
- Cartesian Product
- Rename AS Operation
- String Values
- ORDER BY
- SELECT TOP / FETCH
- WHERE Clause Predicate
- Duplicates
- Set Operations
- NULL Values
- GROUP BY
- HAVING
- SQL Clause Execution Order

---

## Attribute Types

Consider a **Students** relation with the following attributes:

| Attribute    | Data Type | Description |
|-------------|----------|-------------|
| Roll#       | Alphanumeric String | Unique Identifier |
| First Name, Last Name | Alphabetic String | Student's Name |
| DoB (Date of Birth) | Date | Birth Date |
| Passport#   | String (Letter followed by 7 digits) | Nullable (optional) |
| Aadhaar#    | 12-digit Number | Unique Identifier |
| Department  | Alphabetic String | Student's Department |

### Key Properties:
- The set of allowed values for each attribute is called its **domain**.
- Attribute values must be **atomic** (indivisible).
- The **null value** is a member of every domain and indicates an unknown value.

---

## Relation Schema and Instance

- A **relation schema** is represented as:
  
  \[ R = (A1, A2, ..., An) \]
  
  Example: 
  ```
  instructor = (ID, name, dept_name, salary)
  ```
  Here, `instructor` defines a **relational schema**.
- A **relation instance** is the current set of values stored in a relation, typically represented as a **table**.

---

## Keys

A **key** is an attribute or a set of attributes that uniquely identifies tuples in a relation.

### Example
| Key Type      | Example |
|--------------|---------|
| Super Key    | {Roll#}, {Roll#, Aadhaar#} |
| Candidate Key | {Roll#}, {Aadhaar#} |
| Primary Key  | {Roll#} |
| Foreign Key  | Roll# in `Enrolment` references `Students` |

---
### Super Key:
✅ Any column or combination of columns that can uniquely identify a row.

| Super Key Examples |
|--------------------|
| {Roll#} |
| {Roll#, Aadhaar#} |
| {Passport#, DoB} |

### Candidate Key:
✅ The minimal super key – a key with no unnecessary attributes.

| Candidate Key Examples |
|------------------------|
| {Roll#} |
| {Aadhaar#} |
| {Passport#} |

### Primary Key:
✅ The selected **candidate key** that uniquely identifies records.

| Primary Key Example |
|---------------------|
| {Roll#} |

### Surrogate Key:
✅ A system-generated key, often a unique number, used instead of natural keys.

| Surrogate Key Example |
|----------------------|
| StudentID (Database-generated) |

### Foreign Key:
✅ A column in one table that refers to the **primary key** of another table.

---

## Relational Query Language

### Procedural Query Language:
❌ Requires the programmer to **specify steps** to retrieve data.

- The programmer must know an appropriate **algorithm**.
- Example: Writing **procedural SQL** queries with explicit operations.

### Declarative Query Language:
❌ Requires the programmer to **describe the desired result**, not the steps to achieve it.

- Focuses on **what** relationships hold between entities.
- Example: SQL (**Structured Query Language**) is declarative.

---

## SQL Operators

### **Relational Operators**
- **Selection (σ)**: Filters rows based on conditions.
- **Projection (π)**: Selects specific columns.
- **Union (∪)**: Combines results of two queries.
- **Difference (-)**: Finds tuples in one table but not in another.
- **Cartesian Product (×)**: Returns all possible row combinations.
- **Join (⨝)**: Combines rows based on matching conditions.
![image](https://github.com/user-attachments/assets/a6df4099-72f7-4792-a30c-47fea0b4f3d3)

### **Aggregate Operators**
- `COUNT()`, `SUM()`, `AVG()`, `MIN()`, `MAX()`.


## SQL Clauses and Their Execution Order

SQL queries follow a specific execution order of clauses, ensuring logical flow in data retrieval. The priority order is:

1. **FROM** – Specifies the tables involved in the query.
2. **WHERE** – Filters records based on conditions.
3. **GROUP BY** – Groups records based on column values.
4. **HAVING** – Filters groups based on conditions.
5. **SELECT** – Specifies which columns to retrieve.
6. **ORDER BY** – Sorts the result set.
7. **LIMIT / FETCH** – Restricts the number of rows returned.

### **Example Dummy Table: Students**
| Roll# | Name     | Department | Age | Marks |
|-------|---------|------------|-----|-------|
| 101   | Alice   | CS         | 20  | 85    |
| 102   | Bob     | IT         | 21  | 78    |
| 103   | Charlie | CS         | 22  | 92    |
| 104   | Dave    | ECE        | 23  | 76    |

### **Detailed Explanation of SQL Clauses with Examples**

#### **1. FROM Clause**
Retrieves data from a specified table.
```sql
SELECT * FROM Students;
```

#### **2. WHERE Clause**
Filters data based on conditions.
```sql
SELECT * FROM Students WHERE Department = 'CS';
```

#### **3. GROUP BY Clause**
Groups records based on column values.
```sql
SELECT Department, AVG(Marks) FROM Students GROUP BY Department;
```

#### **4. HAVING Clause**
Filters groups after `GROUP BY` is applied.
```sql
SELECT Department, AVG(Marks) FROM Students GROUP BY Department HAVING AVG(Marks) > 80;
```

#### **5. SELECT Clause**
Chooses specific columns to be retrieved.
```sql
SELECT Name, Marks FROM Students;
```

#### **6. ORDER BY Clause**
Sorts the result set.
```sql
SELECT * FROM Students ORDER BY Marks DESC;
```

#### **7. LIMIT / FETCH Clause**
Restricts the number of rows returned.
```sql
SELECT * FROM Students ORDER BY Marks DESC LIMIT 3;
```
### **Other SQL Concepts(discussed below in detail)**
- **Renaming (AS)**: Assigns a new name to a column or table.
- **String Matching**: Uses `LIKE`, `%`, `_` for pattern matching.
- **Set Operations**: `UNION`, `INTERSECT`, `EXCEPT`.
- **Null Values**: `IS NULL`, `IS NOT NULL` for missing data.
- **Handling Duplicates**: `DISTINCT` keyword.
---

## String Operations

Pattern matching can be performed on strings using the `LIKE` operator. We describe patterns using two special characters:

- **Percent (`%`)**: Matches any substring.
- **Underscore (`_`)**: Matches any single character.

Patterns are **case-sensitive**, meaning uppercase and lowercase characters must match exactly.

### Examples:

- `'Intro%'` matches any string beginning with **"Intro"**.
- `'%Comp%'` matches any string containing **"Comp"** as a substring.
  - Example matches: `'Intro to Computer Science'`, `'Computational Biology'`.
- `'___'` matches any string of **exactly three characters**.
- `'__%'` matches any string of **at least three characters**.

## SQL Pattern Matching

SQL expresses patterns using the `LIKE` comparison operator. 

### Example Query:
Find the names of all departments whose building name includes the substring 'Watson':
```sql
SELECT dept_name FROM department WHERE building LIKE '%Watson%';
```

### Escape Characters in SQL
To include special pattern characters (`%` and `_`) as literals in patterns, SQL allows the specification of an **escape character** using the `ESCAPE` keyword.

### Examples:

- `LIKE 'ab\%cd%' ESCAPE '\'` matches all strings beginning with **"ab%cd"**.
- `LIKE 'ab\\cd%' ESCAPE '\'` matches all strings beginning with **"ab\cd"**.

## REGEXP_LIKE for Advanced Pattern Matching

The `REGEXP_LIKE` function allows for more advanced pattern matching using regular expressions.

### Syntax:
```sql
SELECT column_name FROM table_name WHERE REGEXP_LIKE(column_name, 'pattern', 'flags');
```
- **`pattern`**: The regular expression pattern to match.
- **`flags`** (optional): Controls case-sensitivity and multi-line matching.

### Example Queries:

1. Find all department names that start with 'Comp':
   ```sql
   SELECT dept_name FROM department WHERE REGEXP_LIKE(dept_name, '^Comp');
   ```
2. Find all course titles that contain either 'Data' or 'Machine':
   ```sql
   SELECT course_title FROM courses WHERE REGEXP_LIKE(course_title, 'Data|Machine');
   ```
3. Find instructor names that contain digits:
   ```sql
   SELECT name FROM instructor WHERE REGEXP_LIKE(name, '[0-9]');
   ```
4. Case-insensitive search for names containing 'science':
   ```sql
   SELECT dept_name FROM department WHERE REGEXP_LIKE(dept_name, 'science', 'i');
   ```

## IN Operator

- The `IN` operator allows you to specify multiple values in a `WHERE` clause.
- It is a shorthand for multiple `OR` conditions.

### Example Query:
Retrieve the names of instructors belonging to specific departments:
```sql
SELECT name FROM instructor WHERE dept_name IN ('Comp. Sci.', 'Biology');
```

## Set Operations

SQL supports set operations such as `UNION`, `INTERSECT`, and `EXCEPT` to combine query results.

### Examples:

- **Find courses that ran in Fall 2009 or in Spring 2010:**
  ```sql
  (SELECT course_id FROM section WHERE sem = 'Fall' AND year = 2009)
  UNION
  (SELECT course_id FROM section WHERE sem = 'Spring' AND year = 2010);
  ```
- **Find courses that ran in both Fall 2009 and Spring 2010:**
  ```sql
  (SELECT course_id FROM section WHERE sem = 'Fall' AND year = 2009)
  INTERSECT
  (SELECT course_id FROM section WHERE sem = 'Spring' AND year = 2010);
  ```
- **Find courses that ran in Fall 2009 but not in Spring 2010:**
  ```sql
  (SELECT course_id FROM section WHERE sem = 'Fall' AND year = 2009)
  EXCEPT
  (SELECT course_id FROM section WHERE sem = 'Spring' AND year = 2010);
  ```

### Notes:
- `UNION`, `INTERSECT`, and `EXCEPT` automatically **eliminate duplicates**.
- To **retain duplicates**, use `UNION ALL`, `INTERSECT ALL`, and `EXCEPT ALL`.
- If a tuple appears `m` times in `r` and `n` times in `s`, then:
  - It appears **m + n** times in `r UNION ALL s`.
  - It appears **min(m, n)** times in `r INTERSECT ALL s`.
  - It appears **max(0, m - n)** times in `r EXCEPT ALL s`.

## Null Values

- A `NULL` value represents **unknown data** or **nonexistent data**.
- Any arithmetic operation involving `NULL` results in `NULL`.
  - Example: `5 + NULL` returns `NULL`.

### Checking for NULL values:
- Use `IS NULL` to check for `NULL` values.
- Example: Find all instructors with `NULL` salaries:
  ```sql
  SELECT name FROM instructor WHERE salary IS NULL;
  ```
- **Important:** `NULL` **cannot** be compared using `=`, `<`, or `<>` operators.

### Three-Valued Logic:
- **TRUE, FALSE, UNKNOWN**
- Any comparison with `NULL` results in `UNKNOWN`.
  - Example: `5 < NULL`, `NULL <> NULL`, `NULL = NULL` → all return `UNKNOWN`.

### Logical Operations:
- **OR**: `(UNKNOWN OR TRUE) = TRUE`, `(UNKNOWN OR FALSE) = UNKNOWN`, `(UNKNOWN OR UNKNOWN) = UNKNOWN`
- **AND**: `(TRUE AND UNKNOWN) = UNKNOWN`, `(FALSE AND UNKNOWN) = FALSE`, `(UNKNOWN AND UNKNOWN) = UNKNOWN`
- **NOT**: `NOT UNKNOWN = UNKNOWN`

- A `WHERE` clause treating `UNKNOWN` as `FALSE` ensures only `TRUE` results are selected.

# Week 3:

## Lect 2: Intermediate SQL 2

## Unique Construct
- The `unique` construct tests whether a subquery has any duplicate tuples in its result.
- It evaluates to `true` if a given subquery contains no duplicates.

### Query: Find all courses that were offered at most once in 2009
```sql
SELECT T.course_id
FROM course AS T
WHERE UNIQUE (
    SELECT R.course_id
    FROM section AS R
    WHERE R.year = 2009
    AND T.course_id = R.course_id
);
```

## Subqueries in the FROM Clause
- SQL allows a subquery expression to be used in the `FROM` clause.

### Query: Find the average instructor salaries of those departments where the average salary is greater than $42,000
```sql
SELECT dept_name, avg_salary
FROM (
    SELECT dept_name, AVG(salary) AS avg_salary
    FROM instructor
    GROUP BY dept_name
) AS dept_avg
WHERE avg_salary > 42000;
```

- Note: We do not need to use the `HAVING` clause.

### Alternative way to write the above query
```sql
SELECT dept_name, avg_salary
FROM (
    SELECT dept_name, AVG(salary) AS avg_salary
    FROM instructor
    GROUP BY dept_name
) AS dept_avg
WHERE avg_salary > 42000;
```

## The WITH Clause
- The `WITH` clause provides a way of defining a temporary relation whose definition is available only to the query in which the `WITH` clause occurs.

### Query: Find all departments with the maximum budget
```sql
WITH max_budget(value) AS (
    SELECT MAX(budget)
    FROM department
)
SELECT department.name
FROM department, max_budget
WHERE department.budget = max_budget.value;
```

## Modifications of the Database

### Deletion
- Delete all instructors
```sql
delete from instructor;
```

- Delete all instructors from the Finance department
```sql
delete from instructor
where dept_name = 'Finance';
```

- Delete all tuples in the instructor relation for those instructors associated with a department located in the Watson building
```sql
delete from instructor
where dept_name IN (
    select dept_name
    from department
    where building = 'Watson'
);
```

- Delete all instructors whose salary is less than the average salary of instructors
```sql
delete from instructor
where salary < (
    select avg(salary)
    from instructor
);
```

**Problem:** As we delete tuples from `instructor`, the average salary changes.

**Solution used in SQL:**
1. First, compute `avg(salary)` and find all tuples to delete.
2. Next, delete all tuples found above (without recomputing avg or retesting the tuple).

### Insertion
- Add a new tuple to `course`
```sql
insert into course
values ('CS-437', 'Database Systems', 'Comp. Sci.', 4);
```

- Or equivalently:
```sql
insert into course (course_id, title, dept_name, credits)
values ('CS-437', 'Database Systems', 'Comp. Sci.', 4);
```

- Add a new tuple to `student` with `tot_creds` set to `NULL`
```sql
insert into student
values ('3003', 'Green', 'Finance', NULL);
```

- Add all instructors to the `student` relation with `tot_creds` set to `0`
```sql
insert into student
select ID, name, dept_name, 0
from instructor;
```

- The `SELECT FROM WHERE` statement is evaluated fully before any of its results are inserted into the relation. Otherwise, queries like:
```sql
insert into table1 select * from table1;
```
would cause problems.

### Updates
- Increase salaries of instructors whose salary is over $100,000 by 3%, and all others by 5%.

- Write two update statements:
```sql
update instructor
set salary = salary * 1.03
where salary > 100000;

update instructor
set salary = salary * 1.05
where salary <= 100000;
```

- **The order is important.**

- Can be done better using the `CASE` statement:
```sql
update instructor
set salary = CASE
    WHEN salary <= 100000 THEN salary * 1.05 
    ELSE salary * 1.03 
END;
```

### Recomputing and Updating `tot_creds` for Students
- Recompute and update `tot_creds` value for all students
```sql
UPDATE student S
SET tot_creds = (
    SELECT SUM(credits)
    FROM takes, course
    WHERE takes.course_id = course.course_id
    AND S.ID = takes.ID
    AND takes.grade <> 'F'
    AND takes.grade IS NOT NULL
);
```

- Sets `tot_creds` to `NULL` for students who have not taken any course:
```sql
UPDATE student
SET tot_creds = (
    CASE
        WHEN (SELECT SUM(credits) FROM takes, course
              WHERE takes.course_id = course.course_id
              AND student.ID = takes.ID
              AND takes.grade <> 'F'
              AND takes.grade IS NOT NULL) IS NOT NULL
        THEN (SELECT SUM(credits) FROM takes, course
              WHERE takes.course_id = course.course_id
              AND student.ID = takes.ID
              AND takes.grade <> 'F'
              AND takes.grade IS NOT NULL)
        ELSE 0
    END
);
```

## Lect 3: Intermediate SQL 3:
## Join Operations:
> Join operations take two relations and return as a result another relation.
> A join operation is a Cartesian product which requires that tuples in the two relations match (under some condition).
> It also specifies the attributes that are present in the result of the join
> The join operations are typically used as subquery expressions in the from clause

![image](https://github.com/user-attachments/assets/a8eaf4d8-9788-452a-aba9-3d1af6e35bb6)
![image](https://github.com/user-attachments/assets/90de0ffc-7ce3-428c-8b3c-b582fc4e9c36)


### Natural Join
A natural join is a type of join that matches columns with the same name in both tables.

#### Syntax:
```sql
SELECT table1.column1, table2.column2
FROM table1
NATURAL JOIN table2;
```
### Cross Join
The cross join returns the Cartesian product of rows from tables in the join
• Explicit
```sql
select * from employee cross join department;
```
• Implicit
```sql
select * from employee, department;
```

### Outer Join
An Outer Join returns all the rows from one table and the matching rows from the other table. If there is no match, `NULL` values are returned for the missing rows.

#### Left Outer Join
Returns all rows from the left table and the matching rows from the right table. If there is no match, `NULL` values are returned.

#### Syntax:
```sql
SELECT table1.column1, table2.column2
FROM table1
LEFT JOIN table2 ON table1.columnX = table2.columnY;
```

#### Right Outer Join
Returns all rows from the right table and the matching rows from the left table. If there is no match, `NULL` values are returned.

#### Syntax:
```sql
SELECT table1.column1, table2.column2
FROM table1
RIGHT JOIN table2 ON table1.columnX = table2.columnY;
```

#### Full Outer Join
Returns all rows from both tables. If there is no match, `NULL` values are returned.

#### Syntax:
```sql
SELECT table1.column1, table2.column2
FROM table1
FULL JOIN table2 ON table1.columnX = table2.columnY;
```

### View:
> In some cases, it is not desirable for all users to see the entire logical model (that is, all the actual relations stored in the database.)
> Consider a person who needs to know an instructors name and department, but not the salary. This person should see a relation described, in SQL, by
```sql
select ID, name, dept name
from instructor
```
> A view provides a mechanism to hide certain data from the view of certain users
> Any relation that is not of the conceptual model but is made visible to a user as a `virtual relation` is called a **view**.
![image](https://github.com/user-attachments/assets/72533369-edf5-4d10-ade1-407495610809)

```sql
-- views

select ID, name, dept_name
from instructor;


-- A view of instructors without their salary
create view faculty as 
	select ID, name, dept_name
	from instructor;


-- Find all instructors in the Biology department
select name from faculty where dept_name = 'Biology';


-- create view physics fall 2009 as
  select course.course id, sec id, building, room number
  from course, section
  where course.course id = section.course id
    and course.dept name = ’Physics’
    and section.semester = ’Fall’
    and section.year = ’2009’;
```

# Week 4:

# Week 5:

# Week 6: 

# Week 7:

# Week 8:

# Week 9:

# Week 10:

# Week 11:

# Week 12:
