script() { 
    sed -n '/^FROM$/,/^TO$/ {/^FROM$/d; /^TO$/d; p}' input.txt

}
