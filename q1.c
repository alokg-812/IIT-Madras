#include<stdio.h>
int fact(int n) {
    if(n==1 || n==0) return 1;
    return n * fact(n-1);
}
int main() {
    int n ;
    printf("Enter the 'n': ");
    scanf("%d",&n);
    if (n>=0) {
    int res = fact(n);
    printf("The factorial of %d is %d.\n",n,res);
  }
  else printf("%d is a invalid number to get its factorial.",n);
}