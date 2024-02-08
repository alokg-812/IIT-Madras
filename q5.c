#include<stdio.h>
int power(int a, int b, int m) {
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
	printf("%d",power(a,b,m));
	return 0;
}