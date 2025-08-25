#!/bin/bash
echo "Use of for loop:"

for i in $`ls /bin/z*`
do
	echo $i
done
