# GrPA 1
* Write a command that runs in a child shell, prints "hello" and exits with the exit code 179.
* [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week5/GrPA/GrPA1.bash)

# GrPA 2
* The file Pincode_info.csv has information on the pin codes of some places. A sample output of the command head -5 Pincode_info.csv is given below. First line of this file gives the information about the sequence of fields in each line of file following it.
```
Circle Name,Region Name,Division Name,Office Name,Pincode,OfficeType,Delivery,District,StateName
Andhra Pradesh Circle,Kurnool Region,Anantapur Division,A Narayanapuram B.O,515004,BO,Delivery,ANANTHAPUR,Andhra Pradesh
Andhra Pradesh Circle,Kurnool Region,Anantapur Division,Akuledu B.O,515731,BO,Delivery,ANANTHAPUR,Andhra Pradesh
Andhra Pradesh Circle,Kurnool Region,Anantapur Division,Alamuru B.O,515005,BO,Delivery,ANANTHAPUR,Andhra Pradesh
Andhra Pradesh Circle,Kurnool Region,Anantapur Division,Allapuram B.O,515766,BO,Delivery,ANANTHAPUR,Andhra Pradesh
```
* Assume that there are only 10 states for which this system works and the first digit of the pin code is unique for each state. That means for all the places in the entire state the first digit will be same.
* You are given a shell variable named state that contains a state name(Example: state=`Punjab`).
* Display the number of pin codes available in the file Pincode_info.csv within the state given in the variable state that has the same first and the last digit.
* For e.g. if the value of state = “Andhra Pradesh”, one such pin code is 515005(for the file given above).
* [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week5/GrPA/GrPA2.bash)

* Hint: First find the first digit that represents the given state.

# GrPA 3:
* In a course, the instructor asked the students to submit their projects in a single file named as the student’s roll number. A typical roll number of a student is a 10 character string which is a combination of a four digit(decimal) year and six character hexadecimal number, e.g. "20201f3acd". The instructor specified that the name of the file should be in lower case but some students mistakenly used uppercase for their file names. Each file name is either entirely in lower case or entirely in upper case with numbers.
* Your task is to create two arrays(shell variables) named lower and upper. Array lower should not contain the file names that have upper case letters and array upper should contain all the file names that have upper case letters.
* Note: The project files are located in the current directory
* Hint: ``arr=(`ls`) # Each element in arr corresponds to the output from the ls``
* [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week5/GrPA/GrPA3.bash)
