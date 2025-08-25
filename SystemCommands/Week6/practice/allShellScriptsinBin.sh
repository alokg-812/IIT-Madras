#!/bin/bash
echo Shell scripts in /bin directory
for i in $(ls /bin)
do
	# echo /bin/$i
	file /bin/$i | grep "shell script"
done
