#!/bin/bash
echo Shell scripts
for i in $(/mnt/c/Users/alokg/Desktop/IIT-Madras/SystemCommands/Week6/practice/*)
do
	echo "$i"
	file "$i" | grep "shell script"
done
