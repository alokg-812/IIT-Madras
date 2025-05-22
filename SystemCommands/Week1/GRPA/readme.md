## GRPA 1:
We created some directories and change our current working directory using the cd command as given by the sequence of commands below. Write a bash command to make the directory `level2` as your current working directory. i.e. after executing your solution, if we execute the command `pwd` it should return the path of the directory `level2`. <br> <br>
**Ques:** Write your solution as a single line bash command.
```bash	
cd /
mkdir level1
cd level1
mkdir level2
cd level2
mkdir level3
cd ..
cd ..
```
### [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week1/GRPA/GrPA1.bash)

---

## GRPA 2:
We have a file named `systemcommands.txt` in the present working directory. Write a Bash command to change its permissions to

``
user: read, write, execute
group: execute
others: write
``

### [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week1/GRPA/GrPA2.bash)

---

## GRPA 3:

We want to change the file permissions of `someFile.txt` file as follows.
```
user: execute
group: execute, read
others: write
```
We will use the command `chmod XXX someFile.txt` where XXX represents a 3 digit number used to set the above permissions. <br> 
Write a bash command to create a file named `XXX.digits` in the current working directory such XXX is the same three digit number used to set the permissions as mentioned above. <br>
The file your command creates can be empty. <br>


**For e.g.** If your think the command chmod 111 someFile.txt will change the permission of file someFile.txt as mentioned above, then your solution should create a file named 111.digits in the current working directory.

### [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week1/GRPA/GrPA3.bash)

---

## GRPA 4:

Create two folders named dir1 and dir2 in the current working directory. <br>
Try to write a single line bash command to perform the above task.

### [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week1/GRPA/GrPA4.bash)

---

## GRPA 5:

**Ques:** Write two commands one on each line for the following two tasks.

Move only the file file_1 present in dir_1 to the empty directory dir_2. <br>
Delete the directory dir_1. <br>
dir_1 and dir_2 are directories in the current working directory. The operation should not change your current working directory. <br>

### [Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week1/GRPA/GrPA5.bash)
