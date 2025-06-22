alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls ; date ; wc -l /etc/profile ;
README.md
Mon Jun 16 11:08:07 IST 2025
27 /etc/profile
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ (ls ; date ; wc -l /etc/profile ; )
README.md
Mon Jun 16 11:09:09 IST 2025
27 /etc/profile
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ echo $ABSH_SUBSHELL

alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ echo $BASH_SUBSHELL
0
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ (echo $BASH_SUBSHELL)
1
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ (ls ; date ; echo $BASH_SUBSHELL )
README.md
Mon Jun 16 11:10:27 IST 2025
1
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls /blah && date
ls: cannot access '/blah': No such file or directory
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ date && ls /blah
Mon Jun 16 11:12:26 IST 2025
ls: cannot access '/blah': No such file or directory
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls /blah || date
ls: cannot access '/blah': No such file or directory
Mon Jun 16 11:14:15 IST 2025
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls || date
README.md
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ touch a.txt b.txt.c.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls | grep .txt
a.txt
b.txt.c.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls
README.md  a.txt  b.txt.c.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ rm a.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls
README.md  b.txt.c.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ rm b.txt.c.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ ls
README.md
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3$ mkdir practice && cd practice
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ touch file1.txt file2.txt script.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ echo "line 1 in file1" > file1.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ echo "line 2 in file1" >> file1.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ cat file1.txt
line 1 in file1
line 2 in file1
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ echo "line 1 in file2" > file2.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ cat file2.txt
line 1 in file2
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ echo "echo script running" > script.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ chmod +x script.sh
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice$ echo "This runs"; mkdir testdir; cd testdir; echo "You are inside testdir"
This runs
You are inside testdir
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir$ mkdir testok && cd testok && echo "Directory created and changed"
Directory created and changed
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ cd notfound || echo "Oops, folder not found!"
-bash: cd: notfound: No such file or directory
Oops, folder not found!
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ echo "Overwriting file1" > file1.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ echo "Another line added" >> file1.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ cat file1.txt
Overwriting file1
Another line added
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ ls nofolder 2> error.log
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ cat error.log
ls: cannot access 'nofolder': No such file or directory
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ ls file1.txt nofile.txt > out.txt 2> err.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ at out.txt
Command 'at' not found, but can be installed with:
sudo apt install at
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ cat out.txt
file1.txt
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$ cat err.txt
ls: cannot access 'nofile.txt': No such file or directory
alok@AlokThinkbook:/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/week3/practice/testdir/testok$
