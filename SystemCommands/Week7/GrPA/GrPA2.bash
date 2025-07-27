script() {
num=$1

if [ "$num" -lt 2 ]; then
    echo "No"
    exit
fi

for ((i = 2; i * i <= num; i++)); do
    if (( num % i == 0 )); then
        echo "No"
        exit
    fi
done

echo "Yes"

}
