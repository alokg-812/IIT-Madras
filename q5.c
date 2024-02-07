#include<stdio.h>
int temp=0;
int power(a,b,m) {
	if (b==0) return 1;
	return (((a%m) * power(a,b-1,m))%m);
}
int main() {
	int a,b,m;
	printf("Enter a: ");
	scanf("%d",&a);
	printf("Enter b: ");
	scanf("%d",&b);
	printf("Enter m: ");
	scanf("%d",&m);
	power(a,b);
}