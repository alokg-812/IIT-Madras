#!/bin/bash

echo "breaking outer loop using break 1"
n=10
i=0
while [ $i -lt $n ]
do
	echo $i
	j=0
	while [ $j -le $i ]
	do
		printf "$j "
		(( j++ ))
		if [ $i -eq 7 ]
		then
			echo 7 is bad news
			break 1
		fi
	done
	(( i++ ))
done

echo "\n-----------------------------------"
echo "breaking inner loop using break 2"
n=10
i=0
while [ $i -lt $n ]
do
	echo $i
	j=0
	while [ $j -le $i ]
	do
		printf "$j "
		(( j++ ))
		if [ $i -eq 7 ]
		then
			echo 7 is bad news
			break 2
		fi
	done
	(( i++ ))
done
