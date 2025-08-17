{ dept[$3]++ }
END {
	for(d in dept){
		print d, dept[d]
	}
}
