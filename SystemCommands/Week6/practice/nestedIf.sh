#!/bin/bash
# This is my second script
echo I am Invoked as:
echo $0
echo Number of Arguments passed to the script:
echo $#
echo First Argument passed:
echo $1
echo Second Argument passed:
echo $2

# ----------------------------------------
if test $# -ge 2;
then
	echo Number of parameters are more than 2
	if test $1=$2;
	then
		echo The two arguments are same
	fi
fi
# ----------------------------------------
