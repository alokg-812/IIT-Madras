#!/bin/bash
# script1.sh is my first script
echo Hello World
echo The PID of the process running this script is:
echo $$
ps --forest
export myvar=MYVARIABLE
echo $myvar
