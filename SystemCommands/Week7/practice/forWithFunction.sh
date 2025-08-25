#!/bin/bash

toggle(){
	for i in {1..4}; do
		if [ $((i%2)) -eq 0 ]; then
			echo -n "1"
		else
			echo -n "0"
		fi
		sleep 0.3
	done
}


for i in {1..3}; do #first loop
	toggle
done

for i in {1..2}; do #second loop
	toggle &
done
wait #Command issued to finish process
