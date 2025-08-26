#!/bin/bash

i=0
n=10
while [ $i -lt $n ]
do
	echo $i
	(( i++ ))
	if [ $i -eq 5 ]
	then
		echo 5 is bad news
		break
	fi
done
