script(){
    arr=(`ls`)
    
    # Initialize arrays
    lower=()
    upper=()
    
    # Loop through all file names
    for file in "${arr[@]}"
    do
        if [[ "$file" =~ [A-Z] ]]; then
            upper+=("$file")
        else
            lower+=("$file")
        fi
    done
}
