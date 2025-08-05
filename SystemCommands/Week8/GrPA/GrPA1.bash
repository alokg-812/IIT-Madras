script() { 
    sed -n '/^[0-9]/p' input.txt | sed -n '=' | sed -n '$p'
}
