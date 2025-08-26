#!/bin/bash

begin=1
end=10

for (( a = $begin; a <= $end; a++ ))
do
	b=$(( a**2 ))
	echo $b
done
