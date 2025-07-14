script() {
    if [ $# -ne 1 ]; then
        exit 1
    fi
    file="$1"
    if [ ! -f "$file" ]; then
        exit 1
    fi
    perm=$(stat -c "%a" "$file")
    if [ "$perm" = "400" ]; then
        echo "Yes"
    fi
}
