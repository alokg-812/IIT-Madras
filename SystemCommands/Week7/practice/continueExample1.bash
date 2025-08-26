#!/bin/bash

i=0
n=10
while [ $i -lt $n ]
do
        if [ $i -eq 5 ]
        then
                echo 5 is bad news
		(( i++ ))
                continue
        fi

	echo $i
	(( i++ ))
done
