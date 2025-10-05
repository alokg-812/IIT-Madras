# GrPA 1A
- Consider a file named `marks.csv` containing roll number and marks of variable number of subjects of students.
- The values are comma separated values and in the format
```
RollNo,Subject1,Subject2,Subject3,So on...
```
- Write an Awk command to print all the roll numbers(RollNo) in the file.
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA1A.bash)


# GrPA 1B
- Write an Awk command to print the first field of the all the lines containing more than **20 characters** in the file `marks.csv`.
- The field separator in the file is comma (,).
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA1B.bash)


# GrPA 1C
- Write an awk script to print the total number of fields in a csv file with the field separator as comma (,). 
- Print only the number and nothing else.
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA1C.bash)


# GrPA 1D
- Write an Awk Script to print all the lines whose starting and ending character is a digit.
- Also print the count of these lines(only the number) on a new line at the last in your output. The field separator in the file is comma (,).
- Note, that here it is asked to write an Awk script. Read the Programming questions instructions for more clarity.
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA1D.bash)

# GrPA 2
- A software company has published some best practices for writing the code. 
- One of the best practice mentioned is that if no line in your code should exceed 50 characters in total including all type of characters or spaces.
- Given a bash script that intends to print the names of all .c files that contain one or more lines with length more than 50 characters(as specified above).
- The awk script within this bash script to check the files as per above condition is missing in the code, complete that
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA2.bash)


# GrPA 3
Without using the wc command , write a bash script that accepts any number of arguments. Out of these some would be options(hyphen plus a character like -l or -c) and the last argument will be a file path(use ${@: -1} to access the last argument, there is a space before -1). Only four options are accepted by your script -l, -w, -n and -s.

- Assume that file path given will always be for a valid file and we will refer it as file in the next lines. For options,
- If no option is supplied to your script do nothing.
- If -l option is supplied, print the number of lines in the file.
- If -w option is supplied, print the number of words in the file. Assume that any string between spaces is a word. i.e. if using awk count the number of fields in each line to get the word count.
- If -n option is supplied, print the number of lines having only digits(no alphabets or spaces) in the file.
- option -s also accepts an argument say str. In this case print the number of lines containing the string str. If no argument is specified with -s option print 0.
- The above options can be supplied together or more than once. Print the required count for each appearance of the option on a new line. For e.g.
- if -l and -w are both supplied together in the sequence print count of lines and count of words each on separate lines.
- If -l, -n and -l options are supplied in the sequence then print number of lines, number of lines containing only digits and finally again number of lines in the file each on separate line.


**Hints:**
- Use while getopts style code.
- Use awk to find the count. Or a combination of egrep and awk for counting lines which matches some pattern. Or pattern block of awk.
* **Note:** Do not use single quotes in your script. Either replace each single quote with double quotes(if you are not using any double quotes in your awk script) or replace each single quote with <code>'\''</code>
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA3.bash)


# GrPA 4

EmployeeDetails.csv file contains the Employee ID, Employee Name, Leaves taken this year and Gender, of all the employees working in a company XYZ, born between the years 1997 and 2000 (including both). Total employees in the company is less than 1000.

The `employee ID` is of the format: `DepartmentYearOfBirthCode` where:
- `Department` is the department to which the employee belongs to (Department A to G)
- `YearOfBirth` is the birth year of the employee (Eg. 2000)
- `Code` is a three digit number unique to each employee.

**For e.g.:** B1997122 is employee id of an employee working in department B, born in the year 1997 having unique code as 122. The email ID of an employee is in the format EmployeeID@xyz.com, where EmployeeID is the employee id of the employee.
- **For example:** email id of Ram having employee id as A1998001 is A1998001@xyz.com. Email ids are case sensitive.

- Write an awk script that that takes the file EmployeeDetails.csv as input and prints the email ids of all the female employees of the company in the same sequence as the employee details appear in the file EmployeeDetails.csv.
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA3.bash)


# GrPA 5

