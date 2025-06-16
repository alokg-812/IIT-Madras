script(){
    [ -f twingle ] && wc -l twingle || (createTwingle >/dev/null 2>&1 && wc -l twingle)
}