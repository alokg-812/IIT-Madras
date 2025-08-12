script() {


#!/bin/bash

# Shopping Bill Dataset Reconstruction Script
# This script reconstructs shopping bill data from OCR files

# Function to clean and normalize data
clean_data() {
    local data="$1"
    # Remove extra spaces, tabs, normalize formatting, and remove pipe characters
    echo "$data" | sed 's/[[:space:]]\+/ /g' | sed 's/^ *//;s/ *$//' | sed 's/|//g' | sed 's/)//g'
}

# Function to extract card numbers from sequence file
get_card_numbers() {
    if [[ -f "ocr_seqno.txt" ]]; then
        # Extract numbers from lines that contain them, excluding file headers
        grep -o '^[0-9]\+$' "ocr_seqno.txt" | sort -n | uniq
    else
        echo "1" # fallback
    fi
}

# Function to get shop name for a card
get_shop_name() {
    local card_no="$1"
    local shop_name=""
    
    if [[ -f "ocr_shopname.txt" ]]; then
        # Find the shop name for this card number
        shop_name=$(awk -v card="$card_no" '
        BEGIN { found=0; }
        /^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=1; next; }
        /^\[File: Shopping Bill-[0-9]+\.jpg\]/ && found && !/^\[File: Shopping Bill-0*'"$card_no"'\.jpg\]/ { found=0; }
        found && /^[A-Za-z]/ && !/^\[File:/ { print; exit; }
        ' "ocr_shopname.txt")
    fi
    
    # Clean and return shop name
    clean_data "$shop_name"
}

# Function to get customer name for a card
get_customer_name() {
    local card_no="$1"
    local customer_name=""
    
    if [[ -f "ocr_names.txt" ]]; then
        # Find customer name for this card number
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
    
    # Clean and return customer name
    clean_data "$customer_name"
}

# Function to get items data for a card from item record file
get_items_data() {
    local card_no="$1"
    local items_data=""
    
    if [[ -f "ocr_item_record.txt" ]]; then
        # Extract items for specific card number
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

# Function to parse and format item line
format_item_line() {
    local line="$1"
    
    # Clean the line
    line=$(clean_data "$line")
    
    # Skip empty lines or lines with just numbers
    [[ -z "$line" || "$line" =~ ^[0-9]+$ ]] && return
    
    # Parse the line - format should be: Item Category Qty Price Cost
    # Use awk to properly split and handle the fields
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
    
    # Get header information
    local shop_name=$(get_shop_name "$card_no")
    local customer_name=$(get_customer_name "$card_no")
    
    # Default values if extraction fails
    [[ -z "$shop_name" ]] && shop_name="SV Stores"
    [[ -z "$customer_name" ]] && customer_name="Customer$card_no"
    
    # Write header to output file
    echo "$shop_name:$customer_name:$card_no" > "$output_file"
    echo "Item:Category:Qty:Price:Cost" >> "$output_file"
    
    # Get and process items data
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

# Main script execution
main() {
    # Get all card numbers
    card_numbers=$(get_card_numbers)
    
    if [[ -z "$card_numbers" ]]; then
        card_numbers="1"
    fi
    
    # Process each card
    for card_no in $card_numbers; do
        reconstruct_card_data "$card_no"
    done
}

# Handle command line arguments
if [[ $# -gt 0 ]]; then
    # If specific card number provided
    reconstruct_card_data "$1"
else
    # Process all cards
    main
fi

}
