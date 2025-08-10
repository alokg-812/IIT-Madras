script() { echo '


    BEGIN {
        FS = ","   # CSV uses comma as delimiter
    }

    {
        # $2 is item name, $3 is unit cost
        if ($2 == item) {
            print $3 * n
            exit   # No need to process further once found
        }
    }



' > yourScript.awk
awk -v item=$1 -v n=$2 -f yourScript.awk groceries.csv
}