#!/bin/bash

start1=1
start2=20
end=20

for(( a = $start1, b = $start2; a <= $end ; a++,b-- ))
do
	c=$(( a**2 ))
	d=$(( b**2 ))
	echo $c $d
done
