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

Assume that file path given will always be for a valid file and we will refer it as file in the next lines. For options,
If no option is supplied to your script do nothing.
If -l option is supplied, print the number of lines in the file.
If -w option is supplied, print the number of words in the file. Assume that any string between spaces is a word. i.e. if using awk count the number of fields in each line to get the word count.
If -n option is supplied, print the number of lines having only digits(no alphabets or spaces) in the file.
option -s also accepts an argument say str. In this case print the number of lines containing the string str. If no argument is specified with -s option print 0.
The above options can be supplied together or more than once. Print the required count for each appearance of the option on a new line. For e.g.
if -l and -w are both supplied together in the sequence print count of lines and count of words each on separate lines.
If -l, -n and -l options are supplied in the sequence then print number of lines, number of lines containing only digits and finally again number of lines in the file each on separate line.


Hints:
Use while getopts style code.
Use awk to find the count. Or a combination of egrep and awk for counting lines which matches some pattern. Or pattern block of awk.
Note: Do not use single quotes in your script. Either replace each single quote with double quotes(if you are not using any double quotes in your awk script) or replace each single quote with <code>'\''</code>

Sample
Suppose your bash script is named as myCount.sh. In the below sample the argument to -s option is "say" so this should count all the lines containing the string "say".
For the public test case all the commands given in the below sample are executed inthe same sequence one by one on the input file.
$ cat somefile.txt
This is a sample file
this is not end justsay start
that contains say
some number
say like 10
or
20
or
233
444
or say 3444
and now it ends.
​
$ bash myCount.sh -l somefile.txt      
12
$ bash myCount.sh -w somefile.txt
31
$ bash myCount.sh -n somefile.txt
3
$ bash myCount.sh -s say somefile.txt
4
$ bash myCount.sh -l -n somefile.txt
12
3
$ bash myCount.sh -l -s say -l -n somefile.txt                                                                                        
12
4
12
3
$ bash myCount.sh
$ bash myCount.sh somefile.txt



# GrPA 4

EmployeeDetails.csv file contains the Employee ID, Employee Name, Leaves taken this year and Gender, of all the employees working in a company XYZ, born between the years 1997 and 2000 (including both). Total employees in the company is less than 1000.

The employee ID is of the format: DepartmentYearOfBirthCode Where:
- Department is the department to which the employee belongs to (Department A to G)
- YearOfBirth is the birth year of the employee (Eg. 2000)>
- Code is a three digit number unique to each employee.

For e.g. B1997122 is employee id of an employee working in department B, born in the year 1997 having unique code as 122. The email ID of an employee is in the format EmployeeID@xyz.com, where EmployeeID is the employee id of the employee.
For example email id of Ram having employee id as A1998001 is A1998001@xyz.com. Email ids are case sensitive.

Write an awk script that that takes the file EmployeeDetails.csv as input and prints the email ids of all the female employees of the company in the same sequence as the employee details appear in the file EmployeeDetails.csv.

Sample
Suppose your awk script is named as yourScript.awk
$ cat EmployeeDetails.csv
A1998001,Ram Kumar,10,Male
A1998002,Mohammed Iqbal,5,Male
A1998003,Priya Lal,7,Female
A1999001,Sunita Sharma,25,Female
A2000001,Rose Mary Thomas,3,Female
B1999001,Sri Lakshmi Jai,5,Female
​
$ awk -f yourScript.awk EmployeeDetails.csv  
A1998003@xyz.com
A1999001@xyz.com
A2000001@xyz.com
B1999001@xyz.com



# GrPA 5

Consider a special programming file functions.sh that contains several functions (A function is a block of code). Write a bash script/command using sed to insert a line "# START FUNCTION" before the starting of a function and a line "# END FUNCTION" at the end of the function.
Starting of a function in this file can be identified as a line that has some string followed by "(", then followed by ")" or some string followed by ")", and this line should end with "{".
Ending of a function can be identified by a line containing only "}" in the whole line.
In this file curly braces "{" and "}" are not used for any other purpose. Do not change the original file just print the output to STDOUT.



# GrPA 6
EmployeeDetails.csv file contains the Employee ID, Employee Name, Leaves taken this year and Gender, of all the employees working in a company XYZ, born between the years 1997 and 2000 (including both). Total employees in the company is less than 1000.

The employee ID is of the format: DepartmentYearOfBirthCode Where:
- Department is the department to which the employee belongs to (Department A to G)
- YearOfBirth is the birth year of the employee (Eg. 2000)>
- Code is a three digit number unique to each employee.

For e.g. B1997122 is employee id of an employee working in department B, born in the year 1997 having unique code as 122. The email ID of an employee is in the format EmployeeID@xyz.com, where EmployeeID is the employee id of the employee.
For example email id of Ram having employee id as A1998001 is A1998001@xyz.com. Email ids are case sensitive.

Write an awk script takes the file EmployeeDetails.csv as input and prints the name of the employee(s) with lowest number of leaves taken this year. If there are more than one employees with the lowest number of leaves, print the name of each employee on a new line.

Sample
Suppose your awk script is named as yourScript.awk. For the below sample file the lowest number of leaves taken by any employee is 5. And there are two employees who have taken only 5 leaves, print both employee names on a separate line.
$ cat EmployeeDetails.csv
A1998001,Ram Kumar,10,Male
A1998002,Mohammed Iqbal,5,Male
A1998003,Priya Lal,7,Female
A1999001,Sunita Sharma,25,Female
A2000001,Rose Mary Thomas,13,Female
B1999001,Sri Lakshmi Jai,5,Female
​
$ awk -f yourScript.awk EmployeeDetails.csv  
Mohammed Iqbal
Sri Lakshmi Jai

