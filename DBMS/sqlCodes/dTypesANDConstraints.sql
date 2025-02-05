--Syntax to create table tablename (col1 dtype constraints, col2 dtype constraints, col3 dtype constraints)

-- Datatypes:
1. Char(n) : fixed length string of n characters 
2. Varchar(n) : variable length string of n characters
3. Date : Stores calendar dates
4. int : 4-byte integer
5. Boolean : holds True, False or Null

-- Constraints:
1. NULL: Allows missing values.
2. NOT NULL: Disallows missing values.
3. UNIQUE: Ensures all values are distinct.
4. CHECK: Ensures values meet a condition.
5. PRIMARY KEY: Unique identifier for a table (combines NOT NULL and UNIQUE).
6. FOREIGN KEY: Links to a primary key in another table for data integrity.

create table students(roll_no varchar(10) primary key, name char(10) unique, dob date not null, age int not null, active boolean, check(age>18))

insert into students values ('24f2002281', 'Alok', '08-12-2004', 20, True)
select * from students
