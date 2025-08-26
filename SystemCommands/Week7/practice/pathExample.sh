#!/bin/bash

#set -x
echo Path is set to PATH

i=0
IFS=:
for n in $PATH
do
	echo $i $n
	(( i++ ))
done
