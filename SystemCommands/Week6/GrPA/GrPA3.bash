script() {
read input
if [[ "$input" =~ ^-?([0-9]*[.])?[0-9]+$ ]]; then
    if (( $(echo "$input >= 0" | bc -l) )); then
        echo "PNUM"
    else
        echo "NNUM"
    fi
else
    echo "STRING"
fi
}
