eof="EOF"
while read file; do
  if [[ $file =~ $eof ]]; then
    break
  fi
  while read line; do
    if [[ $line =~ $eof ]]; then 
      break
    fi
    echo $line >>$file
  done
done
######### Driver code ends here

######### Script starts here
for file in *.c; do
  awk '

    length($0) > 50 { print FILENAME; exit }
  ' $file
done