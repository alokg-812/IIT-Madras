#!/bin/bash

start=0
end=10

for (( i=$start; i<$end; i++ ))
do
	echo Hello $i
	sleep 2
done
