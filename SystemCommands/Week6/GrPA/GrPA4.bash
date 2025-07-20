script() {

if [ $# -eq 0 ]; then
    echo "Please provide numbers as arguments."
    exit 1
fi

max=$1
min=$1

for num in "$@"
do
    if ! [[ "$num" =~ ^-?([0-9]*[.])?[0-9]+$ ]]; then
        echo "Invalid input detected: $num"
        exit 1
    fi

    if (( $(echo "$num > $max" | bc -l) )); then
        max=$num
    fi

    if (( $(echo "$num < $min" | bc -l) )); then
        min=$num
    fi
done

echo "Maximum: $max | Minimum: $min"


}
