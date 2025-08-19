cat data.txt | awk '$4>50000 { print $2 }' | sort
