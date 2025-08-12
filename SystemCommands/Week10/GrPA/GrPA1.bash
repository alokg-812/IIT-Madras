script() {

#!/bin/bash
clean_data() {
    local data="$1"
    echo "$data" | sed 's/[[:space:]]\+/ /g' | sed 's/^ *//;s/ *$//' | sed 's/|//g' | sed 's/)//g'
}

get_card_numbers() {
    if [[ -f "ocr_seqno.txt" ]]; then
        grep -o '^[0-9]\+$' "ocr_seqno.txt" | sort -n | uniq
    else
        echo "1" # fallback
    fi
}

get_shop_name() {
    local card_no="$1"
    local shop_name=""
    
    if [[ -f "ocr_shopname.txt" ]]; then
        shop_name=$(awk -v card="$card_no" '
        BEGIN { found=0; }
        /^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=1; next; }
        /^\[File: Shopping Bill-[0-9]+\.jpg\]/ && found && !/^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=0; }
        found && /^[A-Za-z]/ && !/^\[File:/ { print; exit; }
        ' "ocr_shopname.txt")
    fi
    
    clean_data "$shop_name"
}

get_customer_name() {
    local card_no="$1"
    local customer_name=""
    
    if [[ -f "ocr_names.txt" ]]; then
        customer_name=$(awk -v card="$card_no" '
        BEGIN { found=0; }
        /^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=1; next; }
        /^\[File: Shopping Bill-[0-9]+\.jpg\]/ && found && !/^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=0; }
        found && /^[A-Za-z]/ && !/^\[File:/ { 
            gsub(/^[[:space:]]*[|I1]*[[:space:]]*/, ""); 
            print; 
            exit; 
        }
        ' "ocr_names.txt")
    fi
    
    clean_data "$customer_name"
}

get_items_data() {
    local card_no="$1"
    local items_data=""
    
    if [[ -f "ocr_item_record.txt" ]]; then
        items_data=$(awk -v card="$card_no" '
        BEGIN { found=0; }
        /^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=1; next; }
        /^\[File: Shopping Bill-[0-9]+\.jpg\]/ && found && !/^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=0; }
        found && /^[A-Za-z]/ && !/^\[File:/ && !/^Item/ && !/^SV Stores/ && !/^Big Bazaar/ && !/^Sun General/ { 
            print; 
        }
        ' "ocr_item_record.txt")
    fi
    
    echo "$items_data"
}

format_item_line() {
    local line="$1"
    line=$(clean_data "$line")
    [[ -z "$line" || "$line" =~ ^[0-9]+$ ]] && return
    echo "$line" | awk '{
        if(NF >= 5) {
            item = $1
            category = $2
            qty = $3
            price = $4
            cost = $5
            
            # Handle multi-word items
            if(NF > 5) {
                # Reconstruct item name if it has spaces
                for(i=1; i<=NF-4; i++) {
                    if(i==1) item = $i
                    else item = item " " $i
                }
                category = $(NF-3)
                qty = $(NF-2)
                price = $(NF-1)
                cost = $NF
            }
            
            # Clean up category format
            if(category ~ /Food$/ && category !~ /\//) {
                category = category
            }
            
            # Output in required format
            print item ":" category ":" qty ":" price ":" cost
        }
    }'
}

# Main reconstruction function
reconstruct_card_data() {
    local card_no="$1"
    local output_file="shopping_bill_${card_no}.txt"
    local shop_name=$(get_shop_name "$card_no")
    local customer_name=$(get_customer_name "$card_no")
    [[ -z "$shop_name" ]] && shop_name="SV Stores"
    [[ -z "$customer_name" ]] && customer_name="Customer$card_no"
    echo "$shop_name:$customer_name:$card_no" > "$output_file"
    echo "Item:Category:Qty:Price:Cost" >> "$output_file"
    local items_data=$(get_items_data "$card_no")
    if [[ -n "$items_data" ]]; then
        while IFS= read -r line; do
            if [[ -n "$line" ]]; then
                formatted_line=$(format_item_line "$line")
                if [[ -n "$formatted_line" ]]; then
                    echo "$formatted_line" >> "$output_file"
                fi
            fi
        done <<< "$items_data"
    fi
}

main() {
    card_numbers=$(get_card_numbers)
    
    if [[ -z "$card_numbers" ]]; then
        card_numbers="1"
    fi
    for card_no in $card_numbers; do
        reconstruct_card_data "$card_no"
    done
}

if [[ $# -gt 0 ]]; then
    reconstruct_card_data "$1"
else
    main
fi

}
