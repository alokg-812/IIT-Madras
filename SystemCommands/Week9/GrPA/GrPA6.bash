script() { echo '

    BEGIN { FS=",";min=-1 } 

    {
        leaves = $3
        if (min == -1 || leaves < min) {
            min = leaves
            names[0] = $2
            count = 1
        }
        else if (leaves == min) {
            names[count++] = $2
        }
    }

    END {
        for (i = 0; i < count; i++) {
            print names[i]
        }
    } 



' > yourScript.awk
awk -f yourScript.awk EmployeeDetails.csv
}