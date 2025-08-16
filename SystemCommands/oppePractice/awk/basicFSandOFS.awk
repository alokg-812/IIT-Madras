BEGIN { FS="\t"; OFS=" | " }
{ print NR, $0 }
