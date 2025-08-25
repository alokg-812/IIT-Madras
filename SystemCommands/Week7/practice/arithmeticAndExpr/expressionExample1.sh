#!/bin/bash

a=256
b=4
c=3

ans=$( expr $a + $b )
echo $ans

ans=$( expr $a - $b )
echo $ans

ans=$( expr $a \* $b )
echo $ans

ans=$( expr $a / $b )
echo $ans

ans=$( expr $a % $b )
echo $ans

ans=$( expr $a \> $b )
echo $ans

ans=$( expr $a \>= $b )
echo $ans

ans=$( expr $a \< $b )
echo $ans

ans=$( expr $a \<= $b )
echo $ans

ans=$( expr $a = $b )
echo $ans

ans=$( expr $a != $b )
echo $ans

ans=$( expr $a \| $b )
echo $ans

ans=$( expr $a \& $b )
echo $ans

str="octavio version as in Jan 2022 is 6.4.0"
reg="[oO]ctav[aeiou]"
ans=$( expr "$str" : $reg )
echo $ans

ans=$( expr index "$str" "Js" )
echo $ans

ans=$( expr substr "$str" 8 8)
echo $ans

ans=$( expr length "$str")
echo $ans
