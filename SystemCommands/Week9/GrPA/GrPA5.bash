script() { 

    sed -E '/^[^()]+(\(\)|\))\s*\{$/i # START FUNCTION
    /^\}$/a # END FUNCTION' functions.sh


}