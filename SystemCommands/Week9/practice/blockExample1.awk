#!/usr/bin/gawk -f
BEGIN{
	print "BEGIN action is processed";
}
{
	print "Default action is processed";
	print $0;
}
END{
	print "END action is processed";
}
