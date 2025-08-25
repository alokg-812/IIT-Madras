a=10
b=30
diff=0

if [ $a -gt $b ]
then
	diff=$(expr $a - $b )
else
	diff=$(expr $b - $a )
fi

echo "Difference b/w $a and $b will give $diff"
