script() { 
    (head -n 2 half1.txt; tail -n 3 half2.txt) >> master.txt
}