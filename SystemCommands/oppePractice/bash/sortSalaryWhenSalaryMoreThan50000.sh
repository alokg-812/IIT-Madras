cat data.txt | awk '$4>50000 { print $4 }' | sort