# GrPA 7
Given some raw programming files, we want them to adhere to the company guidelines. Write a sed script that will run for all ".sh" files in the current directory and print the contents after performing the following actions. You just need to write the sed script, running that for all the files will be taken care of by our driver bash script.

Insert a copyright message at the start of the file(before the first line) as "# Copyright IITM 2022"(Note that there is a space after #).
Insert a copyright message at the end of the file(after the last line) as "# Copyright IITM 2022".
Insert a line "# START FUNCTION" before the starting of a function and a line "# END FUNCTION" at the end of the function. Check GrPA 4 for more details on identifying function boundaries. Use the same logic here.
Change the function "background_sleep" to "inactive_sleep". So replace all the occurrences of the word "background_sleep" in any line with "inactive_sleep". Assume that these keywords are used only in context of a function and nothing else.
Also, the function "active_sleep" is deprecated and we do not have an immediate replacement. So insert a line "# TODO:DEPRECATED" before the function "active_sleep" and in every instance. i.e. before every line containing the word "active_sleep".
After every 10th line (in line numbers 10, 20, 30,... ) add a line with four hashes such as "####" after applying all the above actions.

Perform all the above actions in the order given from top to bottom.

For example, for the input file
echo Hello
EOF
analysis.sh
script() {
  sum=0
  for i in $(cat result); do
    while read hash name; do 
      if [ $i == $hash ]; then
        inv=$(grep INVESTMENT $name)
        inv=${inv//INVESTMENT $/}
        sum=$((sum+inv))
      fi
    done < map
  done
  echo $sum
}

mkdir data

read fnos
for (( i=0; i<fnos; i++ )); do
  read line 
  echo $line | cut -d ":" -f 2- | tr '#' ';' > ./data/${line%%:*}
done

read mnos
for (( i=0; i<mnos; i++ )); do
  read line 
  echo $line  >> map
done

read rnos
for (( i=0; i<rnos; i++ )); do
  read line 
  echo $line  >> result
done
script

Output should be

# Copyright IITM 2022
echo Hello
EOF
analysis.sh
# START FUNCTION
script() {
  sum=0
  for i in $(cat result); do
    while read hash name; do 
      if [ $i == $hash ]; then
        inv=$(grep INVESTMENT $name)
####
        inv=${inv//INVESTMENT $/}
        sum=$((sum+inv))
      fi
    done < map
  done
  echo $sum
}
# END FUNCTION

mkdir data
####

read fnos
for (( i=0; i<fnos; i++ )); do
  read line 
  echo $line | cut -d ":" -f 2- | tr '#' '\n' > ./data/${line%%:*}
done

read mnos
for (( i=0; i<mnos; i++ )); do
####
  read line 
  echo $line  >> map
done

read rnos
for (( i=0; i<rnos; i++ )); do
  read line 
  echo $line  >> result
done
####
script
# Copyright IITM 2022


# GrPA 8
EmployeeDetails.csv file contains the Employee ID, Employee Name, Leaves taken this year and Gender, of all the employees working in a company XYZ, born between the years 1997 and 2000 (including both). Total employees in the company is less than 1000.

The employee ID is of the format: DepartmentYearOfBirthCode Where:
- Department is the department to which the employee belongs to (Department A to G)
- YearOfBirth is the birth year of the employee (Eg. 2000)
- Code is a three digit number unique to each employee.

For e.g. B1997122 is employee id of an employee working in department B, born in the year 1997 having unique code as 122. The email ID of an employee is in the format EmployeeID@xyz.com, where EmployeeID is the employee id of the employee.
For example email id of Ram having employee id as A1998001 is A1998001@xyz.com. Email ids are case sensitive.

Write an awk script that takes input as file EmployeeDetails.csv and calculate and prints the average number of leaves taken by the employees born in each year from 1997 to 2000(both 1997 and 2000 included). The average for each year should be printed on a newline starting from the year 1997 to 2000 in the same sequence i.e. your script should print 4 lines of output always one for each year 1997, 1998, 1999 and 2000. If there are no employees born in some year, print 0 for that years average leaves. Print only the integer part of the average(i.e. if the average is 7.3333 print 7). Use int() function to get the integer part of any float number.

Sample
Suppose your awk script is named as yourScript.awk. For the below sample file the average number of leaves taken by employees born in years 1997 to 2000 is printed in 4 lines in the same sequence.
$ cat EmployeeDetails.csv
A1998001,Ram Kumar,10,Male
A1998002,Mohammed Iqbal,5,Male
A1998003,Priya Lal,7,Female
A1999001,Sunita Sharma,25,Female
A2000001,Rose Mary Thomas,13,Female
B1999001,Sri Lakshmi Jai,5,Female
​
$ awk -f yourScript.awk EmployeeDetails.csv
0
7
15
13


# GrPA 9

You have a csv file named groceries.csv that contains a list of grocery items and their unit cost. The two fields are separated by comma(,). This file will be given as input to your Awk script.

Write an Awk script that takes two arguments(command line) named item and n, where item is the item name and n is the number of units, then prints the total cost of purchasing n units of the item item. The script prints only a number. i.e. you need to find the item cost of the item given in argument while parsing the input file.
Note: You can directly use these variables with the given name in your Awk script. Assume that the item given in the argument will always be present in the csv file.


Sample(suppose your script is named as yourScript.awk)
Here the cost of 3 Tomatoes needs to be calculated. Cost of one Tomato is 40 as seen from the csv file. So total cost = 3*40 

​
$ cat groceries.csv
1,Tomato,40
2,Brinjal,35
3,Banana,60
​
$ awk -f yourScript.awk groceries.csv Tomato 3
120



