script(){ echo '

    BEGIN { FS="," }
    $4 == "Female" {
        print $1 "@xyz.com"
    }


    ' >yourScript.awk
    awk -f yourScript.awk EmployeeDetails.csv
}