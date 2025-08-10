script(){ echo '


    BEGIN { FS=","; count=0 }
    /^[0-9].*[0-9]$/ {
        print $0;
        count++;
    }
    END { print count }


' >yourScript.awk
awk -f yourScript.awk marks.csv
}