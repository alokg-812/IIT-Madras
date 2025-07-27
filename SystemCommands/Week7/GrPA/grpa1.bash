script() { 
    sed -E '/^[0-9]/s/delta/gamma/' input.txt
}
