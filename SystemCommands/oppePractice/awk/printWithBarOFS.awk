BEGIN { OFS=" | " }
{ print NR, $0 }