Consider a special programming file functions.sh that contains several functions (A function is a block of code). Write a bash script/command using sed to insert a line "# START FUNCTION" before the starting of a function and a line "# END FUNCTION" at the end of the function.
Starting of a function in this file can be identified as a line that has some string followed by "(", then followed by ")" or some string followed by ")", and this line should end with "{".
Ending of a function can be identified by a line containing only "}" in the whole line.
In this file curly braces "{" and "}" are not used for any other purpose. Do not change the original file just print the output to STDOUT.

- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA5.bash)


# GrPA 6
EmployeeDetails.csv file contains the Employee ID, Employee Name, Leaves taken this year and Gender, of all the employees working in a company XYZ, born between the years 1997 and 2000 (including both). Total employees in the company is less than 1000.

The employee ID is of the format: DepartmentYearOfBirthCode Where:
- Department is the department to which the employee belongs to (Department A to G)
- YearOfBirth is the birth year of the employee (Eg. 2000)>
- Code is a three digit number unique to each employee.

Write an awk script takes the file EmployeeDetails.csv as input and prints the name of the employee(s) with lowest number of leaves taken this year. If there are more than one employees with the lowest number of leaves, print the name of each employee on a new line.

- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA6.bash)

# GrPA 7
Given some raw programming files, we want them to adhere to the company guidelines. Write a sed script that will run for all ".sh" files in the current directory and print the contents after performing the following actions. You just need to write the sed script, running that for all the files will be taken care of by our driver bash script.

Insert a copyright message at the start of the file(before the first line) as "# Copyright IITM 2022"(Note that there is a space after #).
Insert a copyright message at the end of the file(after the last line) as "# Copyright IITM 2022".
Insert a line "# START FUNCTION" before the starting of a function and a line "# END FUNCTION" at the end of the function. Check GrPA 4 for more details on identifying function boundaries. Use the same logic here.
Change the function "background_sleep" to "inactive_sleep". So replace all the occurrences of the word "background_sleep" in any line with "inactive_sleep". Assume that these keywords are used only in context of a function and nothing else.
Also, the function "active_sleep" is deprecated and we do not have an immediate replacement. So insert a line "# TODO:DEPRECATED" before the function "active_sleep" and in every instance. i.e. before every line containing the word "active_sleep".
After every 10th line (in line numbers 10, 20, 30,... ) add a line with four hashes such as "####" after applying all the above actions.

Perform all the above actions in the order given from top to bottom.

- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA7.bash)

# GrPA 8
EmployeeDetails.csv file contains the Employee ID, Employee Name, Leaves taken this year and Gender, of all the employees working in a company XYZ, born between the years 1997 and 2000 (including both). Total employees in the company is less than 1000.

The employee ID is of the format: DepartmentYearOfBirthCode Where:
- Department is the department to which the employee belongs to (Department A to G)
- YearOfBirth is the birth year of the employee (Eg. 2000)
- Code is a three digit number unique to each employee.

For e.g. B1997122 is employee id of an employee working in department B, born in the year 1997 having unique code as 122. The email ID of an employee is in the format EmployeeID@xyz.com, where EmployeeID is the employee id of the employee.
For example email id of Ram having employee id as A1998001 is A1998001@xyz.com. Email ids are case sensitive.

Write an awk script that takes input as file EmployeeDetails.csv and calculate and prints the average number of leaves taken by the employees born in each year from 1997 to 2000(both 1997 and 2000 included). The average for each year should be printed on a newline starting from the year 1997 to 2000 in the same sequence i.e. your script should print 4 lines of output always one for each year 1997, 1998, 1999 and 2000. If there are no employees born in some year, print 0 for that years average leaves. Print only the integer part of the average(i.e. if the average is 7.3333 print 7). Use int() function to get the integer part of any float number.
- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA8.bash)


# GrPA 9

You have a csv file named groceries.csv that contains a list of grocery items and their unit cost. The two fields are separated by comma(,). This file will be given as input to your Awk script.

Write an Awk script that takes two arguments(command line) named item and n, where item is the item name and n is the number of units, then prints the total cost of purchasing n units of the item item. The script prints only a number. i.e. you need to find the item cost of the item given in argument while parsing the input file.
Note: You can directly use these variables with the given name in your Awk script. Assume that the item given in the argument will always be present in the csv file.

- [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week9/GrPA/GrPA9.bash)


