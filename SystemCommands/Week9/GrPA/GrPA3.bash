myCount(){

    #!/bin/bash

    # Get the last argument as file path
    file="${@: -1}"

    # If last argument is not a file or no options provided, exit silently
    if [[ ! -f "$file" ]] || [[ $# -eq 1 ]]; then
        exit 0
    fi
    OPTIND=1
    while getopts "lwns:" opt; do
        case "$opt" in
            l)  # Count lines
                awk 'END {print NR}' "$file"
                ;;
            w)  # Count words
                awk '{ count += NF } END { print count }' "$file"
                ;;
            n)  # Count lines containing only digits
                awk '/^[0-9]+$/ { count++ } END { print count+0 }' "$file"
                ;;
            s)  # Count lines containing given string
                if [[ -n "$OPTARG" ]]; then
                    awk -v str="$OPTARG" 'index($0,str) { count++ } END { print count+0 }' "$file"
                else
                    echo 0
                fi
                ;;
        esac
    done


}