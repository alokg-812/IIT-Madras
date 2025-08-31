#!/usr/bin/sed -f
1i ---header---
$a ---footer---
1,5s/in place of/instead/g
6i ---simpler stuff here onwards---
6,$s/in place of.*//g
