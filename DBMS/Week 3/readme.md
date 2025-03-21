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

##### Syntax:
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

##### Syntax:
```sql
SELECT table1.column1, table2.column2
FROM table1
LEFT JOIN table2 ON table1.columnX = table2.columnY;
```

#### Right Outer Join
Returns all rows from the right table and the matching rows from the left table. If there is no match, `NULL` values are returned.

##### Syntax:
```sql
SELECT table1.column1, table2.column2
FROM table1
RIGHT JOIN table2 ON table1.columnX = table2.columnY;
```

#### Full Outer Join
Returns all rows from both tables. If there is no match, `NULL` values are returned.

##### Syntax:
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


## Lect 4: Intermediate SQl-3:
> Transactions

> Integrity Constraints

> SQL DataTypes and Schemas

> Authorization

**Trasactions:**
- Unit of work
- Atomic transaction
   * either fully executed or rolled back as if it never occurred
- Isolation from concurrent transactions
- Transactions begin implicitly
   * Ended by commit work or rollback work
- But default on most databases: each SQL statement commits automatically
   * Can turn off auto commit for a session (for example, using API)
   * In SQL:1999, can use: begin atomic ... end
      * Not supported on most databases

**Integrity Constraints:**
- Integrity constraints guard against accidental damage to the database, by ensuring that
authorized changes to the database do not result in a loss of data consistency.

* `not null`
* `primary key`
* `unique`
* `check(P)`, where P is a predicate

```sql
	create table section (
		course id varchar(8),
		sec id varchar(8),
		semester varchar(6),
		year numeric(4,0),
		building varchar(15),
		room number varchar(7),
		time slot id varchar(4),
		primary key (course id, sec id, semester, year),
		`check` (semester in (’Fall’, ’Winter’, ’Spring’, ’Summer’))
	);
```

**Built-in Datatypes in SQL:**
- `date` Dates, containing a (4 digit) year, month and date
	- Example: _date_ ‘2005-7-27’
- `time` Time of day, in hours, minutes and seconds.
	- Example: _time_ ‘09:00:30’, _time_ ‘09:00:30.75’
- `timestamp` date plus time of day
	- Example: _timestamp_ ‘2005-7-27 09:00:30.75’
- `interval` period of time
	- Example: _interval_ ‘1’ day


**Authorization:**
Forms of authorization on parts of the database:
* `Read` - allows reading, but not modification of data
* `Insert` - allows insertion of new data, but not modification of existing data
* `Update` - allows modification, but not deletion of data
* `Delete` - allows deletion of data

Forms of authorization to modify the database schema
* `Index` - allows creation and deletion of indices
* `Resources` - allows creation of new relations
* `Alteration` - allows addition or deletion of attributes in a relation
* `Drop` - allows deletion of relations

>[!NOTE] The grant statement is used to confer authorization
> grant <privilege list>
> on <relation name or view name> to <user list>


## Lecture -5: Advanced SQL:
> Functions and Procedures in SQL

> Triggers
