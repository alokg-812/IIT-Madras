script() { 
    awk -F',' -v state="$state" '
    NR == 1 { next }
    {
        gsub(/^ +| +$/, "", $9)
        if ($9 == state) {
            pin = $5
            gsub(/[^0-9]/, "", pin)
            if (length(pin) > 0 && substr(pin,1,1) == substr(pin,length(pin),1)) {
                count++
            }
        }
    }
    END { print count }
    ' Pincode_info.csv
}
