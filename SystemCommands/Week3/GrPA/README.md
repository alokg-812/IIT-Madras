# GrPA 1

- Print the `number of lines` present in **file1** and **file2** combined, your solution should not print anything else.
- *file1* and *file2* are located in the current working directory.

> 💡Hint: Multiple files can be given as argument to ‘cat’ command.
<br>
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa1.bash)
# GrPA 2

- There are three files `master.txt`, `half1.txt` and `half2.txt` in the current working directory.
- Add `first 2 lines` of half1.txt to the file master.txt at the end(starting at a new line). Then,
- Append the last 3 lines of the file half2.txt to the file master.txt at the end(starting at a new line).
- Append the lines in the sequence mentioned.
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa2.bash)

# GrPA 3

- Print to the output containing the `name of the shell` being used, its `PID` and the `flags` in the following format:
- ``Shell:<shell>|PID:<pid>|Flags:<flags>``
- There are no spaces in the string
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa3.bash)

# GrPA 4
- An observer wrote a script named `createTwingle` that produces a file twingle containing names of all the visible stars present in the sky at that instant. 
- Every line in the file twingle is the `name of a star`. In your current directory the file twingle may or may not be present.
- If the file twingle is present in the directory then `print the number of lines` in the file, else `execute` the command createTwingle it will create the file twingle in the current working directory then print the number of lines in the file twingle.

Note: stderr will not be displayed

> 💡Hint: Try to use operators discussed in the lectures to give a single line solution for the task.
<br>
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa4.bash)



# GrPA 5

- Print the `number of directories` in the current working directory. Do not print anything else.
> 💡Hint: One solution is to make use of 'ls', 'wc' and pipes('|').
<br>
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa5.bash)


# GrPA 6
- The script test will print some text to the `standard output`, it can be run similar to any other command and does not accept any arguments.
- Your task is to `print the output` after running test on the screen and also append the output at the end(starting at new line) of the file log.  - File log is located in the current working directory.

> 💡Hint: To solve it in one line check the man page of tee command for appending to the file. 
<br>
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa6.bash)

# GrPA 7
- Add the string `EOF alpha` at the end of the file(starting at a new line) `alpha.txt`. Then,
- Append the contents of the file numbers.txt at the end of the file(starting at a new line) alpha.txt. alpha.txt and numbers.txt are located in the current working directory.
> [▶️Solution](https://github.com/alokg-812/IIT-Madras/blob/main/SystemCommands/Week3/GrPA/grpa7.bash)