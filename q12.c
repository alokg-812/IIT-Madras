#include<stdio.h>
int sum(int x) {
    if(x==0) return 0;
    return ((x % 10) + sum(x/10));
}
int main() {
    int n,r;
    printf("Enter the n: ");
    scanf("%d",&n);
    printf("%d",sum(n));
}