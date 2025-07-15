script(){
    #!/bin/bash
    
    # Check if the number of arguments is exactly 2
    if [ $# -ne 2 ]; then
        echo "Error"
    else
        # Calculate and print the sum of the two arguments
        sum=$(( $1 + $2 ))
        echo "$sum"
    fi
}
