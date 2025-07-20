script() {

if [ $# -ne 2 ]; then
    echo "Please provide exactly two integer arguments."
    exit 1
fi

num1=$1
num2=$2
for (( i=1; i<=num2; i++ ))
do
    product=$(( num1 * i ))
    echo "${num1}*${i}=${product}"
done



}
