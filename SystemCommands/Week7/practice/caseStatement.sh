#!/bin/bash

echo What is your fav image processor?
read pname

case $pname in
	[gG]imp | inkspace)
		echo Good choice
		;;
	[aA]dobe*)
		echo That costs a lot and need clouds
		;;
	imagej)
		echo Measuring things on an image?
		;;
	*)
		echo I did not know $pname could do image stuff
		;;
esac
